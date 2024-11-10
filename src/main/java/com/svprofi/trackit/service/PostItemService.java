package com.svprofi.trackit.service;

import com.svprofi.trackit.exception.MailNotFoundException;
import com.svprofi.trackit.model.MovementType;
import com.svprofi.trackit.model.PostItem;
import com.svprofi.trackit.model.PostMovement;
import com.svprofi.trackit.model.PostStatus;
import com.svprofi.trackit.repository.PostItemRepository;
import com.svprofi.trackit.repository.PostMovementRepository;
import com.svprofi.trackit.repository.PostOfficeRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class PostItemService {

    private final PostItemRepository postItemRepository;
    private final PostMovementRepository postMovementRepository;
    private final PostOfficeRepository postOfficeRepository;

    public PostItemService(PostItemRepository postItemRepository, PostOfficeRepository postOfficeRepository, PostMovementRepository postMovementRepository) {
        this.postItemRepository = postItemRepository;
        this.postMovementRepository = postMovementRepository;
        this.postOfficeRepository = postOfficeRepository;
    }

    public PostItem registerPostItem(PostItem postItem) {
        postItem.setStatus(PostStatus.REGISTERED);
        PostItem savedPostItem = postItemRepository.save(postItem);
        createPostMovement(savedPostItem);
        return savedPostItem;
    }

    private void createPostMovement(PostItem postItem) {
        PostMovement postMovement = new PostMovement();
        postMovement.setPostItem(postItem);
        postMovement.setMovementType(MovementType.ARRIVAL);
        postMovement.setTimestamp(ZonedDateTime.now());
        postMovementRepository.save(postMovement);
    }

    public PostItem updatePostItemStatus(Long postItemId, PostStatus newStatus) {
        PostItem postItem = postItemRepository.findById(postItemId)
                .orElseThrow(() -> new RuntimeException("Post item not found"));

        postItem.setStatus(newStatus);
        postItemRepository.save(postItem);
        createPostMovement(postItem);
        return postItem;
    }

    public PostItem getPostItemById(Long id) {
        return postItemRepository.findById(id)
                .orElseThrow(() -> new MailNotFoundException("Post item not found with id: " + id));
    }

    public List<PostItem> getAllPostItems() {
        return postItemRepository.findAll(); // Получаем все записи из базы
    }
}

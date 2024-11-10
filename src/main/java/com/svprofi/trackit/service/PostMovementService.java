package com.svprofi.trackit.service;

import com.svprofi.trackit.exception.MailNotFoundException;
import com.svprofi.trackit.model.*;
import com.svprofi.trackit.repository.PostItemRepository;
import com.svprofi.trackit.repository.PostMovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class PostMovementService {

    private final PostMovementRepository postMovementRepository;
    private final PostItemRepository postItemRepository;

    public PostMovementService(PostMovementRepository postMovementRepository, PostItemRepository postItemRepository) {
        this.postMovementRepository = postMovementRepository;
        this.postItemRepository = postItemRepository;
    }

    @Transactional
    public PostMovement arrival(Long postItemId, PostOffice postOffice) {
        PostItem postItem = postItemRepository.findById(postItemId)
                .orElseThrow(() -> new MailNotFoundException("PostItem not found"));

        postItem.setStatus(PostStatus.IN_TRANSIT);
        postItemRepository.save(postItem);

        return createMovement(postItem, postOffice, MovementType.ARRIVAL);
    }

    @Transactional
    public PostMovement departure(Long postItemId, PostOffice postOffice) {
        PostItem postItem = postItemRepository.findById(postItemId)
                .orElseThrow(() -> new MailNotFoundException("Post item not found"));

        postItem.setStatus(PostStatus.IN_TRANSIT);
        postItemRepository.save(postItem);

        return createMovement(postItem, postOffice, MovementType.DEPARTURE);
    }

    @Transactional
    public PostMovement delivery(Long postItemId, PostOffice postOffice) {
        PostItem postItem = postItemRepository.findById(postItemId)
                .orElseThrow(() -> new MailNotFoundException("Post item not found"));

        postItem.setStatus(PostStatus.DELIVERED);
        postItemRepository.save(postItem);

        return createMovement(postItem, postOffice, MovementType.DELIVERY);
    }

    private PostMovement createMovement(PostItem postItem, PostOffice postOffice, MovementType movementType) {
        PostMovement movement = new PostMovement();
        movement.setPostItem(postItem);
        movement.setPostOffice(postOffice);
        movement.setMovementType(movementType);
        movement.setTimestamp(ZonedDateTime.now());

        return postMovementRepository.save(movement);
    }
}

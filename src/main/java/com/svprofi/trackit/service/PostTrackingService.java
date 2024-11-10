package com.svprofi.trackit.service;

import com.svprofi.trackit.exception.MailNotFoundException;
import com.svprofi.trackit.model.PostItem;
import com.svprofi.trackit.model.PostMovement;
import com.svprofi.trackit.repository.PostItemRepository;
import com.svprofi.trackit.repository.PostMovementRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PostTrackingService {

    private final PostItemRepository postItemRepository;
    private final PostMovementRepository postMovementRepository;

    public PostTrackingService(PostItemRepository postItemRepository, PostMovementRepository postMovementRepository) {
        this.postItemRepository = postItemRepository;
        this.postMovementRepository = postMovementRepository;
    }

    public PostItem getStatus(Long postItemId) {
        return postItemRepository.findById(postItemId)
                .orElseThrow(() -> new MailNotFoundException("Post item not found"));
    }

    public List<PostMovement> getMovementHistory(Long postItemId) {
        return postMovementRepository.findByPostItemId(postItemId)
                .stream()
                .sorted(Comparator.comparing(PostMovement::getTimestamp))
                .toList();
    }
}

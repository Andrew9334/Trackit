package com.svprofi.trackit.controller;

import com.svprofi.trackit.model.PostItem;
import com.svprofi.trackit.service.PostTrackingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post-status")
public class PostTrackingController {

    private final PostTrackingService postTrackingService;

    public PostTrackingController(PostTrackingService postTrackingService) {
        this.postTrackingService = postTrackingService;
    }

    @GetMapping("/history/{postItemId}")
    public PostItem getStatus(@PathVariable Long postItemId) {
        return postTrackingService.getStatus(postItemId);
    }
}

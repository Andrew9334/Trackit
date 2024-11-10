package com.svprofi.trackit.controller;

import com.svprofi.trackit.model.PostItem;
import com.svprofi.trackit.service.PostTrackingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post-status")
public class PostTrackingController {

    private static final Logger logger = LoggerFactory.getLogger(PostTrackingController.class);
    private final PostTrackingService postTrackingService;

    public PostTrackingController(PostTrackingService postTrackingService) {
        this.postTrackingService = postTrackingService;
    }

    @Operation(summary = "Get status history of a post item",
            description = "Returns the tracking status history for a given post item by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post item status history retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Post item not found")
    })
    @GetMapping("/history/{postItemId}")
    public PostItem getStatus(@PathVariable Long postItemId) {
        logger.info("Received request to get status for postItemId: {}", postItemId);
        return postTrackingService.getStatus(postItemId);
    }
}

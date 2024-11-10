package com.svprofi.trackit.controller;

import com.svprofi.trackit.model.PostOffice;
import com.svprofi.trackit.service.PostMovementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post-movements")
public class PostMovementController {

    private static final Logger logger = LoggerFactory.getLogger(PostMovementController.class);
    private final PostMovementService postMovementService;

    public PostMovementController(PostMovementService postMovementService) {
        this.postMovementService = postMovementService;
    }

    @Operation(summary = "Process arrival of a post item",
            description = "Marks the arrival of a post item at a specified post office.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Arrival processed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/arrival")
    public ResponseEntity<String> arrival(@RequestParam Long postItemId, @RequestBody PostOffice postOffice) {
        logger.info("Handling arrival for postItemId: {} at post office: {}", postItemId, postOffice);
        postMovementService.arrival(postItemId, postOffice);
        return ResponseEntity.status(HttpStatus.CREATED).body("Arrival processed successfully");
    }

    @Operation(summary = "Process departure of a post item",
            description = "Marks the departure of a post item from a specified post office.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Departure processed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/departure")
    public ResponseEntity<String> departure(@RequestParam Long postItemId, @RequestBody PostOffice postOffice) {
        logger.info("Handling departure for postItemId: {} at post office: {}", postItemId, postOffice);
        postMovementService.departure(postItemId, postOffice);
        return ResponseEntity.status(HttpStatus.CREATED).body("Departure processed successfully");
    }

    @Operation(summary = "Process delivery of a post item",
            description = "Marks the delivery of a post item to the recipient.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Delivery processed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/delivery")
    public ResponseEntity<String> delivery(@RequestParam Long postItemId, @RequestBody PostOffice postOffice) {
        logger.info("Handling delivery for postItemId: {} at post office: {}", postItemId, postOffice);
        postMovementService.delivery(postItemId, postOffice);
        return ResponseEntity.status(HttpStatus.CREATED).body("Delivery processed successfully");
    }
}


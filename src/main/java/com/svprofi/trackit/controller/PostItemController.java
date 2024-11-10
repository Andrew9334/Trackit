package com.svprofi.trackit.controller;

import com.svprofi.trackit.exception.MailNotFoundException;
import com.svprofi.trackit.model.PostItem;
import com.svprofi.trackit.service.PostItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mail-items")
public class PostItemController {

    private static final Logger logger = LoggerFactory.getLogger(PostItemController.class);
    private final PostItemService postItemService;

    public PostItemController(PostItemService postItemService) {
        this.postItemService = postItemService;
    }

    @Operation(summary = "Register a new mail item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mail item successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<PostItem> registerPostItem(@Valid @RequestBody PostItem postItem) {
        logger.info("Регистрация почтового элемента: {}", postItem);
        PostItem registeredPostItem = postItemService.registerPostItem(postItem);
        logger.info("Почтовый элемент зарегистрирован с ID: {}", registeredPostItem.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredPostItem);
    }

    @Operation(summary = "Get all mail items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of mail items retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<PostItem>> getAllPostItems() {
        List<PostItem> postItems = postItemService.getAllPostItems();
        logger.info("Получены все почтовые элементы, количество: {}", postItems.size());
        return ResponseEntity.ok(postItems);
    }

    @Operation(summary = "Get a specific mail item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mail item retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Mail item not found")
    })    @GetMapping("/{id}")
    public ResponseEntity<PostItem> getPostItemById(@PathVariable Long id) {
        PostItem postItem = postItemService.getPostItemById(id);
        logger.info("Получен почтовый элемент с ID: {}", id);
        return ResponseEntity.ok(postItem);
    }
}


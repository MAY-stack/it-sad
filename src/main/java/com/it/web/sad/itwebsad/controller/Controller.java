package com.it.web.sad.itwebsad.controller;


import com.it.web.sad.itwebsad.dto.CommentDTO;
import com.it.web.sad.itwebsad.service.CommentService;
import com.it.web.sad.itwebsad.util.JsonSchemaValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.*;

@RestController
public class Controller {
    @Autowired
    private CommentService commentService;
    private final JsonSchemaValidator jsonSchemaValidator = new JsonSchemaValidator();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/comments")
    @Operation(summary = "get all comments ", responses = {
            @ApiResponse(responseCode = "200", description = "OK")})
    public List<CommentDTO> getComments() throws Exception{
        List<CommentDTO> comments = commentService.getComments();
        List<CommentDTO> validComments = new ArrayList<>();
        for(CommentDTO comment : comments){
            if(commentValidator(comment)){
                validComments.add(comment);
            }
        }
        return validComments;
    }

    @PostMapping("/comments")
    @Operation(summary = "add comment ", responses = {
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "409", description = "id already exist")})
    public ResponseEntity<CommentDTO> postComment(@Valid @RequestBody CommentDTO commentDTO) throws Exception {
        CommentDTO savedComment = commentService.addComment(commentDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedComment.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/comment/{commentId}")
    @Operation(summary = "get comment by comment Id", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "NoSuchElementException")})
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("commentId") String id) throws Exception {
        CommentDTO retrievedComment = commentService.getCommentById(id);
        if (retrievedComment != null && commentValidator(retrievedComment)) {
            return ResponseEntity.ok(retrievedComment);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/comment")
    @Operation(summary = "update comment by comment Id", responses = {
            @ApiResponse(responseCode = "202", description = "accepted"),
            @ApiResponse(responseCode = "400", description = "NoSuchElementException"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody CommentDTO commentDTO) throws Exception {
        if(commentValidator(commentService.updateComment(commentDTO.getId(), commentDTO))){
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/comment/{commentId}")
    @Operation(summary = "delete comment by comment Id", responses = {
            @ApiResponse(responseCode = "201", description = "accepted")})
    public ResponseEntity<CommentDTO> delete(@PathVariable("commentId") String id) throws Exception {
        commentService.deleteComment(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/comments/{storyId}")
    @Operation(summary = "get comment by storyId", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "no Content")})
    public List<CommentDTO> getCommentsByStoryId(@PathVariable String storyId) throws Exception {
        List<CommentDTO> comments = commentService.getCommentsByStoryId(storyId);
        List<CommentDTO> validComments = new ArrayList<>();

        for(CommentDTO comment : comments){
            if(commentValidator(comment)){
                validComments.add(comment);
            }
        }

        return validComments;
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, String>> exceptionHandler(Exception e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getClass().getSimpleName());
        map.put("error code", String.valueOf(httpStatus.value()));

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

    public boolean commentValidator(CommentDTO commentDTO) throws IOException {
        return jsonSchemaValidator.validate(commentDTO);
    }
}

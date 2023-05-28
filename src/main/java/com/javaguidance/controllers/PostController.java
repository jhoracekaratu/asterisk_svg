package com.javaguidance.controllers;

import com.javaguidance.exceptions.PostNotFoundException;
import com.javaguidance.models.Post;
import com.javaguidance.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController()
@Slf4j
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public ResponseEntity<Post> sendPost(@RequestBody Post post) {
        log.info("PostController::sendPost : {}", post);
        return new ResponseEntity<>(postService.create(post),HttpStatus.OK);
    }

    @PutMapping("/post")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        log.info("PostController::updatePost : {}", post);
        return new ResponseEntity<>(postService.update(post),HttpStatus.OK);
    }

    @GetMapping("post/{id}")
    public ResponseEntity<Post> getPost(@PathVariable int id) throws PostNotFoundException {
        log.info("PostController::getPost : {}",id);
        Post post = postService.read(id);
        log.info("PostController::getPost : --fetched data {}", post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


    @DeleteMapping("post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) throws PostNotFoundException {
        log.info("PostController::deletePost : {}",id);
        Boolean result = postService.delete(id);
        log.info("PostController::deletePost : --result {}",result);
        return new ResponseEntity<>("Post "+id+" deleted",HttpStatus.OK);
    }
}

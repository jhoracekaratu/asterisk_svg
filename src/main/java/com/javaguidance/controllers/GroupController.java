package com.javaguidance.controllers;

import com.javaguidance.exceptions.ResourceNotFoundException;
import com.javaguidance.models.Group;
import com.javaguidance.models.Post;
import com.javaguidance.repositories.GroupRepo;
import com.javaguidance.repositories.PostRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class GroupController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private GroupRepo groupRepository;

        @GetMapping("/api/groups")
    public ResponseEntity<List<Group>> getGroups() {
            List<Group> groups= groupRepository.findAll();
//        log.info("PostController::updatePost : {}", groups);
        return new ResponseEntity<>(  groups, HttpStatus.OK);
    }

    @PostMapping("/api/groups/posts")
    public ResponseEntity<Group> post(@RequestBody Group group) {
       Group _group =groupRepository.save(group);
//        log.info("PostController::updatePost : {}", group);
        return new ResponseEntity<>(  _group, HttpStatus.OK);
    }

    @PutMapping("/api/groups")
    public ResponseEntity<Group> update(@RequestBody Group group) throws ResourceNotFoundException {
        Group _group =groupRepository.findById(group.getId()).orElseThrow(()->new ResourceNotFoundException("User not found"));
        _group.setUrl(group.getUrl());
        _group.setName(group.getName());
        _group=groupRepository.save(_group);
//        log.info("PostController::updatePost : {}", _group);
        return new ResponseEntity<>(  _group, HttpStatus.OK);
    }


}

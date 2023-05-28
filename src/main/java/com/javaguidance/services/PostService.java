package com.javaguidance.services;

import com.javaguidance.exceptions.PostNotFoundException;
import com.javaguidance.models.Post;
import com.javaguidance.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public Post create(Post post) {
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    public Post read(int id) throws PostNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post Id : {"+id+"}"));
        return post;
    }

    public boolean delete(int id) throws PostNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post Id : {"+id+"}"));
        postRepository.delete(post);
        return true;
    }

    public Post update(Post update) {
        Post post=postRepository.save(update);
        return post;
    }
}

package com.javaguidance.services;

import com.javaguidance.exceptions.ResourceNotFoundException;
import com.javaguidance.models.Post;
import com.javaguidance.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public Post create(Post post) {
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    public Post read(int id) throws ResourceNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post Id : {"+id+"}"));
        return post;
    }

    public List<Post> readAll() {
        return postRepository.findAll();

    }

    public boolean delete(int id) throws ResourceNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post Id : {"+id+"}"));
        postRepository.delete(post);
        return true;
    }

    public Post update(Post update) {
        Post post=postRepository.save(update);
        return post;
    }

    public List<Post> getPostsByUser(Integer id) throws ResourceNotFoundException {
      return  postRepository.findPostsByUsersId(id).orElseThrow(()->new ResourceNotFoundException("Posts not found"));

    }
    public Boolean checkPost(Integer id) throws ResourceNotFoundException {
        return  postRepository.existsById(id);

    }




}

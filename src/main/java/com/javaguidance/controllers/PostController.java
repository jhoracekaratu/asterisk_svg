package com.javaguidance.controllers;

import com.javaguidance.exceptions.ResourceNotFoundException;
import com.javaguidance.models.Post;
import com.javaguidance.models.User;
import com.javaguidance.repositories.UserRepository;
import com.javaguidance.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()



@Slf4j
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserRepository userRepository;

//    @PostMapping("/users/{userid}/posts")
//    public void createPost(@PathVariable Integer userid,@RequestBody Post post) throws ResourceNotFoundException {
//
//          Post createdPost=userRepository.findById(userid).map(user -> {
//                      System.out.println(user);
//              Post _post = new Post();
//              if (post.getId() != 0) {
//                          try {
//                              _post=postService.read(post.getId());
//                              System.out.println("_post"+_post);
//                              user.addPost(_post);
//                              System.out.println(user);
//                              userRepository.save(user);
//
//                          } catch (ResourceNotFoundException e) {
//                              throw new RuntimeException(e);
//                          }
//                      }
//                      user.addPost(post);
//           return postService.create(post);
//                  })
////
//
//
////       })
//       .orElseThrow(()->new ResourceNotFoundException("User not found"));
////          return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PutMapping("/post")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        log.info("PostController::updatePost : {}", post);
        return new ResponseEntity<>(postService.update(post),HttpStatus.OK);
    }

    @PostMapping("users/{userid}/post")
    public ResponseEntity<User> createPost(@PathVariable Integer userid,@RequestBody Post post) throws ResourceNotFoundException {
        User user=userRepository.findById(userid).orElseThrow(()->new ResourceNotFoundException("User not found"));
        log.info("PostController::createPost : --fetched user {}", post);
        user.getPosts().add(post);
        User _user=userRepository.save(user);
        log.info("PostController::createPost : --created user {}", user);
        log.info("PostController::createPost : --created user {}", _user);
        log.info("PostController::createPost : --created post {}", post);
        return new ResponseEntity<>(_user,HttpStatus.OK);
    }

    @GetMapping("post")
    public ResponseEntity<List<Post>> getAllPosts()  {
        log.info("PostController::getAllPost()");
        List<Post> posts = postService.readAll();
        log.info("PostController::getPost : --fetched data {}", posts);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @GetMapping("post/{id}")
    public ResponseEntity<Post> getPost(@PathVariable int id) throws ResourceNotFoundException {
        log.info("PostController::getPost : {}",id);
        Post post = postService.read(id);
        log.info("PostController::getPost : --fetched data {}", post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("users/{userid}/posts")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Integer userid) throws ResourceNotFoundException {
        if(!userRepository.existsById(userid)) throw new ResourceNotFoundException("User Not found");
        List<Post> posts=postService.getPostsByUser(userid);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("posts/{postid}/users")
    public ResponseEntity<List<User>> getPostsUsers(@PathVariable Integer postid) throws ResourceNotFoundException {
        if(!postService.checkPost(postid)) throw new ResourceNotFoundException("User Not found");

        List<User> users=userRepository.findUsersByPostsId(postid).orElseThrow(()->new ResourceNotFoundException("Post not found"));

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @DeleteMapping("post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) throws ResourceNotFoundException {
        log.info("PostController::deletePost : {}",id);
        Boolean result = postService.delete(id);
        log.info("PostController::deletePost : --result {}",result);
        return new ResponseEntity<>("Post "+id+" deleted",HttpStatus.OK);
    }
}

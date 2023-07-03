package com.javaguidance.controllers;

import com.javaguidance.dtos.SlugDTO;
import com.javaguidance.exceptions.ResourceNotFoundException;
import com.javaguidance.models.Group;
import com.javaguidance.models.Post;
import com.javaguidance.models.PostParts;
import com.javaguidance.models.User;
import com.javaguidance.repositories.GroupRepo;
import com.javaguidance.repositories.PostRepository;
import com.javaguidance.repositories.UserRepository;
import com.javaguidance.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController()



@Slf4j
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private GroupRepo groupRepo;

//    @PutMapping("/api/posts")
//    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
//        log.info("PostController::updatePost : {}", post);
//        return new ResponseEntity<>(postService.update(post),HttpStatus.OK);
//    }
@PutMapping("/posts/{id}")
public ResponseEntity<Post>  updatePost(@PathVariable int id,@RequestBody Post post){
    Post _post=postRepository.findById(id).orElse(null);

    if(_post!=null){
        _post.setTitle(post.getTitle());
        _post=postRepository.save(_post);
    }

    return new ResponseEntity<>(_post, HttpStatus.OK);
}
    @PutMapping("/api/posts")
    public ResponseEntity<Post> updatePostPart(@RequestBody PostParts postPart) throws ResourceNotFoundException {
        List<Post> allPosts=postService.readAll();
//        log.info("PostController:: updatePostPart : --all posts {}", allPosts);
       Post parentPost=allPosts.stream()
                .filter( post -> post.getPostParts().stream().anyMatch(p-> p.getId()==postPart.getId())
                ).findFirst().orElseThrow(()->new ResourceNotFoundException("Not found"));
//        log.info("PostController:: updatePostPart : --Parent post {}", parentPost);
    PostParts childPostPart=parentPost.getPostParts().stream().filter(_postPart -> _postPart.getId()==postPart.getId()).findFirst().orElseThrow(()->new ResourceNotFoundException("Not found"));
      int index=parentPost.getPostParts().indexOf(childPostPart);
//        log.info("PostController:: updatePostPart : --Parent post {}", parentPost);
        parentPost.getPostParts().set(index,postPart);
        return new ResponseEntity<>(postService.update(parentPost),HttpStatus.OK);
    }

    @PutMapping("/api/users/{userid}/posts/{postid}/postparts")
    public ResponseEntity<Post> updatePostPart(@PathVariable Integer userid, @PathVariable Integer postid,@RequestBody PostParts postPart)
            throws ResourceNotFoundException {
    List<Post> _posts=postRepository.findPostsByUsersId(userid).orElse(null);
        assert _posts != null;
        Post _post=_posts.stream().filter(post -> post.getId()==postid).findFirst().orElse(null);
        assert _post != null;
        PostParts _postPart=_post.getPostParts().stream().filter(pp -> pp.getId()==postPart.getId() ).findFirst().orElse(null);
        assert _postPart != null;
        _postPart.setSub_title(postPart.getSub_title());
        _postPart.setSub_content(postPart.getSub_content());
        return new ResponseEntity<>(postRepository.save(_post),HttpStatus.OK);




    }

    @DeleteMapping("/api/posts/{postpartid}")
    public ResponseEntity<Post> deletePostPart(@PathVariable Integer postpartid) throws ResourceNotFoundException {
        List<Post> allPosts=postService.readAll();
//        log.info("PostController:: deletePostPart : --all posts {}", allPosts);
        Post parentPost=allPosts.stream()
                .filter( post -> post.getPostParts().stream().anyMatch(p-> p.getId()==postpartid)
                ).findFirst().orElseThrow(()->new ResourceNotFoundException("Not found"));
//        log.info("PostController:: deletePostPart : --Parent post {}", parentPost);
        PostParts childPostPart=parentPost.getPostParts().stream().filter(_postPart -> _postPart.getId()==postpartid).findFirst().orElseThrow(()->new ResourceNotFoundException("Not found"));
        parentPost.getPostParts().remove(childPostPart);
        return new ResponseEntity<>(postService.update(parentPost),HttpStatus.OK);
    }

    @PostMapping("/api/users/{userid}/posts")
    public ResponseEntity<User> createPost(@PathVariable Integer userid,@RequestBody Post post) throws ResourceNotFoundException {
        User _user=userRepository.findById(userid).orElse(null);
        if ((_user !=null)){
            String extractedString=post.getTitle().toLowerCase();
            System.out.println(extractedString);
            extractedString=Jsoup.parse(extractedString).text();
            System.out.println(extractedString);
            extractedString=extractedString.replace(" ","_").replace("-","_").trim();
            System.out.println(extractedString);
            post.setSlug(extractedString);
            _user.addPost(post);
        }
        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.CREATED);
    }

//    Revise this endpoint mapping
    @PostMapping("/api/users/{userid}/posts/{postid}/postparts")
    public ResponseEntity<Post> createPostPart(@PathVariable Integer userid,@PathVariable Integer postid,@RequestBody PostParts postParts) throws ResourceNotFoundException {
        System.out.println("inside method");
        User _user=userRepository.findById(userid).orElseThrow(()->new ResourceNotFoundException("User not found"));
        System.out.println(_user);
//        problematic stack overflow area
        List<Post> posts=postService.getPostsByUser(userid);
        System.out.println(posts);
        Post _post=  posts.stream().filter(post -> post.getId()==postid).findFirst().orElseThrow(() -> new ResourceNotFoundException("Not found"));
        System.out.println(_post);
        _post.getPostParts().add(postParts);
        _user=userRepository.save(_user);
        final int _postId=_post.getId();
        _post=_user.getPosts().stream().filter(post -> post.getId()==_postId).findFirst().orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return new ResponseEntity<>(_post,HttpStatus.OK);
    }


    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> getAllPosts()  {
        log.info("PostController::getAllPost()");
        List<Post> posts = postService.readAll();
//        log.info("PostController::getPost : --fetched data {}", posts);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @GetMapping("/api/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id) throws ResourceNotFoundException {
        log.info("PostController::getPost : {}",id);
        Post post = postService.read(id);
//        log.info("PostController::getPost : --fetched data {}", post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/api/postsbytitle/{title}")
    public ResponseEntity<Post> getPostByTitle(@PathVariable String title) throws ResourceNotFoundException {
        log.info("PostController::getPost : {}",title);
        Post post = postRepository.findPostsByTitle(title).orElseThrow(()->new ResourceNotFoundException("Post not found"));
//        log.info("PostController::getPost : --fetched data {}", post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @GetMapping("/api/postbyslug/{slug}")
    public ResponseEntity<Map<String,Object>> getPostBySlug(@PathVariable String slug) throws ResourceNotFoundException {
        List<String> list = new ArrayList<>();
        log.info("PostController::getPost : {}",slug);
        Post post = postRepository.findPostsBySlug(slug).orElseThrow(()->new ResourceNotFoundException("Post not found"));
      Group __group=post.getGroups().get(0);
//        toc calculations
        List<Post> _posts=postRepository.findPostsByGroupsId(__group.getId()).orElseThrow(()->new ResourceNotFoundException("User not found"));
        List<SlugDTO> slugs=_posts.stream().map(__post ->{
            list.add(String.valueOf(__post.getId()));
            return SlugDTO.builder()
                    .id(__post.getId())
                    .slug(__post.getSlug())
//                  .category(post.getGroups().stream().map(group -> group.getName()).collect(Collectors.toSet()))
                    .rating(__post.getRating())
                    .build();
        }).collect(Collectors.toList());
        System.out.println(post);
        System.out.println(slug);
//        end
//        fetching top 3 posts

        List<Post> __ratedPosts=postRepository.getPostByRatingCustom(0,3,list ).orElseThrow(()->new ResourceNotFoundException("User not found"));
        System.out.println(__ratedPosts);

Map<String,Object> result=new HashMap<>();
result.put("post",post);
result.put("slugs",slugs);
result.put("related",__ratedPosts);
//        log.info("PostController::getPost : --fetched data {}", post);
//        return new ResponseEntity<>(post, HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/api/users/{userid}/posts")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Integer userid) throws ResourceNotFoundException {
        if(!userRepository.existsById(userid)) throw new ResourceNotFoundException("User Not found");
        List<Post> posts=postService.getPostsByUser(userid);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/api/posts/{postid}/users")
    public ResponseEntity<List<User>> getPostsUsers(@PathVariable Integer postid) throws ResourceNotFoundException {
        if(!postService.checkPost(postid)) throw new ResourceNotFoundException("User Not found");

        List<User> users=userRepository.findUsersByPostsId(postid).orElseThrow(()->new ResourceNotFoundException("Post not found"));

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

        @DeleteMapping("api/users/{userid}/posts/{postid}")
        public ResponseEntity<User> addPost(@PathVariable int userid,@PathVariable int postid){
            User _user=userRepository.findById(userid).orElse(null);
            if ((_user !=null)){

                _user.removePost(postid);

            }
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        }

//groups api
@GetMapping("api/posts/{postid}/groups")
public ResponseEntity<List<Group>>  getUsersGroups(@PathVariable int postid) throws ResourceNotFoundException {
  Post _post=postRepository.findById(postid).orElseThrow(()->new ResourceNotFoundException("User not found"));
       List<Group> _groups=_post.getGroups();

    return new ResponseEntity<>(_groups, HttpStatus.OK);
}
    //    http://localhost:8082/api/posts/'+_group.id+'/groups/'+selectedGroup;
    @PostMapping("api/posts/{postid}/groups/{groupid}")
    public ResponseEntity<Post>  postPostIntoGroup(
            @PathVariable int postid
            ,@PathVariable int groupid) throws ResourceNotFoundException {
        Post _post=postRepository.findById(postid).orElseThrow(()->new ResourceNotFoundException("User not found"));
        Group _group=groupRepo.findById(groupid).orElseThrow(()->new ResourceNotFoundException("User not found"));
        _post.addGroup(_group);
        _post=postRepository.save(_post);
        return new ResponseEntity<>(_post, HttpStatus.OK);
    }
//    mix update and save
    @PostMapping("api/posts/{postid}/groups")
    public ResponseEntity<Post>  PostUsersGroups(@PathVariable int postid,@RequestBody Group group) throws ResourceNotFoundException {
        Post _post=postRepository.findById(postid).orElseThrow(()->new ResourceNotFoundException("User not found"));
        if(_post.getGroups().stream().anyMatch(group1 -> group1.getName()==group.getName())){
            Group _group=_post.getGroups().stream().filter(group1 -> group1.getId()==group.getId() ).findFirst().orElseThrow(()->new ResourceNotFoundException("Post not found"));
            _group.setName(group.getName());
            _group.setUrl(group.getUrl());
            _post.addGroup(_group);
        }else{
            _post.addGroup(group);

        }
        _post=postRepository.save(_post);
        return new ResponseEntity<>(_post, HttpStatus.OK);
    }

    @PutMapping("api/posts/{postid}/groups")
    public ResponseEntity<Post>  updateUsersGroups(@PathVariable int postid,@RequestBody Group group) throws ResourceNotFoundException {
        Post _post=postRepository.findById(postid).orElseThrow(()->new ResourceNotFoundException("User not found"));
//        _post.addGroup(group);
        Group _group=_post.getGroups().stream().filter(group1 -> group1.getId()==group.getId() ).findFirst().orElseThrow(()->new ResourceNotFoundException("Post not found"));
        _group.setName(group.getName());
        _group.setUrl(group.getUrl());
        _post.addGroup(_group);
        _post=postRepository.save(_post);
        return new ResponseEntity<>(_post, HttpStatus.OK);
    }

    @DeleteMapping("api/users/{userid}/posts/{postid}/groups/{groupid}")
    public ResponseEntity<Post>  deleteUsersGroups(@PathVariable int userid,@PathVariable int postid,@PathVariable int groupid) throws ResourceNotFoundException {
       List<Post> _posts=postRepository.findPostsByUsersId(userid).orElseThrow(()->new ResourceNotFoundException("User not found"));
       Post _post=_posts.stream().filter(post -> post.getId()==postid).findFirst().orElseThrow(()->new ResourceNotFoundException("User not found"));
         Group _group=groupRepo.findById(groupid).orElseThrow(()->new ResourceNotFoundException("User not found"));
       _post.removeGroup(_group);
        _post=postRepository.save(_post);
        return new ResponseEntity<>(_post, HttpStatus.OK);

    }
    @GetMapping("api/groups/{groupid}/posts")
    public ResponseEntity<List<Post>>  getGroupsPosts(@PathVariable int groupid) throws ResourceNotFoundException {
        List<Post> _posts=postRepository.findPostsByGroupsId(groupid).orElseThrow(()->new ResourceNotFoundException("User not found"));
        return new ResponseEntity<>(_posts, HttpStatus.OK);
    }
    @GetMapping("api/groups/{groupid}/posts/v2")
    public ResponseEntity<List<SlugDTO>>  getGroupsPostsUrl(@PathVariable int groupid) throws ResourceNotFoundException {
        List<Post> _posts=postRepository.findPostsByGroupsId(groupid).orElseThrow(()->new ResourceNotFoundException("User not found"));
       List<SlugDTO> slugs=_posts.stream().map(post ->{
          return SlugDTO.builder()
                  .slug(post.getSlug())
//                  .category(post.getGroups().stream().map(group -> group.getName()).collect(Collectors.toSet()))
                  .rating(post.getRating())
                  .build();
       }).collect(Collectors.toList());
        return new ResponseEntity<>(slugs, HttpStatus.OK);
    }
    @GetMapping("api/users/{userid}/posts/{postid}/groups")
    public ResponseEntity<List<Group>>  getUsersPostsGroups(@PathVariable int userid,
                                                           @PathVariable int postid
                                                           ) throws ResourceNotFoundException {
        List<Post> _posts=postRepository.findPostsByUsersId(userid).orElseThrow(()->new ResourceNotFoundException("User not found"));
        Post _post=_posts.stream().filter(post -> post.getId()==postid).findFirst().orElseThrow(()->new ResourceNotFoundException("User not found"));
      List<Group> _groups=_post.getGroups();
        return new ResponseEntity<>(_groups, HttpStatus.OK);
    }

    @PostMapping("api/posts/{postid}/groups/v2")
    public ResponseEntity<Post>  postPostIntoGroupList(
            @PathVariable int postid
            , @RequestBody Map<String,Object> payload) throws ResourceNotFoundException {
        List<String> list= (List<String>) payload.get("list");
        
        System.out.println(payload);
        Post _post=postRepository.findById(postid).orElseThrow(()->new ResourceNotFoundException("User not found"));



        _post.clearGroup();
     for(String i:list){
         Group _group=groupRepo.findById(Integer.parseInt(i)).orElseThrow(()->new ResourceNotFoundException("User not found"));
         _post.addGroup(_group);
     }


        _post=postRepository.save(_post);
        System.out.println(_post);
        return new ResponseEntity<>(_post, HttpStatus.OK);
    }


}

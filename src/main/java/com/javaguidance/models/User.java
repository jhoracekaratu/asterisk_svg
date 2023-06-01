package com.javaguidance.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
//import lombok.Builder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")

public class User {
    @Column(name = "id")
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Id
    int id;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "email_address")
    String email_address;
    @ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.MERGE,CascadeType.PERSIST})
            @JoinTable(name = "user_post",
                    joinColumns = {@JoinColumn(name = "user_id")}
                    ,inverseJoinColumns = {@JoinColumn(name = "post_id")})
//@JsonManagedReference
    private Set<Post> posts=new HashSet<>();

//    public void addPost(Post post){
////        System.out.println(post);
////        Post tpost=new Post(post.getId(),post.getData(),post.getUsers());
////
////        System.out.println(tpost.data);
//       this.posts.add(post);
////        System.out.println("I got here well");
//        post.getUsers().add(this);
////        tpost.setUders(this);
////        System.out.println("I got here not well");
//    }

//    public void removePost(int id){
//        Post post=this.posts.stream().filter((p) -> p.id==id).findFirst().orElse(null);
//        if(post!=null){
//            this.posts.remove(post);
//            post.getUsers().remove(this);
//        }
//    }



}

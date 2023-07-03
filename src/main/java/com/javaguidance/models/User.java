package com.javaguidance.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
//import lombok.Builder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user")
//@ToString
public class User {
    @Column(name = "id")
            @GeneratedValue(strategy = GenerationType.AUTO)
            @Id
    int id;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "email_address")
    String email_address;
    @ManyToMany(targetEntity = Post.class,fetch = FetchType.LAZY,
            cascade ={CascadeType.ALL})
            @JoinTable(name = "user_post",
                    joinColumns = {@JoinColumn(name = "user_id")}
                    ,inverseJoinColumns = {@JoinColumn(name = "post_id")})
//@JsonManagedReference
    private Set<Post> posts=new HashSet<>();

    @OneToMany(targetEntity = Roles.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    Set<Roles> rolesSet=new HashSet<>();

    public User(String username, String password, String email_address) {
        this.username = username;
        this.password = password;
        this.email_address = email_address;
    }

    public void addPost(Post post){
        this.posts.add(post);
        post.getUsers().add(this);
    }

    public void removePost(int id) {
        Post _post = this.posts.stream().filter(post -> post.id == id).findFirst().orElse(null);
        if (_post != null) {
            this.posts.remove(_post);
            _post.getUsers().remove(this);
        }
    }
}

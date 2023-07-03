package com.javaguidance.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@Entity
@Table(name = "post")
@Getter
@Setter
//@ToString
public class Post {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    int id;
    @Column(name = "title",unique = true)
    String title;

    @Column(name = "rating",unique = false)
    Integer rating;

    @Column(name = "slug",unique = true)
    String slug;

    @ManyToMany(fetch = FetchType.LAZY,targetEntity = Group.class,cascade = {CascadeType.ALL})
    @JoinTable(name = "post_group",joinColumns = {@JoinColumn(name = "post_id")},inverseJoinColumns = {@JoinColumn(name = "group_id")})

   List<Group> groups=new ArrayList<>();

    @OneToMany(targetEntity = PostParts.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    List<PostParts> postParts=new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL},mappedBy = "posts")
//    @JsonBackReference
@JsonIgnore
    private Set<User> users=new HashSet<>();

    public Post(String title) {
        this.title = title;
    }

    public void addPostPart(PostParts postPart){
        this.postParts.add(postPart);
    }
    public void removePostPart(PostParts postPart){
        this.postParts.remove(postPart);
    }
    public void addGroup(Group group){
        this.groups.add(group);
    }

    public void clearGroup(){
        this.groups.clear();
    }

    public void removeGroup(Group group){
        this.groups.remove(group);
    }
}

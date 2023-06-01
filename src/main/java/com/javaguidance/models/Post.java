package com.javaguidance.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@DynamicInsert
//@DynamicUpdate
@Table(name = "post")

public class Post {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
    @Column(name = "data")
    String data;//rename to post
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST},mappedBy = "posts")
            @JsonBackReference
//    @JsonIgnore
//  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
    private Set<User> users=new HashSet<>();

//    public Post(int id, String data) {
//        this.id = id;
//        this.data = data;
//    }
//
//    public void setUders(User user){
//        System.out.println("Got herew okay");
////        System.out.println(user.id);
//
////        users.add(user.id,user.username,user.);
//    }






}

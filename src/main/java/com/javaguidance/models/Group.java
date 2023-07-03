package com.javaguidance.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Entity
@Table(name = "groupsets")
@Getter
@Setter
//@ToString
public class Group {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "url")
   String url;
    @ManyToMany(cascade = {CascadeType.ALL},mappedBy = "groups",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Post> posts=new HashSet<>();



}

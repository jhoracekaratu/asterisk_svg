package com.javaguidance.controllers;

import com.javaguidance.models.Roles;
import com.javaguidance.models.User;
import com.javaguidance.repositories.RolesRepo;
import com.javaguidance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
public class RolesController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    RolesRepo rolesRepo;

    @PostMapping("/api/users/{userid}/roles")
    public void addRole(@PathVariable Integer userid, @RequestBody Roles role){
    User user=userRepo.findById(userid).orElseThrow(()->new IllegalArgumentException());
    user.getRolesSet().add(role);
    userRepo.save(user);
    }

    @GetMapping("/api/users/{userid}/roles")
    public ResponseEntity<User> getRoles(@PathVariable Integer userid){
        User user=userRepo.findById(userid).orElseThrow(()->new IllegalArgumentException());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{userid}/roles/{roleid}")
    public ResponseEntity<User> deleteRole(@PathVariable Integer userid,@PathVariable Integer roleid){
        User user=userRepo.findById(userid).orElseThrow(()->new IllegalArgumentException());
        user.getRolesSet()
                .removeIf(role -> {

                return Objects.equals(role.getId(), roleid);

                } );
        userRepo.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/api/users/{userid}/roles/{roleid}")
    public ResponseEntity<User> updateRoles(@PathVariable Integer userid,@PathVariable Integer roleid,@RequestBody Roles role){
        User user=userRepo.findById(userid).orElseThrow(()->new IllegalArgumentException());
         user.getRolesSet().stream().filter(_role ->{
           return _role.getId()==roleid;

        } )
                 .map(role_to_map -> {
                     role_to_map.setRole(role.getRole());
                     return role_to_map;
                 });
         ;
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}

package com.javaguidance.security;
import com.javaguidance.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;

@Service
@Slf4j
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.javaguidance.models.User> fetchedUser=userRepository.findByUsername(username);
        if(fetchedUser.isPresent()) {
            MyUserDetails myUserDetails=new MyUserDetails(fetchedUser.get());
            log.info("user {} found with privileges {}",myUserDetails.getUsername(),myUserDetails.getAuthorities());

            return myUserDetails;
        }
        else throw new UsernameNotFoundException("User "+username+" not found");
    }
}

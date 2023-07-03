package com.javaguidance.repositories;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.javaguidance.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GroupRepo  extends JpaRepository<Group,Integer> {
    Optional<List<Group>> findGroupsByPostsId(int postid);
}

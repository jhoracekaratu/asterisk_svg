package com.javaguidance.repositories;

import com.javaguidance.models.Group;
import com.javaguidance.models.Post;
import com.javaguidance.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    Optional<List<Post>> findPostsByUsersId(int id);
    Optional<List<Post>> findPostsByGroupsId(int groupid);
    Optional<Post> findPostsByTitle(String title);
    Optional<Post> findPostsBySlug(String slug);
    @Query(value = "select * from post where rating >= :number  and id not in :list limit :limit"  , nativeQuery = true)
    Optional<List<Post>> getPostByRatingCustom(@Param("number") int number,@Param("limit") int limit,@Param("list") List<String> list);
//@Modifying
//@Transactional
//    @Query(value = "delete from user_post where post_id =3;\n"+
//            "\n"+
//        "SET FOREIGN_KEY_CHECKS=0;\n"+
//        "delete from post where id =3;\n"+
//        "SET FOREIGN_KEY_CHECKS=1;\n"
//        ,nativeQuery = true)
//    public void deleteUsersPost();


}

package com.example.Group.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.Group.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // custom query to search to blog post by title or content
  
}

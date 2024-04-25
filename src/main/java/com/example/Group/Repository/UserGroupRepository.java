package com.example.Group.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.Group.Entity.UserGroup;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

  
}

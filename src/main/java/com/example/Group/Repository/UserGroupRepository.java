package com.example.Group.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Group.Entity.Group;
import com.example.Group.Entity.UserGroup;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    @Query("SELECT ug.group.id FROM UserGroup ug")
    List<Long> findAllGroupIds();

    @Query("SELECT g FROM Group g WHERE g.id NOT IN (:groupIds)")
    List<Group> findGroupsWithoutUsers(List<Long> groupIds);

    @Query("SELECT ug.group.id FROM UserGroup ug")
    List<Long> findGroupIdsWithUsers();

}

package com.example.Group.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.Group.Entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    // @Query("SELECT g.id AS groupId, g.numberUsers - COUNT(ug.id) AS
    // availablePlaces " +
    // "FROM Group g LEFT JOIN g.userGroups ug " +
    // "GROUP BY g.id")
    // List<Object[]> findAvailablePlacesInGroups();

}

package com.sberbank.demoProject.adminMicroservice.repository;

import com.sberbank.demoProject.adminMicroservice.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    @Modifying
    @Query("update User user set user.enabled = :enabled where user.id = :id")
    void blockUser(@Param("id") Long id, @Param("enabled") boolean enabled);
}

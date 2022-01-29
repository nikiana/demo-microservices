package com.sberbank.demoProject.adminMicroservice.repository;

import com.sberbank.demoProject.adminMicroservice.models.entities.Role;
import com.sberbank.demoProject.adminMicroservice.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {

    Set<Role> findByNameIn(Set<String> roles);
}

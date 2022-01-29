package com.sberbank.demoProject.authmicroservice.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
//@Table(name = "roles", schema = "public")
public class Role {
    @Id
    Long id;
    String name;

//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    Set<User> users = new HashSet<>();
}

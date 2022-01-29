package com.sberbank.demoProject.adminMicroservice.models.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    Long id;
    String name;
}

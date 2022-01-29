package com.sberbank.demoProject.adminMicroservice.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {
    @Id
    Long id;
    String name;

    /**
     * Role - это дочерняя сущность, а User - владелец, указываем это с помощью MappedBy.
     * (Нам не нужно подгружать всех юзеров, которые обладают этой ролью)
     */
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}

package com.sberbank.demoProject.adminMicroservice.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    /**
     * Используем стратегию, в которой провайдер JPA должен назначить id,
     * используя автоинкрементный столбец identity из базы данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    /**
     * Устанавливаем enabled по умолчанию - true
     */
    private Boolean enabled = true;
    /**
     * Указываем, что пользователи и роли имеют связь многие-ко-многим,
     * и мы используем для этого таблицу user_roles.
     */
    @ManyToMany(cascade= {CascadeType.MERGE})
    @JoinTable(name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles  = new HashSet<>();
}

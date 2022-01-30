package com.sberbank.demoProject.coursesMicroservice.models.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.*;

@Data
@Entity
@Table(name = "courses")
public class Course {
    /**
     * Используем стратегию, в которой провайдер JPA должен назначить id,
     * используя автоинкрементный столбец identity из базы данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}

package com.sberbank.demoProject.coursesMicroservice.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class CourseRequest {
    @NotBlank(message = "Название курса не может быть пустым")
    @Size(max = 100, message = "Название курса слишком длинное")
    String name;
    @Size(max = 300, message = "Описание курса слишком длинное")
    String description;
    BigDecimal price;
}

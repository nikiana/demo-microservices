package com.sberbank.demoProject.coursesMicroservice.models;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Value
@Builder
@Jacksonized
public class CourseRequest {
    @NotEmpty(message = "Название курса не может быть путым")
    @Size(max = 100, message = "Название курса слишком длинное")
    String name;
    @Size(max = 300, message = "Описание курса слишком длинное")
    String description;
    BigDecimal price;
}

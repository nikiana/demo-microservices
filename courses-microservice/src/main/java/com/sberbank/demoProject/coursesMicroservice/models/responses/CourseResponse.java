package com.sberbank.demoProject.coursesMicroservice.models.responses;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Value
public class CourseResponse {
    Long id;
    String name;
    String description;
    BigDecimal price;
}

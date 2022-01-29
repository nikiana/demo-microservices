package com.sberbank.demoProject.adminMicroservice.models.responces;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CourseResponse {
    Long id;
    String name;
    String description;
    BigDecimal price;
}

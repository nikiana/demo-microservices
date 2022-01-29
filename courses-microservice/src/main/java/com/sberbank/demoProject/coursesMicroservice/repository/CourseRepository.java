package com.sberbank.demoProject.coursesMicroservice.repository;

import com.sberbank.demoProject.coursesMicroservice.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository <Course, Long> {
}

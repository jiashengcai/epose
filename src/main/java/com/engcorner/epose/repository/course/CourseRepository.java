package com.engcorner.epose.repository.course;

import com.engcorner.epose.domain.course.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("courseRepository")
public interface CourseRepository extends CrudRepository<Course, Long> {
    Optional<Course> findById(Long id);
}

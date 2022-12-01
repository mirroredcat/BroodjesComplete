package be.abis.sessionapi.repository;

import be.abis.sessionapi.model.Course;
import be.abis.sessionapi.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaCourseRepository extends JpaRepository<Course, Integer> {

    Course findCourseById(int id);
    Course findCourseByCourseName(String courseName);
   // Course findCourseByTeacher(Staff teacher);
}

package be.abis.sessionapi.repository;



import be.abis.sessionapi.exceptions.CourseNotFoundException;
import be.abis.sessionapi.exceptions.PersonNotFoundException;
import be.abis.sessionapi.model.Course;

import java.util.List;

public interface CourseRepository {

    Course findCourseByID(int id) throws CourseNotFoundException;
    Course convertToCourseObj(String attr) throws PersonNotFoundException;


    List<Course> getAllCourses();
}

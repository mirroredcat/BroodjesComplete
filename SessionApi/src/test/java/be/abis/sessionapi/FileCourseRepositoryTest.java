package be.abis.sessionapi;


import be.abis.sessionapi.exceptions.CourseNotFoundException;
import be.abis.sessionapi.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FileCourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void courseListGetsCreatedTest() {
        assertEquals(6, courseRepository.getAllCourses().size());
    }

    @Test
    public void findCourseById3ShouldBeFrontEndDevelopment() throws CourseNotFoundException {
        assertEquals("FrontEnd Development", courseRepository.findCourseByID(3).getCourseName());
    }
    @Test
    public void findCourseById3ShouldHaveFloorAsTeacher() throws CourseNotFoundException {
        assertEquals("Floor", courseRepository.findCourseByID(3).getTeacher().getFirstName());
    }

    @Test
    public void findCourseById400ShouldThrowCourseNotFoundException() {
        assertThrows(CourseNotFoundException.class,()-> courseRepository.findCourseByID(400));
    }
}

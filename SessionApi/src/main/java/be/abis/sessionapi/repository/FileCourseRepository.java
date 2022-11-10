package be.abis.sessionapi.repository;


import be.abis.sessionapi.exceptions.CourseNotFoundException;
import be.abis.sessionapi.exceptions.PersonNotFoundException;
import be.abis.sessionapi.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
@ConditionalOnBean(value = StaffRepository.class)
public class FileCourseRepository implements CourseRepository{

    @Autowired
    StaffRepository staffRepository;

    private List<Course> allCourses = new ArrayList<>();

    private String fileLocation = "/temp/javacourses/Broodjes/courses.csv";

    @PostConstruct
    public void init() throws PersonNotFoundException {
        this.readFile();
    }


    public FileCourseRepository() {
    }

    public void readFile() throws PersonNotFoundException {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileLocation));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String line : lines) {
            Course c = convertToCourseObj(line);
            allCourses.add(c);
        }
    }


    @Override
    public Course findCourseByID(int id) throws CourseNotFoundException {

        Course foundCourse = allCourses.stream()
                .filter(c -> c.getId() == id)
                .findFirst().orElseThrow(()-> new CourseNotFoundException("Course not found")) ;
        return foundCourse;
    }

    @Override
    public Course convertToCourseObj(String attr) throws PersonNotFoundException {
        String[] vals = attr.split(";");
        if(!vals[0].equals("")){
            Course c = new Course();
            c.setId(!vals[0].equals("null") ? Integer.valueOf(vals[0]) : null);
            c.setCourseName(!vals[1].equals("null")? vals[1] : null );
            c.setTeacher(!vals[2].equals("null")?  staffRepository.findTeacherById(Integer.parseInt(vals[2])) : null);
            return c;
        }
        return null;
    }
    @Override
    public List<Course> getAllCourses() {
        return allCourses;
    }
}

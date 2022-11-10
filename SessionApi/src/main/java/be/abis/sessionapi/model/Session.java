package be.abis.sessionapi.model;

import java.time.LocalDate;
import java.util.List;

public class Session {

    private int id;
    private Course course;
    private Teacher teacher;
    private List<Student> studentList;
    private List<LocalDate> dates;


    public Session() {
    }

    public Session(int id, Course course, Teacher teacher, List<Student> studentList, List<LocalDate> dates) {
        this.id = id;
        this.course = course;
        this.teacher = teacher;
        this.studentList = studentList;
        this.dates = dates;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
    }
}

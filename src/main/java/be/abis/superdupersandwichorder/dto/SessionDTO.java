package be.abis.superdupersandwichorder.dto;


import be.abis.superdupersandwichorder.model.Course;
import be.abis.superdupersandwichorder.model.Student;
import java.util.List;

public class SessionDTO {

    private int sessionId;

    private Course course;
   // private Staff Teacher;
    private List<Student> studentList;

    public SessionDTO() {
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

/*    public Staff getTeacher() {
        return Teacher;
    }

    public void setTeacher(Staff teacher) {
        Teacher = teacher;
    }*/

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}

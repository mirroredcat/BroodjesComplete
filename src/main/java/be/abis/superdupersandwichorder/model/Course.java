package be.abis.superdupersandwichorder.model;

public class Course {

    private int id;
    private String courseName;
    private Teacher teacher;

    public Course() {
    }

    public Course(int id, String courseName, Teacher teacher) {
        this.id = id;
        this.courseName = courseName;
        this.teacher = teacher;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}

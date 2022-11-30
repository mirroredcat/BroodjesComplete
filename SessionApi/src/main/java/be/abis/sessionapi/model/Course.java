package be.abis.sessionapi.model;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @SequenceGenerator(name = "MyCourseSeqGen", sequenceName = "courses_cid_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MyCourseSeqGen")
    @Column(name = "cid")
    private int id;
    @Column(name = "ctitle")
    private String courseName;

    @ManyToOne
    @JoinColumn(name = "c_stid")
    private Staff staff;

    @Column(name = "cduration")
    private int courseDuration;

    public Course() {
    }

    public Course(String courseName, Staff staff, int courseDuration) {
        this.courseName = courseName;
        this.staff = staff;
        this.courseDuration = courseDuration;
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }
}
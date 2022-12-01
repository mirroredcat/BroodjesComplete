package be.abis.superdupersandwichorder.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "sessions")
public class Session {


    @SequenceGenerator(name = "MySessionSeqGen", sequenceName = "sessions_seid_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MySessionSeqGen")
    @Column(name = "seid")
    private int id;

    @OneToOne
    @JoinColumn(name = "se_cid")
    private Course course;
    @Column(name = "sestart")
    private LocalDate startDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "enrolments", joinColumns = @JoinColumn(name = "e_seid"))
    @OrderColumn(name = "eid")
    @Column(name = "e_sid")
    private List<Integer> enrollmentIds;


    public Session() {
    }

    public Session(Course course, LocalDate startDate) {
        this.course = course;
        this.startDate = startDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<Integer> getEnrollmentIds() {
        return enrollmentIds;
    }

    public void setEnrollmentIds(List<Integer> enrollmentIds) {
        this.enrollmentIds = enrollmentIds;
    }
}

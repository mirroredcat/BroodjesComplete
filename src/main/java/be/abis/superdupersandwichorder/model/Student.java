package be.abis.superdupersandwichorder.model;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student extends Person{

    @SequenceGenerator(name = "MyStudentSeqGen", sequenceName = "students_sid_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MyStudentSeqGen")
    @Column(name = "sid")
    private int id;
    @Column(name =  "sfname")
    private String firstName;
    @Column(name = "slname")
    private String lastName;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}


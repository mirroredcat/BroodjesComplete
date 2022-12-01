package be.abis.superdupersandwichorder.model;

import javax.persistence.*;

@Entity
@Table(name = "staff")
public class Staff extends Person {


    @SequenceGenerator(name = "MyStaffSeqGen", sequenceName = "staff_stid_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MyStaffSeqGen")
    @Column(name = "stid")
    private int id;
    @Column(name =  "stfname")
    private String firstName;
    @Column(name = "stlname")
    private String lastName;
    @Column(name = "stemail")
    private String email;
    @Column(name = "stpass")
    private String password;

    public Staff() {
    }

    public Staff(String firstName, String lastName) {
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

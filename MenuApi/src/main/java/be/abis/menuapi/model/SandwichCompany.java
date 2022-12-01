package be.abis.menuapi.model;

import javax.persistence.*;

@Entity
@Table(name="sand_comps")
public class SandwichCompany {

    @Id
    @SequenceGenerator(name = "sandComp_seq", sequenceName = "sand_comp_scid_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sandComp_seq")
    @Column(name="scid")
    private int id;
    @Column(name="scname")
    private String companyName;
    @Column(name="scaddress")
    private String address;
    @Column(name="scphone")
    private String phoneNr;


    public SandwichCompany(String companyName, String address, String phoneNr) {
        this.companyName = companyName;
        this.address = address;
        this.phoneNr = phoneNr;
    }

    public SandwichCompany() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }
}

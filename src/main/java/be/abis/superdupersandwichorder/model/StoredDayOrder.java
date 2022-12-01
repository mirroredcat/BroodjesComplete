package be.abis.superdupersandwichorder.model;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "orders")
public class StoredDayOrder {

    @SequenceGenerator(name = "MyDayOrderSeqGen", sequenceName = "orders_oid_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MyOrderSeqGen")
    @Column(name = "oid")
    int id;
    @Column(name = "odate")
    LocalDate date;
    @Column(name = "osize" )
    int orderListSize;
    @Column(name = "o_scid")
    SandwichCompany sandwichCompany;
    @Column(name = "ototal")
    double dayTotal;
    @Column(name = "o_seid")
    int sessionID;

    public StoredDayOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getOrderListSize() {
        return orderListSize;
    }

    public void setOrderListSize(int orderListSize) {
        this.orderListSize = orderListSize;
    }

    public SandwichCompany getSandwichCompany() {
        return sandwichCompany;
    }

    public void setSandwichCompany(SandwichCompany sandwichCompany) {
        this.sandwichCompany = sandwichCompany;
    }

    public double getDayTotal() {
        return dayTotal;
    }

    public void setDayTotal(double dayTotal) {
        this.dayTotal = dayTotal;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }
}

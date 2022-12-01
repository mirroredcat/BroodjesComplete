package be.abis.superdupersandwichorder.model;

import be.abis.superdupersandwichorder.dto.SessionDTO;

public class Order {

    private Person personWhoOrdered;
    private SessionDTO session;
    private Sandwich orderedSandwich;
    private String breadOption;
    private String vegetableOption;
    private String comment;

    public Order() {
    }

    public Order(Person personWhoOrdered, SessionDTO session, Sandwich orderedSandwich, String breadOption, String vegetableOption, String comment) {
        this.personWhoOrdered = personWhoOrdered;
        this.session = session;
        this.orderedSandwich = orderedSandwich;
        this.breadOption = breadOption;
        this.vegetableOption = vegetableOption;
        this.comment = comment;
    }

    public Person getPersonWhoOrdered() {
        return personWhoOrdered;
    }

    public void setPersonWhoOrdered(Person personWhoOrdered) {
        this.personWhoOrdered = personWhoOrdered;
    }

    public SessionDTO getSession() {
        return session;
    }

    public void setSession(SessionDTO session) {
        this.session = session;
    }

    public Sandwich getOrderedSandwich() {
        return orderedSandwich;
    }

    public void setOrderedSandwich(Sandwich orderedSandwich) {
        this.orderedSandwich = orderedSandwich;
    }

    public String getBreadOption() {
        return breadOption;
    }

    public void setBreadOption(String breadOption) {
        this.breadOption = breadOption;
    }

    public String getVegetableOption() {
        return vegetableOption;
    }

    public void setVegetableOption(String vegetableOption) {
        this.vegetableOption = vegetableOption;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        return String.format("%-15s| %-15s| %-15s| %-15s |%-15s",
                this.getPersonWhoOrdered().getFirstName(),
                this.getOrderedSandwich()==null ?  " ": this.getOrderedSandwich().getSandwichName(),
                this.getOrderedSandwich()==null ?  " ": this.getBreadOption(),
                this.getOrderedSandwich()==null ?  " ": this.getVegetableOption(),
                this.getOrderedSandwich()==null ?  " ": (this.getComment()==null? "": this.getComment()));
    }
}

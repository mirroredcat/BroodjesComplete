package be.abis.superdupersandwichorder.dto;

public class OrderDTO {

    int personId;
    String personName;
    String courseName;
    String sandwichName;
    String breadOption;
    String veggieOption;
    String comments;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSandwichName() {
        return sandwichName;
    }

    public void setSandwichName(String sandwichName) {
        this.sandwichName = sandwichName;
    }

    public String getBreadOption() {
        return breadOption;
    }

    public void setBreadOption(String breadOption) {
        this.breadOption = breadOption;
    }

    public String getVeggieOption() {
        return veggieOption;
    }

    public void setVeggieOption(String veggieOption) {
        this.veggieOption = veggieOption;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

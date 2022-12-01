package be.abis.superdupersandwichorder.model;

public class OrderPreferencesRequestBody {

    private String orderedSandwichName;

    private String breadOption;

    private String vegetableOption;

    private String comment;

    public String getOrderedSandwichName() {
        return orderedSandwichName;
    }

    public void setOrderedSandwichName(String orderedSandwichName) {
        this.orderedSandwichName = orderedSandwichName;
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
}

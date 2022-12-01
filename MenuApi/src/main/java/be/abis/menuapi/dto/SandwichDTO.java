package be.abis.menuapi.dto;

import be.abis.menuapi.model.SandwichCompany;

public class SandwichDTO {

    private int id;
    private String sandwichName;
    private String sandwichCompanyName;
    private String category;
    private String price;
    private String ingredients;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSandwichName() {
        return sandwichName;
    }

    public void setSandwichName(String sandwichName) {
        this.sandwichName = sandwichName;
    }

    public String getSandwichCompanyName() {
        return sandwichCompanyName;
    }

    public void setSandwichCompanyName(String sandwichCompanyName) {
        this.sandwichCompanyName = sandwichCompanyName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}

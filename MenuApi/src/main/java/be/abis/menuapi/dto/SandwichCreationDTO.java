package be.abis.menuapi.dto;

import be.abis.menuapi.model.SandwichCompany;

public class SandwichCreationDTO {

    private String id;
    private String sandwichName;
    private String sandwichCompanyId;
    private String sandwichCompanyName;
    private String sandwichCompanyAddress;
    private String sandwichCompanyTelNo;
    private String category;
    private String price;
    private String ingredients;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSandwichCompanyId() {
        return sandwichCompanyId;
    }

    public void setSandwichCompanyId(String sandwichCompanyId) {
        this.sandwichCompanyId = sandwichCompanyId;
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

    public String getSandwichCompanyAddress() {
        return sandwichCompanyAddress;
    }

    public void setSandwichCompanyAddress(String sandwichCompanyAddress) {
        this.sandwichCompanyAddress = sandwichCompanyAddress;
    }

    public String getSandwichCompanyTelNo() {
        return sandwichCompanyTelNo;
    }

    public void setSandwichCompanyTelNo(String sandwichCompanyTelNo) {
        this.sandwichCompanyTelNo = sandwichCompanyTelNo;
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

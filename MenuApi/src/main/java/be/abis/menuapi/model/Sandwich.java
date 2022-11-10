package be.abis.menuapi.model;

public class Sandwich {

    private int id;
    private String sandwichName;
    private SandwichCompany sandwichCompany;
    private String category;
    private Double price;
    private String ingredients;


    public Sandwich(int id, String sandwichName, SandwichCompany sandwichCompany, String category, double price, String ingredients) {
        this.id = id;
        this.sandwichName = sandwichName;
        this.sandwichCompany = sandwichCompany;
        this.category = category;
        this.price = price;
        this.ingredients = ingredients;
    }

    public Sandwich() {    }


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

    public SandwichCompany getSandwichCompany() {
        return sandwichCompany;
    }

    public void setSandwichCompany(SandwichCompany sandwichCompany) {
        this.sandwichCompany = sandwichCompany;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString(){
        return (this.sandwichName + " " + this.category +" "+   (this.ingredients==null? "": ("\n" + this.ingredients)) );
    }

}



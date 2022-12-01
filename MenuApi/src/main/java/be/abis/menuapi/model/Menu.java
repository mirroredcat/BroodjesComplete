package be.abis.menuapi.model;


import java.util.List;

public class Menu {


    private SandwichCompany sandwichCompany;
    private List<Sandwich> sandwichList;

    public Menu() {}


    public SandwichCompany getSandwichCompany() {
        return sandwichCompany;
    }

    public void setSandwichCompany(SandwichCompany sandwichCompany) {
        this.sandwichCompany = sandwichCompany;
    }

    public List<Sandwich> getSandwichList() {
        return sandwichList;
    }

    public void setSandwichList(List<Sandwich> sandwichList) {
        this.sandwichList = sandwichList;
    }

    // needs to be changed when adding front end
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String currentCategory = sandwichList.get(0).getCategory().toUpperCase();
        sb.append(currentCategory).append("\n").append("\n");
        for (Sandwich s : sandwichList) {
            if (!s.getCategory().equals(currentCategory)) {
                currentCategory = s.getCategory();
                sb.append("\n").append(currentCategory.toUpperCase()).append("\n").append("\n");
            } else {
                if (s.getCategory().equalsIgnoreCase("specials")) {
                    sb.append(s.getSandwichName()).append("\n").append(s.getIngredients()).append("\n");
                } else {
                    sb.append(s.getSandwichName()).append("\n");
                }
            }
        }
        return sb.toString();
    }
}

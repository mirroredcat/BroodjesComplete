package be.abis.menuapi.service;


import be.abis.menuapi.model.Menu;
import be.abis.menuapi.model.Sandwich;

public interface MenuService {

    public void setMenuOfTheDay(String sandwichCompanyName);
    public Menu getMenuOfTheDay();

    public Sandwich findSandwichInMenuOfDay(String sandwichName);
    public Sandwich findSandwich(String sandwichName, String companyName);
    public Sandwich findSandwich(int id);
    public void addSandwich(Sandwich s);
    public void updateSandwichPrice(int id, Double newPrice);
    public void updateSandwichIngredients(int id, String newIngredients);
    public void deleteSandwich(int id);


}

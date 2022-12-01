package be.abis.menuapi.service;


import be.abis.menuapi.exceptions.SandwichAlreadyExistsException;
import be.abis.menuapi.exceptions.SandwichNotFoundException;
import be.abis.menuapi.model.Menu;
import be.abis.menuapi.model.Sandwich;

public interface MenuService {

    public void setMenuOfTheDay(String sandwichCompanyName);
    public Menu getMenuOfTheDay();
    public Sandwich findSandwich(int id) throws SandwichNotFoundException;
    public Sandwich findSandwichInMenuOfDay(String sandwichName) throws SandwichNotFoundException;

    public Sandwich findSandwich(String sandwichName, String companyName) throws SandwichNotFoundException;
    public Sandwich addSandwich(Sandwich s) throws SandwichAlreadyExistsException;
    public void updateSandwichPrice(int id, Double newPrice) throws SandwichNotFoundException;
    public void updateSandwichIngredients(int id, String newIngredients) throws SandwichNotFoundException;
    public void deleteSandwich(int id) throws SandwichNotFoundException;


}

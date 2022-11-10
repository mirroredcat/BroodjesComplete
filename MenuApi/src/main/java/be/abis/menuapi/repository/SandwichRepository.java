package be.abis.menuapi.repository;



import be.abis.menuapi.exceptions.SandwichAlreadyExistsException;
import be.abis.menuapi.exceptions.SandwichNotFoundException;
import be.abis.menuapi.model.Sandwich;

import java.util.List;

public interface SandwichRepository {

    public List<Sandwich> getSandwichList();

    public List<Sandwich> findSandwichesByRestaurant(String name);
    public Sandwich findSandwich(int id) throws SandwichNotFoundException;
    public Sandwich findSandwich(String sandwichName, String companyName) throws SandwichNotFoundException;
    public void addSandwich(Sandwich sandwich) throws SandwichAlreadyExistsException, SandwichNotFoundException;
    public void deleteSandwich(int id) throws SandwichNotFoundException;
    public void updateSandwichPrice(int id, Double newPrice) throws SandwichNotFoundException;
    public void updateSandwichIngredients(int id, String newIngredients) throws SandwichNotFoundException;
}

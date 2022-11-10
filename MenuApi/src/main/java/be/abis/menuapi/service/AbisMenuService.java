package be.abis.menuapi.service;


import be.abis.menuapi.exceptions.SandwichAlreadyExistsException;
import be.abis.menuapi.exceptions.SandwichNotFoundException;
import be.abis.menuapi.model.Menu;
import be.abis.menuapi.model.Sandwich;
import be.abis.menuapi.model.SandwichCompany;
import be.abis.menuapi.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbisMenuService implements MenuService{

    @Autowired
    SandwichRepository sr;

    private Menu menuOfTheDay= new Menu();

    @Override
    public void setMenuOfTheDay(String sandwichCompanyName) {
        menuOfTheDay.setSandwichCompany(SandwichCompany.fromString(sandwichCompanyName));
        menuOfTheDay.setSandwichList(sr.findSandwichesByRestaurant(sandwichCompanyName));
    }

    @Override
    public Menu getMenuOfTheDay(){return menuOfTheDay;}

    @Override
    public Sandwich findSandwichInMenuOfDay(String sandwichName) {
        return menuOfTheDay.getSandwichList().stream()
                .filter(s -> s.getSandwichName().equals(sandwichName))
                .findFirst().get();
    }

    @Override
    public Sandwich findSandwich(String sandwichName, String companyName){
        try {
            return sr.findSandwich(sandwichName, companyName);
        } catch (SandwichNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //TO-DO error handling
    @Override
    public Sandwich findSandwich(int id){
        try {
            return sr.findSandwich(id);
        } catch (SandwichNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addSandwich(Sandwich s) {
        try {
            sr.addSandwich(s);
        } catch (SandwichAlreadyExistsException | SandwichNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateSandwichPrice(int id, Double newPrice) {
        try {
            sr.updateSandwichPrice(id, newPrice);
        } catch (SandwichNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateSandwichIngredients(int id, String newIngredients) {
        try {
            sr.updateSandwichIngredients(id, newIngredients);
        } catch (SandwichNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteSandwich(int id) {
        try {
            sr.deleteSandwich(id);
        } catch (SandwichNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}

package be.abis.menuapi.service;



import be.abis.menuapi.exceptions.SandwichAlreadyExistsException;
import be.abis.menuapi.exceptions.SandwichNotFoundException;
import be.abis.menuapi.model.Menu;
import be.abis.menuapi.model.Sandwich;
import be.abis.menuapi.repository.SandwichCompanyJpaRepository;
import be.abis.menuapi.repository.SandwichJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;

@Service
public class AbisMenuService implements MenuService{

    @Autowired
    SandwichJpaRepository sandwichRepo;

    @Autowired
    SandwichCompanyJpaRepository companyRepo;

    private Menu menuOfTheDay= new Menu();

    @Override
    public void setMenuOfTheDay(String sandwichCompanyName) {
        menuOfTheDay.setSandwichCompany(companyRepo.findCompany(sandwichCompanyName));
        menuOfTheDay.setSandwichList(sandwichRepo.findSandwichesByRestaurant(sandwichCompanyName));
    }

    @Override
    public Menu getMenuOfTheDay(){return menuOfTheDay;}

    @Override
    public Sandwich findSandwichInMenuOfDay(String sandwichName) throws SandwichNotFoundException {
        Sandwich foundS = sandwichRepo.findSandwich(sandwichName, this.menuOfTheDay.getSandwichCompany().getCompanyName());
        if(foundS == null) {
            throw new SandwichNotFoundException("This sandwich does not exist");
        } else {
            return foundS;
        }
    }

    @Override
    public Sandwich findSandwich(String sandwichName, String companyName) throws SandwichNotFoundException {
        Sandwich foundS =  sandwichRepo.findSandwich(sandwichName, companyName);
        if(foundS == null) {
            throw new SandwichNotFoundException("This sandwich does not exist");
        } else {
            return foundS;
        }
    }


    @Override
    public Sandwich findSandwich(int id) throws SandwichNotFoundException {
        Sandwich foundS =  sandwichRepo.findSandwich(id);
        if(foundS == null) {
            throw new SandwichNotFoundException("This sandwich does not exist");
        } else {
            return foundS;
        }
    }

    @Override
    public Sandwich addSandwich(Sandwich s) throws SandwichAlreadyExistsException {
        Sandwich sandwich = sandwichRepo.findSandwich(s.getSandwichName(), s.getSandwichCompany().getCompanyName());

        if (sandwich != null){
            throw new SandwichAlreadyExistsException("The sandwich you are trying to add already exists");
        } else {
            sandwich =  sandwichRepo.save(s);
            if (menuOfTheDay.getSandwichCompany().getCompanyName().trim().equals(s.getSandwichCompany().getCompanyName().trim())){
                menuOfTheDay.getSandwichList().add(sandwich);
            }
            return sandwich;
        }
    }

    @Override
    @Transactional
    public void updateSandwichPrice(int id, Double newPrice) throws SandwichNotFoundException {
        Sandwich sandwich = sandwichRepo.findSandwich(id);

        if (sandwich == null){
            throw new SandwichNotFoundException("The sandwich you are trying to update does not exists");
        } else {
            sandwichRepo.updateSandwichPrice(id, newPrice);
        }
    }

    @Override
    @Transactional
    public void updateSandwichIngredients(int id, String newIngredients) throws SandwichNotFoundException {
        Sandwich sandwich = sandwichRepo.findSandwich(id);

        if (sandwich == null){
            throw new SandwichNotFoundException("The sandwich you are trying to update does not exists");
        } else {
            sandwichRepo.updateSandwichIngredients(id, newIngredients);
        }
    }

    @Override
    public void deleteSandwich(int id) throws SandwichNotFoundException {
        Sandwich sandwich = sandwichRepo.findSandwich(id);

        if (sandwich == null) {
            throw new SandwichNotFoundException("The sandwich you are trying to delete does not exist");
        } else {
            //TODO sandwich does not get deleted from list(but it does from the db). no errors
            if (menuOfTheDay.getSandwichCompany().getCompanyName().trim().equals(sandwich.getSandwichCompany().getCompanyName().trim())){
                menuOfTheDay.getSandwichList().remove(sandwich);
            }
            sandwichRepo.delete(sandwich);
        }

    }
}

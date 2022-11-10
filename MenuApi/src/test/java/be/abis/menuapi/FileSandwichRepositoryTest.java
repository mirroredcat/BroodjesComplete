package be.abis.menuapi;


import be.abis.menuapi.exceptions.SandwichAlreadyExistsException;
import be.abis.menuapi.exceptions.SandwichNotFoundException;
import be.abis.menuapi.model.Sandwich;
import be.abis.menuapi.model.SandwichCompany;
import be.abis.menuapi.repository.SandwichRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FileSandwichRepositoryTest {

    @Autowired
    public SandwichRepository fsr;
    public Sandwich s;

    @BeforeEach
    public void setUp(){
        s = new Sandwich(70, "Test Sandwich", SandwichCompany.PINKYS,"vlees",10.00,null);
    }

    @Test
    public void vleugelsHas30Sandwiches(){
        assertEquals(fsr.findSandwichesByRestaurant("Vleugels").size(),30);
    }

    @Test
    public void sandwichWIthId55IsPrimus() throws SandwichNotFoundException {
        assertEquals(fsr.findSandwich(55).getSandwichName(), "Primus");
    }

    @Test
    public void sandwichIsNotFound(){
        assertThrows(SandwichNotFoundException.class, () -> fsr.findSandwich(1234));
    }

    @Test
    public void sandwichTonijnslaFromPinkysIsFound() throws SandwichNotFoundException {
        assertEquals(fsr.findSandwich("Tonijnsla", "Pinky's").getId(), 34 );
    }

    @Test
    public void sandwichKipslaFromVleugelsIsNotFound(){
        assertThrows(SandwichNotFoundException.class, ()-> fsr.findSandwich("Kipsla", "Vleugels"));
    }

    @Test
    @Order(1)
    public void sandwichIsAddedToRepo() throws SandwichAlreadyExistsException, SandwichNotFoundException {
        int i = fsr.getSandwichList().size();
        fsr.addSandwich(s);
        int j = fsr.getSandwichList().size();
        assertEquals(j, i+1);
    }

    @Test
    @Order(2)
    public void sandwichPriceChangedTo14() throws SandwichNotFoundException {
        fsr.updateSandwichPrice(s.getId(), 14.00);
        assertEquals(fsr.findSandwich(70).getPrice(), 14.00);
    }
/*
    @Test
    @Order(3)
    public void sandwichIngredientsIsChanged() throws SandwichNotFoundException {
        fsr.updateSandwichIngredients(s, "ingredient1, ingredient2, ingredient3");
        assertEquals(fsr.findSandwich(70).getIngredients(), "ingredient1, ingredient2, ingredient3");
    }

 */

    @Test
    @Order(4)
    public void sandwichIsRemovedFromList() throws SandwichNotFoundException {
        int i = fsr.getSandwichList().size();
        fsr.deleteSandwich(70);
        int j = fsr.getSandwichList().size();
        assertEquals(i, j+1);
    }

}

package be.abis.menuapi;

import be.abis.menuapi.exceptions.SandwichAlreadyExistsException;
import be.abis.menuapi.exceptions.SandwichNotFoundException;
import be.abis.menuapi.model.Sandwich;
import be.abis.menuapi.model.SandwichCompany;
import be.abis.menuapi.repository.SandwichCompanyJpaRepository;
import be.abis.menuapi.repository.SandwichJpaRepository;
import be.abis.menuapi.service.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AbisMenuServiceTest {

    @Autowired
    MenuService ms;

    @Autowired
    SandwichCompanyJpaRepository scr;

    @BeforeEach
    public void setUp(){
        ms.setMenuOfTheDay("Pinky's");
    }


    @Test
    public void testMenuInit(){
        ms.setMenuOfTheDay("Vleugels");

        assertEquals(30, ms.getMenuOfTheDay().getSandwichList().size());
    }

    @Test
    public void sandwichWIthId55IsPrimus() throws SandwichNotFoundException {
        assertEquals("Primus",ms.findSandwich(55).getSandwichName().trim());
    }

    @Test
    public void sandwichIsNotFound(){
        assertThrows(SandwichNotFoundException.class, () -> ms.findSandwich(1234));
    }

    @Test
    public void sandwichTonijnslaFromPinkysIsFound() throws SandwichNotFoundException {
        assertEquals(ms.findSandwich("Tonijnsla", "Pinky's").getId(), 34 );
    }

    @Test
    public void sandwichKipslaFromVleugelsIsNotFound(){
        assertThrows(SandwichNotFoundException.class, ()-> ms.findSandwich("Kipsla", "Vleugels"));
    }

    @Test
    @Transactional
    public void sandwichIsAddedToRepo() throws SandwichAlreadyExistsException, SandwichNotFoundException {
        Sandwich s = new Sandwich("Test Sandwich2", scr.findCompany("Pinky's"),"vlees",10.00,null);

        int i = ms.getMenuOfTheDay().getSandwichList().size();
        ms.addSandwich(s);
        int j = ms.getMenuOfTheDay().getSandwichList().size();
        assertEquals(j, i+1);
    }

    @Test
    public void sandwichPriceChangedTo16() throws SandwichNotFoundException {
        ms.updateSandwichPrice(12, 16.00);
        assertEquals(16.00, ms.findSandwich(12).getPrice());
    }

    @Test
    //@Transactional
    public void sandwichIngredientsIsChanged() throws SandwichNotFoundException {
        ms.updateSandwichIngredients(12, "ingredient4, ingredient2, ingredient3");
        assertEquals("ingredient4, ingredient2, ingredient3", ms.findSandwich(12).getIngredients().trim());
    }

    @Test
    @Transactional
    public void sandwichIsRemovedFromList() throws SandwichNotFoundException {
        int i = ms.getMenuOfTheDay().getSandwichList().size();
        ms.deleteSandwich(12);
        int j = ms.getMenuOfTheDay().getSandwichList().size();
        assertEquals(i, j+1);
    }

}

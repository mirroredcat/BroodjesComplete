package be.abis.menuapi;

import be.abis.menuapi.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AbisMenuServiceTest {

    @Autowired
    MenuService ms;

    @Test
    public void testMenuInit(){
        ms.setMenuOfTheDay("Vleugels");

        assertEquals(30, ms.getMenuOfTheDay().getSandwichList().size());
    }

}

package be.abis.superdupersandwichorder;

import be.abis.superdupersandwichorder.model.Order;
import be.abis.superdupersandwichorder.model.Person;
import be.abis.superdupersandwichorder.model.Sandwich;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderTest {

    Order order = new Order();

    @Mock
    Person p1;

    @Mock
    Sandwich s1;

    @Test
    public void printOrderTest(){
        when(this.p1.getFirstName()).thenReturn("Mike");
        when(this.s1.getSandwichName()).thenReturn("Hesp");

        order.setPersonWhoOrdered(p1);
        order.setOrderedSandwich(s1);
        order.setBreadOption("wit");
        order.setVegetableOption("Smos");
        order.setComment("no mayo");

        System.out.println(order);
    }

}

package be.abis.superdupersandwichorder;


import be.abis.superdupersandwichorder.dto.SessionDTO;
import be.abis.superdupersandwichorder.exceptions.CannotSetMenuException;
import be.abis.superdupersandwichorder.exceptions.OrderAlreadyExistsException;
import be.abis.superdupersandwichorder.exceptions.OrderNotFoundException;
import be.abis.superdupersandwichorder.model.*;
import be.abis.superdupersandwichorder.service.DayOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AbisOrderServiceTest {

    @Autowired
    DayOrderService dos;

    @Mock
    DayOrder dayOrder;

    @Mock Order o1;
    @Mock Order o2;
    @Mock Order o3;
    @Mock Order o4;

    @Mock
    SessionDTO ses1;
    @Mock SessionDTO ses2;

    @Mock Course c1;
    @Mock Course c2;

    @Mock Person p1;
    @Mock Person p2;
    @Mock Person p3;

    @Mock Sandwich s1;
    @Mock Sandwich s2;

    @Mock Menu m1;
    @Mock SandwichCompany sc1;

    @Mock OrderPreferencesRequestBody op;

    //this test works if run alone(we changed data and run again, ok)
    //fails if run with all tests
    //no idea why
    @Test
    public void dayOrderHas5Orders() throws CannotSetMenuException {
        SandwichCompanyRequestBody scrb = new SandwichCompanyRequestBody();
        scrb.setSandwichCompanyName("Vleugels");
        dos.newDayOrder(scrb);
        int i = dos.getDayOrder().getOrderList().size();
        assertEquals(5, i);
    }

    @Test
    public void printPrettyPlease(){
        List<Order> mockList = new ArrayList<>();
        when(this.sc1.getCompanyName()).thenReturn("Vleugels");
        when(this.m1.getSandwichCompany()).thenReturn(sc1);

        when(this.dayOrder.getDate()).thenReturn(LocalDate.now());
        when(this.dayOrder.getDayMenu()).thenReturn(m1);

        when(this.c1.getCourseName()).thenReturn("Java");
        when(this.ses1.getCourse()).thenReturn(c1);

        when(this.c2.getCourseName()).thenReturn("HTML / CSS");
        when(this.ses2.getCourse()).thenReturn(c2);

        when(this.o1.getSession()).thenReturn(ses1);
        when(this.o1.toString()).thenReturn("Mike           | Hesp           | wit            | Smos            |no mayo        ");
        mockList.add(o1);

        when(this.o2.getSession()).thenReturn(ses1);
        when(this.o2.toString()).thenReturn("John           | Boulette       | stockbrood     | Smos            |               ");
        mockList.add(o2);

        when(this.o3.getSession()).thenReturn(ses2);
        when(this.o3.toString()).thenReturn("Bob            | Kipcurry       | stockbrood     | Smos            |               ");
        mockList.add(o3);

        when(this.o4.getSession()).thenReturn(ses2);
        when(this.o4.toString()).thenReturn("Wim           | Brie            | stockbrood     | Geen            |               ");
        mockList.add(o4);


        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);

        dos.printDayOrder();
    }

    @Test
    public void johnDoeOrderFound() throws OrderNotFoundException {
        List<Order> mockList = new ArrayList<>();
        when(this.p1.getFirstName()).thenReturn("John");
        when(this.p1.getLastName()).thenReturn("Doe");
        mockList.add(o1);
        mockList.add(o2);
        when(this.o1.getPersonWhoOrdered()).thenReturn(p1);
        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);


        assertEquals("John", dos.findOrder("John", "Doe").getPersonWhoOrdered().getFirstName());
    }

    @Test
    public void findOrderThrowsException (){
        List<Order> mockList = new ArrayList<>();
        when(this.p1.getFirstName()).thenReturn("John");
        when(this.p2.getFirstName()).thenReturn("Lemon");
        mockList.add(o1);
        mockList.add(o2);
        when(this.o1.getPersonWhoOrdered()).thenReturn(p1);
        when(this.o2.getPersonWhoOrdered()).thenReturn(p2);

        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);

        assertThrows(OrderNotFoundException.class, ()-> dos.findOrder("Mary", "Doe"));
    }

    @Test
    public void addNewOrderTest() throws OrderAlreadyExistsException {
        List<Order> mockList = new ArrayList<>();
        when(this.o1.getPersonWhoOrdered()).thenReturn(p1);
        when(this.o2.getPersonWhoOrdered()).thenReturn(p2);


        mockList.add(o1);
        mockList.add(o2);

        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);

        int beforeAdd = dos.getAllOrders().size();
        when(this.o3.getPersonWhoOrdered()).thenReturn(p3);
        when(this.o3.getOrderedSandwich()).thenReturn(s1);
        dos.addOrder(o3);
        int afterAdd = dos.getAllOrders().size();

        assertEquals(afterAdd, beforeAdd + 1);

    }

    @Test
    public void personAlreadyOrdered(){
        List<Order> mockList = new ArrayList<>();
        when(this.o1.getPersonWhoOrdered()).thenReturn(p1);
        when(this.o2.getPersonWhoOrdered()).thenReturn(p2);
        mockList.add(o1);
        mockList.add(o2);

        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);

        assertThrows(OrderAlreadyExistsException.class, ()-> dos.addOrder(o2));
    }

    @Test
    public void removeOrderTest(){
        List<Order> mockList = new ArrayList<>();
        mockList.add(o1);
        mockList.add(o2);

        when(this.p1.getId()).thenReturn(5);
        when(this.o1.getPersonWhoOrdered()).thenReturn(p1);
        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);

        int beforeDelete = dos.getAllOrders().size();
        dos.deleteOrder(5);
        int afterDelete = dos.getAllOrders().size();

        assertEquals(afterDelete, beforeDelete-1);
    }

    // test written badly, it works in postman
    @Test
    public void updateOrderTest() throws OrderNotFoundException {
        List<Order> mockList = new ArrayList<>();
        when(this.p1.getFirstName()).thenReturn("John");
        when(this.p1.getLastName()).thenReturn("Doe");
        when(this.o1.getPersonWhoOrdered()).thenReturn(p1);
        mockList.add(o1);
        //mockList.add(o2);

        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);

        when(this.p1.getId()).thenReturn(5);
        when(this.o1.getPersonWhoOrdered()).thenReturn(p1);
        //when(this.o3.getOrderedSandwich()).thenReturn(s1);
        //when(this.o3.getBreadOption()).thenReturn("wit");
        //when(this.o3.getVegetableOption()).thenReturn("smos");
        when(this.op.getBreadOption()).thenReturn("wit");
        when(this.op.getOrderedSandwichName()).thenReturn("Hesp");
        when(this.op.getVegetableOption()).thenReturn("ja");


        Order or1 = dos.updateOrder(5, op);

        System.out.println(dayOrder.getOrderList().size());
        System.out.println(or1);

        assertEquals("wit", dos.findOrder("John", "Doe").getBreadOption());
    }

    @Test
    public void dayTotalIs15(){
        List<Order> mockList = new ArrayList<>();

        when(this.s1.getPrice()).thenReturn(7.50);
        when(this.s2.getPrice()).thenReturn(7.50);

        when(this.o1.getOrderedSandwich()).thenReturn(s1);
        when(this.o2.getOrderedSandwich()).thenReturn(s2);

        mockList.add(o1);
        mockList.add(o2);

        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);

        assertEquals(15.00, dos.calculateDayTotal());
    }

    @Test
    public void menuPrintsOutTest(){
        when(this.dayOrder.getDayMenu()).thenReturn(m1);
        when(this.m1.toString()).thenReturn("The menu prints correctly");

        dos.setDayOrder(dayOrder);

        assertEquals("The menu prints correctly", dos.printMenu());
    }




}

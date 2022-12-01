package be.abis.superdupersandwichorder;


import be.abis.superdupersandwichorder.dto.SessionDTO;
import be.abis.superdupersandwichorder.exceptions.DayOrderNotFoundException;
import be.abis.superdupersandwichorder.exceptions.OrderAlreadyRegisteredException;
import be.abis.superdupersandwichorder.exceptions.OrderNotFoundException;
import be.abis.superdupersandwichorder.model.*;

import be.abis.superdupersandwichorder.model.Order;
import be.abis.superdupersandwichorder.repository.JpaOrderRepository;
import be.abis.superdupersandwichorder.service.DayOrderService;
import be.abis.superdupersandwichorder.service.OrderHistoryService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderHistoryServiceTest {



    @Autowired
    OrderHistoryService orderHistoryService;


    static StoredDayOrder storedDayOrder;

    @Mock
    DayOrder dayOrder;

    @Mock
    be.abis.superdupersandwichorder.model.Order o1;
    @Mock
    be.abis.superdupersandwichorder.model.Order o2;
    @Mock
    be.abis.superdupersandwichorder.model.Order o3;
    @Mock
    Order o4;

    @Mock
    SessionDTO ses1;
    @Mock SessionDTO ses2;

    @Mock
    Course c1;
    @Mock Course c2;

    @Mock Person p1;
    @Mock Person p2;
    @Mock Person p3;

    @Mock Sandwich s1;
    @Mock Sandwich s2;

    @Mock Menu m1;
    @Mock SandwichCompany sc1;


    @BeforeAll
    static void setup() {
        SandwichCompany c = new SandwichCompany();
        c.setCompanyName("Vleugels");
        storedDayOrder = new StoredDayOrder();
        storedDayOrder.setSandwichCompany(c);
        storedDayOrder.setOrderListSize(5);
        storedDayOrder.setDate(LocalDate.now());
        storedDayOrder.setId(1);
        storedDayOrder.setDayTotal(40.00);
        storedDayOrder.setSessionID(1);
    }

    @Test
    //@Transactional
    public void addOrderTest() throws IOException, OrderAlreadyRegisteredException {
        List<Order> mockList = new ArrayList<>();
        when(this.p1.getFirstName()).thenReturn("John");
        when(this.p2.getFirstName()).thenReturn("Lemon");
        when(this.o1.getPersonWhoOrdered()).thenReturn(p1);
        when(this.o2.getPersonWhoOrdered()).thenReturn(p2);
        when(this.s1.getPrice()).thenReturn(7.50);
        when(this.s2.getPrice()).thenReturn(7.50);
        when(this.o1.getOrderedSandwich()).thenReturn(s1);
        when(this.o2.getOrderedSandwich()).thenReturn(s2);
        when(this.c1.getCourseName()).thenReturn("Java basics");
        when(this.ses1.getSessionId()).thenReturn(1);
        when(this.ses2.getSessionId()).thenReturn(2);
        when(this.ses1.getCourse()).thenReturn(c1);
        when(this.c2.getCourseName()).thenReturn("Java basics");
        when(this.ses2.getCourse()).thenReturn(c2);
        when(this.o2.getSession()).thenReturn(ses1);
        when(this.o1.getSession()).thenReturn(ses2);
        mockList.add(o1);
        mockList.add(o2);
        when(this.sc1.getCompanyName()).thenReturn("Vleugels");
        when(this.sc1.getId()).thenReturn(1);
        when(this.m1.getSandwichCompany()).thenReturn(sc1);

        when(this.dayOrder.getDate()).thenReturn(LocalDate.now());
        when(this.dayOrder.getDayMenu()).thenReturn(m1);
        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        when(this.dayOrder.getDayTotal()).thenReturn(15.00);


        orderHistoryService.addDayOrder(dayOrder);
        assertEquals(2, orderHistoryService.getAllStoredOrders().size());
    }

    @Test
    @Transactional
    public void addOrderThatAlreadyExistsShouldThrowExceptionTest() throws OrderAlreadyRegisteredException, IOException {
        orderHistoryService.addDayOrder(dayOrder);
        System.out.println(orderHistoryService.getAllStoredOrders().get(0).getId());
        assertThrows(OrderAlreadyRegisteredException.class, ()->orderHistoryService.addDayOrder(dayOrder));
    }

    @Test
    @Transactional
    public void findOrderByIDTest() throws DayOrderNotFoundException, OrderAlreadyRegisteredException, IOException {
        assertEquals(10, orderHistoryService.findDayOrderByID(1).getOrderListSize());
    }

    @Test
    @Transactional
    public void findOrderByIDThrowsExceptionTest() {
        assertThrows(DayOrderNotFoundException.class, ()-> orderHistoryService.findDayOrderByID(99));
    }

    @Test
    @Transactional
    public void findOrderByDateTest() throws DayOrderNotFoundException, OrderAlreadyRegisteredException, IOException {
        LocalDate testDate = LocalDate.of(2022,10,25);
        assertEquals(10, orderHistoryService.findDayOrderByDate(testDate).getOrderListSize());
    }

    @Test
    public void findOrderByDateThrowsExceptionTest() {
        assertThrows(DayOrderNotFoundException.class, ()-> orderHistoryService.findDayOrderByDate(LocalDate.of(3023,9,21)));
    }

    @Test
    @Transactional
    public void findOrderBySessionIDTest() throws DayOrderNotFoundException, OrderAlreadyRegisteredException, IOException {
        assertEquals(10, orderHistoryService.getAllOrdersBySessionByID(1).get(0).getOrderListSize());
    }

    @Test
    public void findOrderBySessionIDThrowsExceptionTest() {
        assertThrows(DayOrderNotFoundException.class,()->orderHistoryService.getAllOrdersBySessionByID(999));
    }

 /*   @Test
    @Transactional
    public void deleteDayOrderTest() throws IOException, OrderNotFoundException, OrderAlreadyRegisteredException {
        int listSize = orderHistoryService.getAllStoredOrders().size();
        orderHistoryService.addDayOrder(storedDayOrder);
        orderHistoryService.deleteDayOrder(storedDayOrder);
        assertEquals(listSize, orderHistoryService.getAllStoredOrders().size());
    }*/
}

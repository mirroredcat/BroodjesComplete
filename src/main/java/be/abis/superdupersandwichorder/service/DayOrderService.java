package be.abis.superdupersandwichorder.service;



import be.abis.superdupersandwichorder.exceptions.OrderAlreadyExistsException;
import be.abis.superdupersandwichorder.exceptions.OrderNotFoundException;
import be.abis.superdupersandwichorder.model.DayOrder;
import be.abis.superdupersandwichorder.model.Order;

import java.util.List;

public interface DayOrderService {

    Order findOrder(String firstName, String LastName) throws OrderNotFoundException;
    void addOrder(Order o) throws OrderAlreadyExistsException;
    void updateOrder(Order o);
    void deleteOrder(Order o);
    double calculateDayTotal();
    void printDayOrder();
    String printMenu();
    DayOrder getDayOrder();
    void setDayOrder(DayOrder dayO);

    DayOrder newDayOrder(String sandwichCompanyName);

    List<Order> getAllOrders();


}

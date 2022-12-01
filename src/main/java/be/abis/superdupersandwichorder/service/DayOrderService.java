package be.abis.superdupersandwichorder.service;



import be.abis.superdupersandwichorder.dto.OrderDTO;
import be.abis.superdupersandwichorder.exceptions.CannotSetMenuException;
import be.abis.superdupersandwichorder.exceptions.OrderAlreadyExistsException;
import be.abis.superdupersandwichorder.exceptions.OrderNotFoundException;
import be.abis.superdupersandwichorder.model.DayOrder;
import be.abis.superdupersandwichorder.model.Order;
import be.abis.superdupersandwichorder.model.OrderPreferencesRequestBody;
import be.abis.superdupersandwichorder.model.SandwichCompanyRequestBody;

import java.util.List;

public interface DayOrderService {

    Order findOrder(String firstName, String LastName) throws OrderNotFoundException;
    void addOrder(Order o) throws OrderAlreadyExistsException;
    public Order updateOrder(int personId, OrderPreferencesRequestBody orderPrefs);
    public void deleteOrder(int personId);
    double calculateDayTotal();
    void printDayOrder();
    String printMenu();
    DayOrder getDayOrder();
    void setDayOrder(DayOrder dayO);

    DayOrder newDayOrder(SandwichCompanyRequestBody sandwichCompanyName) throws CannotSetMenuException;

    List<OrderDTO> getAllOrders();


}

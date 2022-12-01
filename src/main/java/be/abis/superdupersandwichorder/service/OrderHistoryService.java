package be.abis.superdupersandwichorder.service;



import be.abis.superdupersandwichorder.exceptions.DayOrderNotFoundException;
import be.abis.superdupersandwichorder.exceptions.OrderAlreadyRegisteredException;
import be.abis.superdupersandwichorder.exceptions.OrderNotFoundException;
import be.abis.superdupersandwichorder.model.StoredDayOrder;
import be.abis.superdupersandwichorder.model.DayOrder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface OrderHistoryService {
    void addDayOrder(DayOrder dayOrder) throws IOException, OrderAlreadyRegisteredException;

    void deleteDayOrder(StoredDayOrder storedDayOrder) throws IOException, OrderNotFoundException;

    StoredDayOrder findDayOrderByDate(LocalDate localDate) throws DayOrderNotFoundException;

    StoredDayOrder findDayOrderByID(int id) throws DayOrderNotFoundException;

    List<StoredDayOrder> getAllStoredOrders();

    List<StoredDayOrder> getAllOrdersBySessionByID(int id) throws DayOrderNotFoundException;
}

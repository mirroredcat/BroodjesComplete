package be.abis.superdupersandwichorder.repository;



import be.abis.superdupersandwichorder.exceptions.DayOrderNotFoundException;
import be.abis.superdupersandwichorder.exceptions.OrderAlreadyRegisteredException;
import be.abis.superdupersandwichorder.model.StoredDayOrder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface OrderHistoryRepository {

    void addDayOrder(StoredDayOrder storedDayOrder) throws IOException, OrderAlreadyRegisteredException;

    void deleteDayOrder(StoredDayOrder storedDayOrder) throws IOException;

    StoredDayOrder findDayOrderByDate(LocalDate localDate) throws DayOrderNotFoundException;

    StoredDayOrder findDayOrderByID(int id) throws DayOrderNotFoundException;

    StoredDayOrder convertToOrderObj(String attr);

    LocalDate convertDateToObj(String attr);

    List<StoredDayOrder> getDayOrderHistoryList();
}

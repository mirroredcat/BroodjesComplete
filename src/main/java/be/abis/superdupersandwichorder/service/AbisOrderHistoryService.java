package be.abis.superdupersandwichorder.service;


import be.abis.superdupersandwichorder.exceptions.DayOrderNotFoundException;
import be.abis.superdupersandwichorder.exceptions.OrderAlreadyRegisteredException;
import be.abis.superdupersandwichorder.model.StoredDayOrder;
import be.abis.superdupersandwichorder.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbisOrderHistoryService implements OrderHistoryService{

    @Autowired
    OrderHistoryRepository orderHistoryRepository;


    public AbisOrderHistoryService() {
    }

    @Override
    public void addDayOrder(StoredDayOrder storedDayOrder) throws IOException, OrderAlreadyRegisteredException {
        orderHistoryRepository.addDayOrder(storedDayOrder);
    }
    @Override
    public void deleteDayOrder(StoredDayOrder storedDayOrder) throws IOException {
        orderHistoryRepository.deleteDayOrder(storedDayOrder);
    }
    @Override
    public StoredDayOrder findDayOrderByDate(LocalDate localDate) throws DayOrderNotFoundException {
        return orderHistoryRepository.findDayOrderByDate(localDate);
    }
    @Override
    public StoredDayOrder findDayOrderByID(int id) throws DayOrderNotFoundException {
      return  orderHistoryRepository.findDayOrderByID(id);
    }

    @Override
    public List<StoredDayOrder> getAllStoredOrders() {
      return  orderHistoryRepository.getDayOrderHistoryList();
    }

    @Override
    public List<StoredDayOrder> getAllOrdersBySessionByID(int id) throws DayOrderNotFoundException {
        List<StoredDayOrder> foundOrders = getAllStoredOrders().stream()
                .filter(storedDayOrder -> storedDayOrder.getSessionID()==id)
                .collect(Collectors.toList());

        if (foundOrders.isEmpty()) {
            throw new DayOrderNotFoundException("No orders found");
        }

        return foundOrders;
    }

}

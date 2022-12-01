package be.abis.superdupersandwichorder.service;


import be.abis.superdupersandwichorder.exceptions.DayOrderNotFoundException;
import be.abis.superdupersandwichorder.exceptions.OrderAlreadyRegisteredException;
import be.abis.superdupersandwichorder.exceptions.OrderNotFoundException;
import be.abis.superdupersandwichorder.mapper.DayOrderToStoredDayOrderMapper;
import be.abis.superdupersandwichorder.model.StoredDayOrder;
import be.abis.superdupersandwichorder.model.DayOrder;
import be.abis.superdupersandwichorder.repository.JpaOrderRepository;
import be.abis.superdupersandwichorder.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbisOrderHistoryService implements OrderHistoryService{



    @Autowired
    JpaOrderRepository jpaOrderRepository;


    public AbisOrderHistoryService() {
    }

    @Override
    @Transactional
    public void addDayOrder(DayOrder dayOrder) throws IOException, OrderAlreadyRegisteredException {

        if (jpaOrderRepository.findStoredDayOrderByDate(dayOrder.getDate())!=null) {
            throw new OrderAlreadyRegisteredException("DayOrder already exists");
        } else {
            List<StoredDayOrder> storedDayOrderList = DayOrderToStoredDayOrderMapper.transformToStoredDayOrder(dayOrder);
            for (StoredDayOrder storedDayOrder : storedDayOrderList) {
                jpaOrderRepository.addDayOrder(storedDayOrder.getDate(), storedDayOrder.getSessionID(), storedDayOrder.getOrderListSize(),
                        storedDayOrder.getSandwichCompany().getId(), storedDayOrder.getDayTotal());
            }
        }
    }
    @Override
    public void deleteDayOrder(StoredDayOrder storedDayOrder) throws IOException, OrderNotFoundException {
       if (jpaOrderRepository.findStoredDayOrderByDate(storedDayOrder.getDate())!=null) {
           jpaOrderRepository.delete(storedDayOrder);
       } else {
           throw new OrderNotFoundException("Order not found");
       }

    }
    @Override
    public StoredDayOrder findDayOrderByDate(LocalDate localDate) throws DayOrderNotFoundException {
       if (jpaOrderRepository.findStoredDayOrderByDate(localDate)==null) {
           throw new DayOrderNotFoundException("Order not found");
       } else {
           return jpaOrderRepository.findStoredDayOrderByDate(localDate);
       }
    }
    @Override
    public StoredDayOrder findDayOrderByID(int id) throws DayOrderNotFoundException {

        if (jpaOrderRepository.existsById(id)) {
            return jpaOrderRepository.getById(id);
        } else {
            throw new DayOrderNotFoundException("Order not found");
        }
    }

    @Override
    public List<StoredDayOrder> getAllStoredOrders() {
        return  jpaOrderRepository.findAll();
    }

    @Override
    public List<StoredDayOrder> getAllOrdersBySessionByID(int id) throws DayOrderNotFoundException {
        List<StoredDayOrder> foundOrders = jpaOrderRepository.findAllOrdersBySessionId(id);

        if (foundOrders.isEmpty()) {
            throw new DayOrderNotFoundException("No orders found");
        } else {
            return foundOrders;
        }
    }


/*        List<StoredDayOrder> foundOrders = getAllStoredOrders().stream()
                .filter(storedDayOrder -> storedDayOrder.getSessionID()==id)
                .collect(Collectors.toList());

        if (foundOrders.isEmpty()) {
            throw new DayOrderNotFoundException("No orders found");
        }

        return foundOrders;
    }*/

}

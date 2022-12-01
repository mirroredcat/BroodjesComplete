package be.abis.superdupersandwichorder.mapper;


import be.abis.sessionapi.model.Session;
import be.abis.superdupersandwichorder.dto.SessionDTO;
import be.abis.superdupersandwichorder.model.StoredDayOrder;
import be.abis.superdupersandwichorder.model.DayOrder;
import be.abis.superdupersandwichorder.model.Sandwich;
import be.abis.superdupersandwichorder.model.Order;
import be.abis.superdupersandwichorder.model.Person;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DayOrderToStoredDayOrderMapper {


    public static List<StoredDayOrder> transformToStoredDayOrder(DayOrder dayOrder) {

        List<StoredDayOrder> totalStoredDayOrders = new ArrayList<>();
        List<Order> orderList = dayOrder.getOrderList();
        Map<SessionDTO, List<Sandwich>> map = orderList.stream()
                .collect(Collectors.groupingBy(Order::getSession, Collectors.mapping(Order::getOrderedSandwich, Collectors.toList())));


        for (SessionDTO session : map.keySet()) {
            StoredDayOrder storedDayOrder = new StoredDayOrder();
            double totalPricePerSession = 0;
            int orderListSize = 0;
            for (Sandwich sandwich :map.get(session)) {
                orderListSize++;
                totalPricePerSession = totalPricePerSession + sandwich.getPrice();
            }
            storedDayOrder.setSessionID(session.getSessionId());
            storedDayOrder.setDate(dayOrder.getDate());
            storedDayOrder.setOrderListSize(orderListSize);
            storedDayOrder.setSandwichCompany(dayOrder.getDayMenu().getSandwichCompany());
            storedDayOrder.setDayTotal(totalPricePerSession);
            totalStoredDayOrders.add(storedDayOrder);

        }

        return totalStoredDayOrders;
    }
}

package be.abis.superdupersandwichorder.service;



import be.abis.superdupersandwichorder.dto.SessionDTO;
import be.abis.superdupersandwichorder.exceptions.OrderAlreadyExistsException;
import be.abis.superdupersandwichorder.exceptions.OrderNotFoundException;
import be.abis.superdupersandwichorder.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class AbisDayOrderService implements DayOrderService{

    @Autowired
    @Qualifier("orderTemplate")
    private RestTemplate rt;

    private String baseUrlSessions="http://localhost:8080/sessions/api/today";
    private String baseUrlMenu = "http://localhost:8000/menu/api";

    DayOrder dayO = new DayOrder();

    public DayOrder getDayOrder() {
        return dayO;
    }

    public void setDayOrder(DayOrder dayO) {this.dayO = dayO;}


    @Override
    public DayOrder newDayOrder(String sandwichCompanyName) {
        List<SessionDTO> sessions;

        ResponseEntity re = rt.getForEntity(baseUrlSessions, SessionDTO[].class);
        SessionDTO[] list = (SessionDTO[])re.getBody();
        sessions = Arrays.asList(list);

        ResponseEntity re2 = rt.getForEntity(baseUrlMenu, Menu.class);
        Menu todaysMenu = (Menu)re2.getBody();

        List<Order> orderList = new ArrayList<>();

        dayO.setDate(LocalDate.now());
        dayO.setDayMenu(todaysMenu);



        for(SessionDTO s:sessions){
            for(Student st:s.getStudentList()){
                Order newOrder = new Order();
                newOrder.setPersonWhoOrdered(st);
                newOrder.setSession(s);
                orderList.add(newOrder);
            }
            Order newOrder = new Order();
            newOrder.setPersonWhoOrdered(s.getCourse().getStaff());
            newOrder.setSession(s);
            orderList.add(newOrder);
        }

        dayO.setOrderList(orderList);

        return dayO;
    }

    @Override
    public List<Order> getAllOrders() {
        return dayO.getOrderList();
    }

    @Override
    public Order findOrder(String firstName, String lastName) throws OrderNotFoundException {
        return dayO.getOrderList().stream()
                .filter(o -> o.getPersonWhoOrdered().getFirstName().equals(firstName)
                        && o.getPersonWhoOrdered().getLastName().equals(lastName))
                .findFirst().orElseThrow(() -> new OrderNotFoundException(firstName + " " + lastName + " has not ordered today"));
    }

    @Override
    public void addOrder(Order o) throws OrderAlreadyExistsException {
        boolean foundO = dayO.getOrderList().stream()
                .anyMatch(order -> order.getPersonWhoOrdered().equals(o.getPersonWhoOrdered()));
        if (!foundO){
            dayO.getOrderList().add(o);
            dayO.setDayTotal(dayO.getDayTotal()+o.getOrderedSandwich().getPrice());
        } else {
            throw new OrderAlreadyExistsException(o.getPersonWhoOrdered().getFirstName() + " " + o.getPersonWhoOrdered().getLastName() + " has already ordered today");
        }
    }

    @Override
    public void updateOrder(Order o) {
        Order foundO = dayO.getOrderList().stream()
                .filter(or -> or.getPersonWhoOrdered().equals(o.getPersonWhoOrdered()))
                .findAny().get();

        dayO.getOrderList().add(o);
        dayO.getOrderList().remove(foundO);


        /*
        foundO.setOrderedSandwich(o.getOrderedSandwich());
        foundO.setBreadOption(o.getBreadOption());
        foundO.setVegetableOption(o.getVegetableOption());
        if (o.getComment()!=null) foundO.setComment(o.getComment());

         */
    }

    @Override
    public void deleteOrder(Order o) {
        dayO.getOrderList().remove(o);
    }

    @Override
    public double calculateDayTotal() {
        double dayTotal = dayO.getOrderList().stream()
                .mapToDouble(o -> o.getOrderedSandwich().getPrice())
                .sum();
        dayO.setDayTotal(dayTotal);
        return dayTotal;
    }

    @Override
    public void printDayOrder() {
        int counter = 1;
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("/temp/javacourses/Broodjes/printedOrder.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("dd-MM-yyyy");


        pw.printf("%-15s  %-5s \n", dayO.getDate().format(dtm), dayO.getDayMenu().getSandwichCompany().getCompanyName());
        pw.println("");

        Map<String, List<Order>> ordersBySession = dayO.getOrderList().stream()
                .collect(Collectors.groupingBy(or -> or.getSession().getCourse().getCourseName()));

        for(String s: ordersBySession.keySet()){
            pw.println(s);
            pw.println("");
            pw.printf("%-5s|%-15s|%-15s|%-15s|%-15s|%-15s", "No.","Name","Sandwich","Bread type","Veggie option","Comments" );
            pw.println("");

            for(Order o:ordersBySession.get(s)){
                pw.println(counter +  "    |" +o);
                counter += 1;
            }
            pw.println("----------------------------------------------");
            pw.println("");
        }
        pw.close();

    }

    @Override
    public String printMenu() {
        return dayO.getDayMenu().toString();
    }
}

package be.abis.superdupersandwichorder.service;



import be.abis.superdupersandwichorder.dto.OrderDTO;
import be.abis.superdupersandwichorder.dto.SessionDTO;
import be.abis.superdupersandwichorder.exceptions.CannotSetMenuException;
import be.abis.superdupersandwichorder.exceptions.OrderAlreadyExistsException;
import be.abis.superdupersandwichorder.exceptions.OrderNotFoundException;
import be.abis.superdupersandwichorder.mapper.OrderMapper;
import be.abis.superdupersandwichorder.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
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
    private String baseUrlMenu = "http://localhost:8000/api/menu";

    DayOrder dayO = new DayOrder();

    public DayOrder getDayOrder() {
        return dayO;
    }

    public void setDayOrder(DayOrder dayO) {this.dayO = dayO;}


    @Override
    public DayOrder newDayOrder(SandwichCompanyRequestBody sandwichCompanyName) throws CannotSetMenuException {

        dayO.setDate(LocalDate.now());
        dayO.setOrderList(createOrderList());

        Menu todaysMenu = setDayMenu(sandwichCompanyName);
        dayO.setDayMenu(todaysMenu);


        setDayOrder(dayO);
        System.out.println("Day Order has "+ dayO.getOrderList().size()+ " orders");
        System.out.println("DayOrder's date is "+ dayO.getDate());
        System.out.println("DayOrder menu company is " + dayO.getDayMenu().getSandwichCompany());

        return dayO;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return dayO.getOrderList().stream()
                .map(o -> OrderMapper.toDto(o))
                .collect(Collectors.toList());
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
    public Order updateOrder(int personId, OrderPreferencesRequestBody orderPrefs) {
        Order foundO = dayO.getOrderList().stream()
                .filter(or -> or.getPersonWhoOrdered().getId()==personId)
                .findAny().get();



        foundO.setOrderedSandwich(findSandwich(orderPrefs.getOrderedSandwichName()));
        foundO.setBreadOption(orderPrefs.getBreadOption());
        foundO.setVegetableOption(orderPrefs.getVegetableOption());
        if (orderPrefs.getComment()!=null) foundO.setComment(orderPrefs.getComment());

        return foundO;
    }

    @Override
    public void deleteOrder(int personId) {
        Order foundO = dayO.getOrderList().stream()
                        .filter(o -> o.getPersonWhoOrdered().getId()==personId)
                                .findFirst().get();
        dayO.getOrderList().remove(foundO);
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
        int fileCounter = 1;
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("/temp/javacourses/Broodjes/printedOrder"+fileCounter + ".txt"));
            fileCounter += 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("dd-MM-yyyy");


        pw.printf("%-15s  %-5s \n", dayO.getDate().format(dtm), dayO.getDayMenu().getSandwichCompany().getCompanyName());
        pw.println("");

        Map<String, List<Order>> ordersBySession = dayO.getOrderList().stream()
                .filter(o -> !o.getOrderedSandwich().getSandwichName().equals("No Sandwich Today"))
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


    private Menu setDayMenu(SandwichCompanyRequestBody sandwichCompanyName) throws CannotSetMenuException {
        ResponseEntity<Menu> re = null;
        try {
            re = rt.postForEntity(baseUrlMenu, sandwichCompanyName, Menu.class);
        } catch (HttpStatusCodeException e){
            throw new CannotSetMenuException("Menu not set. Check company name and try again.");
        }

        return re.getBody();
    }

    private List<Order> createOrderList(){
        List<SessionDTO> sessions;

        ResponseEntity re = rt.getForEntity(baseUrlSessions, SessionDTO[].class);
        SessionDTO[] list = (SessionDTO[])re.getBody();
        sessions = Arrays.asList(list);

        List<Order> orderList = new ArrayList<>();

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
        return orderList;
    }

    private Sandwich findSandwich(String sandwichName){
        SandwichNameRequestBody snrb = new SandwichNameRequestBody();
        snrb.setSandwichName(sandwichName);

        ResponseEntity<Sandwich> re = rt.postForEntity(baseUrlMenu + "/find-sandwich", snrb, Sandwich.class);
        return re.getBody();
    }
}

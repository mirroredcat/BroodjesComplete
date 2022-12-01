package be.abis.superdupersandwichorder.controller;

import be.abis.superdupersandwichorder.dto.OrderDTO;
import be.abis.superdupersandwichorder.exceptions.CannotSetMenuException;
import be.abis.superdupersandwichorder.exceptions.OrderAlreadyRegisteredException;
import be.abis.superdupersandwichorder.mapper.OrderMapper;
import be.abis.superdupersandwichorder.model.*;
import be.abis.superdupersandwichorder.service.DayOrderService;
import be.abis.superdupersandwichorder.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order-sandwiches")
public class OrderController {

    @Autowired
    DayOrderService dayOrderService;

    @Autowired
    OrderHistoryService orderHistoryService;


/*

    public ResponseEntity<? extends Object> saveDayOrder(@RequestBody DayOrder )
    @PostMapping(path = "/all", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void addPerson(@Valid  @RequestBody Person person) throws IOException, PersonAlreadyExistsException {


*/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createNewDayOrder(@RequestBody SandwichCompanyRequestBody sandwichCompanyName) throws CannotSetMenuException {
        dayOrderService.newDayOrder(sandwichCompanyName);
        return ("Today's menu is from " + sandwichCompanyName.getSandwichCompanyName());
    }

    // change for frotend
    @GetMapping("/menu")
    public String printDayMenu(){
        return dayOrderService.printMenu();
    }

    /*

    this is for adding orders to an empty day order (people not in courses)
    @PostMapping("/orders")
    public Order addNewOrder(@RequestBody Order order){
        Order o = dayOrderService.addOrder(order);

    }

     */

    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders(){
        return dayOrderService.getAllOrders();
    }


    //TODO make no sandwich an option
    @PutMapping("/orders/persons/{id}")
    public OrderDTO updateOrder(@PathVariable("id") int personId, @RequestBody OrderPreferencesRequestBody orderPrefs){
        Order o =dayOrderService.updateOrder(personId, orderPrefs );
        return OrderMapper.toDto(o);
    }

    // only for staff, fully deletes order object from day order list
    @DeleteMapping("/orders/persons/{id}")
    public void deleteOrder(@PathVariable("id") int personId){
        dayOrderService.deleteOrder(personId);
    }
    // prints order to file and saves to db(saving doesn't work as intended yet)
    //path: "temp/javacourses/Broodjes/"
    @GetMapping("/orders/print")
    public void printDayOrder() throws OrderAlreadyRegisteredException, IOException {
        dayOrderService.printDayOrder();
        orderHistoryService.addDayOrder(dayOrderService.getDayOrder());
    }

    //---------------
    // maybe it's not strictly necessary
    public Order findOrderByPerson(int id){
    return null;
    }

    // order history stuff

    public void saveDayOrder(){

    }

    public List<StoredDayOrder> findOrdersByDate(LocalDate date){
        return null;
    }

    public List<StoredDayOrder> findOrdersBySession(int sessionId){
        return null;
    }


}

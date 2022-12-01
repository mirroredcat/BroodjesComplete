package be.abis.superdupersandwichorder.controller;

import be.abis.superdupersandwichorder.service.DayOrderService;
import be.abis.superdupersandwichorder.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//@RestController
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




}

package be.abis.menuapi.controller;


import be.abis.menuapi.model.*;

import be.abis.menuapi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("")
public class MenuController {

    @Autowired
    MenuService ms;

    @GetMapping("")
    public Menu getMenuOfTheDay() {return ms.getMenuOfTheDay();}

    @PostMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Menu setMenuOfTheDay(@RequestBody SandwichCompanyRequestBody sandwichCompanyName){
        ms.setMenuOfTheDay(sandwichCompanyName.getSandwichCompanyName());
        return ms.getMenuOfTheDay();
   }


    @PostMapping("/find-sandwich")
    public Sandwich findSandwichByName(@RequestBody SandwichNameRequestBody sandwichName){
        return ms.findSandwichInMenuOfDay(sandwichName.getSandwichName());
    }


    @GetMapping("/find-sandwich/{id}")
    public Sandwich findSandwichById(@PathVariable int id){
        return ms.findSandwich(id);
    }

    @PostMapping("/sandwiches")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSandwich(@RequestBody Sandwich s){
        //s.setSandwichCompany(SandwichCompany.fromString(s.getSandwichCompany()));
        ms.addSandwich(s);
    }

    @PatchMapping("/sandwiches/change-price/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changePrice(@PathVariable int id, @RequestBody Price newPrice){
        ms.updateSandwichPrice(id, newPrice.getNewPrice());
    }

    @PatchMapping("sandwiches/change-ingredients/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeIngredientList(@PathVariable int id, @RequestBody Ingredients newIngredients ){
        ms.updateSandwichIngredients(id, newIngredients.getNewIngredients());
    }

    @DeleteMapping("/sandwiches/{id}")
    public void deleteSandwich(@PathVariable int id){
        ms.deleteSandwich(id);
    }


}

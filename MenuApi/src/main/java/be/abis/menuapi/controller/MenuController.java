package be.abis.menuapi.controller;


import be.abis.menuapi.dto.*;
import be.abis.menuapi.exceptions.SandwichAlreadyExistsException;
import be.abis.menuapi.exceptions.SandwichNotFoundException;
import be.abis.menuapi.mapper.SandwichMapper;
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
    public Sandwich findSandwichByName(@RequestBody SandwichNameRequestBodyDTO sandwichName) throws SandwichNotFoundException {
        return ms.findSandwichInMenuOfDay(sandwichName.getSandwichName());
    }


    @GetMapping("/find-sandwich/{id}")
    public Sandwich findSandwichById(@PathVariable int id) throws SandwichNotFoundException {
        return ms.findSandwich(id);
    }

    @PostMapping("/sandwiches")
    @ResponseStatus(HttpStatus.CREATED)
    public SandwichDTO addSandwich(@RequestBody SandwichCreationDTO s) throws SandwichAlreadyExistsException {
        Sandwich sandwich = SandwichMapper.toSandwich(s);
        ms.addSandwich(sandwich);
        return SandwichMapper.toDto(sandwich);
    }

    @PatchMapping("/sandwiches/change-price/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changePrice(@PathVariable int id, @RequestBody Price newPrice) throws SandwichNotFoundException {
        ms.updateSandwichPrice(id, newPrice.getNewPrice());
    }

    @PatchMapping("sandwiches/change-ingredients/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeIngredientList(@PathVariable int id, @RequestBody IngredientsDTO newIngredients ) throws SandwichNotFoundException {
        ms.updateSandwichIngredients(id, newIngredients.getNewIngredients());
    }

    @DeleteMapping("/sandwiches/{id}")
    public void deleteSandwich(@PathVariable int id) throws SandwichNotFoundException {
        ms.deleteSandwich(id);
    }


}

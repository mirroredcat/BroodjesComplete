package be.abis.menuapi.mapper;

import be.abis.menuapi.dto.IngredientsDTO;
import be.abis.menuapi.model.Ingredients;

public class IngredientsMapper {

    public static IngredientsDTO toDto(Ingredients i){
        IngredientsDTO nid = new IngredientsDTO();
        nid.setNewIngredients(i.getNewIngredients());

        return nid;
    }

}

package be.abis.menuapi.mapper;

import be.abis.menuapi.dto.PriceDTO;
import be.abis.menuapi.model.Price;

public class PriceMapper {

    public static PriceDTO toDto(Price p){
        PriceDTO npd = new PriceDTO();
        npd.setNewPrice(String.valueOf(p.getNewPrice()));
        return npd;
    }

}

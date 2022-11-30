package be.abis.menuapi.mapper;

import be.abis.menuapi.dto.SandwichCompanyRequestBodyDTO;
import be.abis.menuapi.dto.SandwichNameRequestBodyDTO;
import be.abis.menuapi.model.SandwichCompanyRequestBody;
import be.abis.menuapi.model.SandwichNameRequestBody;

public class SandwichNameRequestBodyMapper {

    public static SandwichNameRequestBodyDTO toDto(SandwichNameRequestBody snrb){
        SandwichNameRequestBodyDTO nsrbd = new SandwichNameRequestBodyDTO();
        nsrbd.setSandwichName(snrb.getSandwichName());
        return nsrbd;
    }

}

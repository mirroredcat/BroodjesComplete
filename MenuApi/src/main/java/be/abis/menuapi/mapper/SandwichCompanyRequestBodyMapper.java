package be.abis.menuapi.mapper;

import be.abis.menuapi.dto.SandwichCompanyRequestBodyDTO;
import be.abis.menuapi.model.SandwichCompanyRequestBody;

public class SandwichCompanyRequestBodyMapper {

    public static SandwichCompanyRequestBodyDTO toDto(SandwichCompanyRequestBody scrb){
        SandwichCompanyRequestBodyDTO nsd = new SandwichCompanyRequestBodyDTO();
        nsd.setSandwichCompanyName(scrb.getSandwichCompanyName());
        return nsd;
    }
}

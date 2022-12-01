package be.abis.menuapi.mapper;

import be.abis.menuapi.dto.SandwichCreationDTO;
import be.abis.menuapi.dto.SandwichDTO;
import be.abis.menuapi.model.Sandwich;
import be.abis.menuapi.model.SandwichCompany;

public class SandwichMapper {

    public static SandwichDTO toDto(Sandwich s){
        SandwichDTO nsd = new SandwichDTO();

        nsd.setId(s.getId());
        nsd.setSandwichName(s.getSandwichName().trim());
        nsd.setSandwichCompanyName(s.getSandwichCompany().getCompanyName().trim());
        nsd.setCategory(s.getCategory().trim());
        nsd.setPrice(s.getPrice().toString());
        nsd.setIngredients(s.getIngredients().trim());

        return nsd;
    }
    // if the comp of the sandwich is new, error and prompt to a company creation
    // repo inside a mapper?
    public static Sandwich toSandwich(SandwichCreationDTO sandDto){
        Sandwich s = new Sandwich();
        s.setSandwichName(sandDto.getSandwichName().trim());
        if (sandDto.getSandwichCompanyId()!=null) {
            SandwichCompany sc = new SandwichCompany();
            sc.setId(Integer.parseInt(sandDto.getSandwichCompanyId().trim()));
            sc.setCompanyName(sandDto.getSandwichCompanyName().trim());
            sc.setAddress(sandDto.getSandwichCompanyAddress().trim());
            sc.setPhoneNr(sandDto.getSandwichCompanyTelNo().trim());

            s.setSandwichCompany(sc);
        }

        s.setCategory(sandDto.getCategory().trim());
        s.setPrice(Double.parseDouble(sandDto.getPrice().trim()));
        s.setIngredients(sandDto.getIngredients().trim());

        return s;
    }

}

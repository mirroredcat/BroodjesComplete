package be.abis.superdupersandwichorder.mapper;

import be.abis.superdupersandwichorder.dto.OrderDTO;
import be.abis.superdupersandwichorder.model.Order;

public class OrderMapper {

    public static OrderDTO toDto (Order o){

        OrderDTO nod = new OrderDTO();
        nod.setPersonId(o.getPersonWhoOrdered().getId());
        nod.setPersonName(o.getPersonWhoOrdered().getFirstName().trim() + " " + o.getPersonWhoOrdered().getLastName().trim());
        nod.setCourseName(o.getSession().getCourse().getCourseName().trim());
        if (o.getOrderedSandwich() == null){
            nod.setSandwichName("Not yet ordered");
        } else {
            nod.setSandwichName(o.getOrderedSandwich().getSandwichName().trim());
            nod.setBreadOption(o.getBreadOption().trim());
            nod.setVeggieOption(o.getVegetableOption().trim());
            if (o.getComment() != null) nod.setComments(o.getComment().trim());
        }
        return nod;
    }

}

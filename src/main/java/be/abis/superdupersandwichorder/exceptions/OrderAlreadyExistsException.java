package be.abis.superdupersandwichorder.exceptions;

public class OrderAlreadyExistsException extends Exception{
    public OrderAlreadyExistsException(String message) {
        super(message);
    }
}

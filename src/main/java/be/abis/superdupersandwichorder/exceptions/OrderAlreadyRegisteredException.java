package be.abis.superdupersandwichorder.exceptions;

public class OrderAlreadyRegisteredException extends Exception {
    public OrderAlreadyRegisteredException(String message) {
        super(message);
    }
}

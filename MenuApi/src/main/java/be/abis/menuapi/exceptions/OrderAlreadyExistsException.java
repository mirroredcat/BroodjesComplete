package be.abis.menuapi.exceptions;

public class OrderAlreadyExistsException extends Exception{
    public OrderAlreadyExistsException(String message) {
        super(message);
    }
}

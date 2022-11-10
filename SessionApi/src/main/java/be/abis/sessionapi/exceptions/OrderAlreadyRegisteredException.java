package be.abis.sessionapi.exceptions;

public class OrderAlreadyRegisteredException extends Exception {
    public OrderAlreadyRegisteredException(String message) {
        super(message);
    }
}

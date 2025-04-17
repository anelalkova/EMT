package mk.finki.ukim.mk.lab1_b.model.exceptions;

public class NoRoomsAvailableException extends RuntimeException {
    public NoRoomsAvailableException() {
        super("There are no rooms available");
    }
}

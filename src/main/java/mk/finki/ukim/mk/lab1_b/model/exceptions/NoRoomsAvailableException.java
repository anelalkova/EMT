package mk.finki.ukim.mk.lab1_b.model.exceptions;

public class NoRoomsAvailableException extends RuntimeException {
    public String NoRoomsAvailableException() {
        return "There are no rooms available";
    }
}

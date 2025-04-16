package mk.finki.ukim.mk.lab1_b.model.exceptions;

public class InvalidNumberRoomsException extends RuntimeException {
    public String InvalidNumberRoomsException() {
        return "The accommodation doesn't have that many rooms.";
    }
}

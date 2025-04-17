/*
package mk.finki.ukim.mk.lab1_b.config;

import mk.finki.ukim.mk.lab1_b.dto.ErrorResponse;
import mk.finki.ukim.mk.lab1_b.model.exceptions.InvalidNumberRoomsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidNumberRoomsException.class)
    public ResponseEntity<?> handleInvalidRooms(InvalidNumberRoomsException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }
}
*/

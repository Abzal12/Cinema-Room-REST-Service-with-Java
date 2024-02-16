package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<OutOfBoundsError> handleOutOfBoundsError() {
        OutOfBoundsError body = new OutOfBoundsError("The number of a row or a column is out of bounds!");
        return new ResponseEntity<>(body, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(BookerErrorException.class)
    public ResponseEntity<BookedError> handleBookerError() {
        BookedError body = new BookedError("The ticket has been already purchased!");
        return new ResponseEntity<>(body, HttpStatus.valueOf(400));
    }
}

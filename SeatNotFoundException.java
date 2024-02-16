package cinema;

import org.springframework.http.HttpStatusCode;

public class SeatNotFoundException extends RuntimeException {

public SeatNotFoundException(String message) {
    super(message);
}
}

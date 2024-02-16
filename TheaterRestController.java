package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class TheaterRestController {

    Room room = new Room();
    @GetMapping("/seats")
    public Room getSeats() {
        return room;
    }

    @PostMapping("/purchase")
    public Seat buyTickets(@RequestBody Seat seatChoice) {
        Seat seatResult = null;
        if (seatChoice.getColumn() > 9 || seatChoice.getColumn() < 1
                || seatChoice.getRow() > 9 || seatChoice.getRow() < 1) {

            throw new SeatNotFoundException("123");
        }

        boolean isFound = false;
        for (Seat seat : room.getSeats()) {
            if (seat.getRow() == seatChoice.getRow() && seat.getColumn() == seatChoice.getColumn()) {
                seatResult = seat;
                ArrayList<Seat> newSeats = room.getSeats();
                newSeats.remove(seat);
                room.setSeats(newSeats);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new BookerErrorException("123");
        }
        return seatResult;
    }

}

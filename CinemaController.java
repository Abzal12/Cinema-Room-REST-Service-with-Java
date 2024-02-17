package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CinemaController {
    Cinema cinema = new Cinema();
    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinema;
    }
    @PostMapping("/purchase")
    public ResponseEntity purchaseSeat(@RequestBody Seat seat) {
        if (seat.getRow() < 1 || seat.getRow() > cinema.getRows() ||
                seat.getColumn() < 1 || seat.getColumn() > cinema.getColumns()) {
            return ResponseEntity.badRequest().body(
                    new ConcurrentHashMap<>(Map.of("error", "The number of a row or a column is out of bounds!")));
        }
        
        for (Seat seat1 : cinema.getSeats()) {
            if (seat.getRow() == seat1.getRow() && seat.getColumn() == seat1.getColumn()) {
                Seat seatResult = seat1;
                List<Seat> newSeats = cinema.getSeats();
                newSeats.remove(seat1);
                cinema.setSeats(newSeats);
                return ResponseEntity.ok(seatResult);
            }
        }
        return ResponseEntity.badRequest().body(
                new ConcurrentHashMap<>(Map.of("error", "The ticket has been already purchased!")));
    }
}

package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CinemaController {
    Cinema cinema = new Cinema();
    Ticket ticket = new Ticket();
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
            if (seat.getRow() == seat1.getRow() && seat.getColumn() == seat1.getColumn() && !seat1.isReserved()) {
                seat1.setReserved(true);
                ticket.setSeat(seat1);
                ticket.setToken(seat1.getToken());
//                return ResponseEntity.ok().body(
//                        new ConcurrentHashMap<>(Map.of(
//                               "ticket", seat1, "token", seat1.getToken()))
//                );
                return ResponseEntity.ok(ticket);
            }
        }
        return ResponseEntity.badRequest().body(
                new ConcurrentHashMap<>(Map.of("error", "The ticket has been already purchased!")));
    }

    @PostMapping("/return")
    public ResponseEntity refundTicket(@RequestBody Token tokenForRefund) {
        for (Seat seat : cinema.getSeats()) {
            if(Objects.equals(seat.getToken().getToken(), tokenForRefund.getToken()) && seat.isReserved())
            {
                seat.setReserved(false);
                return ResponseEntity.ok().body(new ConcurrentHashMap<>(Map.of("ticket", seat)));
            }
        }
        return ResponseEntity.badRequest().body(
                new ConcurrentHashMap<>(Map.of("error", "Wrong token!"))
        );
    }
}

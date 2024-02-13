package cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheaterRestController {

    Room room = new Room();
    @GetMapping("/seats")
    public Room getSeats() {
        return room;
    }
}

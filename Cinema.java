package cinema;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private int rows = 9;
    private int columns = 9;
    private List<Seat> seats = new ArrayList<>();

    {
        for (int i = 1; i <= this.rows; i++) {
            for (int j = 1; j <= this.columns; j++) {
                this.seats.add(new Seat(i, j));
            }
        }
    }

    public Cinema() {
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void deleteFromAvailableSeats(Seat seat) {
        this.seats.remove(seat);
    }

//    public boolean isFound(Seat seat) {
//        boolean isFound2 = false;
//        for (Seat seat1 : seats) {
//            if(seat.getRow() == seat1.getRow() && seat.getColumn() == seat1.getColumn()) {
//                isFound2 = true;
//                seat.setPrice(seat.getRow() <= 4 ? 10 : 8);
//                break;
//            }
//        }
//        return isFound2;
//    }
}

package cinema;

import java.util.ArrayList;

public class Room {
    private int rows = 9;
    private int columns = 9;
    private ArrayList<Seat> seats = getAvailableSeats();

    private ArrayList<Seat> getAvailableSeats() {
        ArrayList<Seat> seats2 = new ArrayList<>();
        for (int i = 1; i <= this.rows; i++) {
            for (int j = 1; j <= this.columns; j++) {
                seats2.add(new Seat(i, j));
            }
        }
        return seats2;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }
}

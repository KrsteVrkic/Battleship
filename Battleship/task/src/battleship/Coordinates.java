package battleship;

import java.util.Scanner;

public class Coordinates {

    int row;
    int col;

    public Coordinates(String coordinates) {

        char letter = coordinates.charAt(0);
        int row = letter - 'A' + 1;
        int col = Integer.parseInt(coordinates.substring(1));

        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

}

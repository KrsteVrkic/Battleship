package battleship;

import java.nio.file.LinkPermission;
import java.util.Scanner;


public class Player {

    Scanner scanner = new Scanner(System.in);
    String name;
    public Player(String name) {
        this.name = name;
    }
    public ShipPosition placeShip(ShipsFromHell ship) {
        return placeShip("Enter the coordinates of the %s (%d cells):\n".formatted(ship.name, ship.cells), ship);
    }
    public ShipPosition placeShip(String message, ShipsFromHell ship) {

        String name = ship.getShipName();
        int size = ship.getShipCells();

        Coordinates startPoint;
        Coordinates endPoint;

        System.out.println(message);

        do {
            String inputStart = scanner.next();
            startPoint = new Coordinates(inputStart);
            String inputEnd = scanner.next();
            endPoint = new Coordinates(inputEnd);
        } while (!validateSize(startPoint, endPoint, size, name));
        ShipPosition result = new ShipPosition();
        result.start = startPoint;
        result.end = endPoint;
        return result;
    }


    public boolean validateSize(Coordinates start, Coordinates end, int size, String name) {
        int startRow = start.getRow();
        int startCol = start.getCol();
        int endRow = end.getRow();
        int endCol = end.getCol();
        int shipSize;

        if (startRow == endRow) {
            shipSize = Math.abs(endCol - startCol) + 1;
        } else if (startCol == endCol) {
            shipSize = Math.abs(endRow - startRow) + 1;
        } else if (Math.abs(endRow - startRow) == Math.abs(endCol - startCol)) {
            shipSize = Math.abs(endRow - startRow) + 1;
        } else {
            System.out.println("\nError! Wrong ship location! Try again:");
            return false;
        }

        if (shipSize == size) {
            return true;
        } else {
            System.out.printf("\nError! Wrong length of the %s! Try again:\n\n", name);
            return false;
        }
    }

    public ShotPosition takeAShot() {

        Coordinates shotAttempt;

        String shot = scanner.next();
        shotAttempt = new Coordinates(shot);
        ShotPosition result = new ShotPosition();
        result.shot = shotAttempt;
        return result;
    }
}


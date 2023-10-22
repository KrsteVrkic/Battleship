package battleship;

import java.io.IOException;

enum Turns {
    PLAYER_ONE, PLAYER_TWO, GAME_OVER;
}



public class Game {

    int playerOneShipsDead = 0;
    int playerTwoShipsDead = 0;
    ShipsFromHell destroyer = new ShipsFromHell(2, "Destroyer");
    ShipsFromHell cruiser = new ShipsFromHell(3, "Cruiser");
    ShipsFromHell submarine = new ShipsFromHell(3,"Submarine");
    ShipsFromHell battleship = new ShipsFromHell(4, "Battleship");
    ShipsFromHell carrier = new ShipsFromHell(5, "Aircraft Carrier");

    public void start() {

        WarZoneGrid warZone = new WarZoneGrid();
        WarZoneGrid warZoneTwo = new WarZoneGrid();

        Player playerOne = new Player("Chris");
        Player playerTwo = new Player("Isaac");

        ShipsFromHell[] ships = {carrier, battleship, submarine, cruiser, destroyer};

        System.out.println("Player 1, place your ships on the game field\n");

        warZone.printField();

        for (ShipsFromHell ship : ships) {

            ShipPosition p = playerOne.placeShip(ship);

            while(true) {

                if (!warZone.isNearAnotherShip(p)) break;

                p = playerOne.placeShip("\nError! You placed it too close to another one. Try again:\n", ship);
            }
            warZone.updateArray(p);
            warZone.printField();
        }

        promptEnterKey();

        System.out.println("Player 2, place your ships on the game field\n");

        for (ShipsFromHell ship : ships) {

            ShipPosition p = playerTwo.placeShip(ship);

            while(true) {

                if (!warZoneTwo.isNearAnotherShip(p)) break;

                p = playerTwo.placeShip("\nError! You placed it too close to another one. Try again:\n", ship);
            }
            warZoneTwo.updateArray(p);
            warZoneTwo.printField();
        }

        promptEnterKey();

        Turns state = Turns.PLAYER_ONE;

        while (playerOneShipsDead != 5 || playerTwoShipsDead !=5) {

            if (state == Turns.PLAYER_ONE) {
                warZoneTwo.printFog();
                System.out.println("---------------------");
                warZone.printField();
                System.out.println("Player 1, it's your turn:");
                ShotPosition p = playerTwo.takeAShot();
                switch (warZoneTwo.checkShot(p)) {
                    case "hit":
                        System.out.println("\nYou hit a ship!\n");
                        promptEnterKey();
                        state = Turns.PLAYER_TWO;
                        break;
                    case "miss":
                        System.out.println("\nYou missed!");
                        promptEnterKey();
                        state = Turns.PLAYER_TWO;
                        break;
                    case "error":
                        System.out.println("\nError! You entered the wrong coordinates! Try again:");
                        break;
                    case "shipSunk":
                        if (playerTwoShipsDead == 5) {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                            state = Turns.GAME_OVER;
                            break;
                        } else {
                            System.out.println("\nYou sank a ship!");
                            playerTwoShipsDead++;
                            promptEnterKey();
                            state = Turns.PLAYER_TWO;
                        }
                        break;
                }
            } else if (state == Turns.PLAYER_TWO) {
                warZone.printFog();
                System.out.println("---------------------");
                warZoneTwo.printField();
                System.out.println("Player 2, it's your turn:");
                ShotPosition p = playerOne.takeAShot();
                switch (warZone.checkShot(p)) {
                    case "hit":
                        System.out.println("\nYou hit a ship!\n");
                        promptEnterKey();
                        state = Turns.PLAYER_ONE;
                        break;
                    case "miss":
                        System.out.println("\nYou missed!");
                        promptEnterKey();
                        state = Turns.PLAYER_ONE;
                        break;
                    case "error":
                        System.out.println("\nError! You entered the wrong coordinates! Try again:");
                        break;
                    case "shipSunk":
                        if (playerOneShipsDead == 5) {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                            state = Turns.GAME_OVER;
                            break;
                        } else {
                            System.out.println("\nYou sank a ship!");
                            playerOneShipsDead++;
                            promptEnterKey();
                            state = Turns.PLAYER_ONE;
                        }
                        break;
                }
            } else {
                break;
            }
        }
    }



    public void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package battleship;

public class WarZoneGrid {

    char[][] field;



    WarZoneGrid() {

        field = new char[12][12];

        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                field[i][j] = '~';
            }
        }
    }

    public void printField() {

        System.out.print("  ");
        for (int columnIndex = 1; columnIndex <= 10; columnIndex++) {
            System.out.print(columnIndex + " ");
        }
        System.out.println();

        char rowIndex = 'A';

        for (int i = 1; i < 11 ; i++) {
            System.out.print(rowIndex + " ");
            rowIndex++;
            for (int j = 1; j < 11; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isNearAnotherShip(ShipPosition p) {

        if (p.start.row < p.end.row) {

            for (int i = p.start.row; i <= p.end.row; i++) {
                for (int j = p.start.col; j <= p.end.col; j++) {

                    if (field[i][j + 1] == 'O') {
                        return true;
                    }

                    if (field[i][j - 1] == 'O') {
                        return true;
                    }

                    if (field[i + 1][j] == 'O') {
                        return true;
                    }

                    if (field[i - 1][j] == 'O') {
                        return true;
                    }
                }
            }
        } else {
            for (int i = p.end.row; i <= p.start.row; i++) {
                for (int j = p.start.col; j <= p.end.col; j++) {

                    if (field[i][j + 1] == 'O') {
                        return true;
                    }

                    if (field[i][j - 1] == 'O') {
                        return true;
                    }

                    if (field[i + 1][j] == 'O') {
                        return true;
                    }

                    if (field[i - 1][j] == 'O') {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public void updateArray(ShipPosition p) {

        char shipMarker = 'O';

        if (p.start.col > p.end.col || p.start.row > p.end.row) {
            for (int i = p.end.row; i <= p.start.row; i++) {
                for (int j = p.end.col; j <= p.start.col; j++) {
                    field[i][j] = shipMarker;
                }
            }
        } else {
            for (int i = p.start.row; i <= p.end.row; i++) {
                for (int j = p.start.col; j <= p.end.col; j++) {
                    field[i][j] = shipMarker;
                }
            }
        }



    }

    public String checkShot(ShotPosition p) {

        if (p.shot.col >= 11 || p.shot.row >= 11) {
            return "error";
        }

        if (field[p.shot.row][p.shot.col] == 'O') {
            field[p.shot.row][p.shot.col] = 'X';
            if (field[p.shot.row][p.shot.col + 1] != 'O' && field[p.shot.row][p.shot.col - 1] != 'O' && field[p.shot.row + 1][p.shot.col] != 'O' && field[p.shot.row - 1][p.shot.col] != 'O') {

                return "shipSunk";
            } else {
                return "hit";
            }
        } else if (field[p.shot.row][p.shot.col] == '~') {
            field[p.shot.row][p.shot.col] = 'M';
            return "miss";
        }
        return "hit";
    }

    public void printFog() {

        System.out.print("  ");
        for (int columnIndex = 1; columnIndex <= 10; columnIndex++) {
            System.out.print(columnIndex + " ");
        }
        System.out.println();

        char rowIndex = 'A';

        for (int i = 1; i < 11 ; i++) {
            System.out.print(rowIndex + " ");
            rowIndex++;
            for (int j = 1; j < 11; j++) {
                if (field[i][j] == 'O') {
                    System.out.print('~' + " ");
                } else {
                    System.out.print(field[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}

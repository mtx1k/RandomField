import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Field {
    private final int fieldSize = 10;
    private int[][] field;

    public Field() {
        firstFill();
        randomFieldFill();
    }

    public int[][] getField() {
        return field;
    }

    private void firstFill() {
        field = new int[fieldSize][fieldSize];
        for (int[] cell : field) {
            Arrays.fill(cell, 9);
        }
    }

    private void randomFieldFill() {

        for (int i = 4; i > 0; i--) {
            for (int j = 1; j <= 5 - i; j++) {
                createShip(i);
            }
        }
        finishFill();
    }

    private void finishFill() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if(field[i][j] == 9) {
                    field[i][j] = 0;
                }
            }
        }
    }

    private void createShip(int length) {
        Random random = new Random();
        while (true) {
            int x = random.nextInt(0, fieldSize);
            int y = random.nextInt(0, fieldSize);
            if (createShipField(length, x, y)) {
                break;
            }
        }
    }

    private boolean createShipField(int length, int x, int y) {
        Random random = new Random();
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        int state = random.nextInt(1,5);
        if (state == 1) {
            if (x + length < fieldSize) {
                for (int i = 0; i < length; i++) {
                    if (field[x + i][y] != 9) {
                        return false;
                    }
                    coordinates.add(new Coordinate(x + i, y));
                }
            }
        } else if (state == 2) {
            if (y + length < fieldSize) {
                for (int i = 0; i < length; i++) {
                    if (field[x][y + i] != 9) {
                        return false;
                    }
                    coordinates.add(new Coordinate(x, y + i));
                }
            }
        }else if(state == 3){
            if (x - length >= 0) {
                for (int i = 0; i < length; i++) {
                    if (field[x - i][y] != 9) {
                        return false;
                    }
                    coordinates.add(new Coordinate(x - i, y));
                }
            }
        } else {
            if (y - length >= 0) {
                for (int i = 0; i < length; i++) {
                    if (field[x][y - i] != 9) {
                        return false;
                    }
                    coordinates.add(new Coordinate(x, y - i));
                }
            }
        }
        if (coordinates.isEmpty()) {
            return false;
        }
        addCoordinatesInField(coordinates, length);
        fillAroundShip(coordinates);
        return true;
    }

    private void addCoordinatesInField(ArrayList<Coordinate> coordinates, int length) {
        for (Coordinate coordinate : coordinates) {
            field[coordinate.getX()][coordinate.getY()] = length;
        }
    }

    private void fillAroundShip(ArrayList<Coordinate> coordinates) {
        ArrayList<Coordinate> coordinatesAroundShip = getCoordinatesAroundShip(coordinates);
        for (Coordinate coordinate : coordinatesAroundShip) {
            field[coordinate.getX()][coordinate.getY()] = 0;
        }
    }

    public ArrayList<Coordinate> getCoordinatesAroundShip(ArrayList<Coordinate> shipCoordinates) {
        ArrayList<Coordinate> newCoordinates = new ArrayList<>();
        for (Coordinate coordinate : shipCoordinates) {
            int x = coordinate.getX();
            int y = coordinate.getY();
            int xStart = x - 1;
            int xFinish = x + 1;
            int yStart = y - 1;
            int yFinish = y + 1;
            if (x - 1 < 0) {
                xStart = x;
            } else if (x + 1 > 9) {
                xFinish = x;
            }
            if (y - 1 < 0) {
                yStart = y;
            } else if (y + 1 > 9) {
                yFinish = y;
            }
            for (int i = xStart; i <= xFinish; i++) {
                for (int j = yStart; j <= yFinish; j++) {
                    if (field[i][j] == 9) {
                        newCoordinates.add(new Coordinate(i, j));
                    }
                }
            }
        }
        return newCoordinates;
    }
}

import java.util.Scanner;

public class Puzzle15Matrix {
    Scanner keyboard = new Scanner(System.in);

    // public constant variables
    public static final int SIZE = 4; 
    public static final int EMPTY = 0;
    // private variables
    private int[][] tiles;
    private int emptyCol;
    private int emptyRow;
    private Configuration configuration;

    // fill in the array 
    public int[][] fillTiles() {
        int randomNumber;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == this.emptyRow && j == this.emptyCol) {
                    this.tiles[i][j] = 0; // which indicated that it is empty
                } else {
                    randomNumber = ((int)( Math.random() * 14 ) + 1);
                    for (int e = 0; e < i; e++) {
                        for (int f = 0; f < j; f++) {
                            while(this.tiles[e][f] == randomNumber) {
                                randomNumber = ((int)( Math.random() * 14 ) + 1);
                            }
                        }
                    }
                    this.tiles[i][j] = randomNumber;
                }
            }
        }  
        return this.tiles; 
    }
    // constructors
    public Puzzle15Matrix() {
        this.tiles = new int[SIZE][SIZE];
        this.emptyCol = 0;
        this.emptyRow = 0;
        this.tiles = this.fillTiles();
    }

    public Puzzle15Matrix(int emptyCol, int emptyRow) {
        if ((emptyRow > SIZE -1 || emptyRow < 0) || (emptyCol > SIZE - 1 || emptyCol < 0)) {
            System.out.println("Error: Fatar erro ");
            System.out.println("Please note that the size of the board is " + SIZE + " * " + SIZE);
            System.exit(0);
        } else {
            this.emptyCol = emptyCol;
            this.emptyRow  = emptyRow;
            this.tiles = this.fillTiles();
        }
    }

    public Puzzle15Matrix(String format) {
        this.tiles = new int[SIZE][SIZE];
        // TODO: setup configuration and tiles
        this.configuration = new Configuration(format);
        this.configuration.initialise(this.tiles);
        // TODO: setup emptyCol and emptyRow
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.tiles[i][j] == 0) {
                    this.emptyRow  = i;
                    this.emptyCol = j;
                }
            }
        }
    }

    public Puzzle15Matrix(Puzzle15Matrix puzzleMatrix) {
        if (puzzleMatrix == null) {
            System.out.println("Error: fatal error");
            System.exit(0);
        } else {
            this.tiles = puzzleMatrix.getTiles();
            this.emptyCol = puzzleMatrix.getEmptyColumn();
            this.emptyRow = puzzleMatrix.getEmptyRow();
        }
    }

    // accessor
    public int[][] getTiles() {
        return this.tiles;
    }

    public int getEmptyColumn() {
        return this.emptyCol;
    }

    public int getEmptyRow() {
        return this.emptyRow;
    }

    public int getTile(int col, int row) {
        if (row < 0 || row >=SIZE) {
            System.out.println("Error: positon out of the board");
            System.exit(0);
        }
        if (col < 0 || col >= SIZE) {
            System.out.println("Error: position out of the board");
            System.exit(0);
        }
        return this.tiles[row][col];
    }

    // mutator
    public void setTile(int col, int row, int value) {
        if (row < 0 || row >=SIZE) {
            System.out.println("Error: positon out of the board");
            System.exit(0);
        }
        if (col < 0 || col >= SIZE) {
            System.out.println("Error: position out of the board");
            System.exit(0);
        }

        this.tiles[row][col] = value;
    }

    public void setEmptyColoumn(int newEmptyColoumn) {
        this.emptyCol = newEmptyColoumn;
    }

    public void setEmptyRow(int newEmptyRow) {
        this.emptyRow = newEmptyRow;
    }

    // print
    public void print() {
        System.out.println("The state of the whole board");
    }

    // move
    public void move(char direction) {
        switch(direction) {
            case 'U':
                // indicates sliding up the tile that is initially below the empty space,
                if (this.emptyRow != SIZE-1) {
                    this.tiles[emptyRow][emptyCol] = this.tiles[emptyRow + 1][emptyCol]; 
                    this.tiles[emptyRow + 1][emptyCol] = 0;
                    emptyRow++;
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            case 'D':
                // is for the tile above the empty space
                if (this.emptyRow != 0) {
                    this.tiles[emptyRow][emptyCol] = this.tiles[emptyRow - 1][emptyCol];
                    this.tiles[emptyRow - 1][emptyCol] = 0;
                    emptyRow--;         
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            case 'L':
                // is for the tile at the right of the empty space
                if (this.emptyCol != SIZE-1) {
                    this.tiles[emptyRow][emptyCol] = this.tiles[emptyRow][emptyCol + 1];
                    this.tiles[emptyRow][emptyCol + 1] = 0;
                    emptyCol++;
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            case 'R':
                // is for the tile at the left of the empty space
                if  (this.emptyCol != 0 ) {
                    this.tiles[emptyRow][emptyCol] = this.tiles[emptyRow][emptyCol - 1];
                    this.tiles[emptyRow][emptyCol - 1] = 0;
                    emptyCol--;
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            default:
                System.out.println("Error: " + direction + " doesn't exist"); 
                break;
        }
    }

    // is the game solved
    public boolean isSolved() {
        if (this.tiles[SIZE - 1][SIZE - 1] != 0) {
            return false;
        } else {
            int number = 1;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE ; j++) {
                    if (this.tiles[i][j] != number%16) {
                        return false;
                    }
                    number++;
                }
            }
            return true;
        }
    }

    public void printTiles() {
        System.out.println("---------------------");
        for (int i = 0; i < SIZE; i ++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.tiles[i][j] == 0) {
                    System.out.print(" | | ");
                } else {
                    System.out.print(" |" + this.tiles[i][j] + "| ");
                }
            }
            System.out.println();
            System.out.println("---------------------");
        }
    }


    public void play() {
        // printing the initial game board 
        this.printTiles();
        /*
            and then while the user inputs one
            of the characters ’U’, ’D’, ’L’, or ’R’, making the corresponding sliding move and
            printing the resulting game board; stops when the puzzle is solved or the user inputs
            ’q’
        */
        while(!this.isSolved()) {
            char direction = keyboard.next().charAt(0);
            if (direction == 'q') {
                System.exit(0);
            }
            this.move(direction);
            this.printTiles();
        }
        System.exit(0);
    }

    // toString, equals

    // check the equality of two matrixes
    public static boolean isEqual(int[][] m1, int[][] m2) {
    
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; i < SIZE; j++) {
                if (m1[i][j] != m2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean equals(Object puzzleMatrix) {
        if (puzzleMatrix == null) {
            return false;
        } else if (this.getClass() != puzzleMatrix.getClass()) {
            return false;
        } else {
            Puzzle15Matrix puzzle = (Puzzle15Matrix)puzzleMatrix;
            return (
                isEqual(this.tiles, puzzle.tiles) &&
                this.emptyCol == puzzle.emptyCol &&
                this.emptyRow == puzzle.emptyRow
            );
        }
    }

    // Your next task is to add a specification of a board that uses Strings The Format
    public String toString() {
        String stringFormat = " ";
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                stringFormat += (this.tiles[i][j] + " ");
            }
            stringFormat += " : ";
        }
        return  ("java Puzzle15Matrix \"" + stringFormat + "\"");
    }
}
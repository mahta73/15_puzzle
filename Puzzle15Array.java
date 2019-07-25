import java.util.Scanner;

public class Puzzle15Array {
    Scanner keyboard = new Scanner(System.in);

    public static final int SIZE = 4;
    public static final byte EMPTY = 0;
    private byte[] tiles;
    private int emptyPos;
    private Configuration configuration;

    // constructors
    // fill in the array 
    public void fillTiles() {
        int randSize = 15;
        byte[] rand2;
        byte[] rand = new byte[randSize];
        for (int i = 0; i < randSize; i++) {
            rand[i] = (byte)(i + 1);
        }

        byte randomNumber;
        byte num;
        for (int i = 0; i < SIZE * SIZE; i++) {
            if (i == this.emptyPos) {
                this.tiles[i] = 0;
            } else {
                randomNumber = (byte)(Math.random() * (randSize-1));
                this.tiles[i] = rand[randomNumber];
                num = this.tiles[i];
                randSize--;
                rand2 = new byte[randSize];

                for (int j = 0; j < randSize; j++) {
                    if (rand[j] >= this.tiles[i]) {
                        rand2[j] = rand[j+1];
                    } else {
                        rand2[j] = rand[j];
                    }
                }
                rand = new byte[randSize];
                for (int  f= 0; f < randSize; f++) {
                    rand[f] = rand2[f];
                }
            }
        }
    }
    // constructors
    public Puzzle15Array() {
        tiles = new byte[SIZE * SIZE];
        this.emptyPos = 0;
        this.fillTiles();
    }

    public Puzzle15Array(int emptyPos) {
        if (emptyPos > (SIZE * SIZE) -1 || emptyPos < 0) {
            System.out.println("Error: Fatar erro ");
            System.exit(0);
        } else {
            tiles = new byte[SIZE * SIZE];
            this.emptyPos = emptyPos;
            this.fillTiles();
        }
    }

    public Puzzle15Array(Puzzle15Array puzzleArray) {
        if (puzzleArray == null) {
            System.out.println("Error: fatal error");
            System.exit(0);
        } else {
            this.tiles = puzzleArray.getTiles();
            this.emptyPos = puzzleArray.getEmptyPos();
        }
    }

    public Puzzle15Array(String format) {
        tiles = new byte[SIZE * SIZE];
        // TODO: setup configuration and tiles
        this.configuration = new Configuration(format);
        this.configuration.initialise(this.tiles);
        // TODO: setup emptyCol and emptyRow
        for (int i = 0; i < SIZE * SIZE; i++) {
            if (this.tiles[i] == 0) {
                this.emptyPos = i;
            }
        }
    }

    // accessors
    public byte[] getTiles() {
        return this.tiles;
    }

    public byte getTile(int pos) {
        if (pos < 0 || pos >= SIZE) {
        System.out.println("Error: position out of the board");
        System.exit(0);
        }
        return tiles[pos];
    }

    public int getEmptyPos() {
        return this.emptyPos;
    }

    // mutators

    public void setTile(int pos, byte newValue) {
        if (pos > SIZE || pos < 0) {
            System.out.println("Error: position out of the board");
            System.exit(0);
        }
        this.tiles[pos] = newValue;
    }

    public void setTiles(byte[] newTiles) {
        this.tiles = newTiles;
    }

    public void setEmptyPos(int newEmptyPos) {
        this.emptyPos = newEmptyPos;
    }

    // state
    public void print() {
        System.out.println("state of the whole board");
    }

    // is the game solved
    public boolean isSolved() {
        if (this.tiles[(SIZE*SIZE)-1] != 0) {
            return false;
        } else {
            int number = 1;
            for (int i = 0; i < SIZE*SIZE; i++) {
                if (this.tiles[i] != number%16) {
                    return false;
                }
                number++;
            }
            return true;
        }
    }


    // move
    public void move(char direction) {
        switch(direction) {
            case 'U':
                // indicates sliding up the tile that is initially below the empty space,
                if (!(this.emptyPos+4 > SIZE*SIZE)) {
                    this.tiles[emptyPos] = this.tiles[emptyPos+4];
                    this.tiles[emptyPos+4] = 0;
                    emptyPos += 4;
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            case 'D':
                // is for the tile above the empty space
                if (!(this.emptyPos-4 < 0)) {
                    this.tiles[this.emptyPos] = this.tiles[this.emptyPos-4];
                    this.tiles[this.emptyPos-4] = 0;
                    emptyPos -= 4;
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            case 'L':
                // is for the tile at the right of the empty space
                if (!(this.emptyPos == (SIZE*SIZE)-1)) {
                    this.tiles[emptyPos] = this.tiles[emptyPos+1];
                    this.tiles[emptyPos+1] = 0;
                    emptyPos += 1;
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            case 'R':
                // is for the tile at the left of the empty space
                if (!(this.emptyPos-1 < 0)) {
                    this.tiles[emptyPos] = this.tiles[emptyPos-1];
                    this.tiles[emptyPos-1] = 0;
                    emptyPos -= 1;
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            default:
                System.out.println("Error: " + direction + " doesn't exist"); 
                break;
        }
    }

    public void printTiles() {
        for (int i = 0; i < this.tiles.length; i++) {
            if (i  % 4 == 0) {
                System.out.println();
                System.out.println("---------------------");
            }
            if (this.tiles[i] == 0) {
                System.out.print(" |  | ");
            } else {
                System.out.print(" |" + this.tiles[i] + "| ");
            }
                
        }
        System.out.println();
        System.out.println("---------------------");
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
}
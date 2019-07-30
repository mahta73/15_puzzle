import java.util.Scanner;

public class MatrixTiles extends Tiles {
    private byte[][] tiles;
    private int emptyCol;
    private int emptyRow;

    public MatrixTiles(String format) {
        super(format);
        this.tiles = new byte[this.getSize()][this.getSize()];
        this.getConfiguration().initialise(this);
        // TODO: initialise emptyCol and emptyRow
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (this.tiles[i][j] == 0) {
                    this.emptyRow = i;
                    this.emptyCol = j;
                }
            }
        }
    }

    public byte getTile(int row, int col) {
        return this.tiles[row][col];
    }

    public void setTile(int row, int col, byte newValue) {
        this.tiles[row][col] = newValue;
    }

    protected void moveImpl(Direction direction) {

    }

    // move
    public void move(Direction direction) {
        switch(direction) {
            case UP:
                // indicates sliding up the tile that is initially below the empty space,
                if (this.emptyRow != this.getSize()-1) {
                    this.tiles[emptyRow][emptyCol] = this.tiles[emptyRow + 1][emptyCol]; 
                    this.tiles[emptyRow + 1][emptyCol] = 0;
                    emptyRow++;
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            case DOWN:
                // is for the tile above the empty space
                if (this.emptyRow != 0) {
                    this.tiles[emptyRow][emptyCol] = this.tiles[emptyRow - 1][emptyCol];
                    this.tiles[emptyRow - 1][emptyCol] = 0;
                    emptyRow--;         
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            case LEFT:
                // is for the tile at the right of the empty space
                if (!(this.emptyCol == this.getSize()-1 && this.emptyRow == this.getSize()-1)) {
                    if ((this.emptyCol+1) % this.getSize() == 0) {
                        this.tiles[emptyRow][emptyCol] = this.tiles[++emptyRow][0];
                        this.emptyCol = 0;
                    } else {
                        this.tiles[emptyRow][emptyCol] = this.tiles[emptyRow][emptyCol + 1];
                        this.emptyCol++;
                    } 
                    this.tiles[emptyRow][emptyCol] = 0;  
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            case RIGHT:
                // is for the tile at the left of the empty space
                if  (!(this.emptyRow == 0 && this.emptyCol == 0)) {
                    if (this.emptyCol == 0) {
                        this.tiles[emptyRow][emptyCol] = this.tiles[--emptyRow][this.getSize()-1];
                        this.emptyCol = this.getSize()-1;
                    } else {
                        this.tiles[emptyRow][emptyCol] = this.tiles[emptyRow][emptyCol -1];
                        this.emptyCol--;
                    } 
                    this.tiles[emptyRow][emptyCol] = 0; 
                    // this.tiles[emptyRow][emptyCol] = this.tiles[emptyRow][emptyCol - 1];
                    // this.tiles[emptyRow][emptyCol - 1] = 0;
                    // emptyCol--;
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
        System.out.println("moves: " + this.getMoveCount());
        System.out.println("---------------------");
        for (int i = 0; i < this.getSize(); i ++) {
            for (int j = 0; j < this.getSize(); j++) {
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
        Scanner keyboard  = new Scanner(System.in);
        while(!this.isSolved()) {
            char direction = keyboard.next().charAt(0);
            this.increamentMoveCount();
            Direction d;
            switch(direction) {
                case '0':
                    d = Direction.UP;
                    this.move(d);
                    this.printTiles();
                    break;
                case '1':
                    d = Direction.DOWN;
                    this.move(d);
                    this.printTiles();
                    break;
                case '2':
                    d = Direction.LEFT;
                    this.move(d);
                    this.printTiles();
                    break;
                case '3':
                    d = Direction.RIGHT;
                    this.move(d);
                    this.printTiles();
                    break;
                case 'q':
                    System.exit(0);
                    break;
            } 
        }
        System.exit(0);
    }

    // check if the game is solved
    public boolean isSolved() {
        if (this.tiles[this.getConfiguration().getSize() - 1][this.getConfiguration().getSize() - 1] != 0) {
            return false;
        } else {
            int number = 1;
            for (int i = 0; i < this.getConfiguration().getSize(); i++) {
                for (int j = 0; j < this.getConfiguration().getSize() ; j++) {
                    if (this.tiles[i][j] != number%16) {
                        return false;
                    }
                    number++;
                }
            }
            return true;
        }
    }
    public static void main(String[] args) {
        MatrixTiles m = new MatrixTiles("4: 15 2 1 12 8 5 6 11 4 9 10 7 3 14 13 0");
        m.play();
    }    
}
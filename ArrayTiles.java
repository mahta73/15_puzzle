public class ArrayTiles extends Tiles {
    private byte[] tiles;
    private int emptyPos;

    public ArrayTiles(String format) {
        super(format);
        this.tiles = new byte[(int)Math.pow(this.getConfiguration().getSize(), 2)];
        this.getConfiguration().initialise(this);
        for (int i = 0; i < this.tiles.length; i++) {
            if (this.tiles[i] == 0) {
                this.emptyPos = i;
            }
        }
    }

    public void setTile(int row, int col, byte newValue) {
        // check if the col and row values are elligible
        if ( col < 0 || col > this.getSize() || row < 0 || row > this.getSize()) {
            System.out.println("Fatal Error(index): index cannot be greater than the array's size or less than zeroo");
            System.exit(0);
        }

        this.tiles[ row * this.getSize() + col] = newValue;
    }

    public void setTile(int pos, byte newValue) {
        this.tiles[pos] = newValue;
    }

    public byte getTile(int row, int col) {
        // check if the col and row values are elligible
        if ( col < 0 || col > this.getConfiguration().getSize() || row < 0 || row > this.getConfiguration().getSize()) {
            System.out.println("Fatal Error(index): index cannot be greater than the array's size or less than zeroo");
            System.exit(0);
        }
        return this.tiles[ row * this.getSize() + col];
    } 

    public byte getTile(int pos) {
        return this.tiles[pos];
    }

    // move
    protected void moveImpl(Direction direction) {
        switch(direction) {
            case UP:
                // indicates sliding up the tile that is initially below the empty space,
                if (!(this.emptyPos+this.getSize() >= this.tiles.length)) {
                    this.tiles[emptyPos] = this.tiles[emptyPos+this.getSize()];
                    this.tiles[emptyPos+this.getSize()] = 0;
                    emptyPos += this.getSize();
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            case DOWN:
                // is for the tile above the empty space
                if (!(this.emptyPos-this.getSize() < 0)) {
                    this.tiles[this.emptyPos] = this.tiles[this.emptyPos-this.getSize()];
                    this.tiles[this.emptyPos-this.getSize()] = 0;
                    emptyPos -= this.getSize();
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            case LEFT:
                // is for the tile at the right of the empty space
                if (!(this.emptyPos == (this.tiles.length)-1)) {
                    this.tiles[emptyPos] = this.tiles[emptyPos+1];
                    this.tiles[emptyPos+1] = 0;
                    emptyPos += 1;
                } else {
                    System.out.println("Error: Your are trying to move out of the board");
                }
                break;
            case RIGHT:
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

    // check if the game is the game solved
    public boolean isSolved() {
        if (this.tiles[this.tiles.length-1] != 0) {
            return false;
        } else {
            int number = 1;
            for (int i = 0; i < this.tiles.length; i++) {
                if (this.tiles[i] != number%16) {
                    return false;
                }
                number++;
            }
            return true;
        }
    }
}

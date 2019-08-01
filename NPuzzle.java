import java.util.Scanner;

public class NPuzzle {
    private Tiles tiles;

    public NPuzzle(String format) {
        this.tiles = new MatrixTiles(format);
    }

    public NPuzzle(Tiles tiles) {
        if (tiles == null) {
            System.out.println("Fatal error: tiles object cannot be null");
            System.exit(0);
        }
        this.tiles = tiles;
    }

    public void play() {
        this.print();
        while(!this.tiles.isSolved()) {
            Scanner keyboard = new Scanner(System.in);
            char direction = keyboard.next().charAt(0);

            Tiles.Direction d;
            switch(direction) {
                case '0':
                    d = Tiles.Direction.UP;
                    this.tiles.move(d);
                    this.print();
                    break;
                case '1':
                    d = Tiles.Direction.DOWN;
                    this.tiles.move(d);
                    this.print();
                    break;
                case '2':
                    d = Tiles.Direction.LEFT;
                    this.tiles.move(d);
                    this.print();
                    break;
                case '3':
                    d = Tiles.Direction.RIGHT;
                    this.tiles.move(d);
                    this.print();
                    break;
                case 'q':
                    System.exit(0);
                    break;
            } 
        }
        System.exit(0);
    }

    public void print() {
        System.out.println("-moves: " + this.tiles.getMoveCount());
        System.out.println("---------------------");
        int row = 0;
        int col = 0;
        String[] values = this.tiles.getConfiguration().getData().split(" ");

        for (int i = 1; i <= values.length; i++) {
            if (!(values[i-1].equals(":"))) {
                if ( (col % this.tiles.getSize() == 0) && col != 0) {
                    row++;
                    col = 0;
                    System.out.println();
                    System.out.println("---------------------");
                }
                if (this.tiles.getTile(row, col) == 0) {
                    System.out.print(" | | ");
                    col++;
                } else {
                    System.out.print(" |" + this.tiles.getTile(row, col) + "| ");
                    col++;
                }
            }
        }
        System.out.println();
        System.out.println("---------------------");
    }

    public static void main(String[] args) {
        Tiles t = new ArrayTiles("3: 1 2 3 : 4 5 6 : 7 8 0");
        NPuzzle np = new NPuzzle(t);
        np.play();
    } 
}
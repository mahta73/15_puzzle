public class Configuration extends Puzzle15Matrix {
    private String data;

    public Configuration(String format) {
        //TODO: initialise the only field of this class using ’format’;
        this.data = format;
    }

    //TODO: write a public ’get’ method for the field above;
    public String getData() {
        return this.data;
    }

    public int[][] initialise(int[][] tiles) {
        //TODO: update the elements in the 2D array representing the values
        // of ’tiles’ as expressed by the contents of the field ’data’.
        String[] array = this.data.split(" ");
        int row = 0;
        int coloumn = 0;
        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals(":")) {
                if (coloumn % 4 == 0 && coloumn != 0) {
                  row++; 
                  coloumn = 0; 
                }
                tiles[row][coloumn] = Integer.parseInt(array[i]);
                coloumn++;
            }
        }
        return tiles;
    }

    public byte[] initialise(byte[] tiles) {
        String[] array = this.data.split(" ");
        for (int i = 0; i < array.length; i++) {
            tiles[i] = Byte.parseByte(array[i]);
        }
        return tiles;
    }
}
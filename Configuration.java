public class Configuration {
    private String data = "";

    // constructors
    public Configuration() {
        this.data = "";
    }

    public Configuration(String format) {
        //TODO: initialise the only field of this class using ’format’;
        this.data = format;
    }

    //TODO: write a public ’get’ method for the field above;
    public String getData() {
        return this.data;
    }

    // set method for the data
    public void setData(String newData) {
        this.data = newData;
    }

    public void initialise(int[][] tiles) {
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
            } else {
                continue;
            }
        }
    }

    public void initialise(byte[] tiles) {
        String[] array = this.data.split(" ");
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            tiles[index] = Byte.parseByte(array[i]); 
            index++;  
        }
    }
}
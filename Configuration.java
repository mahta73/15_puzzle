public class Configuration {
    private int size;
    private String data;

    public Configuration(String format) {
        this.data = format.substring(3);
        this.size = Character.getNumericValue(format.charAt(0));
    }

    public int getSize() {
        return this.size;
    }

    public String getData() {
        return this.data;
    }

    public void initialise(Tiles t) {
        // initialise tiles
        if (t == null) {
            System.out.println("NULL: fatal error: tiles object cannot be null");
            System.exit(0);
        } 
        String[] values = this.data.split(" ");
        int row = 0;
        int col = 0;
        for (int i = 1; i <= values.length ; i++) {
            if (!(values[i-1].equals(":"))) {
                if ((col % this.size == 0)  && col != 0) {
                    row++;
                    col = 0;
                }
                t.setTile(row, col, Byte.parseByte(values[i-1]));
                col++;
            }
        }
    }
} 
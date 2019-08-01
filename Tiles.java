public abstract class Tiles {
    public static final byte EMTPY = 0;
    private int moves;
    private Configuration configuration;

    public enum Direction {
        UP, 
        DOWN,
        LEFT,
        RIGHT
    }

    public Tiles(String format) {
        this.configuration = new Configuration(format);
        this.moves = 0;
    }

    // abstract accessors
    public abstract byte getTile(int row, int col);

    // non-abstract accessors
    protected Configuration getConfiguration() {
        return this.configuration;
    }

    public int getSize() {
        return this.configuration.getSize();
    }

    public int getMoveCount() {
        return this.moves;
    }

    // abstract mutators
    public abstract void setTile(int row, int col, byte newValue);
     
    // abstract methods
    protected abstract void moveImpl(Direction direction);
    public abstract boolean isSolved();

    // non-abstact methods
     public void move(Direction direction) {
        this.moveImpl(direction);
        this.moves++;
    }
      
}
public class Puzzle15MatrixDemo {
      public static void main(String[] args){
        // using no-arg constructor
        // Puzzle15Matrix matrix1 = new Puzzle15Matrix();
        // matrix1.play();

        // using Puzzle15Matrix(int emptyCol, int emptyRow) 
        // Puzzle15Matrix matrix2 = new Puzzle15Matrix(2, 3);
        // matrix2.play();

        // using Puzzle15Matrix(String format) constructor
        // Puzzle15Matrix matrix3 = new Puzzle15Matrix("1 2 3 : 4 5 6 : 7 8 9 10 : 11 12 13 : 14 0 15");
        // matrix3.play();

        // Puzzle15Array matrix4 = new Puzzle15Array();
        // matrix4.play();

        // Puzzle15Array matrix5 = new Puzzle15Array(3);
        // matrix5.play();

        Puzzle15Array matrix6 = new Puzzle15Array("1 2 3 4 5 6 7 8 9 10 11 12 13 14 0 15");
        matrix6.play();
    }
}
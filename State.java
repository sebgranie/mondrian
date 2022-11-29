import java.util.ArrayList;
import java.util.LinkedList;
// import java.lang.Math;

/*
 * Class to represent a state, which consist of a mondrian puzzle
 */

/*
 * un état c'est un tableau 2D d'integer. Ces integer témoignent de leur appartenance
 * à un rectangle.
 *
 *
 */
public class State {
    private LinkedList<Rectangle> rectList;
    private int size;
    // private ArrayList<ArrayList<ArrayList<Integer>>> grid;
    private int[][] grid;

    public State(int new_size) {
        rectList = new LinkedList<Rectangle>();
        size = new_size;
        // grid = new ArrayList<ArrayList<ArrayList<Integer>>>(size);
        grid = new int[size][size];

    }

    public int getSize() {
        return size;
    }

    public void setSize(int n) {
        size = n;
    }

    public LinkedList<Rectangle> getRectList() {
        return rectList;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int x, int y, int n) {
        grid[x][y] = n;
    }

    public boolean Ints(int[] grid, int i) {
        for (int a = 0; a < grid.length; a++) {
            if (grid[a] == i)
                return true;
        }
        return false;
    }

    // public int size() {
    // return size;
    // }

    // public ArrayList<ArrayList<ArrayList<Integer>>> getGrid() {
    // return grid;
    // }

    // public void setGrid(int x, int y, int n) {
    // grid.get(x).set(y, n);
    // }

    // public String toString() {
    // for (int i = 0; i < size; i++) {

    // }
    // }

    public int getMondrian(LinkedList<Rectangle> rectList) {
        int largest = 0;
        int smallest = size * size; // Math.pow(size,2)
        for (Rectangle rect : rectList) {
            if (rect.getArea() > largest) {
                largest = rect.getArea();
            }
            if (rect.getArea() < smallest) {
                smallest = rect.getArea();
            }
        }
        return (largest - smallest);
    }

    // public ArrayList<int[]> myRectangles(int n) {
    // int[] res;
    // int[] areas = new LinkedList<>();
    // int i = 1; // minimum width of a rectangle, starting at 0 is no sense
    // while (i <= (int) (n / 2)) {
    // int j = 1;
    // while (j <= n) {
    // if (res.contains([i,j]) && )
    // }
    // }
    // return res;
    // }

}

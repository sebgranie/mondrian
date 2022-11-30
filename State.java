import java.util.ArrayList;
import java.util.LinkedList;
// import java.lang.Math;

/*
 * Class to represent a state, which consist of a mondrian puzzle
 */
public class State {
    private ArrayList<Rectangle> rectList;
    private int size;
    private int[][] grid;

    public State(int new_size) {
        rectList = new ArrayList<Rectangle>();
        size = new_size;
        grid = new int[size][size];

    }

    public int getSize() {
        return size;
    }

    public void setSize(int n) {
        size = n;
    }

    public ArrayList<Rectangle> getRectList() {
        return rectList;
    }

    public void addRectList(Rectangle rect) {
        rectList.add(rect);
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

    public boolean contains(int[] array, int key) {
        for (int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }

    public int getMondrian(ArrayList<Rectangle> rectList) {
        int largest = 0;
        int smallest = size * size;
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

}

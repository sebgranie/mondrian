import java.util.ArrayList;
import java.util.LinkedList;

// import java.lang.Math;

/*
 * Class to represent a state, which consist of a mondrian puzzle
 */
public class State implements Comparable<State> {
    private ArrayList<Rectangle> rectList;
    private int size;
    private int[][] grid;
    private int mondrian;
    private int depth;
    private int globalScore;
    private boolean visited;

    public State(int new_size) {
        rectList = new ArrayList<Rectangle>();
        size = new_size;
        grid = new int[size][size];
    }

    @Override
    public int compareTo(State s) {
        if (mondrian < s.getMondrian(rectList)) {
            return -1;
        } else {
            return 1;
        }
    }

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean b) {
        visited = b;
    }

    public int getGlobalScore() {
        return globalScore;
    }

    public void setGlobalScore(int n) {
        globalScore = n;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int n) {
        depth = n;
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

    public void resetRectList() {
        rectList = new ArrayList<Rectangle>();
    }

    // public ArrayList<Rectangle> setRectList(ArrayList<Rectangle> listRect) {
    // for (Rectangle r : listRect) {
    // rectList.add(new Rectangle(r.getLength(), r.getLength()));
    // }
    // return rectList;
    // }

    public void addRectList(Rectangle rect) {
        rectList.add(rect);
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int x, int y, int n) {
        grid[x][y] = n;
    }

    public boolean searchZero(int[][] grid) {
        for (int a = 0; a < grid.length; a++) {
            for (int b = 0; b < grid.length; b++) {
                if (grid[a][b] == 0)
                    return true;
            }
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

    public void setMondrian(int n) {
        mondrian = n;
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

    public int Mondrian() {
        return mondrian;
    }

    public void split() {
        int minArea = size * size;
        int length = 0;
        int width = 0;
        int current_index = 0;
        int max_index_state = rectList.size();
        int[] coord1 = new int[] { 0, 0, 0, 0 };
        int[] coord2 = new int[] { 0, 0, 0, 0 };
        for (Rectangle rect : rectList) {
            if (rect.getArea() < minArea) {
                minArea = rect.getArea();
                length = rect.getLength();
                width = rect.getWidth();
                current_index = rect.getIndex();
                coord1 = rect.getCoordinates();
            }
            rectList.remove(rect);
        }
        if ((length % 2 == 0) && (width % 2 == 0) && (length > 2) && (width > 2)) {
            int length1 = 0;
            int length2 = 0;
            int width1 = 0;
            int width2 = 0;
            if ((length < width) || (length == width)) {
                length1 = (int) ((length / 2) - 1);
                length2 = length - length1;
                Rectangle rectangle1 = new Rectangle(length1, width, current_index);
                Rectangle rectangle2 = new Rectangle(length2, width, max_index_state + 1);
                coord2 = new int[] { coord1[0] + length1, coord1[1], coord1[2], coord1[3] };
                coord1 = new int[] { coord1[0], coord1[1] - length2, coord1[2], coord1[3] };
                rectangle1.setCoordinates(coord1);
                rectangle2.setCoordinates(coord2);
                rectList.add(rectangle1);
                rectList.add(rectangle2);
                for (int i = coord1[0]; i < coord1[1] + 1; i++) {
                    for (int j = coord1[2]; i < coord1[3]; j++) {
                        grid[i][j] = current_index;
                    }
                }
                for (int i = coord2[0]; i < coord2[1] + 1; i++) {
                    for (int j = coord2[2]; i < coord2[3]; j++) {
                        grid[i][j] = max_index_state;
                    }
                }
            } else {
                width1 = (int) ((width / 2) - 1);
                width2 = width - width1;
                Rectangle rectangle1 = new Rectangle(length, width1, current_index);
                Rectangle rectangle2 = new Rectangle(length, width2, max_index_state + 1);
                coord2 = new int[] { coord1[0], coord1[1], coord1[2] + width1, coord1[3] };
                coord1 = new int[] { coord1[0], coord1[1], coord1[2], coord1[3] - width2 };
                rectangle1.setCoordinates(coord1);
                rectangle2.setCoordinates(coord2);
                rectList.add(rectangle1);
                rectList.add(rectangle2);
                for (int i = coord1[0]; i < coord1[1] + 1; i++) {
                    for (int j = coord1[2]; i < coord1[3]; j++) {
                        grid[i][j] = current_index;
                    }
                }
                for (int i = coord2[0]; i < coord2[1] + 1; i++) {
                    for (int j = coord2[2]; i < coord2[3]; j++) {
                        grid[i][j] = max_index_state;
                    }
                }
            }
        }

        // } else if ((length % 2 == 0) && (width % 2 != 0)) {
        // int b = 0;
        // } else if ((length % 2 != 0) && (width % 2 != 0)) {
        // int c = 0;
        // }
    }
}

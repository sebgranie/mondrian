import java.util.ArrayList;
import java.util.Iterator;

/*
 * Class to represent a state, which consist of a mondrian puzzle
 */
public class State implements Comparable<State> {
    private ArrayList<Rectangle> rectList; // List of rectangle shaping the puzzle
    private int size; // size of the puzzle (grid)
    private int[][] grid; // 2D table to represent a state
    private int mondrian; // mondrian score of the state
    private int depth; // depth in the space state (tree of state)
    private int globalScore; // sum of depth (in the state space) + heuristic function (mondrian score)
    private boolean visited; // boolean asserting if a state has already been processed/studied

    // Create an instance of a state
    public State(int new_size) {
        rectList = new ArrayList<Rectangle>();
        size = new_size;
        grid = new int[size][size];
    }

    // overriden comparison function to manage the order in Priority Queue
    @Override
    public int compareTo(State s) {
        if (globalScore < (s.getMondrian(s.getRectList()) + s.getDepth())) {
            return -1;
        } else {
            return 1;
        }
    }

    // getter to access the boolean visited attribute
    public boolean getVisited() {
        return visited;
    }

    // setter for the boolean visited attribute
    public void setVisited(boolean b) {
        visited = b;
    }

    // compute global score
    public int computeGlobalScore() {
        return (getMondrian(rectList) + getDepth());
    }

    // getter to access the global score of a state
    public int getGlobalScore() {
        return globalScore;
    }

    // setter global score with a integer
    public void setGlobalScore(int n) {
        globalScore = n;
    }

    // getter to access the state's depth in the state space
    public int getDepth() {
        return depth;
    }

    // setter to set the state's depth in the state space
    public void setDepth(int n) {
        depth = n;
    }

    // getter to access the size of the grid
    public int getSize() {
        return size;
    }

    // setter to set the grid's size
    public void setSize(int n) {
        size = n;
    }

    // getter to access the list of all rectangle's shapes of a state
    public ArrayList<Rectangle> getRectList() {
        return rectList;
    }

    // reset the list of rectangle's shape of a state
    public void resetRectList() {
        rectList = new ArrayList<Rectangle>();
    }

    // Add a new rectangle's shape to the list of rectangle's shape
    public void addRectList(Rectangle rect) {
        rectList.add(rect);
    }

    // getter to access the grid of a state
    public int[][] getGrid() {
        return grid;
    }

    // set the value of a coordinate in the grid to show its belonging to a specific
    // rectangle
    public void setGrid(int x, int y, int n) {
        grid[x][y] = n;
    }

    // check whether the state contains a zero value, proof of a cell belonging to
    // any rectangle
    public boolean searchZero(int[][] grid) {
        for (int a = 0; a < grid.length; a++) {
            for (int b = 0; b < grid.length; b++) {
                if (grid[a][b] == 0)
                    return true;
            }
        }
        return false;
    }

    // check whether a row contains a specific integer value
    public boolean contains(int[] array, int key) {
        for (int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }

    // getter to access the mondrian attribute
    public int Mondrian() {
        return mondrian;
    }

    // setter to set to value of mondrian (score) attribute
    public void setMondrian(int n) {
        mondrian = n;
    }

    // Compute the Mondrian Score of a state
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

    // Check whether a generated state respect the conditions to be valid
    public boolean isValid() {
        int i = 0;
        int globalArea = 0;
        // check non-congruency in rectangles shapes
        for (Rectangle rect : rectList) {
            i += 1;
            for (Rectangle r : rectList.subList(i, rectList.size())) {
                if (((rect.getLength() == r.getLength()) && (rect.getWidth() == r.getWidth()))
                        || ((rect.getWidth() == r.getLength()) && (rect.getLength() == r.getWidth()))) {
                    return false;
                }
            }
            globalArea += rect.getArea();
        }
        // check for proper coverage of area
        if (globalArea != size * size)
            return false;
        return true;
    }

    // Split action
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
        }
        for (Iterator<Rectangle> iterator = rectList.iterator(); iterator.hasNext();) {
            Rectangle value = iterator.next();
            if (value.getIndex() == current_index) {
                iterator.remove();
            }
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

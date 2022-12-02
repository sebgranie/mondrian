
/*
 * Class to represent a rectangle of a grid
 */

public class Rectangle {
    private int length; // length of Rectangle
    private int width; // width of Rectangle
    private int index; // each rectangle in a grid has an index
    private int area; // area = length * width
    private int[] coordinates;

    // creates new instance of Rectangle
    public Rectangle(int new_length, int new_width, int new_index) {
        length = new_length;
        width = new_width;
        index = new_index;
        area = length * width;
        coordinates = new int[] { 0, 0, 0, 0 };
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] n) {
        for (int i = 0; i < 4; i++) {
            coordinates[i] = n[i];
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int n) {
        length = n;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int n) {
        width = n;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int n) {
        index = n;
    }

    public int getArea() {
        return area;
    }

    public void setArea() {
        area = length * width;
    }

}

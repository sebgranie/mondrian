
/*
 * Class to represent a rectangle of a grid
 */

public class Rectangle {
    private int length; // length of Rectangle
    private int width; // width of Rectangle
    private int index; // each rectangle in a grid has an index
    private int area; // area = length * width

    // creates new instance of Rectangle
    public Rectangle(int new_length, int new_width) {
        length = new_length;
        width = new_width;
        area = length * width;
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

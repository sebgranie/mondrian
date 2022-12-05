/*
 * Class to represent a rectangle of a grid
 */

public class Rectangle {
    private int length; // length of Rectangle
    private int width; // width of Rectangle
    private int index; // each rectangle in a grid has an index
    private int area; // area = length * width
    private int[] coordinates; // four values corresponding to the bounds of for loop

    // creates new instance of Rectangle
    public Rectangle(int new_length, int new_width, int new_index) {
        length = new_length;
        width = new_width;
        index = new_index;
        area = length * width;
        coordinates = new int[] { 0, 0, 0, 0 };
    }

    // getter to access the coordinates of a rectangle
    public int[] getCoordinates() {
        return coordinates;
    }

    // setter to set the coordinates attribute
    public void setCoordinates(int[] n) {
        for (int i = 0; i < 4; i++) {
            coordinates[i] = n[i];
        }
    }

    // getter to access the length of rectangle
    public int getLength() {
        return length;
    }

    // setter to set the length of a rectangle
    public void setLength(int n) {
        length = n;
    }

    // getter to access the width of a rectangle
    public int getWidth() {
        return width;
    }

    // setter to set the width of a rectangle
    public void setWidth(int n) {
        width = n;
    }

    // getter to access the index of a rectangle
    public int getIndex() {
        return index;
    }

    // setter to set the index of a rectangle
    public void setIndex(int n) {
        index = n;
    }

    // getter to access the area of a rectangle
    public int getArea() {
        return area;
    }

    // mathod to compute the area of a rectangle
    public void ComputeArea() {
        area = length * width;
    }

}

import java.io.*;
import java.util.*;
// import javafx.util.Pair;
// import java.lang.Math;

/*
 * Program representing the Mondrian Tiling Problem
 * Use a grid, also called state and compute actions
 * in order to optimize its Mondrian Score and
 * iterate to find the optimal solution.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        String grid_size = args[0];
        int size = Integer.parseInt(grid_size); // retrie grid size as integer

        State state = new State(size); // create instance of a grid
        int threshold = (int) 3 * size / 4; // threshold of fulfillment
        int step = 0; // improvement to reach the right side of the grid
        int index = 1; // current index of rectangles to be next added

        // Random rand = new Random();
        // int Bx = (int) size - 1;
        int length_gen = 0; // init next length to be generated
        int width_gen = 0; // init next width to be generated
        boolean cond = true; // condition to re-generate length and width to respect conditions
        int min = 1; // minimum edge length for a generated rectangle
        int max_length = size; // maximum edge length for a generated rectangle
        int max_width = size - step; // maximum edge width for a generated rectangle
        // fill the first line until threshold is reached
        while (/* state.contains(state.getGrid()[0], 0) && */ step < threshold) {
            outerloop: while (cond) {
                System.out.println("recherche des dimensions aléatoires");
                // generate length from min to max_length inclusive
                length_gen = (int) Math.floor(Math.random() * (max_length - min + 1) + min);
                if (length_gen == 1) {
                    // generate width from min+1 to max_width inclusive
                    width_gen = (int) Math.floor(Math.random() * (max_width - (min + 1) + 1) + (min + 1));
                } else {
                    // generate width from min to max_width inclusive
                    width_gen = (int) Math.floor(Math.random() * (max_width - min + 1) + min);
                }
                if (state.getRectList().size() == 0) {
                    System.out.println("size rectList :" + state.getRectList().size());
                    cond = false;
                    break outerloop;
                } else {
                    restart: for (Rectangle rec : state.getRectList()) {
                        if (((length_gen == rec.getLength()) && (width_gen == rec.getWidth()))
                                || ((width_gen == rec.getLength()) && (length_gen == rec.getWidth()))) {
                            cond = true;
                            System.out.println("Current shape dictionary : ");
                            for (Rectangle r : state.getRectList()) {
                                System.out.println(" --> " + "(" + r.getLength() + "," + r.getWidth() + ")");
                            }
                            System.out.println("shape impossible : [" + length_gen + "," + width_gen + "]");
                            break restart;
                        } else {
                            System.out.println("Current shape dictionary : ");
                            for (Rectangle r : state.getRectList()) {
                                System.out.println(" --> " + "(" + r.getLength() + "," + r.getWidth() + ")");
                            }
                            System.out.println("shape possible : [" + length_gen + "," + width_gen + "]");
                            cond = false;
                        }
                    }
                }
            }
            state.addRectList(new Rectangle(length_gen, width_gen));
            System.out.println("cond :" + cond);
            System.out.println("length_gen :" + length_gen);
            System.out.println("width_gen :" + width_gen);
            System.out.println("step :" + step);
            System.out.println("index before threshold :" + index);
            for (int a = 0; a < length_gen; a++) {
                for (int b = step; b < step + width_gen; b++) {
                    state.setGrid(a, b, index);
                }
            }
            step += width_gen;
            index += 1;
            System.out.println("Shapes used : ");
            for (Rectangle r : state.getRectList()) {
                System.out.println(" --> " + "(" + r.getLength() + "," + r.getWidth() + ")");
            }
            System.out.println(
                    Arrays.deepToString(state.getGrid()).replace("], ", "]\n").replace("[[",
                            "[").replace("]]", "]"));
            cond = true;
        }
        System.out.println();
        int final_line_length = (int) Math.floor(Math.random() * (max_length - min + 1) + min);
        if (/* (state.contains(state.getGrid()[0], 0)) && */ (step >= threshold) && (step < size)) {
            System.out.println("FINAL STEP ");
            System.out.println("length_gen :" + final_line_length);
            System.out.println("width_gen :" + (size - step));
            System.out.println("step :" + step);
            System.out.println("index final step:" + index);
            for (int c = 0; c < final_line_length; c++) {
                for (int d = step; d < size; d++) {
                    state.setGrid(c, d, index);
                }
            }
            state.addRectList(new Rectangle(final_line_length, (size - step)));
            index += 1;
            System.out.println();
            System.out.println("Shapes used : ");
            for (Rectangle r : state.getRectList()) {
                System.out.println(" --> " + "(" + r.getLength() + "," + r.getWidth() + ")");
            }
            System.out.println(
                    Arrays.deepToString(state.getGrid()).replace("], ", "]\n").replace("[[",
                            "[").replace("]]", "]"));

            System.out.println();
            // System.out.println("index end if:" + index);
        }

        System.out.println();
        System.out.println();
        System.out.println("REMPLISSAGE VERS LE BAS");
        System.out.println();

        ArrayList<Rectangle> tempoList = new ArrayList<Rectangle>();
        int temp1 = 0;
        boolean condition = false;
        // int temp_width_gen = 0;
        for (Rectangle r : state.getRectList()) {
            out1: for (Rectangle R : state.getRectList()) {
                if ((((size - r.getLength()) == R.getLength()) && (r.getWidth() == R.getWidth()))
                        || ((r.getLength() == R.getLength())
                                && ((size - r.getWidth()) == R.getWidth()))) {
                    condition = false;
                    break out1;
                } else {
                    condition = true;
                }
            }
            if (condition) {
                System.out.println("r.getLength() : " + r.getLength());
                System.out.println("size : " + size);
                System.out.println("temp1 : " + temp1);
                System.out.println("r.getWidth()+temp1 : " + (r.getWidth() + temp1));
                System.out.println("index : " + index);
                System.out.println();
                for (int q = r.getLength(); q < size; q++) {
                    for (int s = temp1; s < r.getWidth() + temp1; s++) {
                        state.setGrid(q, s, index);
                    }
                }
                temp1 += r.getWidth();
                index += 1;
                tempoList.add(new Rectangle(size - r.getLength(), r.getWidth()));
                state.getRectList().addAll(tempoList);
            }
            boolean out = false;
            out2: if (!condition) {
                for (Rectangle Rect : state.getRectList()) {
                    if (((size - r.getLength() - 1) == Rect.getLength()
                            && r.getWidth() == Rect.getWidth())
                            || (r.getWidth() == Rect.getLength()
                                    && (size - r.getLength() - 1) == Rect.getWidth())) {
                        condition = false;
                        out = true;
                        break out2;
                    } else {
                        condition = true;
                    }
                }
                if (condition) {
                    System.out.println("r.getLength() : " + r.getLength());
                    System.out.println("size-1 : " + (size - 1));
                    System.out.println("temp1 : " + temp1);
                    System.out.println("r.getWidth()+temp1 : " + (r.getWidth() + temp1));
                    System.out.println("index : " + index);
                    System.out.println();
                    for (int q = r.getLength(); q < size - 1; q++) {
                        for (int s = temp1; s < r.getWidth() + temp1; s++) {
                            state.setGrid(q, s, index);
                        }
                    }
                    temp1 += r.getWidth();
                    index += 1;
                    tempoList.add(new Rectangle(size - r.getLength() - 1, r.getWidth()));

                    for (Rectangle Re : state.getRectList()) {
                        if ((1 == Re.getLength() && (size - temp1) == Re.getWidth())
                                || ((size - temp1) == Re.getLength() && (1 == Re.getWidth()))) {
                            System.out.println("Impossible to build this initial state  1");
                            // throw new Exception("Impossible to build this initial state");
                        }
                    }
                    System.out.println("size-1 : " + (size - 1));
                    System.out.println("size : " + size);
                    System.out.println("temp1 : " + temp1);
                    System.out.println("size : " + size);
                    System.out.println("index : " + index);
                    System.out.println();
                    for (int o = size - 1; o < size; o++) {
                        for (int p = temp1; p < size; p++) {
                            state.setGrid(o, p, index);
                        }
                    }
                    temp1 += size - temp1;
                    index += 1;
                    tempoList.add(new Rectangle(1, size - temp1));
                } else {
                    System.out.println("Impossible to build this initial state 2");
                    // throw new Exception("Impossible to build this initial state");
                }
            } else {
                if (out)
                    System.out.println("Impossible to build this initial state  3");
                // throw new Exception("Impossible to build this initial state");
            }
        }

        state.getRectList().addAll(tempoList);
        System.out.println();
        System.out.println(
                Arrays.deepToString(state.getGrid()).replace("], ", "]\n").replace("[[",
                        "[").replace("]]", "]"));

        // System.out.println();
        System.out.println("Shapes used : ");
        for (Rectangle r : state.getRectList()) {
            System.out.println(" --> " + "(" + r.getLength() + "," + r.getWidth() + ")");
        }
        System.out.println();
        System.out.println("Mondrian Score : " + state.getMondrian(state.getRectList()));
        System.out.println();
        // System.out.println("index :" + index);
        // }

        long end = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (end - start) + " milliseconds");
    }
}

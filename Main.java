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
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        String grid_size = args[0];
        int size = Integer.parseInt(grid_size);

        State state = new State(size);
        int threshold = (int) 3 * size / 4;
        int step = 0;
        int index = 1;
        // int[] shapes = new int[] {};
        // int[] shapes = {};
        // shapes.add({1,2});
        // Pair<Integer, String> p = new Pair<Integer, String>(10, "Hello Geeks!");

        Random rand = new Random();
        int Bx = (int) 3 * size / 4;
        int r3 = 0;
        int r4 = 0;
        boolean cond = true;
        boolean setup = true;
        while (state.contains(state.getGrid()[0], 0) && step <= threshold) {
            int min = 1;
            int max = size - step - 1;
            outerloop: while (cond || setup) {
                System.out.println("oui");
                r3 = rand.nextInt(Bx) + 1;
                if (index == 1) {
                    if (r3 != 1)
                        r4 = rand.nextInt(Bx) + 1;
                    else
                        r4 = rand.nextInt(Bx) + 2;
                } else {
                    if (r3 != 1)
                        r4 = (int) Math.floor(Math.random() * (max - min + 1) + min) + 1;
                    else
                        r4 = (int) Math.floor(Math.random() * (max - min + 1) + min) + 2;
                }
                if (state.getRectList().size() == 0) {
                    break outerloop;
                } else {
                    for (Rectangle rec : state.getRectList()) {
                        if ((r3 == rec.getLength() && r4 == rec.getWidth())
                                || (r4 == rec.getLength() && r3 == rec.getWidth())) {
                            cond = true;
                        } else {
                            cond = false;
                        }
                    }
                }
                setup = false;
            }

            state.addRectList(new Rectangle(r3, r4));
            System.out.println("r3 :" + r3);
            System.out.println("r4 :" + r4);
            System.out.print("step :" + step);
            for (int a = 0; a < r3; a++) {
                for (int b = step; b < step + r4; b++) {
                    state.setGrid(a, b, index);
                }
            }
            step += r4;
            index += 1;
            System.out.println();
            System.out.println(
                    Arrays.deepToString(state.getGrid()).replace("], ", "]\n").replace("[[",
                            "[").replace("]]", "]"));
        }
        System.out.println();
        if (state.contains(state.getGrid()[0], 0)) {
            System.out.println("final");

            System.out.println("x :" + r3);
            System.out.println("y :" + (size - step));
            System.out.println("step :" + step);
            for (int c = 0; c < r3; c++) {
                for (int d = step; d < size; d++) {
                    state.setGrid(c, d, index);
                }
            }
            state.addRectList(new Rectangle(r3, (size - step)));
            index += 1;
            System.out.println();
            System.out.println(
                    Arrays.deepToString(state.getGrid()).replace("], ", "]\n").replace("[[",
                            "[").replace("]]", "]"));

            System.out.println();
            System.out.println("Shapes used : ");
            for (Rectangle r : state.getRectList()) {
                System.out.println(" --> " + "(" + r.getLength() + "," + r.getWidth() + ")");
            }
        }

        System.out.println();
        System.out.println();

        int x = 0;
        int y = 0;
        int w = 0;
        int z = 0;
        out: for (x = 0; x < size; x++) {
            if (state.getGrid()[x][0] == 0) {
                for (y = 0; y < size; y++) {
                    if (state.getGrid()[x][y] != 0) {
                        y = y - 1;
                        break out;
                    }
                }
            }
        }

        for (w = x; w < size; w++) {
            for (z = y; z < size; z++) {
                state.setGrid(z, w, index);
            }
        }
        state.addRectList(new Rectangle(x, y));
        index += 1;

        System.out.println();
        System.out.println(
                Arrays.deepToString(state.getGrid()).replace("], ", "]\n").replace("[[",
                        "[").replace("]]", "]"));

        System.out.println();
        System.out.println("Shapes used : ");
        for (Rectangle r : state.getRectList()) {
            System.out.println(" --> " + "(" + r.getLength() + "," + r.getWidth() + ")");
        }

        long end = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (end - start) + " milliseconds");
    }
}

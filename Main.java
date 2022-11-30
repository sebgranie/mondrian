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

        Random rand = new Random();
        int Bx = (int) 3 * size / 4;
        int r3 = 0;
        int r4 = 0;
        boolean cond = true;
        while (state.contains(state.getGrid()[0], 0) && step <= threshold) {
            int min = 1;
            int max = size - step - 1;
            outerloop: while (cond) {
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
                    restart: for (Rectangle rec : state.getRectList()) {
                        if ((r3 == rec.getLength() && r4 == rec.getWidth())
                                || (r4 == rec.getLength() && r3 == rec.getWidth())) {
                            cond = true;
                            break restart;
                        } else {
                            cond = false;
                        }
                    }
                }
            }
            state.addRectList(new Rectangle(r3, r4));
            System.out.println("r3 :" + r3);
            System.out.println("r4 :" + r4);
            System.out.println("step :" + step);
            System.out.println("index end while :" + index);
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
        System.out.println("FINAL STEP ");
        if (state.contains(state.getGrid()[0], 0)) {
            System.out.println("x :" + r3);
            System.out.println("y :" + (size - step));
            System.out.println("step :" + step);
            System.out.println("index :" + index);
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
            System.out.println("index end if:" + index);
        }

        System.out.println();
        System.out.println();
        System.out.println("remplissage vers le bas");
        System.out.println();

        ArrayList<Rectangle> tempoList = new ArrayList<Rectangle>();
        int temp1 = 0;
        for (Rectangle r : state.getRectList()) {
            for (int q = r.getLength(); q < size; q++) {
                for (int s = temp1; s < r.getWidth() + temp1; s++) {
                    state.setGrid(q, s, index);
                }
            }
            temp1 += r.getWidth();
            index += 1;
            tempoList.add(new Rectangle(size - r.getLength(), r.getWidth()));
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

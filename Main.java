import java.io.*;
import java.util.*;
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

        // while (state.Ints(state.getGrid()[0], 0) || step <= threshold) {
        // }
        Random rand = new Random();
        int By = (int) size / 2;
        int Bx = (int) 3 * size / 4;
        int r1 = rand.nextInt(Bx) + 1;
        int r2 = rand.nextInt(By) + 1;
        step += r1;
        System.out.println("r1 :" + r1);
        System.out.println("r2 :" + r2);
        System.out.println("step :" + step);

        for (int j = 0; j < r1; j++) {
            for (int k = 0; k < r2; k++) {
                // System.out.println(j);
                // System.out.println(k);
                state.setGrid(j, k, 3);
            }
        }
        System.out.println(
                Arrays.deepToString(state.getGrid()).replace("], ", "]\n").replace("[[",
                        "[").replace("]]", "]"));

        // Random rand = new Random();
        int By1 = (int) size - r1;
        int Bx1 = (int) 3 * size / 4;
        int r3 = rand.nextInt(Bx1) + 1;
        int r4 = rand.nextInt(By1) + 1;
        step += r3;
        System.out.println("r3 :" + r3);
        System.out.println("r4 :" + r4);
        System.out.println("step :" + step);

        for (int a = 0; a < r3; a++) {
            for (int b = r2; b < r2 + r4; b++) {
                // System.out.println(a);
                // System.out.println(b);
                state.setGrid(a, b, 2);
            }
        }

        System.out.println(
                Arrays.deepToString(state.getGrid()).replace("], ", "]\n").replace("[[",
                        "[").replace("]]", "]"));

        long end = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (end - start) + " milliseconds");

        // Finding the set of suitable rectangles for n × n grid
        // Input: n
        // Output: The set of suitable rectangles with areas
        // 1: t ← {}
        // 2: s ← Empty list
        // 3: i ← 1
        // 4: while i ≤ bn
        // 2 c do
        // 5: j ← 1
        // 6: while j ≤ n do
        // 7: if (i, j) not in t and (j, i) not in t then
        // 8: t ← t ∪ {(i, j)}
        // 9: Append ij to the list s
        // 10: end if
        // 11: j ← j + 1
        // 12: end while
        // 13: i ← i + 1
        // 14: end while
        // 15: return t, s
    }
}

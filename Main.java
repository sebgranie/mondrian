import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
        while (!state.isValid()) {
            state = new State(size);
            int step = 0; // improvement to reach the right side of the grid
            int index = 1; // current index of rectangles to be next added
            int length_gen = 0; // init next length to be generated
            int width_gen = 0; // init next width to be generated
            int min = 1; // minimum edge length for a generated rectangle
            int max_length = (int) 3 * size / 4; // maximum edge length for a generated rectangle
            int max_width = (int) (3 * (size) / 4) - step; // maximum edge width for a generated rectangle

            /* NEW METHOD */
            length_gen = (int) Math.floor(Math.random() * ((max_length - 1) - min + 1) + min); // generate length from
                                                                                               // min
                                                                                               // to
            // max_length inclusive
            if (length_gen == 1) {
                // generate width from min+1 to max_width inclusive
                width_gen = (int) Math.floor(Math.random() * (max_width - (min + 1) + 1) + (min + 1));
            } else {
                // generate width from min to max_width inclusive
                width_gen = (int) Math.floor(Math.random() * (max_width - min + 1) + min);
            }
            // int prev_length = length_gen;
            int prev_width = width_gen;
            Rectangle rectangle = new Rectangle(length_gen, width_gen, index);
            int[] coordArray = new int[] { 0, length_gen - 1, 0, width_gen - 1 };
            rectangle.setCoordinates(coordArray);
            state.addRectList(rectangle);
            // System.out.println("New shape added (length_gen, width_gen) : [" + length_gen
            // + "," + width_gen + "]");
            for (int a = 0; a < length_gen; a++) {
                for (int b = step; b < step + width_gen; b++) {
                    state.setGrid(a, b, index);
                }
            }
            step += width_gen;
            index += 1;
            width_gen = size - width_gen; // fill first row
            if (width_gen == 1) {
                // generate width from min+1 to max_length-1 inclusive
                length_gen = (int) Math.floor(Math.random() * ((max_length - 1) - (min + 1) + 1) + (min + 1));
            } else {
                // generate width from min to max_length-1 inclusive
                length_gen = (int) Math.floor(Math.random() * ((max_length - 1) - min + 1) + min);
            }
            rectangle = new Rectangle(length_gen, width_gen, index);
            coordArray = new int[] { 0, length_gen - 1, prev_width, size - 1 };
            rectangle.setCoordinates(coordArray);
            state.addRectList(rectangle);
            // System.out.println("New shape added (length_gen, width_gen) : [" + length_gen
            // + "," + width_gen + "]");
            for (int a = 0; a < length_gen; a++) {
                for (int b = step; b < step + width_gen; b++) {
                    state.setGrid(a, b, index);
                }
            }
            step += width_gen;
            index += 1;
            ArrayList<Rectangle> tempoList = new ArrayList<Rectangle>();
            step = 0;
            for (Rectangle rect : state.getRectList()) {
                length_gen = size - rect.getLength(); // fill the vertical space under each rectangle from first row
                width_gen = rect.getWidth(); // same width as above's rectangle
                rectangle = new Rectangle(length_gen, width_gen, index);
                coordArray = new int[] { rect.getLength(), size - 1, step, step + rect.getWidth() - 1 };
                rectangle.setCoordinates(coordArray);
                tempoList.add(rectangle);
                // System.out.println("New shape added (length_gen, width_gen) : [" + length_gen
                // + "," + width_gen + "]");
                for (int a = rect.getLength(); a < size; a++) {
                    for (int b = step; b < step + width_gen; b++) {
                        state.setGrid(a, b, index);
                    }
                }
                step += width_gen;
                index += 1;
            }
            state.getRectList().addAll(tempoList);
        }
        System.out.println(
                Arrays.deepToString(state.getGrid()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        System.out.println("Shapes used : ");
        for (Rectangle r : state.getRectList()) {
            System.out.println(" --> " + "(" + r.getLength() + "," + r.getWidth() + ")");
        }
        System.out.println("Mondrian Score : " + state.getMondrian(state.getRectList()));

        // beginning of the state space after building initial state
        Graph stateSpace = new Graph(state);
        List<String> actions = List.of("Split", "Merge", "SplitMerge", "MergeSplit");
        int maxDepth = 10;
        int maxSize = 30;
        PriorityQueue<State> pQueue = new PriorityQueue<State>(maxSize);
        state.setDepth(0);
        state.setMondrian(state.getMondrian(state.getRectList()));
        state.setGlobalScore(state.getDepth() + state.Mondrian());
        pQueue.offer(state);
        State bestState = state;
        State worstState = state;
        while (!pQueue.isEmpty()) {
            State s = pQueue.poll();
            if (s.getDepth() < maxDepth) {
                for (String string : actions) {
                    if (string == "Split") {
                        s.split();
                    }
                    // } else if (string == "Merge"){
                    // s.Merge();
                    // } else if (string == "SplitMerge"){
                    // s.SplitMerge();
                    // } else if (string == "MergeSplit"){
                    // s.MergeSplit();
                    // }
                    if (s.getVisited()) {
                        continue;
                    } else {
                        s.setDepth(s.getDepth() + 1);
                        s.setMondrian(s.getMondrian(s.getRectList()));
                        s.setGlobalScore(s.getDepth() + s.Mondrian());
                        if (s.getGlobalScore() < bestState.getGlobalScore()) {
                            bestState = s;
                        }
                        if (pQueue.size() < maxSize) {
                            pQueue.offer(s);
                        } else {
                            if (s.getGlobalScore() < worstState.getGlobalScore()) {
                                pQueue.remove(worstState);
                                pQueue.offer(s);
                            }
                        }
                    }
                }
            }
        }
        stateSpace.display_state_space();
        System.out.println("The best state is : ");
        System.out.println();
        Arrays.deepToString(bestState.getGrid()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]");
        System.out.println();
        System.out.println("Mondrian Score : " + bestState.getMondrian(bestState.getRectList()));
        /* END NEW METHOD */

        long end = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (end - start) + " milliseconds");
    }
}

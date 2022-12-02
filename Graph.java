import java.io.*;
import java.util.*;

/*
 * Classe to represent the state space using adjacency lists
 */

public class Graph {
    private State[] states;
    private int numStates = 0;

    // create a new innstance of Graph
    public Graph(State initial_state) {
        int max_size = 16;
        states = new State[max_size];
        states[0] = initial_state;
        numStates += 1;
    }

    // getter to the states attribute
    public State[] getStates() {
        return states;
    }

    // setter to add a new state and its index using index
    public void setState(int size, int i) {
        states[i] = new State(size);
    }

    // getter to a specific state list of neighbours using index
    public State getState(int n) {
        return states[n];
    }

    // getter to the numStates attribute
    public int size() {
        return numStates;
    }

    // display all the states of the state space built
    public void display_state_space() {
        int layer = 0;
        int index = 0;
        boolean cond = false;
        System.out.println();
        System.out.println("State space representation : ");
        System.out.println("Layer number : " + layer);
        System.out.println("length states : " + states.length);
        outerloop: for (State state : states) {
            if (state != null) {
                System.out.println("index : " + index);
                if (index == 1) {
                    System.out.println("Layer number : " + index);
                    index = 0;
                }
                if (((index) == (Math.pow(4, layer + 1) + 1))) {
                    System.out.println("Layer number : " + layer);
                    layer += 1;
                    index = 0;
                }
                if ((cond) && (index % 4 == 0)) {
                    System.out.println("Result of Split action");
                } else if ((cond) && (index % 4 == 1)) {
                    System.out.println("Result of Merge action");
                } else if ((cond) && (index % 4 == 2)) {
                    System.out.println("Result of Split-Merge action");
                } else if ((cond) && (index % 4 == 3)) {
                    System.out.println("Result of Merge-Split action");
                }
                System.out.println(
                        Arrays.deepToString(state.getGrid()).replace("], ", "]\n").replace("[[",
                                "[").replace("]]", "]"));
                System.out.println("Shapes used : ");
                for (Rectangle r : state.getRectList()) {
                    System.out.println(" --> " + "(" + r.getLength() + "," + r.getWidth() + ")");
                }
                index += 1;
                cond = true;
            } else {
                break outerloop;
            }
        }
    }
}
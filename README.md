# Artificial Intelligence (H) Coursework - Mondrian Tiling Problem

## Introduction

This project deals with the **Mondrian Tiling Problem**. To make a long story short, these programs build a `state space` coming from an initial state, the goal is to reach the more optimal state at the end of the execution.

The Mondrian Score is computed using the largest and smallest area of rectangles that fit in a specific puzzle. The Mondrian Score is the difference between these two areas. The less the Mondrian Score is, the more optimal the solution is.

However, it is mandatory to respect a few rules. The Mondrian Puzzle is a puzzle that seeks to tile an integer-sided square with a set of integer-sided rectangles. `Tiling` means that the entire given square needs to be covered (there should be no residual area in the square that is not covered by any tile), and `rectangular tiling` means that the only allowed shape of the tiles is that of a rectangle. All used rectangles must be integer-sided and pairwise non-congruential (i.e., none of them must have heights and widths that match with any other tile placed).

## 1. Code

[Github link where the code is stored](https://github.com/sebgranie/mondrian)

### 1.1. Execution

In order to compile the project, thanks to the `Makefile`, be at the root of the project and do the followed command in your shell :

```sh
make all
```

This command will compile all the `*.java` files and you will be ready to execute the **Main.java** file.

#### 1.1.1 - Programs explanation

Once the **.class** files have been created, you are ready to run the code using the followed command :

```sh
java Main size_grid
```

- **Main.java** : it is the main function that contains the code to build the initial state, corresponding to a combination of rectangles that fit in the grid. Using this state as initial state, you build a quaternary tree of possible states generated using different actions to minimise the Mondrian Score. The less the Mondrian Score is, the more optimal the solution is.
- **size_grid** is the size of the studied grid (square). It is built as a 2D table filled with integers corresponding to the belonging of a cell to a specific rectangle.

* **Expected output**

Any expected outputs have been specified in the coursework. As a consequence, I decided on my own a proper form of outputs. The algorithm outputs the followed results :

- The size of the studied grid passed in arguments to the program;
- The initial state to know where the research start from;
- The quaternary tree specifying all its layer, generated states with the name of the action performed to get this new state;
- The main outputs is the best state found in the state space and its Mondrian Score.
- The elapsed time to compute the program.

### 1.2. Clean and re-compile

At any time, it is possible to clean all the **\*.class** files by entering the followed command line :

```sh
make clean
```

You can compile again with :

```sh
make all
```

package com.rishab.threadProblems;

import java.util.Arrays;

public class Maze {
    public static final int MAZE_SIZE = 4;
    private final String[][] cells = new String[MAZE_SIZE][MAZE_SIZE];

    public Maze() {
        for (var row : cells) {
            Arrays.fill(row, "");
        }
    }

    public int[] getNextLocation(int[] lastLocation) {
        int[] nextLocation = new int[2];
        nextLocation[1] = (lastLocation[1] == MAZE_SIZE - 1) ? 0 : lastLocation[1] + 1;
        nextLocation[0] = lastLocation[0];

        if (nextLocation[1] == 0) {
            nextLocation[0] = (lastLocation[0] == MAZE_SIZE - 1) ? 0 : lastLocation[0] + 1;
        }
        return nextLocation;
    }

    public void moveLocation(int locX, int locY, String name) {
        cells[locX][locY] = name.substring(0, 1);
        resetSearchedCells(name);
    }

    public void resetSearchedCells(String name) {
        for (var row : cells) {
            Arrays.asList(row).replaceAll(c -> c.equals("!" + name.charAt(0)) ? "" : c);
        }
    }

    public boolean searchCell(String name, int[] nextLocation, int[] lastLocation) {
        if (cells[nextLocation[0]][nextLocation[1]].equals(name.substring(0, 1))) {
            return true;
        }
        cells[lastLocation[0]][lastLocation[1]] = "!" + name.charAt(0);
        return false;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(cells);
    }
}

package com.rishab.threadProblems;

import java.util.Arrays;
import java.util.concurrent.Executors;

record Participant(String name, String searchingFor, Maze maze, int[] startingPosition) {
    Participant {
        maze.moveLocation(startingPosition[0], startingPosition[1], name);
    }
}

class ParticipantThread extends Thread {
    public final Participant participant;

    public ParticipantThread(Participant participant) {
        super(participant.name());
        this.participant = participant;
    }

    @Override
    public void run() {
        int[] lastLocation = participant.startingPosition();
        Maze maze = participant.maze();

        while (true) {
            int[] nextLocation = maze.getNextLocation(lastLocation);
            try {
//                change the sleep time for Eve to 2900 to avoid livelock
                Thread.sleep(participant.name().equals("Eve") ? 2900 : 500);
                if (maze.searchCell(participant.searchingFor(), nextLocation, lastLocation)) {
                    System.out.printf("%s found %s at %s!%n", participant.name(), participant.searchingFor(), Arrays.toString(nextLocation));
                    break;
                }
                synchronized (maze) {
                    maze.moveLocation(nextLocation[0], nextLocation[1], participant.name());
                }
                lastLocation = new int[]{nextLocation[0], nextLocation[1]};
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println(participant.name() + " searching " + participant.maze());
        }
    }
}

public class MazeRunner {
    public static void main(String[] args) {

        Maze maze = new Maze();
        Participant adam = new Participant("Adam", "Eve", maze, new int[]{3, 3});
        Participant eve = new Participant("Eve", "Adam", maze, new int[]{1, 1});

        System.out.println(maze);

        var executor = Executors.newCachedThreadPool();
        var adamsResult = executor.submit(new ParticipantThread(adam));
        var eveResult = executor.submit(new ParticipantThread(eve));

//        as both threads are searching for each other, and each thread is searching for the other thread, the program will never terminate
        while (!adamsResult.isDone() && !eveResult.isDone()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (adamsResult.isDone()) eveResult.cancel(true);
        else if (eveResult.isDone()) adamsResult.cancel(true);

        executor.shutdown();

    }
}

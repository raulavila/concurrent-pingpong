package com.raulavila.pingpong.version4;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameScale {

    public static final int NUM_PLAYERS = 6;
    
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        int length = NUM_PLAYERS;

        Player[] players = new Player[length];

        for (int i=0; i < length; i++) {
            Player player = new Player("player"+i, lock);
            players[i] = player;
        }

        for (int i=0; i < length - 1; i++) {
            players[i].setNextPlayer(players[i+1]);
        }
        players[length - 1].setNextPlayer(players[0]);

        System.out.println("Game starting...!");

        players[0].setPlay(true);

        //Threads creation
        Thread[] threads = new Thread[length];
        for (int i=0; i < length; i++) {
            Thread thread = new Thread(players[i]);
            threads[i] = thread;
            thread.start();
        }

        //Let the players play!
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Tell the players to stop
        for (Thread thread : threads) {
            thread.interrupt();
        }

        //Don't progress main thread until all players have finished
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Game finished!");
    }

}

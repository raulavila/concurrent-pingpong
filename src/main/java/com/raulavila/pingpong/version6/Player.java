package com.raulavila.pingpong.version6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Player implements Runnable {

    private final String text;

    private final Lock lock;
    private final Condition myTurn;

    private final CountDownLatch entryBarrier;
    private final CountDownLatch exitBarrier;

    private Condition nextTurn;

    private Player nextPlayer;

    private volatile boolean play = false;

    public Player(String text,
                  Lock lock,
                  CountDownLatch entryBarrier,
                  CountDownLatch exitBarrier) {
        this.text = text;
        this.lock = lock;
        this.myTurn = lock.newCondition();

        this.entryBarrier = entryBarrier;
        this.exitBarrier = exitBarrier;
    }

    @Override
    public void run() {
        if(entryBarrierOpen())
            play();
    }

    public boolean entryBarrierOpen() {
        try {
            entryBarrier.await();
            return true;
        } catch (InterruptedException e) {
            System.out.println("Player "+text+" was interrupted before starting Game!");
            return false;
        }
    }

    private void play() {
        while (!Thread.interrupted()) {
            lock.lock();

            try {
                while (!play)
                    myTurn.awaitUninterruptibly();

                System.out.println(text);

                this.play = false;
                nextPlayer.play = true;

                nextTurn.signal();
            } finally {
                lock.unlock();
            }
        }

        exitBarrier.countDown();
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
        this.nextTurn = nextPlayer.myTurn;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }
}

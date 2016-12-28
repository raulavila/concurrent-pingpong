package com.raulavila.pingpong.version3;

public class Player implements Runnable {

    private final String text;

    private final Object lock;

    private Player nextPlayer;

    private volatile boolean play = false;

    public Player(String text,
                  Object lock) {
        this.text = text;
        this.lock = lock;
    }

    @Override
    public void run() {

        while(!Thread.interrupted()) {
            synchronized (lock) {
                try {
                    while (!play)
                        lock.wait();

                    System.out.println(text);

                    this.play = false;
                    nextPlayer.play = true;

                    lock.notifyAll();

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }
}
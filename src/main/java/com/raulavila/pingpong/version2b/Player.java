package com.raulavila.pingpong.version2b;

public class Player implements Runnable {

    private final String text;

    private Player nextPlayer;

    private volatile boolean mustPlay = false;

    public Player(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            while (!mustPlay);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(text);

            this.mustPlay = false;
            nextPlayer.mustPlay = true;

        }
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public void setMustPlay(boolean mustPlay) {
        this.mustPlay = mustPlay;
    }
}
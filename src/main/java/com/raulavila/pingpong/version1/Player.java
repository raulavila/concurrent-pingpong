package com.raulavila.pingpong.version1;

public class Player implements Runnable {

    private final String text;

    private int turns = Game.MAX_TURNS;
    
    private Player nextPlayer;

    private volatile boolean mustPlay = false;

    public Player(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        while(!gameFinished()) {
            while (!mustPlay);

            System.out.println(text);
            turns--;

            this.mustPlay = false;
            nextPlayer.mustPlay = true;

        }
    }

    private boolean gameFinished() {
        return turns == 0;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public void setMustPlay(boolean mustPlay) {
        this.mustPlay = mustPlay;
    }
}
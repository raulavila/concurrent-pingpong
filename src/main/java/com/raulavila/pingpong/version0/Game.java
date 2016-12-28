package com.raulavila.pingpong.version0;

public class Game {

    public static final int MAX_TURNS = 10;
    
    public static void main(String[] args) {

        Player player1 = new Player("ping");
        Player player2 = new Player("pong");

        player1.setNextPlayer(player2);
        player2.setNextPlayer(player1);

        System.out.println("Game starting...!");

        player1.play();
        
        System.out.println("Game finished!");
    }
}

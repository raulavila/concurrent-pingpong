package com.raulavila.pingpong.version1;

public class Game {

    public static final int MAX_TURNS = 10;
    
    public static void main(String[] args) {

        Player player1 = new Player("ping");
        Player player2 = new Player("pong");

        player1.setNextPlayer(player2);
        player2.setNextPlayer(player1);

        System.out.println("Game starting...!");

        player1.setMustPlay(true);

        Thread thread2 = new Thread(player2);
        thread2.start();
        Thread thread1 = new Thread(player1);
        thread1.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Game finished!");
    }

}

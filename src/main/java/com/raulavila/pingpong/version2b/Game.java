package com.raulavila.pingpong.version2b;

public class Game {

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

        //Let the players play!
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Tell the players to stop
        thread1.interrupt();
        thread2.interrupt();
        
        //Wait until players finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Game finished!");
    }

}

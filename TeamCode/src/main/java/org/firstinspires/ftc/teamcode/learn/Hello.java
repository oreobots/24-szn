package org.firstinspires.ftc.teamcode;

public class Hello {

    public static void main(String[] args){
        System.out.println("TIC TAC TOE");
        Board board = new Board();
        board.print();
        while(true) {
            if (board.makeMoveandCheckGameEnded()) {
                System.out.println("someone won game ended!");
                return;
            }
            if (board.isGridFull()) {
                System.out.println("game tied!!");
                return;
            }
        }
    }
}


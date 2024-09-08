package org.firstinspires.ftc.teamcode;

import java.util.Scanner;

public class Board {

    private int[][] grid = new int[3][3];
    private Scanner input = new Scanner(System.in);
    private boolean firstUser = true;

    public boolean makeMoveandCheckGameEnded() {

        if (firstUser) {
            System.out.println("user 1 move");
        } else {
            System.out.println("user 2 move");
        }
        System.out.print("input row:");
        int row = input.nextInt();

        System.out.println("input column:");
        int col = input.nextInt();

        System.out.println("user entered:" + row + " " + col);
        if (isGridFull()) {
            System.out.println("TIE!!");
            return true;
        }
        if (grid[row][col] != 0) {
            System.out.println("BAD MOVE, TRY AGAIN!");
        } else {

            if (firstUser) {
                grid[row][col] = 1;
                if (isWinningMove(row, col)) {
                    System.out.println("User 1 wins!");
                    return true;
                }
                firstUser = false;
            } else {
                grid[row][col] = 2;
                if(isWinningMove(row, col)) {
                    System.out.println("User 2 wins!");
                    return true;
                }
                firstUser = true;
            }
            print();

            if(isWinningMove(row, col)) {
                System.out.println("You WIN!!");
            }
            print();
        }
        return false;
    }

    public boolean isGridFull() {
        if (grid [0][0] != 0 &&
                grid [0][1] != 0 &&
                grid [0][2] != 0 &&
                grid [1][0] != 0 &&
                grid [1][1] != 0 &&
                grid [1][2] != 0 &&
                grid [2][0] != 0 &&
                grid [2][1] != 0 &&
                grid [2][2] != 0) {

                    return true;
        }
        return false;
    }
    private boolean isWinningMove(int row, int col) {

        int user = 0;
        if(firstUser){
            user=1;
        }else{
            user=2;
        }



        if (grid[row][0] == user && grid[row][1] == user && grid[row][2] == user) {
                return true;
        }

        if (grid[0][col] == user && grid[1][col] == user && grid[2][col] == user) {
            return true;
        }

        if (grid[0][0] == user && grid[1][1] == user && grid[2][2] == user) {
            return true;
        }

        if (grid[2][0] == user && grid[1][1] == user && grid[0][2] == user) {
            return true;
        }

            return false;

    }
    public void print() {
        for(int row=0; row<3; row++) {
            for(int col=0; col<3; col++){
                System.out.print(grid[row][col]);
                System.out.print("|");
            }
            System.out.println();
        }
        //String s = "  |  |  ";
        //String r = "--------";

        //System.out.println(s);
        //System.out.println(r);
        //System.out.println(s);
        //System.out.println(r);
        //System.out.println(s);

    }

}

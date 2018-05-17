package com.wfy.work6.nttt;

import com.wfy.work6.nttt.networking.Connection;

import java.io.IOException;
import java.util.Scanner;

public class NTTT {
    private static Board board = new Board();
    private static Connection con;

    public static void main(String[] args) {
        System.out.println("Welcome to NetTicTacToe!");
        Scanner scan = new Scanner(System.in);

        // Connect to other player

        // This tag allows us to reference this line so we can break out of the while loop
        loop:
        while (true) {
            System.out.print("Do you want to be a host or client? ");
            switch (scan.next().toLowerCase()) {
                case "h":
                case "host":
                    while (true) {
                        System.out.print("What port will you host on? ");
                        try {
                            con = new Connection(scan.nextInt());
                            break loop;
                        } catch (IOException e) {
                            System.out.println("Could not host on that port");
                        } catch (Exception e) {
                            System.out.println("Invalid port");
                            scan.next();
                        }
                    }
                case "c":
                case "client":
                    while (true) {
                        System.out.print("What IP address is the host on? ");
                        String ip = scan.next();
                        System.out.print("What port is the host on? ");
                        try {
                            con = new Connection(ip, scan.nextInt());
                            break loop;
                        } catch (IOException e) {
                            System.out.println("Could not find server");
                        } catch (Exception e) {
                            System.out.println("Invalid IP/port");
                            scan.next();
                        }
                    }
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }

        // Start TTT game
        // Show board info
        System.out.println(
                "Here's how you will input your moves:\n" +
                        "- 1 2 3\n" +
                        "\n" +
                        "1 - - -\n" +
                        "\n" +
                        "2 - - -\n" +
                        "\n" +
                        "3 - - -\n"
        );

        // Turns
        boolean myTurn = con.isHost();
        while (hasWinner() == -1) {
            // If host, move
            if (myTurn) {
                move();
            }
            // Else, wait for host move
            else {
                System.out.println("Waiting for the other player");
                try {
                    int x = con.receiveInt();
                    int y = con.receiveInt();
                    board.set(x, y, (con.isHost() ? Slot.O : Slot.X));
                    System.out.println("Got player's move! (" + x + ", " + y + ")");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            myTurn = !myTurn;
        }

        int winner = hasWinner();
        switch (winner) {
            // Host won
            case 1:
                if (con.isHost())
                    System.out.println("You won!!");
                else
                    System.out.println("You lost...");
                break;
            // Client won
            case 0:
                if (con.isHost())
                    System.out.println("You lost...");
                else
                    System.out.println("You won!!");
                break;
            // Tie
            case 2:
                System.out.println("It's a tie!");
                break;
        }
        System.out.println(board);
    }

    public static void move() {
        // Display board and prompt
        System.out.println("Make your move:\n" + board);
        Scanner scan = new Scanner(System.in);
        int x, y;

        while (true) {
            int input;

            // Get X
            while (true) {
                System.out.print("X (1-3): ");
                try {
                    input = scan.nextInt();
                } catch (Exception e) {
                    System.out.println("Wrong Input");
                    scan.next();
                    continue;
                }
                // Check input
                if (input < 1 || input > 3) {
                    System.out.println("Wrong Input");
                    continue;
                }
                x = input;
                break;
            }

            // Get Y
            while (true) {
                System.out.print("Y (1-3): ");
                try {
                    input = scan.nextInt();
                } catch (Exception e) {
                    System.out.println("Wrong Input");
                    scan.next();
                    continue;
                }
                // Check input
                if (input < 1 || input > 3) {
                    System.out.println("Wrong Input");
                    continue;
                }
                y = input;
                break;
            }

            // Check if it's valid
            if (board.get(x, y) == Slot.NONE) {
                board.set(x, y, (con.isHost() ? Slot.X : Slot.O));
                break;
            } else {
                System.out.println("That's already occupied!");
            }
        }
        System.out.println("Sending to " + (con.isHost() ? "client" : "host"));
        con.sendInt(x);
        con.sendInt(y);
    }

    /**
     * @return 1 if host wins, 0 if client wins, and -1 if no one has won yet, 2 if tie
     */
    public static int hasWinner() {
        // Check rows
        for (int y = 1; y <= 3; y++) {
            if (board.get(1, y) == Slot.X && board.get(2, y) == Slot.X && board.get(3, y) == Slot.X)
                return 1;
            if (board.get(1, y) == Slot.O && board.get(2, y) == Slot.O && board.get(3, y) == Slot.O)
                return 0;
        }

        // Check columns
        for (int x = 1; x <= 3; x++) {
            if (board.get(x, 1) == Slot.X && board.get(x, 2) == Slot.X && board.get(x, 3) == Slot.X)
                return 1;
            if (board.get(x, 1) == Slot.O && board.get(x, 2) == Slot.O && board.get(x, 3) == Slot.O)
                return 0;
        }

        // Check diagonals
        if ((board.get(1, 1) == Slot.X && board.get(2, 2) == Slot.X && board.get(3, 3) == Slot.X) ||
                (board.get(1, 3) == Slot.X && board.get(2, 2) == Slot.X && board.get(3, 1) == Slot.X))
            return 1;

        if ((board.get(1, 1) == Slot.O && board.get(2, 2) == Slot.O && board.get(3, 3) == Slot.O) ||
                (board.get(1, 3) == Slot.O && board.get(2, 2) == Slot.O && board.get(3, 1) == Slot.O))
            return 0;

        for (int y = 1; y <= 3; y++) {
            for (int x = 1; x <= 3; x++) {
                if (board.get(x, y) == Slot.NONE) {
                    // Game is still going and no one has won yet
                    return -1;
                }
            }
        }

        // No one won and there are no empty spaces
        return 2;
    }
}

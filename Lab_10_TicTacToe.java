import java.util.Scanner;

public class Lab_10_TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X"; // X starts the game

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the board and start the game loop
        clearBoard();
        display();
        
        while (true) {
            // Get player input
            System.out.println("Player " + currentPlayer + "'s turn.");
            int rowMove = getRangedInt(scanner, "Enter row (1-3): ", 1, 3);
            int colMove = getRangedInt(scanner, "Enter column (1-3): ", 1, 3);

            // Convert player move coordinates to array indices
            int row = rowMove - 1;
            int col = colMove - 1;

            // Check if the move is valid
            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                
                // Display the updated board
                display();
                
                // Check for win or tie
                if (isWin(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                } else if (isTie()) {
                    System.out.println("It's a tie!");
                    break;
                }
                
                // Toggle player
                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
        
        scanner.close();
    }

    private static void clearBoard() {
        // Clear the board by setting all elements to empty strings
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        // Display the current state of the board
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j]);
                if (j < COL - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < ROW - 1) {
                System.out.println("---------");
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        // Check if the specified cell is empty
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        // Check for win conditions
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player) {
        // Check for win in columns
        for (int i = 0; i < COL; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        // Check for win in rows
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        // Check for win in diagonals
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
               (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        // Check for a tie condition: all spaces on the board are filled
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false; // Empty cell found, game not tied yet
                }
            }
        }
        return true; // No empty cells found, game tied
    }

    private static int getRangedInt(Scanner scanner, String prompt, int low, int high) {
        // Prompt the user for input within a specified range
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextInt();
            if (input < low || input > high) {
                System.out.println("Input out of range. Please enter a number between " + low + " and " + high + ".");
            }
        } while (input < low || input > high);
        return input;
    }
}

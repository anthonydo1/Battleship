package battleship;

import java.util.Random;
import java.util.Scanner;

public class Battleship {
    static String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    static int[] shipSizes = {5, 4, 3, 3, 2};
    static String[] shipNames = {"Aircraft Carrier", "Battleship", "Destroyer", "Submarine", "Patrol Boat"};
    
    private Cell[][] board;
    private int turns;
    private int shipsDestroyed;
    
    public Battleship() {
        board = new Cell[10][10];
        turns = 0;
        shipsDestroyed = 0;
    }
    
    public void startGame() throws Exception {
        generateBoard();
        addShips();
        
        Scanner sc = new Scanner(System.in);
        printBoard();
        
        while (turns < 50) {
            turns++;
            int row = 0;
            int column = 0;
            try {
                System.out.print("\nSelect row: ");
                row = sc.next().toUpperCase().charAt(0) - 'A';
                
                System.out.print("Select column: ");
                column = sc.nextInt() - 1;
                
                fire(row, column);
                printBoard();
            } catch (Exception e){
                System.out.println("Invalid Input!");
            }
            
            if (shipsDestroyed == 5) {
                System.out.println("You won the game!");
                break;
            }
            
            if (turns >= 50) {
                System.out.println("You lost the game!");
                break;
            }
        }
        
        printAnswers();
    }
    
    public void generateBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Cell();
            }
        }
    }
    
    public void fire(int row, int column) {
        if (row >= 10 || column >= 10) System.out.println("Invalid input");
        if (board[row][column].hasShip()) {
            board[row][column].setDestroyed(true);
            System.out.println("Hit!");
            if (board[row][column].getShip().isDestroyed()) {
                System.out.println("You destroyed: " + board[row][column].getShip().getName());
                shipsDestroyed++;
            }
            return;
        }
        board[row][column].setMissed(true);
        System.out.println("Miss!");
    }
    
    public void addShips() {
        Random random = new Random();
        
        for (int i = 0; i < shipSizes.length; i++) {
            Ship ship = new Ship(shipNames[i], shipSizes[i]);
            int orientation = random.nextInt(2);
            
            boolean found = false;
                
            while (!found) {
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                boolean foundSpot = true;
                
                for (int j = 0; j < shipSizes[i]; j++) {
                    if (orientation == 0) {
                        if (col + j >= 10 || board[row][col + j].hasShip()) foundSpot = false;
                    } else {
                        if (row + j >= 10 || board[row + j][col].hasShip()) foundSpot = false;
                    }
                }
                
                if (foundSpot == false) {
                    continue;
                }
                
                for (int j = 0; j < shipSizes[i]; j++) {
                    if (orientation == 0) {
                        board[row][col + j].setHasShip(true);
                        board[row][col + j].setShip(ship);
                        ship.addShipCell(board[row][col + j]);
                    } else {
                        board[row + j][col].setHasShip(true);
                        board[row + j][col].setShip(ship);
                        ship.addShipCell(board[row + j][col]);
                    }
                }
                
                found = true;
            }
        }
    }
    
    public void printBoard() {
        System.out.print("  ");
        
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + 1 + " ");
        }
        
        for (int i = 0; i < board.length; i++) {
            System.out.print("\n");
            System.out.print(alphabet[i] + " ");
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].hasShip() && board[i][j].isDestroyed()) {
                    System.out.print("X ");
                } else if (board[i][j].getMissed() == true) {
                    System.out.print("M ");
                } else {
                    System.out.print("O ");
                }
            }
        } 
    }
    
    public void printAnswers() {
        System.out.print("  ");
        
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + 1 + " ");
        }
        
        for (int i = 0; i < board.length; i++) {
            System.out.print("\n");
            System.out.print(alphabet[i] + " ");
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].hasShip()) {
                    System.out.print("X ");
                } else if (board[i][j].getMissed() == true) {
                    System.out.print("M ");
                } else {
                    System.out.print("O ");
                }
            }
        } 
    }
    
    public static void main(String[] args) {
        Battleship bs = new Battleship();
        try {
            bs.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

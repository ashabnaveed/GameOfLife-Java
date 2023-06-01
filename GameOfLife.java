//Ashab Naveed
//Henry Wise Wood High School
//Computer Science 20
//2022 - 2023 Semester Two

//imports array list utility
import java.util.ArrayList;

public class GameOfLife {

  //creates an array for the gameboard
  boolean [][] gameBoard;
  int row; //initalizes a row and column variable
  int col;

  //creates the game of life with the board size being set to 10x10
  public GameOfLife (boolean[][] board) { 
    
    gameBoard = board;
    row = 10; 
    col = 10; 
    
  }

  public void print() { //prints the dead/alive cells via a nested for loop that iterates through the number of rows and coloums. If the array of i and j is true, prints a white (alive) cell in that place, or else prints a black (dead) cell
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (gameBoard[i][j] == true) {
          System.out.print('⬜'); //white cell if alive
        } else {
          System.out.print('⬛'); //black cell if dead
        }
      }
      System.out.println(""); //formatting into the square area by creating a new line after each line is produced 
    }
  }

  public void setAlive (int x, int y, boolean isAlive) { //sets the tile to alive if the board is not null and the x value inputted is less than a row or greater than and equal to 0, and if the y value meets the same conditions.
    if (gameBoard != null && ((x < row && x >= 0) && (y < col && y >= 0))) {
      gameBoard[x][y] = isAlive; //sets the tile at x and y to be alive
    }
  }

  public boolean isAlive (int x, int y) { //if the board is not null and the x value and y value are within bounds and a valid integer, returns the alive star. if it isnt alive then returns false
    if (gameBoard != null && ((x < row && x >= 0) && (y < col && y >= 0))) {
      return gameBoard[x][y];
    } else {
      return false;
    }
  }

  public void calculateNextGeneration () { //calculates the next generation

    boolean [][] editedBoard = new boolean[row][col]; //creates an edited board with the row and column values inputted
    
    if (gameBoard != null) { //nested loop throug h each row and column 
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          if (gameBoard[i][j] == true) { //checks if the current cell is alive
            int aliveNeighbours = this.countNeighbours(i, j, true); //counts the number of alive neighbours
            if (aliveNeighbours > 3 || aliveNeighbours < 2) { //if the neighbours alive are more than 3 (4 or more) or less than 2 (one or less)
              editedBoard[i][j] = false; //the cell is dead
            } else {
            editedBoard[i][j] = gameBoard[i][j]; //updates the board to reflect the current cell
            }
          }

          else if (gameBoard[i][j] == false) { //if the current cell is dead
            int aliveNeighbours = this.countNeighbours(i, j, false); //counts alive neighbours relative to this cell
            if (aliveNeighbours == 3) { //if the neighbours equal 3
              editedBoard[i][j] = true; //makes the cell nearby alive
            } else {
            editedBoard[i][j] = gameBoard[i][j]; //updates board to reflect the cell
            }
          }
          
        }
      }
    }

    gameBoard = editedBoard; //updates the overall gameboard for the next generation
  }


  //using conditional statements to handle cases and taking inputs of the position and status of the cell
  public int countNeighbours (int x, int y, boolean isAlive) {
    int counter = 0; //alive neighbours counter

    //top row but not corners
    if (x == 0 && y!= 0 && y != (col-1)) {  
      for (int i = -1; i < 2; i++) { //check the three cells above and 2 beside
        for (int j = 0; j < 2; j++) {
          if (gameBoard[x + j][y + i] == true) { //check if the cell is alive
            counter++; //if it is increments the counter
          }
        }
      }
    }

      //bottom row but not corners
    else if (x == (row-1) && y!= 0 && y != (col-1)) {   //checks 3 cells below and 2 beside the current cell
      for (int i = -1; i < 2; i++) {
        for (int j = -1; j < 1; j++) {
          if (gameBoard[x + j][y + i] == true) { //checks if the cell is alive
            counter++; //increments
          }
        }
      }
    }

      //top left corner
    else if (x == 0 && y == 0) { //checks and counts nearby alive cells 
      for (int i = 0; i < 2; i++) { 
        for (int j = 0; j < 2; j++) {
          if (gameBoard[x + j][y + i] == true) {
            counter++; //increment
          }
        }
      }
    }

      //bottom left corner
    else if (x == (row-1) && y == 0) { //checks and counts nearby alive cells
      for (int i = 0; i < 2; i++) {
        for (int j = -1; j < 1; j++) {
          if (gameBoard[x + j][y + i] == true) {
            counter++; //increments
          }
        }
      }
    }

      //bottom right corner
    else if (x == (row-1) && y == (col-1)) { //checks and counts nearby alive cells
      for (int i = -1; i < 1; i++) {
        for (int j = -1; j < 1; j++) {
          if (gameBoard[x + j][y + i] == true) {
            counter++; //increments
          }
        }
      }
    }

      //top right corner
    else if (x == 0 && y == (col-1)) { //checks and counts nearby alive cells
      for (int i = -1; i < 1; i++) {
        for (int j = 0; j < 2; j++) {
          if (gameBoard[x + j][y + i] == true) {
            counter++; //increments
          }
        }
      }
    }

      //left side no corner
    else if (y == 0 && x != 0 && x != (row-1)) { //checks nearby cells and counts
      for (int i = 0; i < 2; i++) { 
        for (int j = -1; j < 2; j++) {
          if (gameBoard[x + j][y + i] == true) {
            counter++; //increments
          }
        }
      }
    }

      //right side no corner
    else if (y == (col-1) && x != 0 && x != (row-1)) { //same as above for the rest of the else statements...
      for (int i = -1; i < 1; i++) {
        for (int j = -1; j < 2; j++) {
          if (gameBoard[x + j][y + i] == true) {
            counter++;
          }
        }
      }
    }

    else { //middle of the board
      for (int i = -1; i < 2; i++) {
        for (int j = -1; j < 2; j++) {
          if (gameBoard[x + j][y + i] == true) {
            counter++;
          }
        }
      }
    }

    if (isAlive == true && counter != 0) { //if the cell is alive and the counter is not 0
      counter -= 1; //subtracts one from the counter to ensure the cell itself is not counted
    }

    return counter; //returns the counter after incrementation
    
  }
  
} 

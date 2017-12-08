package challenge;

import java.util.Arrays;
import java.util.Random;

public class BEchlng {

    public static void main(String[] args)
    {
        int numRows = Integer.valueOf(args[0]), numCols = Integer.valueOf(args[1]), generations = Integer.valueOf(args[2]);
 
        // Initializing the grid.
        Boolean[][] curGen = new Boolean[numRows][numCols];

        // sets dead cells array
        for (int row = 0; row < numRows; row++) {
            Arrays.fill(curGen[row], false);
        }

        // fills array with random booleans
        Random random = new Random();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                curGen[row][col] = random.nextBoolean();
            }
        }
 
        // Displaying the grid
        System.out.println("Original Generation");
        printGeneration(curGen, numRows, numCols);
        for (int gens = 0; gens < generations; gens++) {
	        System.out.println();
	        curGen = nextGeneration(curGen, numRows, numCols);
        }
    }
 
    static void printGeneration(Boolean[][] curGen, int numRows, int numCols) {
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numCols; j++)
            {
                if (!curGen[i][j])
                    System.out.print(".");
                else
                    System.out.print("*");
            }
            System.out.println();
        }

    }
    // Function create the next generation
    static Boolean[][] nextGeneration(Boolean grid[][], int numRows, int numCols)
    {
        Boolean[][] future = new Boolean[numRows][numCols];
        // sets future cells array
        for (int row = 0; row < numRows; row++) {
            Arrays.fill(future[row], false);
        }
 
        // Loop through every cell
        for (int currRowl = 1; currRowl < numRows - 1; currRowl++)
        {
            for (int currCol = 1; currCol < numCols - 1; currCol++)
            {
                // finding no Of Neighbors that are alive
                int aliveNeighbours = 0;
                for (int row = -1; row <= 1; row++)
                    for (int col = -1; col <= 1; col++)
                        aliveNeighbours += grid[currRowl + row][currCol + col] ? 1:0;
 
                // The cell needs to be subtracted from
                // its neighbors as it was counted before
                aliveNeighbours -= grid[currRowl][currCol] ? 1:0;
 
                // Implementing the Rules of Life
 
                // Cell dies
                if ((grid[currRowl][currCol]) && (aliveNeighbours < 2))
                    future[currRowl][currCol] = new Boolean(false);
 
                // Cell dies due to over population
                else if ((grid[currRowl][currCol]) && (aliveNeighbours > 3))
                    future[currRowl][currCol] =  new Boolean(false);
 
                // A new cell is born
                else if ((!grid[currRowl][currCol]) && (aliveNeighbours == 3))
                    future[currRowl][currCol] =  new Boolean(true);
 
                // Remains the same
                else
                    future[currRowl][currCol] = grid[currRowl][currCol];
            }
        }
 
        System.out.println("Next Generation");
        printGeneration(future, numRows, numCols);
        
        return future;
    }
}

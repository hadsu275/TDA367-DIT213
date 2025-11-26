package game;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class MazeGenerator {
    private static final Random RANDOM = new Random();


    public static void generateMaze(Cell[][] grid) {
        int rows = grid.length; //Length of rows
        int cols = grid[0].length; // Length of columns
        Cell current; //Current cell
        Deque<Cell> stack = new ArrayDeque<>(); //Stack to hold cells path
        Cell[] neighbors; //Array to hold unvisited neighbors

        //1. Initial starting cell
        Cell startCell = grid[0][0];
        startCell.setVisited(true);
        stack.push(startCell);

        //2. While there are unvisited cells
        while (!stack.isEmpty()) {
            //1. Look at the current cell at the top of stack and make it current
            current = stack.peek();

            //Get unvisited neighbors
            neighbors = getUnvisitedNeighbors(current, grid, rows, cols);

            //2.If the current cell has any neighbours which have not been visited
            if (neighbors.length > 0) {
                //Choose one of the unvisited neighbors at random
                Cell neighbor = neighbors[RANDOM.nextInt(neighbors.length)];

                //3. Remove wall between current and neighbor
                removeWall(current, neighbor);

                //4. Mark neighbor as visited push to stack
                neighbor.setVisited(true);
                stack.push(neighbor); //Make neighbor the current cell

            } else {
                stack.pop(); //Else pop a cell from stack
            }
        }
        addLoops(grid, 0.1);
    }


    private static Cell[] getUnvisitedNeighbors(Cell current, Cell[][] grid, int gridRows, int gridCols) {
        int currentRow = current.getRow();
        int currentCol = current.getCol();
        Cell[] tempNeighbors = new Cell[4];
        int count = 0;

        //top neighbor, only adds if is not on the first row and not visited
        if (currentRow > 0 && !grid[currentRow - 1][currentCol].isVisited()) {
            tempNeighbors[count++] = grid[currentRow - 1][currentCol];
        }
        //bottom neighbor, only adds if its not on the last row and not visited
        if (currentRow < gridRows - 1 && !grid[currentRow + 1][currentCol].isVisited()) {
            tempNeighbors[count++] = grid[currentRow + 1][currentCol];
        }

        //left neighbor, only adds if its not on the first column and not visited
        if (currentCol > 0 && !grid[currentRow][currentCol - 1].isVisited()) {
            tempNeighbors[count++] = grid[currentRow][currentCol - 1];
        }
        //right neighbor, only adds if its not on the last column and not visited
        if (currentCol < gridCols - 1 && !grid[currentRow][currentCol + 1].isVisited()) {
            tempNeighbors[count++] = grid[currentRow][currentCol + 1];
        }
        //Create array of exact size
        Cell[] neighbors = new Cell[count];
        //Copy valid neighbors to new array
        for (int i = 0; i < count; i++) {
            neighbors[i] = tempNeighbors[i];
        }
        return neighbors; //Return array of unvisited neighbors
    }

    private static void removeWall(Cell current, Cell neighbor) {
        int rowDiff = neighbor.getRow() - current.getRow();
        int colDiff = neighbor.getCol() - current.getCol();

        if (rowDiff == -1) { //Neighbor is above current
            current.setTopWall(false);
            neighbor.setBottomWall(false);
        }
        else if (rowDiff == 1) { //Neighbor is below current
            current.setBottomWall(false);
            neighbor.setTopWall(false);
        }
        else if (colDiff == -1) { //Neighbor is left of current
            current.setLeftWall(false);
            neighbor.setRightWall(false);
        }
        else if (colDiff == 1) { //Neighbor is right of current
            current.setRightWall(false);
            neighbor.setLeftWall(false);
        }
    }

    private static void addLoops(Cell [][] grid, double loopFactor){
        int rows = grid.length;
        int cols = grid[0].length;

        int wallsToRemove =(int) (rows * cols * loopFactor);

        for(int doneLoops = 0; doneLoops <= wallsToRemove;){
            Cell current = grid[RANDOM.nextInt(rows)][RANDOM.nextInt(cols)];
            Cell neighbor = getNeighbor(current, grid);
            if(hasWallBetween(current, neighbor)){
                removeWall(current,neighbor);
                doneLoops++;
            }
        }
    }

    private static Cell getNeighbor(Cell current, Cell[][] grid){
        int row = current.getRow();
        int col = current.getCol();

        int neighborRow = row;
        int neighborCol = col;

        while(true) {
            int dir = RANDOM.nextInt(4); // 0 up, 1 down, 2 left, 3 right

            switch (dir) {
                case 0 -> neighborRow = row - 1; //top
                case 1 -> neighborRow = row + 1; //bottom
                case 2 -> neighborCol = row - 1; //left
                case 3 -> neighborCol = row + 1; //right
            }

            if(isValidNeighbor(grid ,neighborRow,neighborCol)){
                return grid[neighborRow][neighborCol];
            }
        }

    }

    private static boolean isValidNeighbor(Cell[][] grid, int neighborRow, int neighborCol){
        int rows = grid.length;
        int cols =  grid[0].length;


        if(neighborRow < 0 || neighborRow >= rows || neighborCol < 0 || neighborCol >= cols){
            return false;
        }
        else{return true;}
    }

    //Helper method to check if there is a wall between two adjacent cells
    private static boolean hasWallBetween(Cell current, Cell neighbor){
        int rowDiff = neighbor.getRow() - current.getRow();
        int colDiff = neighbor.getCol() - current.getCol();

        if(rowDiff == -1){
            return current.hasTopWall() && neighbor.hasBottomWall();
        }
        if(rowDiff == 1){
            return current.hasBottomWall() && neighbor.hasTopWall();
        }
        if(colDiff == -1) {
            return current.hasLeftWall() && neighbor.hasRightWall();
        }
        if(colDiff == 1){
            return current.hasRightWall() && neighbor.hasLeftWall();
        }
        return false;
    }
}

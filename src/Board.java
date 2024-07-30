import java.util.Random;
public class Board {
    private  Cell [][] cells;


    public  Board(int rows , int cols, int seed, int range)
    {
        Random random = new Random(seed);
        cells = new Cell[rows][cols];
        for (int i = 0 ; i < rows ; i++)
        {
            for (int j = 0 ; j < cols; j++)
            {
                cells[i][j] =  (random.nextInt(range) % 2 == 0) ? new Cell(Cell.State.DEAD) : new
                        Cell(Cell.State.HEALTHY);
            }
        }
    }

    public void nextGeneration() {
        Cell[][] updatedBoard = new Cell[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                int numHealthyNeighbors = countNeighbors(i, j, Cell.State.HEALTHY);
                int numSickNeighbors = countNeighbors(i, j, Cell.State.SICK);
                updatedBoard[i][j] = cells[i][j].nextGeneration(numHealthyNeighbors, numSickNeighbors);
            }
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = updatedBoard[i][j];
            }
        }
    }


    public  int  countNeighbors (int row , int col , Cell.State state)
    {
        int count = 0;
        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                if (i == row && j == col) continue;
                if (i >= 0 && i < cells.length && j >= 0 && j < cells[0].length) {
                    if (cells[i][j].getStatus() == state) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    @Override
    public String toString() {
        String theBoard = "";
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                theBoard += cells[i][j].toString() ;
                if (j  <  cells[i].length -1 )
                    theBoard += " ";

            }
            if (i != cells.length - 1)
                theBoard += "\n";
        }
        return theBoard;
    }

    @Override
    public int hashCode() {
        int sum = 1;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                int cellHashCode = cells[i][j] != null ? cells[i][j].hashCode() : 0;
                sum = 31 * sum + cellHashCode;
            }
        }
        return sum;
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }

        Board otherBoard = (Board) other;

        if (cells.length != otherBoard.cells.length)
            return false;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[j].length != otherBoard.cells[j].length  || !cells[i][j].equals(otherBoard.cells[i][j]))
                    return false;
            }
        }

        return true;
    }


    public Board(Board other) {
        int rows = other.cells.length;
        int cols = other.cells[0].length;
        this.cells = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.cells[i][j] = new Cell(other.cells[i][j].getStatus());
            }
        }
    }
    public boolean areAllCellsDead() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getStatus() != Cell.State.DEAD) {
                    return false;
                }
            }
        }
        return true;
    }
}


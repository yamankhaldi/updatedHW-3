public class ManageBoard {
    private final Board firstBoard;
    private Board currentBoard;
    private Board previousBoard;

    public ManageBoard(Board board) {
        this.firstBoard = new Board(board);
        this.currentBoard = new Board(board);
        this.previousBoard = null;
    }

    public Board getFirstBoard() {
        return firstBoard;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public Board getPreviousBoard() {
        return previousBoard;
    }

    public void updateBoard() {
        previousBoard = new Board(currentBoard);
    }

    public void nextGeneration() {
        updateBoard();
        currentBoard.nextGeneration();
    }

    public void reset() {
        currentBoard = new Board(firstBoard);
        previousBoard = null;
    }
}
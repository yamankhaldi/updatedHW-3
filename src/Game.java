public class Game {
    private final int maxGeneration;
    private final ManageBoard manageBoard;

    public Game(int rows, int cols, int seed, int range, int maxGeneration) {
        Board board = new Board(rows, cols, seed, range);
        this.maxGeneration = maxGeneration;
        this.manageBoard = new ManageBoard(board);
    }

    public void runGame() {
        manageBoard.reset();
        System.out.println("Generation 0:");
        System.out.println(manageBoard.getCurrentBoard());

        for (int i = 1; i <= maxGeneration; i++) {
            manageBoard.nextGeneration();
            System.out.println("Generation " + i + ":");
            System.out.println(manageBoard.getCurrentBoard());

            if (manageBoard.getCurrentBoard().areAllCellsDead()) {
                System.out.println("All cells are dead.");
                break;
            }

            if (manageBoard.getPreviousBoard() != null && manageBoard.getPreviousBoard().equals(manageBoard.getCurrentBoard())) {
                System.out.println("Cells have stabilized.");
                break;
            }

            if (i == maxGeneration) {
                System.out.println("The generation limitation was reached.");
            }
        }
    }
}

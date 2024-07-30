public class Cell {
    public enum State {
        HEALTHY, SICK, DYING, DEAD
    }

    private final State status;

    public Cell(State status) {
        this.status = status;
    }

    public State getStatus() {
        return status;
    }

    public Cell nextGeneration(int healthyNeighbors, int sickNeighbors) {
        if (status == State.DEAD) {
            if (healthyNeighbors == 3) {
                return new Cell(State.HEALTHY);
            } else {
                return new Cell(State.DEAD);
            }
        } else if (status == State.HEALTHY) {
            if (healthyNeighbors < 2 || healthyNeighbors > 3 || sickNeighbors > 3) {
                return new Cell(State.SICK);
            } else {
                return new Cell(State.HEALTHY);
            }
        } else if (status == State.SICK) {
            if (healthyNeighbors < 2 || healthyNeighbors > 3 || sickNeighbors > 2) {
                return new Cell(State.DYING);
            } else {
                return new Cell(State.HEALTHY);
            }
        } else if (status == State.DYING) {
            if (healthyNeighbors == 3 && sickNeighbors <= 1) {
                return new Cell(State.HEALTHY);
            } else {
                return new Cell(State.DEAD);
            }
        }
        return new Cell(State.DEAD);
    }

    @Override
    public String toString() {
        if (this.status == State.HEALTHY)
            return "H";
        if (this.status == State.SICK)
            return "S";
        if (this.status == State.DYING)
            return "D";
        if (this.status == State.DEAD)
            return "-";

        return "-";
    }
    @Override
    public int hashCode() {
        return status.hashCode();
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Cell)) {
            return false;
        }
        Cell otherCell = (Cell) other;
        return (this.status == otherCell.status);
    }



}
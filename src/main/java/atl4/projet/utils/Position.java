package atl4.projet.utils;

import java.util.Objects;

public class Position {
    public int row, column;
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
    public void reverse() {
        int buffer = column;
        column = row;
        row = buffer;
    }
    public void add(Position other) {
        column += other.column;
        row += other.row;
    }
    public static Position add(Position p1, Position p2) {
        return new Position(p1.row + p2.row, p1.column + p2.column);
    }
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Position otherPosition = (Position) o;
        // field comparison
        return Objects.equals(row, otherPosition.row)
            && Objects.equals(column, otherPosition.column);
    }
}

package atl4.projet.model;

import atl4.projet.utils.Position;

public class Utils {
    public static Die[] getDice() {
        return new Die[] {
                new Die(new Position(0, 0), new Position(2, 0), new Position(3, 0), new Position(3, 1), new Position(4, 1), new Position(5, 2)),
                new Die(new Position(0, 1), new Position(1, 1), new Position(2, 1), new Position(0, 2), new Position(1, 0), new Position(1, 2)),
                new Die(new Position(2, 2), new Position(3, 2), new Position(4, 2), new Position(1, 3), new Position(2, 3), new Position(3, 3)),
                new Die(new Position(4, 0), new Position(5, 1), new Position(5, 1), new Position(1, 5), new Position(0, 4), new Position(0, 4)),
                new Die(new Position(0, 3), new Position(1, 4), new Position(2, 5), new Position(2, 4), new Position(3, 5), new Position(5, 5)),
                new Die(new Position(4, 3), new Position(5, 3), new Position(4, 4), new Position(5, 4), new Position(3, 4), new Position(4, 5)),
                new Die(new Position(5, 0), new Position(5, 0), new Position(5, 0), new Position(0, 5), new Position(0, 5), new Position(0, 5))
        };
    }
    public static Piece[] getPieces() {
        return new Piece[] {
                new Piece("dot", new Position(0, 0)),
                new Piece("line2", new Position(0, 0), new Position(1, 0)),
                new Piece("corner", new Position(0, 0), new Position(0, 1), new Position(1, 0)),
                new Piece("square", new Position(0, 0), new Position(0, 1), new Position(1, 0), new Position(1, 1)),
                new Piece("line3", new Position(0, 0), new Position(0, 1), new Position(0, 2)),
                new Piece("l", new Position(0, 0), new Position(0, 1), new Position(0, 2), new Position(1, 2)),
                new Piece("z", new Position(0, 0), new Position(0, 1), new Position(1, 1), new Position(1, 2)),
                new Piece("t", new Position(0, 0), new Position(0, 1), new Position(0, 2), new Position(1, 1)),
                new Piece("line4", new Position(0, 0), new Position(1, 0), new Position(2, 0), new Position(3, 0))
        };
    }
    public static void rollDice(Die[] dice) {
        for (Die die : dice)
            die.roll();
    }
    public static Position[] getDiceValues(Die[] dice) {
        var result = new Position[dice.length];
        for(int i=0; i<dice.length; ++i)
            result[i] = dice[i].getValue();
        return result;
    }
    public enum UserInput {
        ROTATE_LEFT, ROTATE_RIGHT, FLIP
    }
}

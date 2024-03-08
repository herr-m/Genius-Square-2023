package atl4.projet.model;

import atl4.projet.utils.Position;
import java.util.Objects;

public class Piece {
    private final String _name;
    private final Position[] _structure;

    private void normalize() {
        if(_structure.length == 0) { return; }

        int deltaFirst = _structure[0].column, deltaSecond = _structure[0].row;
        for(var position : _structure) {
            if(position.column < deltaFirst) { deltaFirst = position.column; }
            if(position.row < deltaSecond) { deltaSecond = position.row; }
        }

        for(var position : _structure) {
            position.column -= deltaFirst;
            position.row -= deltaSecond;
        }
    }
    public Piece(String name, Position... blocks) {
        Objects.requireNonNull(blocks);
        for(var block : blocks)
            Objects.requireNonNull(block);

        _name = name;
        _structure = blocks;
        normalize();
    }
    public void reverse() {
        for(var position : _structure)
            position.reverse();
    }
    public void rotateClockwise() {
        reverse();
        for(var position : _structure)
            position.column = -position.column;
        normalize();
    }
    public void rotateCounterClockwise() {
        reverse();
        for(var position : _structure)
            position.row = -position.row;
        normalize();
    }
    public String getName() {
        return _name;
    }
    public Position[] getStructure() { return _structure; }
}

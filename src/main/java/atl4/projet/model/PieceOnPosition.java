package atl4.projet.model;

import atl4.projet.utils.Position;

public record PieceOnPosition(Piece piece, Position position) {
    public boolean contains(Position other) {
        for(var block : piece.getStructure())
            if(other.equals(Position.add(position, block)))
                return true;
        return false;
    }
    public boolean intersects(PieceOnPosition other) {
        for(var block : piece.getStructure())
            if(other.contains(Position.add(position, block)))
                return true;
        return false;
    }
    public Position[] getPositions() {
        var structure = piece.getStructure();
        var result = new Position[structure.length];
        for(int i = 0; i < structure.length; ++i) {
            result[i] = Position.add(structure[i], position);
        }
        return result;
    }
}

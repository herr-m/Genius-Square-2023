package atl4.projet.model;

import atl4.projet.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<PieceOnPosition> _pieces;
    private PieceOnPosition _hintPiece;
    private final List<Position> _blockers;

    public Board() {
        _pieces = new ArrayList<>();
        _blockers = new ArrayList<>();
    }
    public void reset() {
        _pieces.clear();
        _blockers.clear();
        _hintPiece = null;
    }
    public Position removePiece(Piece piece) {
        var found = _pieces.stream().filter(boardPiece -> boardPiece.piece().equals(piece)).findFirst();
        if(found.isPresent()) {
            _pieces.remove(found.get());
            return found.get().position();
        }
        return null;
    }
    public void placePiece(PieceOnPosition piece) {
        for(var blocker : _blockers)
            if(piece.contains(blocker)) { throw new RuntimeException("Can't place a piece on a blocker."); }
        for(var boardPiece : _pieces)
            if(!piece.equals(boardPiece) && piece.intersects(boardPiece)) { throw new RuntimeException("Can't place a piece on another piece."); }
        for(var block : piece.getPositions())
            if(block.row >= 6 || block.column >= 6) { throw new RuntimeException("Can't place a piece outside the board."); }
        _pieces.add(piece);
    }
    public void placeBlockers(Position... positions) {
        _blockers.addAll(List.of(positions));
    }
    public void placeHint(PieceOnPosition piece) {
        _hintPiece = piece;
    }
    public PieceOnPosition getHint() {
        return _hintPiece;
    }
    public Piece getPieceAt(Position position) {
        var found = _pieces.stream().filter(piece -> {
            for(Position block : piece.getPositions())
                if(block.equals(position)) { return true; }
            return false;
        }).findFirst();

        return found.map(PieceOnPosition::piece).orElse(null);
    }
    public int getNumberOfPieces() {
        return _pieces.size();
    }
}

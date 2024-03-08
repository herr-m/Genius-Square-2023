package atl4.projet.controller.commands;

import atl4.projet.model.Model;
import atl4.projet.model.Piece;
import atl4.projet.utils.Position;

import java.util.Objects;

public class PlacePiece implements Command {
    private final Model _model;
    private final Piece _piece;
    private final Position _position;
    public PlacePiece(Model model, Piece piece, Position position) {
        _model = Objects.requireNonNull(model);
        _piece = Objects.requireNonNull(piece);
        _position = Objects.requireNonNull(position);
    }
    @Override
    public boolean execute() {
        _model.setSelectedPiece(_piece);
        return _model.placeOrRemovePiece(_position);
    }
    @Override
    public void undo() {
        _model.removePiece(_piece);
    }
}

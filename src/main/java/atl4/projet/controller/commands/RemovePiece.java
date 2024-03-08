package atl4.projet.controller.commands;

import atl4.projet.model.Model;
import atl4.projet.model.Piece;
import atl4.projet.utils.Position;

import java.util.Objects;

public class RemovePiece implements Command {
    private final Model _model;
    private final Position _position;
    private final Piece _removedPiece;

    public RemovePiece(Model model, Position position) {
        _model = Objects.requireNonNull(model);
        _position = Objects.requireNonNull(position);
        _removedPiece = _model.getPieceAt(position);
    }
    @Override
    public boolean execute() {
        _model.setSelectedPiece(null);
        return _model.placeOrRemovePiece(_position);
    }
    @Override
    public void undo() {
        _model.setSelectedPiece(_removedPiece);
        _model.placeOrRemovePiece(_position);
    }
}

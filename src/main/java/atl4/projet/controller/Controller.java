package atl4.projet.controller;

import atl4.projet.controller.commands.Command;
import atl4.projet.controller.commands.PlacePiece;
import atl4.projet.controller.commands.RemovePiece;
import atl4.projet.model.Model;
import atl4.projet.utils.Position;
import atl4.projet.view.View;
import javafx.application.Platform;

import java.util.Stack;

public class Controller {
    private final Model _model;
    private final View _view;
    private final Stack<Command> _commandHistory;
    private final Stack<Command> _undoHistory;

    public Controller(Model model, View view) {
        _model = model;
        _view = view;
        _model.addObserver(_view);
        _view.setController(this);

        _commandHistory = new Stack<>();
        _undoHistory = new Stack<>();
    }
    public void setup() {
        _commandHistory.clear();
        _undoHistory.clear();
        _model.setup();
    }

    public void start() {
        _model.start();
    }

    public void exit() {
        _view.exit();
    }
    public void pieceSlotClicked(String pieceId) {
        _model.toggleSelectPiece(pieceId);
    }
    public void boardCellClicked(Position cell) {
        var pieceOnPosition = _model.getPieceAt(cell);
        var selectedPiece = _model.getSelectedPiece();

        if(selectedPiece != null) {
            var command = new PlacePiece(_model, selectedPiece, cell);
            if(command.execute())
                _commandHistory.push(command);
        }
        else if(pieceOnPosition != null) {
            var command = new RemovePiece(_model, cell);
            if(command.execute())
                _commandHistory.push(command);
        }
    }
    public void boardCellHovered(Position cell) { _model.placeHint(cell); }
    public void rotateClockwise() { _model.rotateClockwise(); }
    public void rotateCounterclockwise() { _model.rotateCounterclockwise(); }
    public void flip() { _model.flip(); }
    public void undo() {
        if(_commandHistory.isEmpty()) {
            _view.displayMessage("No commands to undo");
            return;
        }
        _undoHistory.push(_commandHistory.pop());
        _undoHistory.peek().undo();
    }
    public void redo() {
        if(_undoHistory.isEmpty()) {
            _view.displayMessage("No commands to redo");
            return;
        }
        _commandHistory.push(_undoHistory.pop());
        _commandHistory.peek().execute();
    }
}

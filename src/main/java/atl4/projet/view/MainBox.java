package atl4.projet.view;

import atl4.projet.controller.Controller;
import atl4.projet.model.PieceOnPosition;
import atl4.projet.utils.Position;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;



public class MainBox extends GridPane {
    private final PiecesPane _leftPieces, _rightPieces;
    private final BoardPane _board;
    private final DicePane _dice;
    private final Timer _timer;
    private final Text _messageBox;

    public MainBox() {
        _leftPieces = new LeftPiecesPane();
        _rightPieces = new RightPiecesPane();
        _board = new BoardPane();
        _dice = new DicePane();
        _timer = new Timer();
        _messageBox = new Text();
        _messageBox.getStyleClass().add("text-20");
        add(_leftPieces, 0, 0, 1, 2);
        add(_board, 1, 0);
        add(_dice, 1, 1);
        add(_rightPieces, 2, 0, 1, 2);
        addRow(2, _timer, _messageBox);
        getStyleClass().add("mainBox");
    }
    public void clearBoard() {
        _board.clear();
        _leftPieces.setAllAvailable(true);
        _rightPieces.setAllAvailable(true);
    }
    public void updateDiceBlockers(Position[] diceValues) {
        _dice.update(diceValues);
        _board.updateBlockers(diceValues);
    }
    public void setController(Controller controller) {
        _leftPieces.setController(controller);
        _rightPieces.setController(controller);
        _board.setController(controller);
    }

    public void addPiece(PieceOnPosition piece) {
        _board.addPiece(piece);
        _leftPieces.setPieceAvailable(piece.piece().getName(), false);
        _rightPieces.setPieceAvailable(piece.piece().getName(), false);
    }
    public void removePiece(PieceOnPosition piece) {
        _board.removePiece(piece);
        _leftPieces.setPieceAvailable(piece.piece().getName(), true);
        _rightPieces.setPieceAvailable(piece.piece().getName(), true);
    }
    public void updateHint(PieceOnPosition newHint) {
        _board.updateHint(newHint);
    }
    public void updateTimer(int seconds) { _timer.update(seconds); }
    public void displayMessage(String message) { _messageBox.setText(message); }
}

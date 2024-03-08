package atl4.projet.model;

import atl4.projet.utils.Position;
import atl4.projet.view.View;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Model {
    private enum State { SETUP, STARTED, GAMEOVER, GAMEWON };
    private static PropertyChangeSupport _observable;
    private static final Die[] _dice = Utils.getDice();
    private static final Piece[] _pieces = Utils.getPieces();
    private final Board _board;
    private Piece _selectedPiece;
    private Timer _timer;
    private int _timeLeft;
    private State _state;

    private void removePiece(Position cell) {
        var pieceToRemove = _board.getPieceAt(cell);
        if(pieceToRemove == null) { return; }

        removePiece(pieceToRemove);
    }
    private boolean placePiece(Position cell) {
        var pieceOnPos = new PieceOnPosition(_selectedPiece, cell);
        boolean success = false;
        try {
            _board.placePiece(pieceOnPos);
            _selectedPiece = null;
            success = true;

            _observable.firePropertyChange("addPiece", 0, pieceOnPos);
        } catch (RuntimeException e) {
            _observable.firePropertyChange("displayMessage", 0, e.getMessage());
        }
        return success;
    }
    private void gameOver() {
        _state = State.GAMEOVER;
        _board.reset();
        _selectedPiece = null;
        _observable.firePropertyChange("clearBoard", 0, 1);
        _observable.firePropertyChange("updateHint", 0, null);
        _observable.firePropertyChange("displayMessage", 0, "Game Over");
    }
    private void gameWon() {
        _state = State.GAMEWON;
        _observable.firePropertyChange("displayMessage", 0, "Victory!");
    }

    public Model() {
        _observable = new PropertyChangeSupport(this);

        _board = new Board();
        _timer = new Timer();
        _timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(_state != State.STARTED) { return; }
                if(_timeLeft >= 0) {
                    _timeLeft--;
                    _observable.firePropertyChange("updateTimer", -1, _timeLeft);
                }
                else
                    gameOver();
            }
        }, 0, 1000);
    }
    public void addObserver(PropertyChangeListener observer) {
        _observable.addPropertyChangeListener(observer);
    }

    public void start() {
        if(_state != State.SETUP) {
            _observable.firePropertyChange("displayMessage", 0, "You need to setup the game first. (File -> New game)");
            return;
        }
        _state = State.STARTED;
        _timeLeft = 120;
    }
    public void setup() {
        _state = State.SETUP;
        _selectedPiece = null;
        // Setup board
        _board.reset();

        // Setup blockers
        Utils.rollDice(_dice);
        _board.placeBlockers(Utils.getDiceValues(_dice));

        _observable.firePropertyChange("clearBoard", 0, 1);
        _observable.firePropertyChange("dice&blockers", 0, Utils.getDiceValues(_dice));
        _observable.firePropertyChange("displayMessage", 0, "");
    }
    public void setSelectedPiece(Piece piece) {
        if(_state != State.STARTED) { return; }
        _selectedPiece = piece;
    }
    public void toggleSelectPiece(String pieceId) {
        if(_state != State.STARTED) { return; }
        var found = Arrays.stream(_pieces).filter(piece -> Objects.equals(piece.getName(), pieceId)).findFirst();
        found.ifPresent(piece -> _selectedPiece = piece.equals(_selectedPiece) ? null : piece);
    }
    public boolean placeOrRemovePiece(Position cell) {
        if(_state != State.STARTED) { return false; }
        boolean success = true;
        if(_selectedPiece == null)
            removePiece(cell);
        else
            success = placePiece(cell);

        if(_board.getNumberOfPieces() == _pieces.length)
            gameWon();
        return success;
    }
    public void removePiece(Piece piece) {
        if(_state != State.STARTED) { return; }
        var position = _board.removePiece(piece);
        _observable.firePropertyChange("removePiece", 0, new PieceOnPosition(piece, position));
    }
    public void placeHint(Position cell) {
        if(_state != State.STARTED) { return; }
        if(_selectedPiece == null) { return; }
        PieceOnPosition newHint = null;
        if(cell != null)
            newHint = new PieceOnPosition(_selectedPiece, cell);

        _observable.firePropertyChange("updateHint", 0, newHint);
        _board.placeHint(newHint);
    }
    public void rotateClockwise() {
        if(_selectedPiece == null) { return; }
        _selectedPiece.rotateClockwise();
        placeHint(_board.getHint().position());
    }
    public void rotateCounterclockwise() {
        if(_selectedPiece == null) { return; }
        _selectedPiece.rotateClockwise();
        placeHint(_board.getHint().position());
    }
    public void flip() {
        if(_selectedPiece == null) { return; }
        _selectedPiece.reverse();
        placeHint(_board.getHint().position());
    }
    public Piece getSelectedPiece() { return _selectedPiece; }
    public Piece getPieceAt(Position position) { return _board.getPieceAt(position); }
}

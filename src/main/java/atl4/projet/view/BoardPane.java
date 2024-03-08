package atl4.projet.view;

import atl4.projet.controller.Controller;
import atl4.projet.model.PieceOnPosition;
import atl4.projet.utils.Position;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class BoardPane extends StackPane {
    static class BoardLabel extends StackPane {
        public BoardLabel() {
            getChildren().addAll(new Rectangle(50, 50));
        }
        public BoardLabel(String label) {
            getChildren().addAll(new Rectangle(50, 50), new Text(label));
            getStyleClass().add("boardLabel");
        }
    }
    private static final String[] rowToString = {"A", "B", "C", "D", "E", "F"};
    private Controller _controller;

    private final Rectangle[][] _cells;
    private final Rectangle[][] _hintCells;

    public BoardPane() {
        _cells = new Rectangle[6][6];
        _hintCells = new Rectangle[6][6];
        for(int i=0; i<_cells.length; ++i)
            for(int j=0; j<_cells.length; ++j) {
                int finalI = i, finalJ = j;
                _hintCells[i][j] = new Rectangle(50, 50);
                _hintCells[i][j].getStyleClass().add("hintCell");
                _cells[i][j] = new Rectangle(50, 50);
                _cells[i][j].getStyleClass().add("boardCell");
                _cells[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    _controller.boardCellClicked(new Position(finalI, finalJ));
                    event.consume();
                });
                _cells[i][j].addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event -> {
                    _controller.boardCellHovered(new Position(finalI, finalJ));
                    event.consume();
                });
                _cells[i][j].addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event -> {
                    _controller.boardCellHovered(null);
                    event.consume();
                });
            }

        GridPane _cellsPane = new GridPane();
        _cellsPane.add(new BoardLabel(), 0, 0);
        for(int i = 0; i < _cells.length; ++i) {
            _cellsPane.add(new BoardLabel(Integer.toString(i + 1)), i + 1, 0);
            _cellsPane.add(new BoardLabel(rowToString[i]), 0, i + 1);
            _cellsPane.addRow(i + 1, _cells[i]);
        }

        GridPane _hintCellsPane = new GridPane();
        _hintCellsPane.setMouseTransparent(true);
        _hintCellsPane.setAlignment(Pos.BOTTOM_RIGHT);
        for(int i = 0; i < _hintCells.length; ++i) {
            _hintCellsPane.addRow(i, _hintCells[i]);
        }

        getChildren().addAll(_cellsPane, _hintCellsPane);
    }
    public void clear() {
        for(int i = 0; i < _cells.length; ++i)
            for(int j = 0; j < _cells.length; ++j) {
                _cells[i][j].setFill(Color.WHITE);
                _hintCells[i][j].setFill(Color.TRANSPARENT);
            }
    }
    public void updateBlockers(Position[] blockers) {
        for(Position blocker : blockers) {
            _cells[blocker.row][blocker.column].setFill(Color.BURLYWOOD);
        }
    }
    public void addPiece(PieceOnPosition piece) {
        for(Position block : piece.getPositions())
            _cells[block.row][block.column].setFill(Utils.shapeIdToColor.get(piece.piece().getName()));
        updateHint(null);
    }
    public void removePiece(PieceOnPosition piece) {
        for(Position block : piece.getPositions())
            _cells[block.row][block.column].setFill(Color.WHITE);
    }
    public void updateHint(PieceOnPosition newHint) {
        for(var row : _hintCells)
            for(var col : row)
                col.setFill(Color.TRANSPARENT);
        if(newHint != null)
            for(Position position : newHint.getPositions())
                if(position.row < _hintCells.length && position.column <_hintCells.length)
                    _hintCells[position.row][position.column].setFill(Utils.shapeIdToColor.get(newHint.piece().getName()));
    }
    public void setController(Controller controller) {
        _controller = controller;
    }
}

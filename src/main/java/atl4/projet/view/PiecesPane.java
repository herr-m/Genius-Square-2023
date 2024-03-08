package atl4.projet.view;

import atl4.projet.controller.Controller;
import atl4.projet.utils.Position;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Map;

public class PiecesPane extends VBox {
    static class Slot extends GridPane {
        private final String _pieceId;
        private boolean _available;
        private Controller _controller;
        public Slot(String pieceId, Color color, Position... blocks) {
            _pieceId = pieceId;
            _available = true;
            for (var block : blocks) {
                var rectangle = new Rectangle(50, 50);
                rectangle.setFill(color);
                add(rectangle, block.column, block.row);
            }
            addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if(_available)
                    _controller.pieceSlotClicked(_pieceId);
                event.consume();
            });
        }
        public void setController(Controller controller) {
            _controller = controller;
        }
        public void setAvailable(boolean available) {
            _available = available;
            setVisible(_available);
        }
    }

    protected final Map<String, Slot> _pieces;
    public PiecesPane(Map<String, Slot> pieces) {
        _pieces = pieces;
        getStyleClass().add("piecesPane");
    }

    public void setController(Controller controller) {
        for(var piece : _pieces.values())
            piece.setController(controller);
    }
    public void setPieceAvailable(String pieceId, boolean available) {
        if(_pieces.containsKey(pieceId)) { _pieces.get(pieceId).setAvailable(available); }
    }
    public void setAllAvailable(boolean available) {
        for(var piece : _pieces.values())
            piece.setAvailable(available);
    }
}

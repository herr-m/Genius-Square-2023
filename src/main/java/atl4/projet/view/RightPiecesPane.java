package atl4.projet.view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RightPiecesPane extends PiecesPane {

    public RightPiecesPane() {
        super(Utils.getRightPieces());
        getChildren().add(new HBox(_pieces.get("line4"), new VBox(_pieces.get("line2"), _pieces.get("dot"))));
        getChildren().addAll(_pieces.get("corner"), _pieces.get("square"));
    }
}

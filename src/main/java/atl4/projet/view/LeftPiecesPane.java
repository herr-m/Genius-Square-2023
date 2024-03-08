package atl4.projet.view;

public class LeftPiecesPane extends PiecesPane {
    public LeftPiecesPane() {
        super(Utils.getLeftPieces());
        getChildren().addAll(_pieces.values());
    }
}

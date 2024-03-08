package atl4.projet.view;

import atl4.projet.controller.Controller;
import atl4.projet.model.PieceOnPosition;
import atl4.projet.utils.Position;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private Controller _controller;
    private TopMenu _menu;
    private final MainBox _mainBox;
    public View() {
        _mainBox = new MainBox();
        Platform.startup(() -> {
            _menu = new TopMenu();
            _menu.setController(_controller);

            // JFX Setup
            Stage primaryStage = new Stage();
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add("/css/FxView.css");
            primaryStage.setScene(scene);
            primaryStage.show();

            // HUD Setup
            root.setCenter(new VBox(_menu, _mainBox));

            // Keyboard events
            scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                switch (event.getCode()) {
                    case RIGHT -> _controller.rotateClockwise();
                    case LEFT -> _controller.rotateCounterclockwise();
                    case DOWN -> _controller.flip();
                }
            });
        });
    }
    public void exit() {
        Platform.exit();
    }
    public void displayMessage(String message) {
        _mainBox.displayMessage(message);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "clearBoard" -> _mainBox.clearBoard();
            case "dice&blockers" -> _mainBox.updateDiceBlockers((Position[]) evt.getNewValue());
            case "addPiece" -> _mainBox.addPiece((PieceOnPosition) evt.getNewValue());
            case "removePiece" -> _mainBox.removePiece((PieceOnPosition) evt.getNewValue());
            case "updateHint" -> _mainBox.updateHint((PieceOnPosition) evt.getNewValue());
            case "updateTimer" -> _mainBox.updateTimer((int) evt.getNewValue());
            case "displayMessage" -> displayMessage((String) evt.getNewValue());
        }
    }
    public void setController(Controller controller) {
        _controller = controller;
        _mainBox.setController(_controller);
    }
}

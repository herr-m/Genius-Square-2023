package atl4.projet.view;

import atl4.projet.controller.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TopMenu extends MenuBar {
    private Controller _controller;
    private final Menu _file;
    private final Menu _edit;

    private void initFile() {
        MenuItem setup = new MenuItem("New game");
        MenuItem start = new MenuItem("Start");
        MenuItem exit = new MenuItem("Exit");

        exit.addEventHandler(ActionEvent.ACTION, event -> {
            _controller.exit();
        });
        setup.addEventHandler(ActionEvent.ACTION, event -> {
            _controller.setup();
        });
        start.addEventHandler(ActionEvent.ACTION, event -> {
            _controller.start();
        });
        _file.getItems().addAll(setup, start, exit);
    }
    private void initEdit() {
        MenuItem undo = new MenuItem("Undo");
        MenuItem redo = new MenuItem("Redo");
        undo.addEventHandler(ActionEvent.ACTION, event -> {
            _controller.undo();
        });
        redo.addEventHandler(ActionEvent.ACTION, event -> {
            _controller.redo();
        });
        _edit.getItems().addAll(undo, redo);
    }
    public TopMenu() {
        _file = new Menu("File");
        _edit = new Menu("Edit");
        initFile();
        initEdit();

        getMenus().addAll(_file, _edit);
    }
    public void setController(Controller controller) {
        _controller = controller;
    }
}

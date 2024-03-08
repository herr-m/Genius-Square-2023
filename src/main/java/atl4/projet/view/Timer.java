package atl4.projet.view;

import javafx.scene.text.Text;

public class Timer extends Text {
    public Timer() {
        setText("0:0");
        getStyleClass().add("text-20");
    }
    public void update(int seconds) {
        setText(String.format("%d:%d", seconds / 60, seconds % 60));
    }
}

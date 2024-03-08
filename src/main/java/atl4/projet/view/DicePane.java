package atl4.projet.view;

import atl4.projet.utils.Position;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class DicePane extends HBox {
    private final Text[] _dice;
    private static final String[] rowToString = {"A", "B", "C", "D", "E", "F"};

    public DicePane() {
        var children = getChildren();
        _dice = new Text[7];
        for(int i=0; i<_dice.length; ++i) {
            _dice[i] = new Text();
            StackPane die = new StackPane(new Rectangle(50, 50), _dice[i]);
            die.getStyleClass().add("die");
            children.add(die);
        }
    }
    public void update(Position[] diceValues) {
        for(int i=0; i<diceValues.length; ++i) {
            var dieVal = diceValues[i];
            _dice[i].setText(String.format("%s%d", rowToString[dieVal.row], dieVal.column+1));
        }
    }
}

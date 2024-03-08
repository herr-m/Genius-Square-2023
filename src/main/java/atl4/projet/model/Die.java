package atl4.projet.model;

import atl4.projet.utils.Position;
import javafx.geometry.Pos;

import java.util.Random;

public class Die {
    private final Position[] _possibleValues;
    private Position _currentValue;

    public Die(Position... possibleValues) {
        _possibleValues = possibleValues;
        _currentValue = _possibleValues[0];
    }
    public void roll() {
        _currentValue = _possibleValues[new Random().nextInt(_possibleValues.length)];
    }
    public Position getValue() {
        return _currentValue;
    }
}

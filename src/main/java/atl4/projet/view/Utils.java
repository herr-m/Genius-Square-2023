package atl4.projet.view;

import atl4.projet.utils.Position;
import javafx.scene.paint.Color;
import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {
    public static Map<String, PiecesPane.Slot> getLeftPieces() {
        var result = new LinkedHashMap<String, PiecesPane.Slot>();
        result.put("line3", new PiecesPane.Slot("line3", Color.ORANGE, new Position(0, 0), new Position(0, 1), new Position(0, 2)));
        result.put("t", new PiecesPane.Slot("t", Color.YELLOW, new Position(0, 0), new Position(0, 1), new Position(0, 2), new Position(1, 1)));
        result.put("z", new PiecesPane.Slot("z", Color.RED, new Position(0, 0), new Position(0, 1), new Position(1, 1), new Position(1, 2)));
        result.put("l", new PiecesPane.Slot("l", Color.CYAN, new Position(0, 0), new Position(0, 1), new Position(0, 2), new Position(1, 2)));
        return result;
    }
    public static Map<String, PiecesPane.Slot> getRightPieces() {
        var result = new LinkedHashMap<String, PiecesPane.Slot>();
        result.put("line4", new PiecesPane.Slot("line4", Color.GREY, new Position(0, 0), new Position(1, 0), new Position(2, 0), new Position(3, 0)));
        result.put("line2", new PiecesPane.Slot("line2", Color.BROWN, new Position(0, 0), new Position(1, 0)));
        result.put("dot", new PiecesPane.Slot("dot", Color.BLUEVIOLET, new Position(0, 0)));
        result.put("corner", new PiecesPane.Slot("corner", Color.PURPLE, new Position(0, 0), new Position(0, 1), new Position(1, 0)));
        result.put("square", new PiecesPane.Slot("square", Color.GREEN, new Position(0, 0), new Position(0, 1), new Position(1, 0), new Position(1, 1)));
        return result;
    }
    public static Map<String, Color> shapeIdToColor = Map.of(
            "line3", Color.ORANGE,
            "t", Color.YELLOW,
            "z", Color.RED,
            "l", Color.CYAN,
            "line4", Color.GREY,
            "line2", Color.BROWN,
            "dot", Color.BLUEVIOLET,
            "corner", Color.PURPLE,
            "square", Color.GREEN
    );
}

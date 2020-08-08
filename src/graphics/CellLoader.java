package graphics;

import javafx.scene.layout.GridPane;
import logic.Updater;
import tile.Tile;

public class CellLoader {

    public static GridPane gameDisplay = new GridPane();

    public static void loadCell(int posX, int posY) {
        try {
            Tile currentTile = Updater.currentRoom.getTile(posX, posY);
            gameDisplay.add(currentTile.load(), posX, posY);
        } catch (NullPointerException e) {
            gameDisplay.add(Tile.loadBlank(), posX, posY);
        }
    }

    public static void loadAll() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                loadCell(i, j);
            }
        }
    }

}

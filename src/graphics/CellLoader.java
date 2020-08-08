package graphics;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import logic.Room;
import logic.Updater;
import tile.Tile;

public class CellLoader {

    public static GridPane display = new GridPane();

    public static void loadCell(int posX, int posY) {
        try {
            Tile currentTile = Updater.currentRoom.getTile(posX, posY);
            display.add(currentTile.load(), posX, posY);
        } catch (NullPointerException e) {
            display.add(Tile.loadBlank(), posX, posY);
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

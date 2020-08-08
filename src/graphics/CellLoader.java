package graphics;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import logic.Updater;
import tile.Tile;

public class CellLoader {

    public static GridPane gameDisplay = new GridPane();

    public static void loadCell(int posX, int posY) {
        try {
            Tile currentTile = Updater.currentRoom.getTile(posX, posY);
            StackPane loaded = currentTile.load();
            if (Updater.avatar.location[0] == posX && Updater.avatar.location[1] == posY) {
                loaded.getChildren().add(Updater.avatar.load());
            }
        } catch (NullPointerException e) {
            StackPane loaded = Tile.loadBlank();
            if (Updater.avatar.location[0] == posX && Updater.avatar.location[1] == posY) {
                loaded.getChildren().add(Updater.avatar.load());
            }
            gameDisplay.add(loaded, posX, posY);
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

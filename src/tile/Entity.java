package tile;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

abstract class Entity extends Tile {

    public Entity(String name, Image texture, int posX, int posY) {
        super(name, texture, posX, posY);
    }

}

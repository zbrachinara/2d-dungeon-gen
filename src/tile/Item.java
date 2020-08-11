package tile;

import javafx.scene.image.Image;

public abstract class Item extends Tile {

    public boolean inWorld;

    public Item(String name, Image texture, int posX, int posY, boolean inWorld) {

        super(name, texture, posX, posY);
        this.inWorld = inWorld;

    }

}

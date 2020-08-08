package tile;

import javafx.scene.image.Image;

public abstract class Item extends Tile {

    public boolean inWorld;

    public Item(String name, Image texture, int[] location, boolean inWorld) {

        super(name, texture, location);
        this.inWorld = inWorld;

    }

}

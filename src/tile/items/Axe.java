package tile.items;

import javafx.scene.image.Image;
import tile.Item;

public class Axe extends Item {

    public Axe(int[] location, boolean inWorld) {
        super("Axe", new Image("resources/AXE.png"), location, inWorld);
    }

}

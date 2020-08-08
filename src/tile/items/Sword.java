package tile.items;

import javafx.scene.image.Image;
import tile.Item;

public class Sword extends Item {

    public Sword(int[] location, boolean inWorld) {
        super("Sword", new Image("resources/sword_temp.png"), location, inWorld);
    }

}

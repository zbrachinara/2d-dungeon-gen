package tile.items;

import javafx.scene.image.Image;
import tile.Item;

public class Shield extends Item {

    public Shield(int[] location, boolean inWorld) {
        super("Shield", new Image("resources/SHEILD.png"), location, inWorld);
    }

}

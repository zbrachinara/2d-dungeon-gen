package tile.blocks;

import javafx.scene.image.Image;
import tile.Block;
import logic.Updater.Direction;

public class Entrance extends Block {

    public Direction direction;

    public Entrance(Direction direction, int[] location) {
        // replaced getBackImage with new Image for now
        super("Entrance", new Image("resources/entrance_tmp.png"), location, true);
        this.direction = direction;

    }

}

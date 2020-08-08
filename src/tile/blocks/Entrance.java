package tile.blocks;

import javafx.scene.image.Image;
import tile.Block;

public class Entrance extends Block {

    public Entrance(Directions direction, int[] location) {
        // replaced getBackImage with new Image for now
        super("Entrance", new Image("resources/entrance.png"), location, true);
        Image texture;


    }

    private static Image getBackImage(Directions direction) {
        switch(direction) {
            case NORTH:
                return new Image("");
            case SOUTH:
                return new Image("");
            case EAST:
                return new Image("");
            case WEST:
                return new Image("");
            default:
                return new Image("");
        }
    }

}

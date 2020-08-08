package tile.blocks;

import javafx.scene.image.Image;
import tile.Block;


public class Entrance extends Block {
    public Image texture;

    public Entrance(Directions direction) {
        switch(direction) {
            case NORTH:
                texture = new Image("");
                break;
            case SOUTH:
                texture = new Image("");
                break;
            case EAST:
                texture = new Image("");
                break;
            case WEST:
                texture = new Image("");
                break;
        }
    }
    
}
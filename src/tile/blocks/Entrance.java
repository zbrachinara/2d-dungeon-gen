package tile.blocks;

import javafx.scene.image.Image;
import tile.Block;


public class Entrance extends Block {
    public Image texture;

    public Entrance(Directions direction) {
        switch(direction) {
            case NORTH -> texture = new Image("");
            case SOUTH -> texture = new Image("");
            case EAST -> texture = new Image("");
            case WEST -> texture = new Image("");
        }
    }
    
}
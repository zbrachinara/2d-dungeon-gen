package tile;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Avatar extends Tile {

    public ArrayList<Item> pocket;

    public Avatar() {

        super("Avatar", new Image("resources/Avatar.png"), new int[]{10, 10});

    }

}

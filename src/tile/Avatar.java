package tile;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Avatar extends Entity {

    public ArrayList<Item> pocket = new ArrayList<>();

    public Avatar(int posX, int posY) {

        super("Avatar", new Image("resources/Avatar_tmp.png"), posX, posY);

    }

}

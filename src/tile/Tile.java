package tile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class Tile {

    public String name;
    public Image texture;
    public int[] location;

    public static Image back = new Image("resources/Grid_YES");

    public StackPane load() {
        StackPane out = new StackPane();

        out.getChildren().add(new ImageView(back));
        out.getChildren().add(new ImageView(texture));

        return out;
    }

}

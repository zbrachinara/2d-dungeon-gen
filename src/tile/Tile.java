package tile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class Tile {

    public String name;
    public Image texture;
    public int posX;
    public int posY;

    public static Image back = new Image("resources/Grid_YES_tmp.png");

    public Tile(String name, Image texture, int posX, int posY) {
        this.name = name;
        this.texture = texture;
        this.posX = posX;
        this.posY = posY;
    }

    public StackPane load() {
        StackPane out = new StackPane();
        out.getChildren().add(new ImageView(back));
        out.getChildren().add(new ImageView(texture));
        return out;
    }

    public static StackPane loadBlank() {
        StackPane out = new StackPane();
        out.getChildren().add(new ImageView(back));
        return out;
    }

}

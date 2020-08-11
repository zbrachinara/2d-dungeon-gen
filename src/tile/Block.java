package tile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Block extends Tile {

    public int[] position;
    public boolean canMoveThrough;
//    Pane root = new Pane();

//    public static void blockGen(int type, int Xcoord, int Ycoord) throws FileNotFoundException {
//
//        GridPane pane = new GridPane();
//        FileInputStream imageGrid = new FileInputStream("resources/Grid2.jpg");
//        Image grid;
//        switch (type){
//            case 1:
//                grid = new Image(imageGrid);
//                break;
//            default:
//                grid = new Image("");
//        }
//        pane.add(new ImageView(grid), Xcoord, Ycoord);
//    };

    public Block(String name, Image texture, int posX, int posY, boolean canMoveThrough) {

        super(name, texture, posX, posY);
        this.canMoveThrough = canMoveThrough;

    }

}

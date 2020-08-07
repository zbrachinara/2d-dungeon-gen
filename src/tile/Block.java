package tile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Block extends Tile {

    public int[] position;
    public boolean transparent; // can the block be passed through (NOT SEE THROUGH!!!!!)
    Pane root = new Pane();

    public static void blockGen(int type, int Xcoord, int Ycoord) throws FileNotFoundException {

        GridPane pane = new GridPane();
        FileInputStream imageGrid = new FileInputStream("resources/Grid2.jpg");
        switch (type){
            case 1:
                Image grid = new Image(imageGrid);
                break;
        }
        pane.add(new ImageView(grid), Xcoord, Ycoord);


    };
}

package tile;


import javafx.scene.layout.Pane;

public abstract class Block extends Tile {

    public int[] position;
    public boolean transparent; // can the block be passed through (NOT SEE THROUGH!!!!!)
    Pane root = new Pane();

    public static void blockGen(String Image, int Xcoord, int Ycoord){



    };
}

package tile;

import graphics.Updater.Direction;

import tile.Item;
import tile.Tile;

import java.util.ArrayList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Avatar extends Tile {

    public ArrayList<Item> pocket;

    public Avatar() {

        location = new int[]{10, 10};

    }

}

package graphics;

import graphics.Updater.Direction;

import tile.Item;
import tile.Tile;

import java.util.ArrayList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Avatar extends Tile implements KeyListener {

    public ArrayList<Item> pocket;

    public Avatar() {

        location = new int[]{10, 10};

    }

    @Override
    public void keyTyped(KeyEvent e) {

        Updater.update(e);

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

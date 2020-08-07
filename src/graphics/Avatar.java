package graphics;

import tile.Item;
import tile.Tile;

import java.util.ArrayList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Avatar extends Tile implements KeyListener {

    public int[] position;
    public int[] room = new int[2];

    public ArrayList<Item> pocket;

    enum Direction {UP, DOWN, LEFT, RIGHT}

    public Avatar() {

        position = new int[]{25, 25};

    }

    // returns based on whether or not it moves
    public boolean move(Direction direction) {

        if (position[0] == 0 || position[0] == 49 || position[1] == 0 || position[1] == 49) {
            return false;
        }

        switch(direction) {
            case UP:
                position[1]++;
            case DOWN:
                position[1]--;
            case LEFT:
                position[0]--;
            case RIGHT:
                position[0]++;
        }

        return true;

    }

    @Override
    public void keyTyped(KeyEvent e) {

        switch (e.getKeyChar()) {
            case 'w':
                move(Direction.UP);
            case 'a':
                move(Direction.LEFT);
            case 's':
                move(Direction.DOWN);
            case 'd':
                move(Direction.RIGHT);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

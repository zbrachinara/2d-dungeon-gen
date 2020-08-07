package graphics;

import tile.Block;
import tile.Tile;

import java.awt.event.KeyEvent;

public class Updater {

    public enum Direction {UP, DOWN, LEFT, RIGHT}

    static int[] currentRoom = new int[]{0, 0};
    public static Room[][] rooms;
    static Avatar avatar;
    static Tile standingOn;

    public static void update(KeyEvent event) {

    }

    private static boolean move(Direction direction) {
        if (isObstacle(direction)) {
            switch (direction) {
                case UP -> avatar.location[1]++;
                case DOWN -> avatar.location[1]--;
                case LEFT -> avatar.location[0]--;
                case RIGHT -> avatar.location[0]++;
            }
            return true;
        }
        return false;

    }

    private static boolean isObstacle(Direction direction) {
        boolean verticalMove = direction == Direction.UP || direction == Direction.DOWN;
        int[] difference = new int[2];

        if (verticalMove) {
            difference[1] = direction == Direction.UP ? 1 : -1;
        } else {
            difference[0] = direction == Direction.RIGHT ? 1 : -1;
        }

        Tile objectTo;
        try {
            objectTo = rooms[currentRoom[0]][currentRoom[1]].tiles[avatar.location[0] + difference[0]][avatar.location[1] + difference[1]];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        if (objectTo instanceof Block) {
            if (!((Block) objectTo).transparent) return false;
        }

        return true;

    }

}



package graphics;

import tile.Tile;

public class Updater {

    static int[] currentRoom = new int[]{0, 0};
    static Avatar avatar;
    Tile standingOn;

    public static void update() {


    }

    private static boolean move(Avatar.Direction direction) {
        switch (direction) {
            case UP -> {
                if (avatar.position[1] == 19) return false;
                avatar.position[1]++;
            }
            case DOWN -> {
                if (avatar.position[1] == 0) return false;
                avatar.position[1]--;
            }
            case LEFT -> {
                if (avatar.position[0] == 0) return false;
                avatar.position[0]--;
            }
            case RIGHT -> {
                if (avatar.position[0] == 19) return false;
                avatar.position[0]++;
            }
        }

        return true;

    }
    }



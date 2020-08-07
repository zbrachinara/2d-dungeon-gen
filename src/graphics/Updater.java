package graphics;

import javafx.event.Event;
import tile.Block;
import tile.Item;
import tile.Tile;
import tile.blocks.Entrance;

import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Updater {


    public enum Direction {UP, DOWN, LEFT, RIGHT}

    static int[] currentRoom = new int[]{0, 0};
    public static Room[][] rooms;
    static Avatar avatar;
    static Tile standingOn;

    public static void update(KeyEvent event) {
        move(event.getKeyChar());
        standingOn = rooms[currentRoom[0]][currentRoom[1]].tiles[avatar.location[0]][avatar.location[1]];
        pickupItem();
        if(standingOn instanceof Entrance) {
            System.out.println("standing on entrance");
        }

    }

    private static boolean move(char keyInput) {

        Map<Character, Direction> characterDirectionMap = new HashMap<>();
        characterDirectionMap.put('w', Direction.UP);
        characterDirectionMap.put('s', Direction.DOWN);
        characterDirectionMap.put('a', Direction.LEFT);
        characterDirectionMap.put('d', Direction.RIGHT);

        Direction direction = characterDirectionMap.get(keyInput);

        if (isObstacle(direction)) {
            switch (direction) {
                case UP:
                    avatar.location[1]++;
                    break;
                case DOWN:
                    avatar.location[1]--;
                    break;
                case LEFT:
                    avatar.location[0]--;
                    break;
                case RIGHT:
                    avatar.location[0]++;
                    break;
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

    private static void pickupItem() {
        if(standingOn instanceof Item) {
            avatar.pocket.add((Item)standingOn);
        }
        System.out.println("picked up item");
    }

    private static void viewInventory() {
        
    }

}

        


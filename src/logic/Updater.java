package logic;

import graphics.CellLoader;
import tile.Avatar;
import tile.Block;
import tile.Item;
import tile.Tile;

import javafx.scene.input.KeyEvent;

import tile.blocks.Entrance;
import java.util.HashMap;
import java.util.Map;

public class Updater  {

    public enum Direction {UP, DOWN, LEFT, RIGHT}

    public static int[] currentRoomID = new int[]{0, 0};
    public static Room[][] rooms = new Room[2][2];
    public static Room currentRoom;
    public static Avatar avatar = new Avatar();
    static Tile standingOn;
    static boolean isInventoryOpen = false;

    public static void update(KeyEvent event) {

        toggleInventory(event);
        move(event);
        standingOn = currentRoom.getTile(avatar.location[0], avatar.location[1]);
        pickupItem();
        if(standingOn instanceof Entrance) {
            System.out.println("standing on entrance");
        }
        if (!event.isConsumed()) {
            event.consume();
        }

    }

    private static boolean toggleInventory(javafx.scene.input.KeyEvent e) {

        char invKeyChar = 'e';

        if (isInventoryOpen) {
            if(e.getCharacter().charAt(0) == invKeyChar) {
                isInventoryOpen = false;
                closeInventory();
            }
            e.consume();
        } else { // inventory is NOT open
            if (e.getCharacter().charAt(0) == invKeyChar) {
                isInventoryOpen = true;
                e.consume();
                viewInventory();
                return true;
            }
            // pass the event to movement if not 'e'
        }
        return false;

    }

    private static boolean move(KeyEvent keyInput) {

        Map<Character, Direction> characterDirectionMap = new HashMap<>();
        characterDirectionMap.put('w', Direction.UP);
        characterDirectionMap.put('s', Direction.DOWN);
        characterDirectionMap.put('a', Direction.LEFT);
        characterDirectionMap.put('d', Direction.RIGHT);

        Direction direction = characterDirectionMap.get(keyInput.getText().charAt(0));

        if (isObstacle(direction)) {
            int[] difference = new int[2];
            switch (direction) {
                case UP -> difference[1] = -1;
                case DOWN -> difference[1] = 1;
                case LEFT -> difference[0] = -1;
                case RIGHT -> difference[0] = 1;
            }

            avatar.location = new int[]{avatar.location[0] + difference[0], avatar.location[1] + difference[1]};
            CellLoader.loadCell(avatar.location[0], avatar.location[1]);
            CellLoader.loadCell(avatar.location[0] - difference[0], avatar.location[1] - difference[1]);

            return true;
        }
        return false;

    }

    private static boolean isObstacle(Direction direction) {
        boolean movingVertically = direction == Direction.UP || direction == Direction.DOWN;
        int[] difference = new int[2];

        if (movingVertically) {
            difference[1] = direction == Direction.UP ? -1 : 1;
        } else {
            difference[0] = direction == Direction.RIGHT ? 1 : -1;
        }

        Tile tileTravellingTo;
        try {
            tileTravellingTo = currentRoom.getTile(avatar.location[0] + difference[0], avatar.location[1] + difference[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        if (tileTravellingTo instanceof Block) {
            return ((Block) tileTravellingTo).canMoveThrough;
        }

        return true;

    }

    private static void pickupItem() {
        if(standingOn instanceof Item) {
            avatar.pocket.add((Item) standingOn);
        }
        System.out.println("picked up item");
    }

    private static void viewInventory() {
        int index = 0;
        for(Item item : avatar.pocket) {
            System.out.print(index);
            System.out.println(item.toString());
            index++;
        }
    }


    private static void closeInventory() {

    }

}




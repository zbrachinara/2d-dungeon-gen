package logic;

import tile.Avatar;
import tile.Block;
import tile.Item;
import tile.Tile;
import tile.blocks.Entrance;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class Updater implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        update(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public enum Direction {UP, DOWN, LEFT, RIGHT}

    public static int[] currentRoomID = new int[]{0, 0};
    public static Room[][] rooms = new Room[2][2];
    public static Room currentRoom;
    public static Avatar avatar = new Avatar();
    static Tile standingOn;
    static boolean isInventoryOpen = false;

    public static void update(KeyEvent event) {

        toggleInventory(event);
        move(event.getKeyChar());
        standingOn = currentRoom.getTile(avatar.location[0], avatar.location[1]);
        pickupItem();
        if(standingOn instanceof Entrance) {
            System.out.println("standing on entrance");
        }

    }

    private static boolean toggleInventory(KeyEvent e) {

        char invKeyChar = 'e';

        if (isInventoryOpen) {
            if(e.getKeyChar() == invKeyChar) {
                isInventoryOpen = false;
                closeInventory();
            }
            e.consume();
        } else { // inventory is NOT open
            if (e.getKeyChar() == invKeyChar) {
                isInventoryOpen = true;
                e.consume();
                viewInventory();
                return true;
            }
            // pass the event to movement if not 'e'
        }
        return false;

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
        boolean movingVertically = direction == Direction.UP || direction == Direction.DOWN;
        int[] difference = new int[2];

        if (movingVertically) {
            difference[1] = direction == Direction.UP ? 1 : -1;
        } else {
            difference[0] = direction == Direction.RIGHT ? 1 : -1;
        }

        Tile objectTo;
        try {
            objectTo = currentRoom.getTile(avatar.location[0] + difference[0], avatar.location[1] + difference[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        if (objectTo instanceof Block) {
            if (!((Block) objectTo).canMoveThrough) return false;
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




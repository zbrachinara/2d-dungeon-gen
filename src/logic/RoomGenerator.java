package logic;

import logic.Room.RoomType;

import tile.Tile;
import tile.TileLinker;
import tile.blocks.Entrance;

import logic.Updater.Direction;
import logic.Updater;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public abstract class RoomGenerator {

    public static Room generateRoom(RoomType roomType, int[] roomID) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Room out = new Room(roomType);
        Random random = new Random();

        //TODO: add the entrances
        int[][] checkIDs = new int[][]{
                new int[]{roomID[0]-1, roomID[1]}, // LEFT
                new int[]{roomID[0]+1, roomID[1]}, // RIGHT
                new int[]{roomID[0], roomID[1]-1}, // UP
                new int[]{roomID[0], roomID[1]+1}  // DOWN
        };
        int directionIndex = 0;

        for (int[] ID: checkIDs) {
//            try {
//                Room adjRoom = Updater.rooms[ID[0]][ID[1]]; // the IDE will tell you to remove this
                // DO NOT REMOVE THIS
                // it is what will trigger the exception
//                if ((adjRoom = Updater.rooms[ID[0]][ID[1]]) != null) {
//
//                    Tile[] edge = getEdge(adjRoom.tiles, Direction.opposite(checkDirection[directionIndex]), Tile.class);
//                    for (int i = 0; i < edge.length; i++) {
//                        if (edge[i] instanceof Entrance) {
//                            switch (checkDirection[directionIndex]) {
//                                case RIGHT:
//
//                                case LEFT:
//                                case UP:
//                                case DOWN:
//                            }
//                        }
//                    }
//
//                } else {
//
//                }
                int generated = random.nextInt(20);
                switch (directionIndex) {
                    case 0: // left
                        out.addTile(0, generated, new Entrance(Direction.LEFT, new int[]{0, generated}));
                    case 1: // right
                        out.addTile(19, generated, new Entrance(Direction.RIGHT, new int[]{19, generated}));
                    case 2: // up
                        out.addTile(generated, 0, new Entrance(Direction.RIGHT, new int[]{generated, 0}));
                    case 3: // down
                        out.addTile(generated, 19, new Entrance(Direction.RIGHT, new int[]{generated, 19}));
                }
//                directionIndex++;
//            } catch (ArrayIndexOutOfBoundsException ignored) { directionIndex++; }
        }

        // chance of each object spawned
        HashMap<String, Float> chanceMap = new HashMap<>();
        chanceMap.put("Axe", .07f);
        chanceMap.put("Shield", .05f);
        chanceMap.put("Sword", .0314f);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (out.getTile(i, j) == null) {
                    Tile addedTile = determineNormalTile(random.nextFloat(), chanceMap, new int[]{i, j});
                    if (addedTile != null) {
                        addedTile.location = new int[]{i, j};
                        out.addTile(i, j, addedTile);
                    }
                }
            }
        }

        return out;
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] getEdge(T[][] in, Direction direction, Class<T> t) {
        switch(direction) {
            case UP:
                return in[0];
            case DOWN:
                return in[in.length-1];
            case LEFT:
                T[] out = (T[]) Array.newInstance(t, in.length);
                int counter = 0;
                for (T[] i: in) {
                    out[counter] = i[0];
                    counter++;
                }
                return out;
            case RIGHT:
                out = (T[]) Array.newInstance(t, in.length);
                counter = 0;
                for (T[] i: in) {
                    out[counter] = i[in.length-1];
                    counter++;
                }
                return out;
            default:
                throw new IllegalArgumentException("You didn't put in a valid direction (UP, DOWN, LEFT, RIGHT)");
        }
    }

    private static Tile determineNormalTile(float generated, HashMap<String, Float> chanceMap, int[] position) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Tile out = null;
        Float chance = 1f;
        chanceMap = processChanceMap(chanceMap);
        for (String i : chanceMap.keySet()) {
            try {
                if (chanceMap.get(i) > generated && chanceMap.get(i) < chance) {
                    Class<?> tileClass = TileLinker.availableTiles.get(i);
                    out = instantiateTile(tileClass, position);
                    chance = chanceMap.get(i);
                }
            } catch (NullPointerException ignored) {}
        }
        return out;

    }

    private static Tile instantiateTile(Class<?> in, int[] position) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, TileNotFoundException {
        if (in.getSuperclass().getName().equals("tile.Item")) {
            return (Tile) in.getDeclaredConstructor(int[].class, boolean.class).newInstance(position, true);
        } else if (in.getSuperclass().getName().equals("tile.Block")) {
            // TODO: Fill this in
        }
        throw new TileNotFoundException("Go back and fix your stupid mistake (logic.RoomGenerator)");
    }

    private static <T> HashMap<T, Float> processChanceMap(HashMap<T, Float> chanceMap) {

        HashMap<T, Float> outMap = new HashMap<>(chanceMap.size());
        Float totalChance = 0f;
        for (T i : chanceMap.keySet()) {

            totalChance += chanceMap.get(i);
            outMap.put(i, totalChance);

        }
        if (totalChance > 1) {
            throw new IllegalArgumentException("The total chance for spawn is greater than 1, go back and fix it in graphics.RoomGenerator.chanceMap");
        }
        return outMap;

    }

}

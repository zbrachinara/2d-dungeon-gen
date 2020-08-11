package logic;

import logic.Room.RoomType;

import tile.Tile;
import tile.TileLinker;
import tile.blocks.Entrance;

import logic.Updater.Direction;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Random;

public abstract class RoomGenerator {

    public static Room generateRoom(RoomType roomType) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Room out = new Room(roomType);
        Random random = new Random();

        // TODO: Generate entrances

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
                        addedTile.posX = i;
                        addedTile.posY = j;
                        out.addTile(i, j, addedTile);
                    }
                }
            }
        }

        return out;
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

    private static <T extends Object> HashMap<T, Float> processChanceMap(HashMap<T, Float> chanceMap) {

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

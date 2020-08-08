package logic;

import logic.Room.RoomType;

import tile.Tile;
import tile.TileLinker;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Random;

public abstract class RoomGenerator {

    public static Room generateRoom(RoomType roomType) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Room out = new Room(roomType);
        Random random = new Random();

        // add the avatar
        if (roomType == RoomType.STARTING_ROOM) {
            out.addTile(10, 10, Updater.avatar);
        }

        //TODO: add the entrances

        // chance of each object spawned
        HashMap<String, Float> chanceMap = new HashMap<>();
        chanceMap.put("Axe", .5f);
        chanceMap.put("Shield", .4f);
        chanceMap.put("Sword:", .05f);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (out.getTile(i, j) != null) {
                    Tile addedTile = determineNormalTile(random.nextFloat(), chanceMap);
                    if (addedTile != null) {
                        addedTile.location = new int[]{i, j};
                        out.addTile(i, j, addedTile);
                    }
                }
            }
        }

        return out;
    }

    private static Tile determineNormalTile(float generated, HashMap<String, Float> chanceMap) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Tile out = null;
        Float chance = 1f;
        chanceMap = processChanceMap(chanceMap);
        for (String i : chanceMap.keySet()) {
            try {
                if (chanceMap.get(i) > generated && chanceMap.get(i) < chance) {
                    out = (Tile) TileLinker.availableTiles.get(i).getDeclaredConstructor().newInstance();
                }
            } catch (NullPointerException e) {
                out = null;
            }
        }
        return out;

    }

    private static <T extends Object> HashMap<T, Float> processChanceMap(HashMap<T, Float> chanceMap) {

        HashMap<T, Float> outMap = new HashMap<>(chanceMap.size());
        Float totalChance = 0f;
        for (T i : chanceMap.keySet()) {

            totalChance += chanceMap.get(i);
            outMap.put(i, totalChance);

        }
        if (totalChance > 1) {
            throw new IllegalArgumentException("The total chance for spawn is greater than 1, go back and fix it in graphics.RoomGenerator");
        }
        return outMap;

    }

}

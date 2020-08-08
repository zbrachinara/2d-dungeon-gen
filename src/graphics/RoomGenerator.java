package graphics;

import graphics.Room.RoomType;

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

        // add the entrances


        HashMap<String, Float> chanceMap = new HashMap<>();

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (out.getTile(i, j) != null) {
                    out.addTile(i, j, determineNormalTile(random.nextFloat(), chanceMap));
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
            if (chanceMap.get(i) > generated && chanceMap.get(i) < chance) {
                out = (Tile) TileLinker.availableTiles.get(i).getDeclaredConstructor().newInstance();
            }
        }
        if (out == null) {
            throw new IllegalArgumentException("Check your chanceMap or update processChanceMap in graphics.RoomGenerator");
        } else {
            return out;
        }

    }

    private static <T extends Object> HashMap<T, Float> processChanceMap(HashMap<T, Float> chanceMap) {

        HashMap<T, Float> outMap = new HashMap<>(chanceMap.size());
        Float totalChance = 0f;
        for (T i : chanceMap.keySet()) {

            totalChance += chanceMap.get(i);
            outMap.put(i, totalChance);

        }
        if (totalChance > 1) {
            throw new IllegalArgumentException("The chances of each Tile are greater than 100, go back and fix it in graphics.RoomGenerator");
        }
        return outMap;

    }

}

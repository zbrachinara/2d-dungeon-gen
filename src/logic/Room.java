package logic;

import tile.Tile;

public class Room {

    public enum RoomType {
        STARTING_ROOM,
        REGULAR_ROOM
    }

    Tile[][] tiles = new Tile[20][20];
    public RoomType type;

    public Room(RoomType type) {
        this.type = type;
    }

    public void addTile(int posX, int posY, Tile in) {
        tiles[posX][posY] = in;
    }

    public Tile getTile(int posX, int posY) {
        return tiles[posX][posY];
    }

}

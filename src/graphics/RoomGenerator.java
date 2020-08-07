package graphics;

import tile.*;
import tile.blocks.*;

public abstract class RoomGenerator {

    public static Room generateRoom(long seed) {
        return new Room();
    }

}

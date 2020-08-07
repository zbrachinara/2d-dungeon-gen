package graphics;

import tile.*;
import tile.block.*;

abstract class RoomGenerator {

    public Room generateRoom(long seed) {
        return new Room();
    }

}

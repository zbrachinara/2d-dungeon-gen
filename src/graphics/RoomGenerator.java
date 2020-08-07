package graphics;

import tile.*;
import tile.blocks.*;

abstract class RoomGenerator {

    public Room generateRoom(long seed) {
        return new Room();
    }

}

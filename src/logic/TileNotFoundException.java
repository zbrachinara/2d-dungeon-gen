package logic;

public class TileNotFoundException extends RuntimeException {

    private static final long serialVersionUUID = 1l;
    TileNotFoundException(String message) {
        super(message);
    }

}

package main;

import graphics.CellLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import logic.RoomGenerator;
import logic.Room;
import logic.Updater;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tile.TileLinker;

import static logic.Updater.currentRoomID;
import static logic.Updater.currentRoom;

public class Main extends Application { // main class

    public static void do_nothing() {

        /*
        Now let me go on a little rant here.
        This here function is my favorite function.
        And no one is going to remove it.
        NO
        ONE
        .
        REMOVE
        IT
        .
        It has:
        0 errors
        It gives me:
        1 warning
        It's predicted error rate is
        NONE
        ok?
        so don't remove it
        If you do
        I will go after your family
        And feed you to the digital dogs that I raise in my digital backyard
        and they have this function too.
        You'd better believe it.
         */

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        TileLinker.load();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Updater.rooms[i][j] = RoomGenerator.generateRoom(Room.RoomType.REGULAR_ROOM);
            }
        }
        Updater.rooms[0][0] = RoomGenerator.generateRoom(Room.RoomType.STARTING_ROOM);
        currentRoom = Updater.rooms[currentRoomID[0]][currentRoomID[1]]; // to ensure that the room is displayed correctly



        CellLoader.loadAll();
        StackPane root = new StackPane();
        root.getChildren().add(CellLoader.gameDisplay);
        CellLoader.gameDisplay.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 740, 500);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(Updater::update);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

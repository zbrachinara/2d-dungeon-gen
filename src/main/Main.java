package main;

import graphics.CellLoader;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import logic.Room;
import logic.RoomGenerator;
import logic.Updater;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tile.TileLinker;

import java.lang.reflect.InvocationTargetException;

public class Main extends Application {

    public static StackPane root = new StackPane();


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

//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 2; j++) {
//                Updater.rooms[i][j] = RoomGenerator.generateRoom(Room.RoomType.REGULAR_ROOM, new int[]{i, j});
//            }
//        }
//        Updater.rooms[0][0] = RoomGenerator.generateRoom(Room.RoomType.STARTING_ROOM, new int[]{0, 0});
//        currentRoom = Updater.rooms[currentRoomID[0]][currentRoomID[1]]; // to ensure that the room is displayed correctly


        Updater.currentRoom = RoomGenerator.generateRoom(Room.RoomType.REGULAR_ROOM, new int[]{0, 0});
        CellLoader.loadAll();
        root.getChildren().add(CellLoader.gameDisplay);
        root.getChildren().add(logic.Counter.child);
        CellLoader.gameDisplay.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Game");
        Scene scene = new Scene(root, 740, 500);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(e -> {
            try {
                Updater.update(e);
            } catch (NoSuchMethodException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            } catch (InstantiationException instantiationException) {
                instantiationException.printStackTrace();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            } catch (InvocationTargetException invocationTargetException) {
                invocationTargetException.printStackTrace();
            }
        });

        logic.Timer gameTimer = new logic.Timer();
        root.getChildren().add(gameTimer.timerDisplay);
        new AnimationTimer(){
            @Override
            public void handle(long x) {
                gameTimer.update();
            }
        }.start();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

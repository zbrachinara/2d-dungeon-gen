package main;

import tile.Avatar;
import logic.Room;
import logic.Updater;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tile.TileLinker;

public class Main extends Application { // main class

    public static Room[][] rooms = new Room[2][2];

    //public static void do_nothing() {

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

    //}

    @Override
    public void start(Stage primaryStage) throws Exception {

        TileLinker.load();
        Updater.rooms = rooms;
        Updater.avatar = new Avatar();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
//                rooms[i][j] = RoomGenerator.generateRoom();
            }
        }

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

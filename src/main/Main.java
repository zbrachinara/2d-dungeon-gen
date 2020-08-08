package main;

import graphics.Room;
import graphics.RoomGenerator;
import graphics.Updater;

import java.util.Random;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tile.TileLinker;

public class Main extends Application { // main class

    public static Room[][] rooms = new Room[2][2];

    public static void do_nothing() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                rooms[i][j] = RoomGenerator.generateRoom((long) (new Random().nextFloat() * Long.MAX_VALUE));
            }
        }

        TileLinker.load();
        Updater.rooms = rooms;

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        for (int i = 0; i < 100; i++) {
            System.out.println((long) (new Random().nextFloat() * Long.MAX_VALUE));
        }
        System.out.println(Long.MAX_VALUE);

    }


    public static void main(String[] args) {
        launch(args);
    }
}

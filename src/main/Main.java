package main;

import graphics.Room;
import graphics.RoomGenerator;

import java.util.Random;

import graphics.Updater;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application { // main class

    public static Room[][] rooms = new Room[2][2];

    @Override
    public void start(Stage primaryStage) throws Exception{

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                rooms[i][j] = RoomGenerator.generateRoom((long) (new Random().nextFloat() * Long.MAX_VALUE));
            }
        }

        Updater.rooms = rooms;

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

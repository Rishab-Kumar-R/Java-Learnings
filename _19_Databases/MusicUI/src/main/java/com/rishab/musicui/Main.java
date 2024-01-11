package com.rishab.musicui;

import com.rishab.musicui.model.Datasource;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Controller controller = fxmlLoader.getController();

            if (controller == null) {
                System.out.println("Error: Unable to get controller");
                Platform.exit();
            } else {
                controller.listArtists();
            }

            stage.setTitle("Music Database");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error: Unable to load FXML file");
            Platform.exit();
        }
    }

    @Override
    public void init() throws Exception {
        super.init();
//        Initialize the database connection
        if (!Datasource.getInstance().open()) {
            System.out.println("FATAL ERROR: Couldn't connect to database");
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
//        Close the database connection
        Datasource.getInstance().close();
    }

    public static void main(String[] args) {
        launch();
    }
}
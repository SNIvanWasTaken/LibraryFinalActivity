package com.ilm2223.unit5.libraryfinalactivity;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Stage myStage;

    @Override
    public void start(Stage stage) throws IOException {
        myStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("libraryView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 583,511);
        stage.setTitle("Library");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void closeApp(){
        myStage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}
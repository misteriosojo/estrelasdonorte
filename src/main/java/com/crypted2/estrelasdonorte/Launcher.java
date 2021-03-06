package com.crypted2.estrelasdonorte;

import com.crypted2.estrelasdonorte.database.DbConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        primaryStage.setTitle("Estrelas do Norte - Manager");
        primaryStage.setScene(new Scene(root, 1024, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package com.crypted2.estrelasdonorte;

import com.crypted2.estrelasdonorte.database.DbConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        primaryStage.setTitle("Estrelas do Norte - Manager");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();

        DbConnector dbConnector = new DbConnector();
        dbConnector.init();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

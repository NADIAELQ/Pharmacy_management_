package application;

import com.example.pharmacygestion.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;


import java.io.IOException;
import java.util.Objects;

public class StartApplication extends Application {
    @Override
    public void start(Stage primaryStage){

        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Interfaces/facture.fxml")));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application/Application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Home");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }}

    public static void main(String[] args) {
        launch(args);
    }}

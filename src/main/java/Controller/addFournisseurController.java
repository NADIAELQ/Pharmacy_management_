package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class addFournisseurController implements Initializable {

        @FXML
        private TextField text;
        @FXML
        private TextField orderNumberField;
        @FXML
        private TextField orderNumberField2;
        @FXML
        private TextField orderNumberField3;
        @FXML
        private TextField orderNumberField4;

        private Connection connection;

        private Scene scene;

        public void setScene(Scene scene) {
            this.scene = scene;
        }



        @Override
        public void initialize(URL location, ResourceBundle resources) {
            // Connect to the database
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Failed to connect to the database.");
            }
        }

        @FXML
        private void addFournisseur() {
            String nom = orderNumberField.getText();
            String prenom = orderNumberField4.getText();
            String email = orderNumberField3.getText();
            int tel = Integer.parseInt(orderNumberField2.getText());


            // Insert data into the database
            try {
                String query = "INSERT INTO fournisseur (nom, prenom, email, tel) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, nom);
                statement.setString(2, prenom);
                statement.setString(3, email);
                statement.setInt(4, tel);

                statement.executeUpdate();

                statement.close();

                text.insertText(0,"Data inserted\r\n successfully\r\n into the database.");

                orderNumberField.clear();
                orderNumberField2.clear();
                orderNumberField3.clear();
                orderNumberField4.clear();
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Failed to insert data into the database.");
            }
        }
        @FXML
        private void goBack() {

        }


        private void showAlert(String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();}
    }

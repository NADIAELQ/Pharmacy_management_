package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjouterproduitController implements Initializable {

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
    private void addProduct() {
        String libelle = orderNumberField.getText();
        int quantite = Integer.parseInt(orderNumberField4.getText());
        double prixVente = Double.parseDouble(orderNumberField3.getText());
        double prixAchat = Double.parseDouble(orderNumberField2.getText());

        // Insert data into the database
        try {
            String query = "INSERT INTO produit (libelle, quantite, prix_vente, prix_achat) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, libelle);
            statement.setInt(2, quantite);
            statement.setDouble(3, prixVente);
            statement.setDouble(4, prixAchat);

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/produit.fxml"));
            Parent root = loader.load();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Failed to load the previous interface.");
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();}
}




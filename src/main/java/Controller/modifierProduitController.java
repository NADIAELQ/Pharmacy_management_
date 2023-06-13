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

public class modifierProduitController implements Initializable {

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
    private ProduitController.Produit selectedProduct;

    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public void setSelectedProduct(ProduitController.Produit selectedProduct) {
        this.selectedProduct = selectedProduct;

        orderNumberField.setText(selectedProduct.getLibelle());
        orderNumberField2.setText(Integer.toString(selectedProduct.getQuantite()));
        orderNumberField3.setText(Double.toString(selectedProduct.getPrixVente()));
        orderNumberField4.setText(Double.toString(selectedProduct.getPrixAchat()));
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to connect to the database.");
        }
    }

    @FXML
    private void modifier() {
        String libelle = orderNumberField.getText();
        int quantite = Integer.parseInt(orderNumberField2.getText());
        double prixVente = Double.parseDouble(orderNumberField3.getText());
        double prixAchat = Double.parseDouble(orderNumberField4.getText());

        // Insert data into the database
        try {
            String query = "UPDATE produit SET libelle = ?, quantite = ?, prix_vente = ?, prix_achat = ? WHERE libelle = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, libelle);
            statement.setInt(2, quantite);
            statement.setDouble(3, prixVente);
            statement.setDouble(4, prixAchat);
            statement.setString(5, libelle);

            statement.executeUpdate();

            statement.close();

            text.insertText(0,"Data modified\r\n successfully\r\n into the database.");

            orderNumberField.clear();
            orderNumberField2.clear();
            orderNumberField3.clear();
            orderNumberField4.clear();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to modify data into the database.");
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ProduitController implements Initializable {
    @FXML private AnchorPane rootPane;

    @FXML
    private TableView<Produit> productsTable1;

    @FXML
    private TableColumn<Produit, String> libelleColumn1;

    @FXML
    private TableColumn<Produit, Integer> quantiteColumn1;

    @FXML
    private TableColumn<Produit, Double> prixVenteColumn1;

    @FXML
    private TableColumn<Produit, Double> prixAchatColumn1;


    @FXML
    private TableView<Produit> productsTable;

    @FXML
    private TableColumn<Produit, String> libelleColumn;

    @FXML
    private TableColumn<Produit, Integer> quantiteColumn;

    @FXML
    private TableColumn<Produit, Double> prixVenteColumn;

    @FXML
    private TableColumn<Produit, Double> prixAchatColumn;

    @FXML
    private Button quantitereshDataButton;

    @FXML
    private Button addProductButton;

    @FXML
    private Button supprimerProduit;

    @FXML
    private TextField searchProduct;

    @FXML
    private Button searchButton;

    @FXML
    private Button productListButton;

    @FXML
    private Button backButton;

    @FXML private AnchorPane rootPane2;

    private Connection connection;

    private Scene scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the table columns
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixVenteColumn.setCellValueFactory(new PropertyValueFactory<>("prixVente"));
        prixAchatColumn.setCellValueFactory(new PropertyValueFactory<>("prixAchat"));

        // Connect to the database
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "");
            loadProduits();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Database Error", "Failed to connect to the database.");
        }
    }

    private void loadProduits() throws SQLException {


        String query = "SELECT * FROM produit";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String libelle = resultSet.getString("libelle");
                int quantite = resultSet.getInt("quantite");
                double prixVente = resultSet.getDouble("prix_vente");
                double prixAchat = resultSet.getDouble("prix_achat");
                Produit produit = new Produit(libelle, quantite, prixVente, prixAchat);
                productsTable.getItems().add(produit);
            }
        }
    }

    @FXML
    private void searchProduct() {

        libelleColumn1.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        quantiteColumn1.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixVenteColumn1.setCellValueFactory(new PropertyValueFactory<>("prixVente"));
        prixAchatColumn1.setCellValueFactory(new PropertyValueFactory<>("prixAchat"));
        String searchText = searchProduct.getText();
        productsTable1.getItems().clear();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "");
            String query = "SELECT * FROM produit WHERE libelle LIKE ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchText + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                String libelle = resultSet.getString("libelle");
                int quantite = resultSet.getInt("quantite");
                double prixVente = resultSet.getDouble("prix_vente");
                double prixAchat = resultSet.getDouble("prix_achat");
                Produit produit = new Produit(libelle, quantite, prixVente, prixAchat);
                productsTable1.getItems().add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Database Error", "Failed to connect to the database.");
        }}



    @FXML
    private void refreshData() {
        productsTable.getItems().clear();
        String query = "SELECT * FROM produit";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String libelle = resultSet.getString("libelle");
                int quantite = resultSet.getInt("quantite");
                double prixVente = resultSet.getDouble("prix_vente");
                double prixAchat = resultSet.getDouble("prix_achat");

                Produit produit = new Produit(libelle, quantite, prixVente, prixAchat);
                productsTable.getItems().add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Database Error", "Failed to fetch product data.");
        }
    }



    @FXML
    private void deleteProduct(ActionEvent event) {
        Produit selectedProduct = productsTable1.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            String libelle = selectedProduct.getLibelle();
            String query = "DELETE FROM produit WHERE libelle = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, libelle);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    showAlert(AlertType.INFORMATION, "Success", "Product deleted successfully.");
                    refreshData();
                } else {
                    showAlert(AlertType.ERROR, "Error", "Failed to delete product.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Database Error", "Failed to delete product.");
            }
        } else {
            showAlert(AlertType.WARNING, "No Selection", "Please select a product to delete.");
        }
    }


    @FXML
    private void addProduct(ActionEvent event) throws IOException {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/ajouterProduit.fxml"));
                Parent root = loader.load();
                Controller.AjouterproduitController ajouterProduitController = loader.getController();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Ajouter Produit");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

        }

    @FXML
    private void modifierProduit(ActionEvent event) throws IOException {
        Produit selectedProduct = productsTable1.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/modifierProduit.fxml"));
            Parent root = loader.load();
            Controller.modifierProduitController ModifierProduitController = loader.getController();
            ModifierProduitController.setSelectedProduct(selectedProduct);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Modifier Produit");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            searchProduct();
        } else {
            showAlert(AlertType.WARNING, "No Product Selected", "Please select a product to modify.");
        }
    }

/*
    @FXML
    private void displayProductList() {
        refreshData();
    }*/

    @FXML
    private void goBack(ActionEvent event) {

    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public class Produit {
        private String libelle;
        private int quantite;
        private double prixVente;
        private double prixAchat;

       
        public Produit(String libelle, int quantite, double prixVente, double prixAchat) {
            this.libelle = libelle;
            this.quantite = quantite;
            this.prixVente = prixVente;
            this.prixAchat = prixAchat;
        }
        public String getLibelle() {
            return libelle;
        }
        public void setLibelle(String libelle) {
            this.libelle = libelle;
        }
        public int getQuantite() {
            return quantite;
        }
        public void setQuantite(int quantite) {
            this.quantite = quantite;
        }
        public double getPrixVente() {
            return prixVente;
        }
        public void setPrixVente(double prixVente) {
            this.prixVente = prixVente;
        }
        public void setPrixAchat(double  prixAchat) {
            this.prixAchat = prixAchat;
        }
        public double getPrixAchat() {
            return prixAchat;
        

}
}}

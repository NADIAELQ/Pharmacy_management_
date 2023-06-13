package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class FactureController implements Initializable {
        @FXML
        private TableView<Produit> tableView;
        @FXML
        private TableColumn<Produit, String> colProduit;
        @FXML
        private TableColumn<Produit, Integer> colQuantite;
        @FXML
        private TableColumn<Produit, Double> colPrixUnitaire;
        @FXML
        private TableColumn<Produit, Double> colTotal;
        @FXML
        private Label lblTotal;
        @FXML
        private TextField txtLibelleProduit;
        @FXML
        private TextField txtQuantiteProduit;
        @FXML
        private TextArea txtAreaPrixTotal;
        private ObservableList<Produit> produits = FXCollections.observableArrayList();
        private Connection connection;
        private double total = 0.0;
        public FactureController() {
        String url = "jdbc:mysql://localhost/pharmacy";
        String username = "root";
        String password = "";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Lier les colonnes du TableView aux propriétés de l'objet Produit
        colProduit.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colPrixUnitaire.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        // Associer la liste des produits à afficher avec le TableView
        tableView.setItems(produits);
    }

    @FXML
    public void ajouterProduit() {
        String libelle = txtLibelleProduit.getText();
        /*int quantite = Integer.parseInt(txtQuantiteProduit.getText());*/

        // Récupérer les informations du produit à partir de la base de données
        Produit produit = getProduitFromDatabase(libelle);
        if (produit != null) {
            produit.setQuantite(Integer.parseInt(txtQuantiteProduit.getText()));
            // Ajouter le produit à la liste des produits
            produits.add(produit);
            // Effacer les champs de texte
            txtLibelleProduit.clear();
            txtQuantiteProduit.clear();
            // Calculer le total
            /*calculerTotal();*/
        } else {
            System.out.println("Produit introuvable dans la base de données.");
        }
    }
    private Produit getProduitFromDatabase(String libelle) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Produit produit = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/pharmacy", "root", "");
            String query = "SELECT * FROM produit WHERE libelle = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, libelle);
            resultSet = statement.executeQuery();
            // Vérifier si le produit existe dans la base de données
            if (resultSet.next()) {
                // Récupérer les informations du produit
                int quantite = resultSet.getInt("quantite");
                double prixVente = resultSet.getDouble("prix_vente");

                // Créer l'objet Produit avec les informations récupérées
                produit = new Produit(libelle, quantite, prixVente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return produit;
    }

    

    @FXML
    public void calculerTotal() {
        for (Produit produit : produits) {
            total += produit.quantite * produit.prixUnitaire;
        }
        // Afficher le total dans le label et le champ de texte
        lblTotal.setText("Total : " + total + " $");
        txtAreaPrixTotal.setText(String.valueOf(total));
    }

        public static class Produit {
            private final String produit;
            private final int quantite;
            private final double prixUnitaire;


            public Produit(String produit, int quantite, double prixUnitaire) {
                this.produit = produit;
                this.quantite = quantite;
                this.prixUnitaire = prixUnitaire;
            }

            public String getProduit() {
                return produit;
            }

            public int getQuantite() {
                return quantite;
            }

            public double getPrixUnitaire() {
                return prixUnitaire;
            }


            public String setProduit() {
                return produit;
            }

            public int setQuantite(int quantite) {
                return this.quantite;
            }

            public double setPrixUnitaire() {
                return prixUnitaire;
            }
            

    }}

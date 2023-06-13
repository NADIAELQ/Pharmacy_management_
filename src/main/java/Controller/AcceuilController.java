package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class AcceuilController {
    @FXML
    private AnchorPane root;
    private Scene scene;

    @FXML
    void pharmacien(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/pharmacien.fxml"));
            Parent fxml = loader.load();
            PharmacieController controller = loader.getController();
            scene = new Scene(fxml);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(" Pharmacien");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void commande(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/commande.fxml"));
            Parent fxml = loader.load();
            CommandeController controller = loader.getController();
            scene = new Scene(fxml);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("COMMENDE");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void facture(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/facture.fxml"));
            Parent fxml = loader.load();
            FactureController controller = loader.getController();
            scene = new Scene(fxml);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("FACTURE");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void fournisseur(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/Fournisseur.fxml"));
            Parent fxml = loader.load();
            FournisseurController controller = loader.getController();
            scene = new Scene(fxml);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("FACTURE");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void historique(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/historique.fxml"));
            Parent fxml = loader.load();
            HistoriqueController controller = loader.getController();
            scene = new Scene(fxml);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("FACTURE");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void acceuil(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/acceuil.fxml"));
            Parent fxml = loader.load();
            AcceuilController controller = loader.getController();
            scene = new Scene(fxml);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("FACTURE");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void produit(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/produit.fxml"));
            Parent fxml = loader.load();
            ProduitController controller = loader.getController();
            scene = new Scene(fxml);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(" Produit");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            //root.getChildren().clear();
            //root.getChildren().add(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

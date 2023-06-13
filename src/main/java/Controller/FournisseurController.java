package Controller;

import Class.Fournisseur;
import Connecte.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
public class FournisseurController implements Initializable {


    @FXML
    private TableView<Fournisseur> table;
    @FXML
    private TableColumn<Fournisseur,Integer> id;
    @FXML
    private TableColumn<Fournisseur,String>nom;
    @FXML
    private TableColumn<Fournisseur,String>prenom;
    @FXML
    private TableColumn<Fournisseur,String>email;
    @FXML
    private TableColumn<Fournisseur,Integer>tel;

    public ObservableList<Fournisseur> data= FXCollections.observableArrayList();
    @FXML
    public void afficherFournisseur() {
        try{
            Connection con= dbConnection.connect();
            String sql ="SELECT*FROM Fournisseur ";
            PreparedStatement stat=con.prepareStatement(sql);
            ResultSet rs=stat.executeQuery();
            while(rs.next()){
                data.add(new Fournisseur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
            }
            con.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        id.setCellValueFactory(new PropertyValueFactory<Fournisseur,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("email"));
        tel.setCellValueFactory(new PropertyValueFactory<Fournisseur,Integer>("tel"));
        table.setItems(data);
    }
    @FXML
    public void ajouterFournisseur() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/addFournisseur.fxml"));
        Parent root = loader.load();
        Controller.addFournisseurController addFournisseurController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ajouter autre Fournisseur");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }


}




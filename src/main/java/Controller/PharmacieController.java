package Controller;

import Class.pharmacien;
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

public class PharmacieController implements Initializable {
	@FXML private TableView<pharmacien>table;
	@FXML private TableColumn<pharmacien,Integer>id;
	@FXML private TableColumn<pharmacien,String>nom;
	@FXML private TableColumn<pharmacien,String>prenom;
	@FXML private TableColumn<pharmacien,String>email;
	@FXML private TableColumn<pharmacien,Integer>tel;
	
public ObservableList<pharmacien> data=FXCollections.observableArrayList();
@FXML 
public void afficherPharmacien() {
	try{
	Connection con=dbConnection.connect();
	String sql ="SELECT*FROM pharmacien ";
	PreparedStatement stat=con.prepareStatement(sql);
	ResultSet rs=stat.executeQuery();
	while(rs.next()){
		data.add(new pharmacien(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
		}
	con.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	id.setCellValueFactory(new PropertyValueFactory<pharmacien,Integer>("id"));
	nom.setCellValueFactory(new PropertyValueFactory<pharmacien,String>("nom"));
	prenom.setCellValueFactory(new PropertyValueFactory<pharmacien,String>("prenom"));
	email.setCellValueFactory(new PropertyValueFactory<pharmacien,String>("email"));
	tel.setCellValueFactory(new PropertyValueFactory<pharmacien,Integer>("tel"));
    table.setItems(data);
  }
@FXML
public void ajouterPharmacien() throws IOException {

	                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/ajouterPharmacien.fxml"));
	                Parent root = loader.load();
	                Controller.AjouterPharmacieController ajouterPharmacieController = loader.getController();
	                Scene scene = new Scene(root);
	                Stage stage = new Stage();
	                stage.setScene(scene);
	                stage.setTitle("Ajouter autre Pharmacien");
	                stage.initModality(Modality.APPLICATION_MODAL);
	                stage.showAndWait();

	        }

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}
	
		
	}


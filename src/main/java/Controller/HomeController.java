package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
	@FXML private AnchorPane rootPane;
	@FXML private Scene scene;
@FXML 
public void quitter(ActionEvent event) {
	System.exit(0);
}
@FXML
public void lancerwin1(ActionEvent event) throws IOException {
	AnchorPane pane =FXMLLoader.load(getClass().getResource("/Interfaces/win1.fxml"));
	rootPane.getChildren().setAll(pane);
}

@FXML
public void lancerwin2(ActionEvent event) throws IOException {
	AnchorPane pane =FXMLLoader.load(getClass().getResource("/Interfaces/Win2.fxml"));
	rootPane.getChildren().setAll(pane);
}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	@FXML
	public void informationAlert(ActionEvent event) throws IOException {
		Alert alert =new Alert(AlertType.INFORMATION);
		alert.setTitle("information");
		alert.setContentText("cette application pour gestion de pharmacie");
		alert.showAndWait();
	}

	@FXML
	public void lancerAccueill(ActionEvent event) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/Acceuil.fxml"));
	        Parent fxml = loader.load();
	       AcceuilController controller = loader.getController();
	        Scene scene = new Scene(fxml);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.setTitle("Accueil");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
//AnchorPane pane =FXMLLoader.load(getClass().getResource("/Interfaces/Acceuil.fxml"));
	    	//rootPane.getChildren().setAll(pane);
	    	
	    }




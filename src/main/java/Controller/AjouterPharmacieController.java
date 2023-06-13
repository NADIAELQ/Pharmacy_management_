package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AjouterPharmacieController {

    @FXML
    private TextField id;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private TextField tel;

    @FXML
    private Button btn;

    @FXML
    private void ajouterPharm() {
    	 int  pharmacienId= Integer.parseInt(id.getText());
    	 String  pharmacienNom = nom.getText();
        String pharmacienPrenom = prenom.getText();
        String pharmacienEmail = email.getText();
        String pharmacienTel = tel.getText();

        // Insert data into the database
        try {
            Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy","root","");


            String query = "INSERT INTO pharmacien (id, nom, prenom, email, tel) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, pharmacienId);
            statement.setString(2, pharmacienNom);
            statement.setString(3, pharmacienPrenom);
            statement.setString(4, pharmacienEmail);
            statement.setString(5, pharmacienTel);

            statement.executeUpdate();
            statement.close();
            connection.close();
            
   
            id.clear();
            nom.clear();
            prenom.clear();
            email.clear();
            tel.clear();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to insert data into the database.");
        }
    }
    private void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();}
}


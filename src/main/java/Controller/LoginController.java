package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField txtF;
    @FXML
    private PasswordField passF;
    @FXML
    private Label lbletat;

    public void login(ActionEvent event) throws SQLException, IOException {
        Connection con = Connecte.dbConnection.connect();
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM administrateur WHERE nom=? AND prenom=?";
        try {
            stat = con.prepareStatement(sql);
            stat.setString(1, txtF.getText().toString());
            stat.setString(2, passF.getText().toString());
            rs = stat.executeQuery();
            if (rs.next()) {
                //Chargement de la nouvelle interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/Home.fxml"));
                Parent root = loader.load();
                //Création d'une nouvelle scène
                Scene scene = new Scene(root);
                //Accès au stage actuel
                Stage stage = (Stage) lbletat.getScene().getWindow();
                //Remplacement de la scène actuelle par la nouvelle scène
                stage.setScene(scene);
                stage.show();
            } else {
                lbletat.setText("Non Connecté");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                stat.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }
}



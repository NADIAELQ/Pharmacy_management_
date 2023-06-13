package Controller;

import Connecte.dbConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HistoriqueController implements Initializable {
	Connection cnx;
	public PreparedStatement st;
	public ResultSet result;

    @FXML
    private TableColumn<?, ?> cin_date;

    @FXML
    private TableColumn<?, ?> cin_number;

    @FXML
    private TableView<?> cin_qt;

    @FXML
    private TextField txt_historique;

    @FXML
    void personId( ) {

    }

    @FXML
    void tableEvent( ) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		cnx=dbConnection.connect();
	}

}

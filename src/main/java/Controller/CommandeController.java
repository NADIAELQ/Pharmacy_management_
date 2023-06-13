package Controller;

import Class.commander;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CommandeController implements Initializable{
	    @FXML
	    private TextField txt_number;

	    @FXML
	    private TextField txt_prix;

	    @FXML
	    private TextField txt_etat;

	    @FXML
	    private TextField txt_date;

	    @FXML
	    private ComboBox<String> cb_jour;

	    @FXML
	    private ComboBox<String> cb_année;

	    @FXML
	    private ComboBox<String> cb_mois;

	    @FXML
	    private TableView<commander> table_cmd;

	    @FXML
	    private TableColumn<commander, Integer> cin_number;

	    @FXML
	    private TableColumn<commander, Double> cin_prix;

	    @FXML
	    private TableColumn<commander, String> cin_etat;

	    @FXML
	    private TableColumn<commander, Date> cin_date;

	    @FXML
	    private Button btn_ajouter;

	    @FXML
	    private Button btn_annuler;

	    @FXML
	    private Button btn_valider;
	    
	    private Connection connection;

	    @FXML
	    void ajoutercmd() {

	    }

	    @FXML
	    void annulercmd() {

	    }

	    @FXML
	    void etatcommande() {

	    }

	    @FXML
	    void prixcommande() {

	    }

	    @FXML
	    void remplirannée() {

	    }

	    @FXML
	    void remplirjour() {

	    }

	    @FXML
	    void remplirmois() {

	    }

	    @FXML
	    void searchcommander() {

	    }

	    @FXML
	    void validercmd() {

	    }
	    private void loadCommande() throws SQLException {


	        String query = "SELECT * FROM produit";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                int nbrCmd   = resultSet.getInt("numCmd");
	                double prixCmd = resultSet.getDouble("prix_total");
	                String etatcmd = resultSet.getString(" etat_cmd");
	                Date date = resultSet.getDate("date");
	                commander commander = new commander( nbrCmd,prixCmd, etatcmd, date);
	                table_cmd.getItems().add(commander);
	            }
	        }
	    }
	    ObservableList<commander> ListCmd = FXCollections.observableArrayList();

	    public void showCommander() {
	        table_cmd.getItems().clear();
	        String sql = "select numCmd ,date,prix_total,etat_cmd from ";
	    }
	    @Override
	   public void initialize(URL location, ResourceBundle resources) {
	        cin_number.setCellValueFactory(new PropertyValueFactory<>("number"));
	        cin_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
	        cin_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
	        cin_date.setCellValueFactory(new PropertyValueFactory<>("date"));

	        // Ajouter des valeurs factices pour les ComboBox (à remplacer par les valeurs réelles)
	        cb_jour.getItems().addAll("Jour 1", "Jour 2", "Jour 3");
	        cb_mois.getItems().addAll("Mois 1", "Mois 2", "Mois 3");
	        cb_année.getItems().addAll("Année 1", "Année 2", "Année 3");
	        try {
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "");
	            loadCommande();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            showAlert(AlertType.ERROR, "Database Error", "Failed to connect to the database.");
	        }
	        
	    }
	    
	    private void showAlert(AlertType alertType, String title, String message) {
	        Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();}
}


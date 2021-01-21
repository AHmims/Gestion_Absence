package sample.interfaces.admin;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.layout.HBox;

import javafx.scene.control.TextField;

import javafx.scene.control.PasswordField;

import javafx.scene.layout.VBox;
import sample.daoAPI.ApprenantDao;
import sample.helpers.Connexion;
import javafx.scene.control.DatePicker;

import javafx.scene.control.ChoiceBox;

public class ajouterController implements Initializable{
	@FXML
	private VBox root_ajouter;
	@FXML
	private VBox form_add;
	@FXML
	private TextField idNom;
	@FXML
	private TextField idPrenom;
	@FXML
	private TextField idCin;
	@FXML
	private HBox idDateNaissance;
	@FXML
	private TextField idLogin;
	@FXML
	private PasswordField idPassword;
	@FXML
	private ChoiceBox <String>idRole;
	@FXML
	private TextField idMatricule;
	@FXML
	private DatePicker idDateEmbauche;
	@FXML
	private TextField idService;
	@FXML
	private TextField idCne;
	@FXML
	private TextField idPromotion;
	@FXML
	private TextField idNomTuteur;
	@FXML
	private TextField idPrenomTuteur;
	@FXML
	private TextField idNiveau;
	@FXML
	private TextField idGroupe;
	@FXML
	private Button btn_add;
	
	@FXML 
	private VBox idVboxApprenant;
	@FXML 
	private VBox idVboxAdmin;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chargeCmbRole();
		admin = apprenant = sectretaire = formateur = false;
	   
		
	}
	
	Boolean admin , apprenant ,sectretaire, formateur ;
 
	
	public void chargeCmbRole()
	{
		idRole.getItems().add("Administrateur");
		idRole.getItems().add("Formateur");
		idRole.getItems().add("Secretaire");
		idRole.getItems().add("Apprenant");
		 idRole.setOnAction(eventRole); 
		 btn_add.setOnAction(eventAdd); 
		idRole.getSelectionModel().select(0);;
		
	}
	
	public void showAndHideSection()
	{
		if(idRole.getValue().equals("Apprenant") )
		{
			apprenant = true;
			idVboxApprenant.setVisible(true);
			idVboxAdmin.setVisible(false);
			idVboxApprenant.managedProperty().bind(idVboxApprenant.visibleProperty());
			idVboxAdmin.managedProperty().bind(idVboxAdmin.visibleProperty());
			
		}
		else if(idRole.getValue().equals("Formateur") )
		{
			formateur = true;
			idVboxApprenant.setVisible(false);
			idVboxAdmin.setVisible(true);
			idVboxApprenant.managedProperty().bind(idVboxApprenant.visibleProperty());
			idVboxAdmin.managedProperty().bind(idVboxAdmin.visibleProperty());
			
		}
		else if(idRole.getValue().equals("Secretaire") )
		{
			sectretaire = true;
			idVboxApprenant.setVisible(false);
			idVboxAdmin.setVisible(true);
			idVboxApprenant.managedProperty().bind(idVboxApprenant.visibleProperty());
			idVboxAdmin.managedProperty().bind(idVboxAdmin.visibleProperty());
			
			 
		}
		else if(idRole.getValue().equals("Administrateur"))
		{
			idVboxApprenant.setVisible(false);
			idVboxAdmin.setVisible(true);
			idVboxApprenant.managedProperty().bind(idVboxApprenant.visibleProperty());
			idVboxAdmin.managedProperty().bind(idVboxAdmin.visibleProperty());
		}
	}
	/*
	public void hideAdminAndSecrSection()
	{
		idMatricule.setVisible(false);
		idDateEmbauche.setVisible(false);
		idService.setVisible(false);
		
		
		
		
	}
	public void showAdminAndSecSection()
	{
		idMatricule.setVisible(true);
		idDateEmbauche.setVisible(true);
		idService.setVisible(true);
	}
	public void showFormateurSection()
	{
		showAdminAndSecSection();
		idGroupe.setVisible(true);
	}
	public void showApprenantSection()
	{
		idCne.setVisible(true);
		idPromotion.setVisible(true);
		idNomTuteur.setVisible(true);
		idPrenomTuteur.setVisible(true);
		idGroupe.setVisible(true);
		idNiveau.setVisible(true);
	}
	public void hideApprenanatSection()
	{
		idCne.setVisible(false);
		idPromotion.setVisible(false);
		idNomTuteur.setVisible(false);
		idPrenomTuteur.setVisible(false);
		idGroupe.setVisible(false);
		idNiveau.setVisible(false);
	}
	*/
	// Selectionner un role event

	
	 EventHandler<ActionEvent> eventRole = 
             new EventHandler<ActionEvent>() { 
       public void handle(ActionEvent e) 
       { 
    	   showAndHideSection();
       } 
   }; 
   EventHandler<ActionEvent> eventAdd = 
           new EventHandler<ActionEvent>() { 
     public void handle(ActionEvent e) 
     { 
    	 ajouterUtilisateur();
     } 
 }; 
   
   public void ajouterUtilisateur()
   {
	   if(apprenant)
	   {
		    
		   if(existe(idCin.getText()))
		   {
			   System.out.println("existed");
		   }
		   else
		   {
			   
		   }
		   
	   }
   }
   
   public Boolean existe(String id)
	{
		Boolean res = false;
		String query = "select * from Utilisateur where cin ='"+id+"'";
		 if(executeQuery(query))
		 {
			res = true;
		 }
		
		
		return res;
	}
	 private  Boolean executeQuery(String query) {
			Boolean res = false;
			Connection con = Connexion.db_connect();
			 
			Statement st;

			ResultSet rs;
			try
			{
				st = con.createStatement();
				rs = st.executeQuery(query);
				 
				while(rs.next())
				{
					res = true;
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return res;
			
		}
   
   

}

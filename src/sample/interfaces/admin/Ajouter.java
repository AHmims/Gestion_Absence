package sample.interfaces.admin;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.daoAPI.AdministrateurDao;
import sample.daoAPI.ApprenantDao;
import sample.daoAPI.FormateurDao;
import sample.daoAPI.GroupeDao;
import sample.daoAPI.SecretaireDao;
import sample.daoAPI.UtilisateurDao;
import sample.domainClasses.Administrateur;
import sample.domainClasses.Apprenant;
import sample.domainClasses.Formateur;
import sample.domainClasses.Groupe;
import sample.domainClasses.Secretaire;
import sample.domainClasses.Utilisateur;
import sample.helpers.Connexion;
import javafx.scene.control.DatePicker;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

public class Ajouter implements Initializable {
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
    private DatePicker idDateNaissance;
    @FXML
    private TextField idLogin;
    @FXML
    private PasswordField idPassword;
    @FXML
    private ChoiceBox<String> idRole;
    @FXML
    private ChoiceBox<String> idCmbNiveau;
    @FXML
    private ChoiceBox<String> idCmbGroupe;
    @FXML
    private ChoiceBox<String> idCmbGroupeFor;
    @FXML
    private ChoiceBox<String> idCmbPromotion;
    @FXML
    private VBox idVboxAdmin;
    @FXML
    private HBox idHboxForm;
    @FXML
    private TextField idMatricule;
    @FXML
    private DatePicker idDateEmbauche;
    @FXML
    private TextField idService;
    @FXML
    private VBox idVboxApprenant;
    @FXML
    private TextField idCne;
 
    @FXML
    private TextField idNomTuteur;
    @FXML
    private TextField idPrenomTuteur;
   
    @FXML
    private Button btn_add;
    Boolean admin, apprenant, sectretaire, formateur;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        admin = apprenant = sectretaire = formateur = false;
        chargeCmbRole();
        chargercmbGroupeAndNiveauAndPromotion();


    }


    public void chargeCmbRole() {
        idRole.getItems().add("Administrateur");
        idRole.getItems().add("Formateur");
        idRole.getItems().add("Secretaire");
        idRole.getItems().add("Apprenant");
        idRole.setOnAction(eventRole);
        btn_add.setOnAction(eventAdd);
        idRole.getSelectionModel().select(0);
        ;

    }

    public void showAndHideSection() {
        if (idRole.getValue().equals("Apprenant")) {
            apprenant = true;
            admin = sectretaire = formateur = false;
            idVboxApprenant.setVisible(true);
            idVboxAdmin.setVisible(false);
            idVboxApprenant.managedProperty().bind(idVboxApprenant.visibleProperty());
            idVboxAdmin.managedProperty().bind(idVboxAdmin.visibleProperty());
            //extra field for formateur
            idHboxForm.setVisible(false);
            idHboxForm.managedProperty().bind(idHboxForm.visibleProperty());

        } else if (idRole.getValue().equals("Formateur")) {
            formateur = true;
            admin = apprenant = sectretaire = false;
            idVboxApprenant.setVisible(false);
            idVboxAdmin.setVisible(true);
            idVboxApprenant.managedProperty().bind(idVboxApprenant.visibleProperty());
            idVboxAdmin.managedProperty().bind(idVboxAdmin.visibleProperty());
            //extra field for formateur
            idHboxForm.setVisible(true);
            idHboxForm.managedProperty().bind(idHboxForm.visibleProperty());
           

        } else if (idRole.getValue().equals("Secretaire")) {
            sectretaire = true;
            admin = apprenant = formateur = false;
            idVboxApprenant.setVisible(false);
            idVboxAdmin.setVisible(true);
            idVboxApprenant.managedProperty().bind(idVboxApprenant.visibleProperty());
            idVboxAdmin.managedProperty().bind(idVboxAdmin.visibleProperty());
            //extra field for formateur
            idHboxForm.setVisible(false);
            idHboxForm.managedProperty().bind(idHboxForm.visibleProperty());


        } else if (idRole.getValue().equals("Administrateur")) {
            admin = true;
            apprenant = sectretaire = formateur = false;
            idVboxApprenant.setVisible(false);
            idVboxAdmin.setVisible(true);
            idVboxApprenant.managedProperty().bind(idVboxApprenant.visibleProperty());
            idVboxAdmin.managedProperty().bind(idVboxAdmin.visibleProperty());
            //extra field for formateur
            idHboxForm.setVisible(false);
            idHboxForm.managedProperty().bind(idHboxForm.visibleProperty());
        }
    }

    // Selectionner un role event


    EventHandler<ActionEvent> eventRole =
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    showAndHideSection();
                }
            };
    EventHandler<ActionEvent> eventAdd =
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    ajouterUtilisateur();
                }
            };
            
            /*
            public Boolean checkFields(Boolean user)
            {
            	Boolean ret = false;
            	if(user == admin || user == sectretaire)
            	{
            		if(idCin.getText()!= "" && idNom.getText()!="" && idPrenom.getText()!="")
            	}
            	else if (user = apprenant)
            	{
                    Apprenant a = new Apprenant(idCin.getText(), idNom.getText(), idPrenom.getText(), date, idLogin.getText(), idPassword.getText(), 0, idCne.getText(), idCmbPromotion.getValue(), idNomTuteur.getText(), idPrenomTuteur.getText(), valeurCmbNiveau(idCmbNiveau.getValue()), idCmbGroupe.getValue());

            	}
            	else if(user = formateur)
            	{
            		
            	}
            	return ret;
            	
            }
            */

    public void ajouterUtilisateur() {
        if (existe("cin", idCin.getText()) || existe("user", idLogin.getText())) {
            System.out.println(" cin ou login existant !!");
        } else {
            Calendar date = Calendar.getInstance();
            date.setTime(Date.from(idDateNaissance.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));


            if (apprenant) {
 
                UtilisateurDao ud = new UtilisateurDao();
                ApprenantDao ad = new ApprenantDao();


                Utilisateur u = new Utilisateur(idCin.getText(), idNom.getText(), idPrenom.getText(), date, idLogin.getText(), idPassword.getText(), 0);
           

                Apprenant a = new Apprenant(idCin.getText(), idNom.getText(), idPrenom.getText(), date, idLogin.getText(), idPassword.getText(), 0, idCne.getText(), idCmbPromotion.getValue(), idNomTuteur.getText(), idPrenomTuteur.getText(), valeurCmbNiveau(idCmbNiveau.getValue()), idCmbGroupe.getValue());


                if (ad.save(a) &&  ud.save(u)) {
                    System.out.println("Apprenant added successfully !");
                }


            } else if (admin) {
                Calendar dateEmbauche = Calendar.getInstance();
                dateEmbauche.setTime(Date.from(idDateEmbauche.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

                UtilisateurDao ud = new UtilisateurDao();


                Utilisateur u = new Utilisateur(idCin.getText(), idNom.getText(), idPrenom.getText(), date, idLogin.getText(), idPassword.getText(), 3);
      
                AdministrateurDao ad = new AdministrateurDao();
                Administrateur adm = new Administrateur(idCin.getText(), idNom.getText(), idPrenom.getText(), date, idLogin.getText(), idPassword.getText(), 3, idMatricule.getText(), dateEmbauche, idService.getText());
                /*
                if (ad.save(adm) &&    ud.save(u)) {
                    System.out.println("Admin added successfully !");
                }
                */
                ad.save(adm) ; ud.save(u);
                System.out.println("Admin added successfully !");


            } else if (formateur) {
                Calendar dateEmbauche = Calendar.getInstance();
                dateEmbauche.setTime(Date.from(idDateEmbauche.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                UtilisateurDao ud = new UtilisateurDao();
                FormateurDao fd = new FormateurDao();

                Utilisateur u = new Utilisateur(idCin.getText(), idNom.getText(), idPrenom.getText(), date, idLogin.getText(), idPassword.getText(), 2);
               

                Formateur f = new Formateur(idCin.getText(), idNom.getText(), idPrenom.getText(), date, idLogin.getText(), idPassword.getText(), 2, idMatricule.getText(), dateEmbauche, idService.getText(),idCmbGroupeFor.getValue());
                System.out.println(f.toString());

                if (fd.save(f) &&  ud.save(u)) {
                    System.out.println("Formateur added successfully !");
                }


            } else if (sectretaire) {
                Calendar dateEmbauche = Calendar.getInstance();
                dateEmbauche.setTime(Date.from(idDateEmbauche.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                UtilisateurDao ud = new UtilisateurDao();
                SecretaireDao sd = new SecretaireDao();


                Utilisateur u = new Utilisateur(idCin.getText(), idNom.getText(), idPrenom.getText(), date, idLogin.getText(), idPassword.getText(), 1);
                
                Secretaire s = new Secretaire(idCin.getText(), idNom.getText(), idPrenom.getText(), date, idLogin.getText(), idPassword.getText(), 1, idMatricule.getText(), dateEmbauche, idService.getText());

                if (sd.save(s) &&    ud.save(u)) {
                    System.out.println("Secretaire added successfully !");
                }

            }


        }


    }

    public Boolean existe(String det, String id) {
        String query = "";

        if (det == "cin") {
            query = "select * from Utilisateur where cin ='" + id + "'";
        } else {
            query = "select * from Utilisateur where user ='" + id + "'";
        }


        return executeQueryCheck(query);

    }
    public void chargercmbGroupeAndNiveauAndPromotion()
    {
    	idCmbNiveau.getItems().clear();
    	idCmbGroupe.getItems().clear();
    	idCmbPromotion.getItems().clear();
    	idCmbNiveau.getItems().add("Première année");
    	idCmbNiveau.getItems().add("Deuxième année");
    	idCmbGroupeFor.getItems().clear();
    	
    	GroupeDao gd = new GroupeDao();
    	ArrayList<Groupe> lg = new ArrayList<Groupe>();
    	lg = gd.getAll();
    	 for (Groupe groupe : lg) {
    		 idCmbGroupe.getItems().add(groupe.getIntitule());
    		 idCmbGroupeFor.getItems().add(groupe.getIntitule());
		}
    	 //promotion setUp
    	 int year = Calendar.getInstance().get(Calendar.YEAR);
    	 
    	 for(int i=2017;i<=year;i++)
    	 {
    		 idCmbPromotion.getItems().add(String.valueOf(i));
    		 
    	 }
    	 idCmbPromotion.getSelectionModel().select(0);
    	idCmbNiveau.getSelectionModel().select(0);
    	idCmbGroupe.getSelectionModel().select(0);
    	idCmbGroupeFor.getSelectionModel().select(0);
    }
    public int valeurCmbNiveau(String in)
    {
    	int ret = 0;
    	if(in.equals("Première année"))
    	
    		ret = 1;
    	
    	else
    		ret =2;
    	return ret;
    }

    private Boolean executeQueryCheck(String query) {
        Boolean res = false;
        Connection con = Connexion.db_connect();

        Statement st;

        ResultSet rs;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                res = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;

    }

     


}

package interfaces.formateur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import sample.daoAPI.AdministrateurDao;
import sample.daoAPI.ApprenantDao;
import sample.daoAPI.SeanceDao;
import sample.domainClasses.Apprenant;
import sample.domainClasses.Seance;
import sample.helpers.Session;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class GestionAbsense implements Initializable {
    private ArrayList<Apprenant> list_apprenants = new ArrayList<>();
    private ArrayList<Pane> components_apprenants = new ArrayList<>();
    private ArrayList<Pane> selected_apprenants = new ArrayList<>();
    //
    private ArrayList<Seance> list_seances = new ArrayList<>();
    private ArrayList<Pane> components_seances = new ArrayList<>();
    private Pane selected_seance = null;
    //
    @FXML
    HBox cont_apprenants;
    @FXML
    TextField srch_apprenant;
    @FXML
    HBox cont_seances;
    @FXML
    DatePicker abs_date;
    @FXML
    ToggleGroup abs_type;
    @FXML
    TextField abs_duration;

    //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayLearners();
        displayClasses();
    }

    @FXML
    public void search() {
        String query = srch_apprenant.getText().toLowerCase();
        //
        for (int i = 0; i < list_apprenants.size(); i++) {
            Apprenant apprenant = list_apprenants.get(i);
            //
            boolean exists = false;
            //
            if (apprenant.getNom().toLowerCase().contains(query) || apprenant.getPrenom().toLowerCase().contains(query) || apprenant.getCne().toLowerCase().contains(query) || apprenant.getGroupe().toLowerCase().equals(query) || (apprenant.getNiveau() + "").equals(query)) {
                exists = true;
            }
            //
            components_apprenants.get(i).setVisible(exists);
            components_apprenants.get(i).setManaged(exists);
        }

    }
    @FXML
    public void add(){
        System.out.println(abs_date.getValue()!=null);
        /*try{
            if(selected_apprenants.size()==0)
                throw new Exception("1");
            if(selected_seance == null)
                throw new Exception("2");
            if(abs_date.getValue()!=null)
        }catch(Exception e){
            System.out.println(e.getMessage());
        } */
    }
    //
    private void displayLearners() {
        try {
            list_apprenants.clear();
            components_apprenants.clear();
            cont_apprenants.getChildren().clear();
            //
            ApprenantDao apprenantDao = new ApprenantDao();
            list_apprenants = apprenantDao.getAllByFormateur(Session.cin);
            if (list_apprenants != null) {
                if (list_apprenants.size() > 0) {
                    for (Apprenant apprenant : list_apprenants) {
                        cont_apprenants.getChildren().add(make_apprenant(apprenant));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Pane make_apprenant(Apprenant apprenant) {
        Pane container = new Pane();
        container.getStyleClass().add("apprenant_card");
        //
        //
        VBox cont_data = new VBox();
        cont_data.getStyleClass().add("inner_cont");
        //
        Label nom = new Label(String.format("%s %s", apprenant.getNom(), apprenant.getPrenom()));
        nom.getStyleClass().add("nom");
        Label cne = new Label(apprenant.getCne());
        Label grp_niv = new Label(String.format("Groupe: %s | Niveau: %s", apprenant.getGroupe(), apprenant.getNiveau()));
        //
        cont_data.getChildren().addAll(nom, cne, grp_niv);
        //
        //
        Circle indicator = new Circle();
        indicator.setRadius(10);
        indicator.getStyleClass().add("indicator");
        //
        container.getChildren().addAll(cont_data, indicator);
        //
        container.setOnMouseClicked(e -> {
            if (container.getStyleClass().contains("apprenant_card_active")) {
                selected_apprenants.remove(container);
                container.getStyleClass().remove("apprenant_card_active");
            } else {
                container.getStyleClass().add("apprenant_card_active");
                selected_apprenants.add(container);
            }
        });
        //
        //container.managedProperty().bind(container.visibleProperty());
        components_apprenants.add(container);
        return container;
    }

    private void displayClasses() {
        try {
            list_seances.clear();
            components_seances.clear();
            cont_seances.getChildren().clear();
            //
            SeanceDao seanceDao = new SeanceDao();
            list_seances = seanceDao.getAllByFormateur(Session.cin);
            if (list_seances != null) {
                if (list_seances.size() > 0) {
                    for (Seance seance : list_seances) {
                        cont_seances.getChildren().add(make_seance(seance));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Pane make_seance(Seance seance) {
        Pane container = new Pane();
        container.getStyleClass().add("seance_card");
        //
        //
        VBox cont_data = new VBox();
        cont_data.getStyleClass().add("inner_cont");
        //
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/mm/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        Label dateS = new Label(sdf.format(seance.getDateSeance().getTime()));
        dateS.getStyleClass().add("dateS");
        Label groupe = new Label(String.format("Groupe: %s", seance.getGroupe()));
        Label date_h_f = new Label(String.format("%s -> %s", sdf2.format(seance.getHeureDebut().getTime()), sdf2.format(seance.getHeureFin().getTime())));
        //
        cont_data.getChildren().addAll(dateS, groupe, date_h_f);
        //
        //
        Circle indicator = new Circle();
        indicator.setRadius(10);
        indicator.getStyleClass().add("indicator");
        //
        container.getChildren().addAll(cont_data, indicator);
        //
        container.setOnMouseClicked(e -> {
            if (container.getStyleClass().contains("seance_card_active")) {
                selected_seance = null;
                container.getStyleClass().remove("seance_card_active");
            } else {
                for (Pane p_s : components_seances) {
                    p_s.getStyleClass().remove("seance_card_active");
                }
                container.getStyleClass().add("seance_card_active");
                selected_seance = container;
            }
        });
        //
        //container.managedProperty().bind(container.visibleProperty());
        components_seances.add(container);
        return container;
    }
}

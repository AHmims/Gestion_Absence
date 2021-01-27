package sample.interfaces.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import sample.daoAPI.ApprenantDao;
import sample.daoAPI.UtilisateurDao;
import sample.domainClasses.Apprenant;
import sample.domainClasses.Utilisateur;
import sample.helpers.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Utilisateurs implements Initializable {
    @FXML
    TilePane skills_container;

    //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //
    private void displayLearners() {
        try {
            UtilisateurDao utilisateurDao = new UtilisateurDao();
            ArrayList<Utilisateur> list_utilisateurs = utilisateurDao.getAll();
            if (list_utilisateurs != null) {
                if (list_utilisateurs.size() > 0) {
                    for (Utilisateur utilisateur : list_utilisateurs) {
                        skills_container.getChildren().add(make_user(utilisateur));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erreur inconnue").showAndWait();
        }
    }

    private VBox make_user(Utilisateur utilisateur) {
        VBox container = new VBox();
        container.getStyleClass().add("user_card");
        container.setSpacing(5.0);
        //
        //
        HBox cont_data_1 = new HBox();
        cont_data_1.setSpacing(10.0);
        VBox.setVgrow(cont_data_1, Priority.ALWAYS);
        //
        Label nom = new Label(String.format("%s %s", apprenant.getNom(), apprenant.getPrenom()));
        nom.getStyleClass().add("card_name");
        Label role = new Label();
        switch ()
        //
        cont_data.getChildren().addAll(nom, cne, grp_niv);
        //
        //
        Circle indicator = new Circle();
        indicator.setRadius(10);
        indicator.getStyleClass().add("indicator");
        //
        container.getChildren().addAll(cont_data, indicator);
        container.getProperties().put("index_app", components_apprenants.size());
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
}

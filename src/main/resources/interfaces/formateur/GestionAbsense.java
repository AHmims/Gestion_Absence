package interfaces.formateur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import sample.domainClasses.Apprenant;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class GestionAbsense implements Initializable {
    @FXML
    HBox cont_apprenants;
    //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //
    private void displayçlearners() {
        try{

        }catch (Exception e){
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
        Label grp_niv = new Label(String.format("%s | %s", apprenant.getGroupe(), apprenant.getNiveau()));
        //
        cont_data.getChildren().addAll(nom, cne, grp_niv);
        //
        //
        Circle indicator = new Circle();
        indicator.setRadius(10);
        indicator.getStyleClass().add("indicator");
        //
        container.getChildren().addAll(cont_data, indicator);
    }
}

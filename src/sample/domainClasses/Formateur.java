package sample.domainClasses;

import java.util.Calendar;

public class Formateur extends Administrateur{

    public Formateur(String cin, String nom, String prenom, Calendar dateNaissance, String user, String password, String matricule, Calendar dateEmbauche, String service, int nivDroit) {
        super(cin, nom, prenom, dateNaissance, user, password, matricule, dateEmbauche, service, nivDroit);
    }
}

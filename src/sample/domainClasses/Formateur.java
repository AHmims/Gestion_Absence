package sample.domainClasses;

import java.util.Calendar;

public class Formateur extends Administrateur {
    public Formateur(String cin, String nom, String prenom, Calendar dateNaissance, String user, String password, String matricule, Calendar dateEmbauche, String service) {
        super(cin, nom, prenom, dateNaissance, user, password, matricule, dateEmbauche, service);
        super.setNivDroit(2);
    }

    public Formateur(String cin, String nom, String prenom) {
        super(cin, nom, prenom);
        super.setNivDroit(2);
    }
}
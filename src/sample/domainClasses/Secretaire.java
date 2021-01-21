package sample.domainClasses;

import java.util.Calendar;

public class Secretaire extends Administrateur {

    public Secretaire(String cin, String nom, String prenom, Calendar dateNaissance, String user, String password, int nivDroit, String matricule, Calendar dateEmbauche, String service) {
        super(cin, nom, prenom, dateNaissance, user, password, nivDroit, matricule, dateEmbauche, service);
    }
}

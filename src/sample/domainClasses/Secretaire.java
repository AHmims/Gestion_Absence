package sample.domainClasses;

import java.util.Calendar;

public class Secretaire extends Administrateur {
    public Secretaire(String cin, String nom, String prenom, Calendar dateNaissance, String user, String password, String matricule, Calendar dateEmbauche, String service, int nivDroit) {
        super(cin, nom, prenom, dateNaissance, user, password, matricule, dateEmbauche, service, nivDroit);
    }
}

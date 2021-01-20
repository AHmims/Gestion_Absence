package sample.domainClasses;

import java.util.Calendar;

public class Administrateur extends Utilisateur {
    private String matricule;
    private Calendar dateEmbauche;
    private String service;
    private int nivDroit;
    //

    public Administrateur(String cin, String nom, String prenom, Calendar dateNaissance, String user, String password, String matricule, Calendar dateEmbauche, String service, int nivDroit) {
        super(cin, nom, prenom, dateNaissance, user, password);
        this.matricule = matricule;
        this.dateEmbauche = dateEmbauche;
        this.service = service;
        this.nivDroit = nivDroit;
    }
    //


    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Calendar getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Calendar dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getNivDroit() {
        return nivDroit;
    }

    public void setNivDroit(int nivDroit) {
        this.nivDroit = nivDroit;
    }

    //
    @Override
    public int getRole() {
        return this.nivDroit;
    }
}

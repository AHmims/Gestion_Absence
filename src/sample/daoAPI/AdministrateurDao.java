package sample.daoAPI;

import sample.daoAPI.api.Dao;
import sample.domainClasses.Administrateur;
import sample.domainClasses.Utilisateur;
import sample.helpers.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class AdministrateurDao implements Dao<Administrateur> {
    @Override
    public Administrateur get(String id) {
        return null;
    }

    @Override
    public Optional<Administrateur> get(Calendar id) {
        return Optional.empty();
    }

    @Override
    public List<Administrateur> getAll() {
        return null;
    }

    @Override
    public void save(Administrateur administrateur) {

    }

    @Override
    public void update(Administrateur administrateur) {

    }

    @Override
    public void delete(Administrateur administrateur) {

    }
    //
    public Administrateur login_(String login, String pass){
        try {
            Connection con = Connexion.db_connect();
            if (con == null)
                throw new Exception("Connection error");
            //
            PreparedStatement statement = con.prepareStatement("SELECT u.*, a.matricule, a.dateEmbauche, a.service, a.nivDroit FROM `Administrateur` AS a, `Utilisateur` as u WHERE u.`user` = ? AND u.`password` = ?");
            statement.setString(1, login);
            statement.setString(2, pass);
            ResultSet res = statement.executeQuery();
            if (!res.next()) {
                con.close();
                throw new Exception("Administrateur non trouvé");
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH-mm-ss");
                Calendar cl1 = Calendar.getInstance();
                cl1.setTime(res.getDate(4));
                Calendar cl2 = Calendar.getInstance();
                cl2.setTime(res.getDate(8));
                //
                return new Administrateur(res.getString(1), res.getString(2), res.getString(3), cl1, res.getString(5), res.getString(6), res.getString(7), cl2, res.getString(9), res.getInt(10));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

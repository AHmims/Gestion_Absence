package sample.daoAPI;

import sample.daoAPI.api.Dao;
import sample.domainClasses.*;
import sample.helpers.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

public class UtilisateurDao implements Dao<Utilisateur> {
    @Override
    public Optional<Utilisateur> get(String pk) {
        return Optional.empty();
    }

    @Override
    public Optional<Utilisateur> get(Calendar pk) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Utilisateur> getAll() {
        try {
            Connection con = Connexion.db_connect();
            if (con == null)
                throw new Exception("Connection error");
            //
            PreparedStatement statement = con.prepareStatement("SELECT u.* FROM `Utilisateur` AS u");
            ResultSet res = statement.executeQuery();

            ArrayList<Utilisateur> list_users = new ArrayList<>();
            while (res.next()) {
                Calendar dateN = Calendar.getInstance();
                dateN.setTime(res.getTimestamp("dateNaissance"));
                switch (res.getInt("nivDroit")) {
                    case 0:
                        list_users.add(new Apprenant(res.getString("cin"), res.getString("nom"), res.getString("prenom"), dateN, res.getString("user"), res.getString("password")));
                    case 1:
                        list_users.add(new Secretaire(res.getString("cin"), res.getString("nom"), res.getString("prenom"), dateN, res.getString("user"), res.getString("password")));
                    case 2:
                        list_users.add(new Formateur(res.getString("cin"), res.getString("nom"), res.getString("prenom"), dateN, res.getString("user"), res.getString("password")));
                    case 3:
                        list_users.add(new Administrateur(res.getString("cin"), res.getString("nom"), res.getString("prenom"), dateN, res.getString("user"), res.getString("password")));
                    default:
                        list_users.add(null);
                }
            }

            return list_users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean save(Utilisateur utilisateur) {
        return false;
    }

    @Override
    public boolean update(Utilisateur utilisateur) {
        return false;
    }

    @Override
    public boolean delete(Utilisateur utilisateur) {
        return false;
    }
}

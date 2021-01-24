package sample.daoAPI;

import sample.daoAPI.api.Dao;
import sample.domainClasses.Seance;
import sample.helpers.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

public class SeanceDao implements Dao<Seance> {
    @Override
    public Optional<Seance> get(String pk) {
        return Optional.empty();
    }

    @Override
    public Optional<Seance> get(Calendar pk) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Seance> getAll() {
        return null;
    }
    public ArrayList<Seance> getAllByFormateur(String cin_formateur){
        try{
            Connection con = Connexion.db_connect();
            if (con == null)
                throw new Exception("Connection error");
            //
            PreparedStatement statement = con.prepareStatement("");
            statement.setString(1, cin_formateur);
            ResultSet res = statement.executeQuery();
            //

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Seance seance) {

    }

    @Override
    public void update(Seance seance) {

    }

    @Override
    public void delete(Seance seance) {

    }
}

package sample.daoAPI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import sample.daoAPI.api.Dao;
import sample.domainClasses.Groupe;
import sample.helpers.Connexion;

public class GroupeDao implements Dao<Groupe> {

	@Override
	public Optional<Groupe> get(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Groupe> get(Calendar pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Groupe> getAll() {
		 ArrayList<Groupe> listeGroupe = new  ArrayList<Groupe>();
		 try {
	            Connection con = Connexion.db_connect();
	            if (con == null)
	                throw new Exception("Connection error");
	            String query = "SELECT * FROM Groupe ";
	            Statement st;
	            ResultSet rs;
	            try {
	                st = con.createStatement();
	                rs = st.executeQuery(query);
	                Groupe g;
	                while (rs.next()) {
 
	                    g = new Groupe(rs.getInt(1),rs.getString(2));

	                    listeGroupe.add(g);
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	            return listeGroupe;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return listeGroupe;
	        }
	}

	@Override
	public boolean save(Groupe t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Groupe t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Groupe t) {
		// TODO Auto-generated method stub
		return false;
	}

}

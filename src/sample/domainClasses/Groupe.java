package sample.domainClasses;

public class Groupe {

	private int id;
	private String intitule;
	
	public Groupe(int i , String intitule)
	{
		setId(i);
		setIntitule(intitule);
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
}

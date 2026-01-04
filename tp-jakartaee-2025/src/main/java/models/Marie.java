package models;

public class Marie {
	
	private String nom;
	private Long codePostal;
	
	public Marie(String nom, Long CodePostal) {
	this.nom = nom;
	this.codePostal = codePostal;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Long getCodePostal() {
		return codePostal;
	}
	
	public void setCodePostal(Long codePostal) {
		this.codePostal = codePostal;
	}
	
}

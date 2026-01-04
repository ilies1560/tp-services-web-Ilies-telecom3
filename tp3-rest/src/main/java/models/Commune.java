package models;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Commune {
    private int codePostal;
    private String nom;

    public Commune() {
    }

    public Commune(int codePostal, String nom) {
        this.codePostal = codePostal;
        this.nom = nom;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

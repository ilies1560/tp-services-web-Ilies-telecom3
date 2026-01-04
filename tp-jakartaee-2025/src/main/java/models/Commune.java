package models;

public class Commune {
    private int codePostal;
    private String name;

    public Commune() {}

    public Commune(int codePostal, String name) {
        this.codePostal = codePostal;
        this.name = name;
    }

    public int getCodePostal() { return codePostal; }
    public void setCodePostal(int codePostal) { this.codePostal = codePostal; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

package Examen;

public class Matiere {
    private String code;
    private int coef;
    private String nom;

    //constructeur
    public Matiere(String code, int coef ,String nom) {
        this.code = code;
        this.coef = coef;
        this.nom = nom;
    }

    //méthode
    public String getCode() {
        return code;
    }


    public int getCoef() {
        return coef;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Matiere{" +
                "code='" + code + '\'' +
                ", coefficient=" + coef +
                ", nom='" + nom + '\'' +
                '}';
    }
}

package Examen;

import Inscription.Etudiant;

public class Note {
    private Matiere matiere;
    private Etudiant etudiant;
    private double valeur;

    public Note(Matiere matiere, Etudiant etudiant, double valeur) {
        this.matiere = matiere;
        this.etudiant = etudiant;
        this.valeur = valeur;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public double getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return "Note{" +
                "matiere=" + matiere.getNom() +
                ", etudiant=" + etudiant.getNom() + " " + etudiant.getPrenom() +
                ", valeur=" + valeur +
                '}';
    }
}

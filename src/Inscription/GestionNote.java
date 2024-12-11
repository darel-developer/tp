package Inscription;

import Examen.Matiere;
import Examen.Note;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GestionNote {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Liste des étudiants
        List<Etudiant> etudiants = new ArrayList<>();
        // Liste des matières
        List<Matiere> matieres = new ArrayList<>();
        // Liste des notes
        List<Note> notes = new ArrayList<>();

        // Saisie des étudiants
        System.out.println("Entrez le nombre d'étudiants : ");
        int nbEtudiants = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < nbEtudiants; i++) {
            System.out.println("Entrez le matricule, nom, prénom et âge de l'étudiant " + (i + 1) + ": ");
            String matricule = scanner.nextLine();
            String nom = scanner.nextLine();
            String prenom = scanner.nextLine();
            int age = scanner.nextInt();
            scanner.nextLine();
            etudiants.add(new Etudiant(matricule, nom, prenom, age));
        }

        // Saisie des matières
        System.out.println("Entrez le nombre de matières : ");
        int nbMatieres = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < nbMatieres; i++) {
            System.out.println("Entrez le code, coefficient et nom de la matière " + (i + 1) + ": ");
            String code = scanner.nextLine();
            int coefficient = scanner.nextInt();
            scanner.nextLine();
            String nom = scanner.nextLine();
            matieres.add(new Matiere(code, coefficient, nom));
        }

        // Saisie des notes
        for (Etudiant etudiant : etudiants) {
            for (Matiere matiere : matieres) {
                System.out.println("Entrez la note de " + etudiant.getNom() + " " + etudiant.getPrenom() + " pour la matière " + matiere.getNom() + ": ");
                double valeur = scanner.nextDouble();
                notes.add(new Note(matiere, etudiant, valeur));
            }
        }

        // Menu principal
        int choix;
        do {
            System.out.println("\nMenu : ");
            System.out.println("1. Affichage des résultats par ordre numérique");
            System.out.println("2. Affichage du premier et dernier de la classe");
            System.out.println("3. Calcul et affichage de la moyenne de la classe");
            System.out.println("4. Affichage des admis");
            System.out.println("5. Affichage des étudiants ayant une moyenne supérieure ou égale à celle de la classe");
            System.out.println("6. Quitter");
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    // Affichage des résultats
                    notes.sort(Comparator.comparingDouble(Note::getValeur).reversed());
                    for (Note note : notes) {
                        System.out.println(note);
                    }
                    break;

                case 2:

                    Note meilleur = Collections.max(notes, Comparator.comparingDouble(Note::getValeur));
                    Note pire = Collections.min(notes, Comparator.comparingDouble(Note::getValeur));
                    System.out.println("1 er : " + meilleur);
                    System.out.println("Dernier : " + pire);
                    break;

                case 3:
                    // Moyenne de la classe
                    double somme = notes.stream().mapToDouble(Note::getValeur).sum();
                    double moyenne = somme / notes.size();
                    System.out.println("Moyenne de la classe : " + moyenne);
                    break;

                case 4:
                    // Affichage des admis
                    System.out.println("Étudiants admis (moyenne >= 10) :");
                    for (Etudiant etudiant : etudiants) {
                        double moyenneEtudiant = notes.stream()
                                .filter(note -> note.getEtudiant().equals(etudiant))
                                .mapToDouble(Note::getValeur).average().orElse(0);
                        if (moyenneEtudiant >= 10) {
                            System.out.println(etudiant);
                        }
                    }
                    break;

                case 5:
                    // Moyenne >= moyenne de la classe
                    double moyenneClasse = notes.stream().mapToDouble(Note::getValeur).average().orElse(0);
                    System.out.println("Étudiants avec une moyenne >= à celle de la classe (" + moyenneClasse + ") :");
                    for (Etudiant etudiant : etudiants) {
                        double moyenneEtudiant = notes.stream()
                                .filter(note -> note.getEtudiant().equals(etudiant))
                                .mapToDouble(Note::getValeur).average().orElse(0);
                        if (moyenneEtudiant >= moyenneClasse) {
                            System.out.println(etudiant);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 6);

        scanner.close();
    }
}

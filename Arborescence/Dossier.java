import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Dossier implements ComposantArborescence {
    private String nom;

    // Path: Dossier.java
    private List<ComposantArborescence> enfants = new ArrayList<>();

    public Dossier(String nom) {
        this.nom = nom;
    }

    public void ajouterEnfant(ComposantArborescence enfant) {
        enfants.add(enfant);
    }

    @Override
    public void afficher(int profondeur) {
        System.out.println(getIndentation(profondeur) + "|--> " + nom);
        for (ComposantArborescence enfant : enfants) {
            enfant.afficher(profondeur + 1); // Affiche les enfants
        }
    }

    private String getIndentation(int profondeur) { // Recupere l'indentation

        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < profondeur; i++) {

            indentation.append("\t");
        }
        return indentation.toString(); // Retourne l'indentation
    }
}

import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        // Création du dossier principal
        Dossier dossierPrincipal = new Dossier("genie logicielle");

        // Création de l'arborescence
        File file = new File("D:/genie logicielle");
        construireArborescence(file, dossierPrincipal);
        // Affichage de l'arborescence
        dossierPrincipal.afficher(0);
    }

    // Construit l'arborescence des fichiers et dossiers à partir du fichier passé en paramètre
    public static void construireArborescence(File file, Dossier dossier) {
        if (file.isDirectory()) {
            Dossier nouveauDossier = new Dossier(file.getName());
            dossier.ajouterEnfant(nouveauDossier);
            File[] fichiers = file.listFiles(); // Liste des fichiers et dossiers dans le dossier
            if (fichiers != null) {
                for (File f : fichiers) {

                    // Appel récursif pour construire l'arborescence
                    construireArborescence(f, nouveauDossier);
                }
            }
        } else { // Si c'est un fichier
            dossier.ajouterEnfant(new Fichier(file.getName()));  // Ajout du fichier au dossier
        }
    }
}
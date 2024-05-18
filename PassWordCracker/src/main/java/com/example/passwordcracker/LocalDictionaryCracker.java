package com.example.passwordcracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

// classe qui permet de cracker un mot de passe en utilisant un dictionnaire local
public class LocalDictionaryCracker implements DictionaryCracker {
    private static final String DICTIONARY_FILE = "D:\\PassWordCracker\\src\\main\\java\\com\\example\\passwordcracker\\rockyou.txt"; // fichier de dictionnaire
    private final String passwordHash;

    public LocalDictionaryCracker(String passwordHash) {
        this.passwordHash = passwordHash;
    }


    // méthode qui permet de cracker un mot de passe en utilisant un dictionnaire local
    @Override
    public void crack() {
        System.out.println("Commencement de la méthode de dictionnaire...");
        List<String> dictionary = loadDictionary(DICTIONARY_FILE); // charge le dictionnaire
        for (String word : dictionary) {
            String hashedWord = hash(word);
            if (hashedWord.equals(passwordHash)) {
                System.out.println("Mot de passe trouvé dans le dictionnaire: " + word);
                return;
            }
        }
        System.out.println("Le mot de passe n'a pas été trouvé dans le dictionnaire.");
    }

    // méthode qui permet de charger le dictionnaire
    private List<String> loadDictionary(String dictionaryFile) {
        List<String> dictionary = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dictionaryFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier de dictionnaire.");
            e.printStackTrace();
        }
        return dictionary;
    }

    // méthode qui permet de hasher un mot de passe avec SHA-256
    private String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                String hex = Integer.toHexString(0xff & hashedByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

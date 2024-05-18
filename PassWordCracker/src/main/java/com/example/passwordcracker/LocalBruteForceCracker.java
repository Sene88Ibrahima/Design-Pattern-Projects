package com.example.passwordcracker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LocalBruteForceCracker implements BruteForceCracker { // class fille de BruteForceCracker en local


    private static final char[] CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray(); // caractères possibles pour le mot de passe
    private static final int PASSWORD_LENGTH = 4; // longueur du mot de passe
    private final String passwordHash; // mot de passe hashé

    // constructeur de la classe
    public LocalBruteForceCracker(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    // méthode pour cracker le mot de passe
    public void crack() {
        System.out.println("Commencement de la méthode de force brute...");
        for (int i = 1; i <= PASSWORD_LENGTH; i++) {
            bruteForceRecursion("", i); // appel de la méthode récursive
        }
        System.out.println("Fin de la méthode de force brute.");
    }

    // méthode récursive pour tester toutes les combinaisons possibles
    private void bruteForceRecursion(String prefix, int i) {
        if (i == 0) {
            String candidate = prefix;
            String hashedCandidate = hash(candidate);
            if (hashedCandidate.equals(passwordHash)) {
                System.out.println("Mot de passe trouvé par force brute: " + candidate);
            }
            return; // retourne si le mot de passe n'est pas trouvé
        }

        // teste toutes les combinaisons possibles
        for (char c : CHARSET) { // pour chaque caractère du charset
            String newPrefix = prefix + c; // ajoute le caractère au préfixe
            bruteForceRecursion(newPrefix, i - 1); // appel récursif avec le nouveau préfixe
        }
    }

    // méthode pour hasher le mot de passe avec SHA-256
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



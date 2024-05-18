package com.example.passwordcracker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez une méthode :"); // choix de la méthode de craquage
        System.out.println("1. Craquage local");
        System.out.println("2. Craquage en ligne");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: // craquage local
                System.out.print("Entrez le hash SHA256 du mot de passe à craquer : ");
                String passwordHash = scanner.next();
                // choix de la méthode de craquage local
                System.out.println("Choisissez une méthode de craquage local :");
                System.out.println("1. Force brute locale");
                System.out.println("2. Dictionnaire local");
                int localChoice = scanner.nextInt();

                switch (localChoice) {
                    case 1: // force brute
                        LocalBruteForceCracker localBruteForceCracker = new LocalBruteForceCracker(passwordHash);
                        localBruteForceCracker.crack();
                        break;
                    case 2: // dictionnaire
                        LocalDictionaryCracker localDictionaryCracker = new LocalDictionaryCracker(passwordHash);
                        localDictionaryCracker.crack();
                        break;
                    default:
                        System.out.println("Choix invalide.");
                }
                break;
            case 2: // craquage en ligne
                System.out.println("Choisissez une méthode de craquage en ligne :"); // choix de la méthode de craquage en ligne
                System.out.println("1. Force brute en ligne");
                System.out.println("2. Dictionnaire en ligne");
                int onlineChoice = scanner.nextInt();

                switch (onlineChoice) {
                    case 1: // force brute
                        String formUrl = "http://localhost/crack/connexion.php";
                        String successMessage = "Connexion réussie";
                        String username = "admin";
                        OnlineBruteForceCracker onlineBruteForceCracker = new OnlineBruteForceCracker(formUrl, username, successMessage);
                        onlineBruteForceCracker.crack();
                        break;
                    case 2: // dictionnaire
                        formUrl = "http://localhost/crack/connexion.php";
                        username = "admin";
                        successMessage = "Connexion réussie";
                        OnlineDictionaryCracker onlineDictionaryCracker = new OnlineDictionaryCracker(formUrl, username, successMessage);
                        onlineDictionaryCracker.crack();
                        break;
                    default:
                        System.out.println("Choix invalide.");
                }
                break;
            default:
                System.out.println("Choix invalide.");
        }
    }
}


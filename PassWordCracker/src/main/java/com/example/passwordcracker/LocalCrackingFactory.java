package com.example.passwordcracker;

// Fabrique concrète pour les methodes de cracking en local
public class LocalCrackingFactory implements CrackingFactory {
    private final String passwordHash;

    public LocalCrackingFactory(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    // Créer un BruteForceCracker en local
    public BruteForceCracker createBruteForceCracker() {
        return new LocalBruteForceCracker(passwordHash);
    }

    // Créer un DictionaryCracker en local
    public DictionaryCracker createDictionaryCracker() {
        return new LocalDictionaryCracker(passwordHash);
    }
}
package com.example.passwordcracker;
// classe OnlineCrackingFactory qui permet de créer des instances de OnlineBruteForceCracker et OnlineDictionaryCracker
public class OnlineCrackingFactory implements CrackingFactory {
    private final String passwordHash;
    private final String formUrl;
    private final String username;
    private final String successMessage;

    public OnlineCrackingFactory(String formUrl, String passwordHash, String formUrl1, String username, String successMessage) {
        this.passwordHash = passwordHash;
        this.formUrl = formUrl1;
        this.username = username;
        this.successMessage = successMessage;
    }

    // Créer un BruteForceCracker en ligne
    public BruteForceCracker createBruteForceCracker() {
        return new OnlineBruteForceCracker(formUrl, username, successMessage);
    }

    // Créer un DictionaryCracker en ligne
    public DictionaryCracker createDictionaryCracker() {
        return new OnlineDictionaryCracker(formUrl, username, successMessage);
    }
}

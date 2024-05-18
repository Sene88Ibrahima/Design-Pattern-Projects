package com.example.passwordcracker;

public interface CrackingFactory { // interface Factory pour les différentes classes de cracking
    BruteForceCracker createBruteForceCracker(); // méthode pour créer un BruteForceCracker
    DictionaryCracker createDictionaryCracker(); // méthode pour créer un DictionaryCracker
}
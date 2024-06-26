﻿# Design-Pattern-Projects

# Password Cracker

Ce projet implémente différentes méthodes de craquage de mot de passe, incluant des méthodes de force brute locales et en ligne, ainsi que des attaques par dictionnaire.

## Fonctionnalités

- Force brute locale
- Attaque par dictionnaire locale
- Force brute en ligne
- Attaque par dictionnaire en ligne

## Prérequis

- Java 8 ou supérieur
- Maven (facultatif, pour la gestion des dépendances)

## Installation

### 1. Cloner le dépôt
git clone https://github.com/votre-utilisateur/password-cracker.git
cd password-cracker
### 2. Compilations et Exécution
Avec Maven
Si vous utilisez Maven, vous pouvez compiler et exécuter le programme avec les commandes suivantes :

mvn clean install
mvn exec:java -Dexec.mainClass="com.example.passwordcracker.Test"
Sans Maven
Sinon, vous pouvez compiler et exécuter le programme directement avec les commandes Java :

javac -d bin src/com/example/passwordcracker/*.java
java -cp bin com.example.passwordcracker.Test

Configuration pour le Craquage en Ligne
Pour les attaques en ligne, assurez-vous d'avoir un serveur HTTP configuré avec un formulaire de connexion que le programme peut cibler. Vous devrez mettre à jour les URL et les détails du formulaire dans le code si nécessaire.


Utilisation
Exécutez le programme.
Choisissez la méthode de craquage :
1 pour un craquage local.
2 pour un craquage en ligne.
Suivez les instructions pour entrer le hash du mot de passe (pour le craquage local) ou configurez l'URL du formulaire en ligne (pour le craquage en ligne).

Contribuer
Les contributions sont les bienvenues. Veuillez soumettre des pull requests et signaler des problèmes via le dépôt GitHub.

Licence
Ce projet est sous licence MIT. Voir le fichier LICENSE pour plus de détails.


### Dépendances pour le Craquage en Ligne

Pour les attaques en ligne, vous pouvez utiliser des bibliothèques comme `HttpClient` d'Apache pour gérer les requêtes HTTP. Assurez-vous d'inclure cette dépendance dans votre fichier `pom.xml` si vous utilisez Maven :
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.5.13</version>
</dependency>

#### Structure de PasswordCracker

- **`PassWordCracker`** : Répertoire contenant les fichiers liés au projet de crackage de mot de passe.
  - **`src`** : Répertoire source du projet.
    - **`main`** : Répertoire principal du code source.
      - **`java`** : Répertoire contenant les fichiers Java.
        - **`com/example/passwordcracker`** : Paquetage Java du projet de crackage de mot de passe.
          - **`rockyou.txt`** : Fichier de liste de mots de passe.
         
    

# Arborescence

Ce projet contient une représentation arborescente des fichiers et dossiers à partir d'un répertoire spécifique.

## Structure du Projet

  - **`ComposantArborescence.java`** : Interface représentant un composant de l'arborescence.
  - **`Dossier.java`** : Classe Java représentant un dossier dans l'arborescence.
  - **`Fichier.java`** : Classe Java représentant un fichier dans l'arborescence.
  - **`Main.java`** : Classe principale du programme.



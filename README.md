# Projet Bataille Navale par Julien NOEL et Jules SAYER S2D.

Lien du dépôt Git : https://github.com/Wilders/BatailleNavale


## POUR LANCER LE JEU (Main) : Exécutez la classe Jeu.java


Librairie de Test : JUnit 4


## Explications des différentes classes :

Notre jeu est composé d'une classe Grille permettant de représenter la grille du jeu qui est elle-même constituée d'un tableau de cases (Case représentée par la classe Case).

Une classe abstraite Bateau est également présente permettant de regrouper toutes les méthodes et caractéristiques d'un bateau en général.
Héritent de cette classe 5 autres classes (une classe par type de bateau Croiseur, Sous-Marin...).

De plus, chaque bateau est constitué d'une liste de cases afin de connaître sa position à l'intérieur d'une grille.

La classe Joueur permet de représenter un joueur, il est doté d'une grille ainsi que d'une liste de bateau qu'il possède.

La classe abstraite Partie modélise une partie et contient les méthodes de sauvegarde et de chargement de partie.
Les classes MonoJoueur et DeuxJoueurs héritent de la classe Partie et permettent de lancer une partie mono joueur ou bien à deux joueurs.

Enfin la classe Jeu correspond au Main et permet de lancer le jeu Bataille Navale.

3 classes d'exceptions sont également présentes (BateauException, CaseException et GrilleException) et permettent de gérer les exceptions personnalisées.

La classe de tests unitaires est nommée Test et est écrite avec la librairie JUnit 4.

import java.io.*;
import java.util.Scanner;


/**
 * Classe abstraite Partie consistant a stocker les differentes methodes pour lancer les differents modes de jeu
 */
public abstract class Partie implements Serializable {

    /**
     * Joueur 1 de la partie
     */
    protected Joueur j1;


    /**
     * Joueur 2 de la partie
     */
    protected Joueur j2;


    /**
     * Constructeur vide de la partie
     */
    public Partie(){}


    public static void sauvegarderMono(String dest, Joueur j) throws IOException {
        ObjectOutputStream d = new ObjectOutputStream(new FileOutputStream(dest));
        d.writeObject(j);
        d.close();
    }


    public static Joueur chargerMono(String source) throws IOException, ClassNotFoundException {
        ObjectInputStream di = new ObjectInputStream(new FileInputStream(source));
        Joueur j = (Joueur)(di.readObject());
        di.close();
        return j;
    }


    public static void sauvegarderDeuxJoueurs(String dest, Partie partie) throws IOException {
        ObjectOutputStream d = new ObjectOutputStream(new FileOutputStream(dest));
        d.writeObject(partie);
        d.close();
    }


    public static Partie chargerDeuxJoueurs(String source) throws IOException, ClassNotFoundException {
        ObjectInputStream di = new ObjectInputStream(new FileInputStream(source));
        Partie partie = (Partie)(di.readObject());
        di.close();
        return partie;
    }


    /**
     * Methode abstraite permettant de jouer en mode mono joueur, contre soit meme
     * @throws BateauException,GrilleException,CaseException
     */
    public abstract void monoJoueur() throws BateauException, GrilleException, CaseException, IOException, ClassNotFoundException;


    /**
     * Methode abstraite permettant de jouer a deux joueurs
     * @throws BateauException,GrilleException,CaseException
     */
    public abstract void deuxJoueurs() throws BateauException, GrilleException, CaseException, IOException;


    /**
     * Methode permettant de creer la grille avec des entrees clavier en debut de partie
     * @param sc Scanner permettant les saisies clavier
     * @return  Grille cree
     * @throws GrilleException,CaseException
     */
    public Grille creerGrille(Scanner sc) throws GrilleException,CaseException{
        int largeur=0;
        int hauteur=0;
        System.out.print("Entrez la largeur de la grille souhaitee (10 minimum, 100 maximum) : ");
        largeur = sc.nextInt();
        System.out.print("Entrez la hauteur de la grille souhaitee (10 minimum, 100 maximum) : ");
        hauteur = sc.nextInt();
        while ((largeur<10 || largeur>100)||(hauteur<10 || hauteur>100)){
            System.out.println("Taille de la grille trop grande ou trop petite, entrez de nouvelles dimensions : ");
            System.out.print("Entrez la largeur de la grille souhaitee (10 minimum, 100 maximum) : ");
            largeur = sc.nextInt();
            System.out.print("Entrez la hauteur de la grille souhaitee (10 minimum, 100 maximum) : ");
            hauteur = sc.nextInt();
        }
        Grille g = new Grille(largeur,hauteur);
        return g;
    }


    /**
     * Methode permettant de saisir les bateaux pour les ajouter a la partie
     * @param sc Scanner permettant la saisie clavier
     * @param j Joueur pour lequelle on veut ajouter les bateaux
     * @throws BateauException
     */
    public void saisieBateau(Scanner sc, Joueur j) throws BateauException{
        for (int i=1; i<=5; i++) {
            Object[] tmp=j.saisirBateau(sc,i);
            int xBat = ((Case)(tmp[0])).getX();
            int yBat = ((Case)(tmp[0])).getY();
            boolean orientation = (boolean)(tmp[1]);
            if (xBat>=0 && xBat<j.getGrille().getLargeur() && yBat>=0 && yBat<j.getGrille().getHauteur() && j.verifierPosBateau(j.getGrille().gettCases()[xBat][yBat],orientation,i)){
                j.ajouterBateau(j.getGrille().gettCases()[xBat][yBat], orientation, i);
            }else {
                Object[] infoB= j.replacerBateau(i);
                j.ajouterBateau((Case)(infoB[0]),(boolean)(infoB[1]),i);
            }
            System.out.println(j.getGrille().toString());
        }
    }
}

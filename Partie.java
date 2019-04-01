import java.util.Scanner;


/**
 * Classe abstraite Partie consistant a stocker les differentes methodes pour lancer les differents modes de jeu
 */
public abstract class Partie {


    protected Joueur j1;
    protected Joueur j2;


    /**
     * Constructeur vide de la partie
     */
    public Partie(){}


    /**
     * Methode abstraite permettant de jouer en mode mono joueur, contre soit meme
     * @throws BateauException,GrilleException,CaseException
     */
    public abstract void monoJoueur() throws BateauException,GrilleException,CaseException;


    /**
     * Methode abstraite permettant de jouer a deux joueurs
     * @throws BateauException,GrilleException,CaseException
     */
    public abstract void deuxJoueurs() throws BateauException,GrilleException,CaseException;


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

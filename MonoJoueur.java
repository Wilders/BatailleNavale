import java.util.Scanner;


/**
 * Classe MonoJoueur heritant de la classe Partie permettant de modeliser une partie mono joueur
 */
public class MonoJoueur extends Partie{


    /**
     * Constructeur vide de la classe MonoJoueur
     */
    public MonoJoueur(){}


    /**
     * Methode permettant de jouer en mode mono joueur, contre soit meme
     * @throws BateauException,GrilleException,CaseException
     */
    public void monoJoueur() throws BateauException,GrilleException,CaseException{
        Scanner sc = new Scanner(System.in);
        Grille g = creerGrille(sc);
        j1 = new Joueur(g);
        System.out.println(g.toString());
        saisieBateau(sc,j1);
        while (!j1.perdu()){
            System.out.print("Joueur 1 : Entrez le x pour tirer : ");
            int x1 = sc.nextInt();
            System.out.print("Joueur 1 : Entrez le y pour tirer : ");
            int y1 = sc.nextInt();
            if (x1>=0 && x1<j1.getGrille().getLargeur() && y1>=0 && y1<j1.getGrille().getHauteur()){
                j1.tirer(j1, j1.getGrille().gettCases()[x1][y1]);
            }else {
                j1.retirer(j1,sc);
            }

            System.out.println(g.toString());
            if (j1.perdu()){
                break;
            }
        }
        if (j1.perdu()){
            System.out.println("Tu as gagne !");
        }
        sc.close();
    }


    /**
     * Methode permettant de jouer a deux joueurs
     * @throws BateauException,GrilleException,CaseException
     */
    public void deuxJoueurs() throws BateauException,GrilleException,CaseException{}
}

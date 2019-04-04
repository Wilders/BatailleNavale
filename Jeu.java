import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Classe Jeu consistant a lancer le jeu et a choisir le mode de jeu
 */
public class Jeu implements Serializable {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        int choix=1;
        while (!ok){
            try {
                System.out.print("A combien de joueur voulez vous jouez ? (1 ou 2) : ");
                choix = sc.nextInt();
                sc.nextLine();
                ok=true;
            }catch (InputMismatchException ime){
                System.out.println("Erreur, entrez un entier (1 ou 2) : ");
                sc.nextLine();
            }
        }
        switch (choix){
            case 1 :
                MonoJoueur partieMonoJoueur = new MonoJoueur();
                partieMonoJoueur.monoJoueur();
                break;
            case 2:
                DeuxJoueurs partieDeuxJoueurs = new DeuxJoueurs();
                partieDeuxJoueurs.deuxJoueurs();
                break;
            default:
                System.out.println("Vous ne pouvez jouer qu'a 1 ou 2 joueurs, relancez la partie !");
        }
        sc.close();
    }
}
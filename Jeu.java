import java.util.Scanner;


/**
 * Classe Jeu consistant a lancer le jeu et a choisir le mode de jeu
 */
public class Jeu {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Partie partie = new Partie();
        System.out.print("A combien de joueur voulez vous jouez ? (1 ou 2) : ");
        int choix = sc.nextInt();
        switch (choix){
            case 1 :
                partie.monoJoueur();
                break;
            case 2:
                partie.deuxJoueurs();
                break;
            default:
                System.out.println("Vous ne pouvez jouer qu'à 1 ou 2 joueurs, relancez la partie !");
        }
        sc.close();
    }
}
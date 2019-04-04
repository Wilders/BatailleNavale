import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Classe MonoJoueur heritant de la classe Partie permettant de modeliser une partie mono joueur
 */
public class MonoJoueur extends Partie implements Serializable {


    /**
     * Constructeur vide de la classe MonoJoueur
     */
    public MonoJoueur(){}


    /**
     * Methode permettant de jouer en mode mono joueur, contre soit meme
     * @throws BateauException,GrilleException,CaseException
     */
    public void monoJoueur() throws BateauException, GrilleException, CaseException, IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Voulez vous chargez la derniere partie en cours ? (Oui/Non) : ");
        boolean charger=false;
        if (sc.nextLine().toUpperCase().compareTo("OUI")==0){
            charger=true;
        }
        if (charger){
            try{
                j1=chargerMono("saveMonoJoueur");
                System.out.println(j1.getGrille().toString());
                while (!j1.perdu()){
                    int x1=0;
                    int y1=0;
                    boolean ok = false;
                    while (!ok){
                        try {
                            System.out.print("Joueur 1 : Entrez le x pour tirer : ");
                            x1 = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Joueur 1 : Entrez le y pour tirer : ");
                            y1 = sc.nextInt();
                            sc.nextLine();
                            ok=true;
                        }catch (InputMismatchException ime){
                            System.out.println("Erreur, entrez deux entiers : ");
                            sc.nextLine();
                        }
                    }
                    if (x1>=0 && x1<j1.getGrille().getLargeur() && y1>=0 && y1<j1.getGrille().getHauteur()){
                        j1.tirer(j1, j1.getGrille().gettCases()[x1][y1]);
                    }else {
                        j1.retirer(j1,sc);
                    }

                    System.out.println(j1.getGrille().toString());
                    if (j1.perdu()){
                        break;
                    }
                    System.out.println("Voulez-vous sauvegarder et quitter votre partie ? (Oui/Non) : ");
                    boolean sauvegarder=false;
                    if (sc2.nextLine().toUpperCase().compareTo("OUI")==0){
                        sauvegarder=true;
                    }
                    if (sauvegarder){
                        sauvegarderMono("saveMonoJoueur",j1);
                        break;
                    }
                }
                if (j1.perdu()){
                    System.out.println("Tu as gagne !");
                }
                sc.close();
            }catch (IOException ioe){
                System.out.println("Aucun fichier de sauvegarde existant");
                partieMonoJoueur(sc, sc);
            }
        }else{
            partieMonoJoueur(sc, sc2);
        }
    }


    /**
     * Methode permettant d'eviter la duplication de code pour une partie mono joueur
     * @param sc Scanner 1 permettant la saisie clavier
     * @param sc2 Scanner 2 permettant la saisie clavier
     * @throws GrilleException
     * @throws CaseException
     * @throws BateauException
     * @throws IOException
     */
    public void partieMonoJoueur(Scanner sc, Scanner sc2) throws GrilleException, CaseException, BateauException, IOException {
        Grille g = creerGrille(sc);
        j1 = new Joueur(g);
        System.out.println(g.toString());
        saisieBateau(sc,j1);
        while (!j1.perdu()){
            int x1=0;
            int y1=0;
            boolean ok = false;
            while (!ok){
                try {
                    System.out.print("Joueur 1 : Entrez le x pour tirer : ");
                    x1 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Joueur 1 : Entrez le y pour tirer : ");
                    y1 = sc.nextInt();
                    sc.nextLine();
                    ok=true;
                }catch (InputMismatchException ime){
                    System.out.println("Erreur, entrez deux entiers : ");
                    sc.nextLine();
                }
            }
            if (x1>=0 && x1<j1.getGrille().getLargeur() && y1>=0 && y1<j1.getGrille().getHauteur()){
                j1.tirer(j1, j1.getGrille().gettCases()[x1][y1]);
            }else {
                j1.retirer(j1,sc);
            }

            System.out.println(g.toString());
            if (j1.perdu()){
                break;
            }
            System.out.println("Voulez-vous sauvegarder et quitter votre partie ? (Oui/Non) : ");
            boolean sauvegarder=false;
            if (sc2.nextLine().toUpperCase().compareTo("OUI")==0){
                sauvegarder=true;
            }
            if (sauvegarder){
                sauvegarderMono("saveMonoJoueur",j1);
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

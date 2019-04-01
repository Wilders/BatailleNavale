import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;


/**
 * Classe DeuxJoueurs heritant de la classe Partie permettant de modeliser une partie a deux joueurs
 */
public class DeuxJoueurs extends Partie implements Serializable {


    /**
     * Constructeur vide de la calsse DeuxJoueurs
     */
    public DeuxJoueurs(){}


    /**
     * Methode permettant de jouer a deux joueurs
     * @throws BateauException,GrilleException,CaseException
     */
    public void deuxJoueurs() throws BateauException, GrilleException, CaseException, IOException {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);


        System.out.print("Voulez vous chargez la derniere partie en cours ? (Oui(o) / Non(n) : ");
        boolean charger=false;
        if (sc.nextLine().toUpperCase().compareTo("OUI")==0){
            charger=true;
        }
        if (charger){
            try {
                Partie p =chargerDeuxJoueurs("saveDeuxJoueurs");
                this.j1=p.j1;
                this.j2=p.j2;
                System.out.println(j1.getGrille().toString());
                while(!j1.perdu() && !j2.perdu()){
                    System.out.print(j1.getNomJoueur().toUpperCase()+" : Entrez le x pour tirer : ");
                    int x1 = sc.nextInt();
                    System.out.print(j1.getNomJoueur().toUpperCase()+" : Entrez le y pour tirer : ");
                    int y1 = sc.nextInt();
                    if (x1>=0 && x1<j2.getGrille().getLargeur() && y1>=0 && y1<j2.getGrille().getHauteur()){
                        j1.tirer(j2, j2.getGrille().gettCases()[x1][y1]);
                    }else {
                        j1.retirer(j2,sc);
                    }
                    System.out.println(j2.getGrille().toString());


                    System.out.print(j2.getNomJoueur().toUpperCase()+" : Entrez le x pour tirer : ");
                    int x2 = sc.nextInt();
                    System.out.print(j2.getNomJoueur().toUpperCase()+" : Entrez le y pour tirer : ");
                    int y2 = sc.nextInt();
                    if (x2>=0 && x2<j1.getGrille().getLargeur() && y2>=0 && y2<j1.getGrille().getHauteur()){
                        j2.tirer(j1, j1.getGrille().gettCases()[x2][y2]);
                    }else {
                        j2.retirer(j1,sc);
                    }
                    System.out.println(j1.getGrille().toString());

                    if (j1.perdu()){
                        break;
                    }
                    if (j2.perdu()){
                        break;
                    }
                    System.out.println("Voulez-vous sauvegarder et quitter votre partie ? (Oui(o) / Non(n) : ");
                    boolean sauvegarder=false;
                    if (sc2.nextLine().toUpperCase().compareTo("OUI")==0){
                        sauvegarder=true;
                    }
                    if (sauvegarder){
                        sauvegarderDeuxJoueurs("saveDeuxJoueurs",this);
                        break;
                    }
                }
                if (j1.perdu()){
                    System.out.println(j2.getNomJoueur().toUpperCase()+" a gagne !");
                }
                if (j2.perdu()){
                    System.out.println(j1.getNomJoueur().toUpperCase()+" a gagne !");
                }
                sc.close();
                sc2.close();
            }catch (IOException | ClassNotFoundException ioe){
                System.out.println("Aucun fichier de sauvegarde existant");
                System.out.print("Joueur 1, entrez votre nom : ");
                String nomJ1  = sc.nextLine();

                System.out.println(nomJ1.toUpperCase()+ " saisissez les informations pour votre grille : ");
                Grille g1 = creerGrille(sc);
                j1 = new Joueur(g1);
                j1.setNomJoueur(nomJ1);
                System.out.println(g1.toString());

                System.out.print("Joueur 2, entrez votre nom : ");
                String nomJ2  = sc2.nextLine();
                System.out.println(nomJ2.toUpperCase()+ " saisissez les informations pour votre grille : ");
                Grille g2 = creerGrille(sc);
                System.out.println(g2.toString());
                j2 = new Joueur(g2);
                j2.setNomJoueur(nomJ2);

                System.out.println(nomJ1.toUpperCase()+" placez vos batteaux : ");
                saisieBateau(sc,j1);

                System.out.println(nomJ2.toUpperCase()+" placez vos batteaux : ");
                saisieBateau(sc,j2);

                while(!j1.perdu() && !j2.perdu()){
                    System.out.print(nomJ1.toUpperCase()+" : Entrez le x pour tirer : ");
                    int x1 = sc.nextInt();
                    System.out.print(nomJ1.toUpperCase()+" : Entrez le y pour tirer : ");
                    int y1 = sc.nextInt();
                    if (x1>=0 && x1<j2.getGrille().getLargeur() && y1>=0 && y1<j2.getGrille().getHauteur()){
                        j1.tirer(j2, j2.getGrille().gettCases()[x1][y1]);
                    }else {
                        j1.retirer(j2,sc);
                    }
                    System.out.println(g2.toString());


                    System.out.print(nomJ2.toUpperCase()+" : Entrez le x pour tirer : ");
                    int x2 = sc.nextInt();
                    System.out.print(nomJ2.toUpperCase()+" : Entrez le y pour tirer : ");
                    int y2 = sc.nextInt();
                    if (x2>=0 && x2<j1.getGrille().getLargeur() && y2>=0 && y2<j1.getGrille().getHauteur()){
                        j2.tirer(j1, j1.getGrille().gettCases()[x2][y2]);
                    }else {
                        j2.retirer(j1,sc);
                    }
                    System.out.println(g1.toString());


                    if (j1.perdu()){
                        break;
                    }
                    if (j2.perdu()){
                        break;
                    }
                    System.out.println("Voulez-vous sauvegarder et quitter votre partie ? (Oui(o) / Non(n) : ");
                    boolean sauvegarder=false;
                    if (sc2.nextLine().toUpperCase().compareTo("OUI")==0){
                        sauvegarder=true;
                    }
                    if (sauvegarder){
                        sauvegarderDeuxJoueurs("saveDeuxJoueurs",this);
                        break;
                    }
                }
                if (j1.perdu()){
                    System.out.println(nomJ2.toUpperCase()+" a gagne !");
                }
                if (j2.perdu()){
                    System.out.println(nomJ1.toUpperCase()+" a gagne !");
                }
                sc.close();
                sc2.close();
            }
        }else{
            System.out.print("Joueur 1, entrez votre nom : ");
            String nomJ1  = sc.nextLine();

            System.out.println(nomJ1.toUpperCase()+ " saisissez les informations pour votre grille : ");
            Grille g1 = creerGrille(sc);
            j1 = new Joueur(g1);
            j1.setNomJoueur(nomJ1);
            System.out.println(g1.toString());

            System.out.print("Joueur 2, entrez votre nom : ");
            String nomJ2  = sc2.nextLine();
            System.out.println(nomJ2.toUpperCase()+ " saisissez les informations pour votre grille : ");
            Grille g2 = creerGrille(sc);
            System.out.println(g2.toString());
            j2 = new Joueur(g2);
            j2.setNomJoueur(nomJ2);

            System.out.println(nomJ1.toUpperCase()+" placez vos batteaux : ");
            saisieBateau(sc,j1);

            System.out.println(nomJ2.toUpperCase()+" placez vos batteaux : ");
            saisieBateau(sc,j2);

            while(!j1.perdu() && !j2.perdu()){
                System.out.print(nomJ1.toUpperCase()+" : Entrez le x pour tirer : ");
                int x1 = sc.nextInt();
                System.out.print(nomJ1.toUpperCase()+" : Entrez le y pour tirer : ");
                int y1 = sc.nextInt();
                if (x1>=0 && x1<j2.getGrille().getLargeur() && y1>=0 && y1<j2.getGrille().getHauteur()){
                    j1.tirer(j2, j2.getGrille().gettCases()[x1][y1]);
                }else {
                    j1.retirer(j2,sc);
                }
                System.out.println(g2.toString());


                System.out.print(nomJ2.toUpperCase()+" : Entrez le x pour tirer : ");
                int x2 = sc.nextInt();
                System.out.print(nomJ2.toUpperCase()+" : Entrez le y pour tirer : ");
                int y2 = sc.nextInt();
                if (x2>=0 && x2<j1.getGrille().getLargeur() && y2>=0 && y2<j1.getGrille().getHauteur()){
                    j2.tirer(j1, j1.getGrille().gettCases()[x2][y2]);
                }else {
                    j2.retirer(j1,sc);
                }
                System.out.println(g1.toString());


                if (j1.perdu()){
                    break;
                }
                if (j2.perdu()){
                    break;
                }
                System.out.println("Voulez-vous sauvegarder et quitter votre partie ? (Oui(o) / Non(n) : ");
                boolean sauvegarder=false;
                if (sc2.nextLine().toUpperCase().compareTo("OUI")==0){
                    sauvegarder=true;
                }
                if (sauvegarder){
                    sauvegarderDeuxJoueurs("saveDeuxJoueurs",this);
                    break;
                }
            }
            if (j1.perdu()){
                System.out.println(nomJ2.toUpperCase()+" a gagne !");
            }
            if (j2.perdu()){
                System.out.println(nomJ1.toUpperCase()+" a gagne !");
            }
            sc.close();
            sc2.close();
        }
    }


    /**
     * Methode permettant de jouer en mode mono joueur, contre soit meme
     * @throws BateauException,GrilleException,CaseException
     */
    public void monoJoueur() throws BateauException,GrilleException,CaseException{}
}

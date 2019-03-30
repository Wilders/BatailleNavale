import java.util.Scanner;


/**
 * Classe Partie consistant à stocker les différentes méthodes pour lancer les différents modes de jeu
 */
public class Partie {


    /**
     * Constructeur vide de la partie
     */
    public Partie(){}


    /**
     * Méthode permettant de jouer en mode mono joueur, contre soit meme
     * @throws BateauException,GrilleException,CaseException
     */
    public void monoJoueur() throws BateauException,GrilleException,CaseException{
        Scanner sc = new Scanner(System.in);
        Grille g = creerGrille(sc);
        Joueur j = new Joueur(g);
        System.out.println(g.toString());
        saisieBateau(sc,j);
        while (!j.perdu()){
            System.out.print("Joueur 1 : Entrez le x pour tirer : ");
            int x1 = sc.nextInt();
            System.out.print("Joueur 1 : Entrez le y pour tirer : ");
            int y1 = sc.nextInt();
            if (x1>=0 && x1<j.getGrille().getLargeur() && y1>=0 && y1<j.getGrille().getHauteur()){
                j.tirer(j, j.getGrille().gettCases()[x1][y1]);
            }else {
                j.retirer(j,sc);
            }

            System.out.println(g.toString());
            if (j.perdu()){
                break;
            }
        }
        if (j.perdu()){
            System.out.println("Tu as gagné !");
        }
        sc.close();
    }


    /**
     * Methode permettant de jouer a deux joueurs
     * @throws BateauException,GrilleException,CaseException
     */
    public void deuxJoueurs() throws BateauException,GrilleException,CaseException{
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        System.out.print("Joueur 1, entrez votre nom : ");
        String nomJ1  = sc.nextLine();
        System.out.println(nomJ1.toUpperCase()+ " saisissez les informations pour votre grille : ");
        Grille g1 = creerGrille(sc);
        Joueur j1 = new Joueur(g1);
        System.out.println(g1.toString());

        System.out.print("Joueur 2, entrez votre nom : ");
        String nomJ2  = sc2.nextLine();
        System.out.println(nomJ2.toUpperCase()+ " saisissez les informations pour votre grille : ");
        Grille g2 = creerGrille(sc);
        System.out.println(g2.toString());
        Joueur j2 = new Joueur(g2);

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
        }
        if (j1.perdu()){
            System.out.println(nomJ2.toUpperCase()+" a gagné !");
        }
        if (j2.perdu()){
            System.out.println(nomJ1.toUpperCase()+" a gagné !");
        }
        sc.close();
        sc2.close();
    }


    /**
     * Methode permettant de creer la grille avec des entrees clavier en debut de partie
     * @param sc Scanner permettant les saisies clavier
     * @return  Grille cree
     * @throws GrilleException,CaseException
     */
    public Grille creerGrille(Scanner sc) throws GrilleException,CaseException{
        int largeur=0;
        int hauteur=0;
        System.out.print("Entrez la largeur de la grille souhaitée (10 minimum, 100 maximum) : ");
        largeur = sc.nextInt();
        System.out.print("Entrez la hauteur de la grille souhaitée (10 minimum, 100 maximum) : ");
        hauteur = sc.nextInt();
        while ((largeur<10 || largeur>100)||(hauteur<10 || hauteur>100)){
            System.out.println("Taille de la grille trop grande ou trop petite, entrez de nouvelles dimensions : ");
            System.out.print("Entrez la largeur de la grille souhaitée (10 minimum, 100 maximum) : ");
            largeur = sc.nextInt();
            System.out.print("Entrez la hauteur de la grille souhaitée (10 minimum, 100 maximum) : ");
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

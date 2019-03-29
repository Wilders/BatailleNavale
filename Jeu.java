import java.util.Scanner;

public class Jeu {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("A combien de joueur voulez vous jouez ? (1 ou 2) : ");
        int choix = sc.nextInt();

        switch (choix){
            case 1 :
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
                Joueur j = new Joueur(g);
                System.out.println(g.toString());
                for (int i=1; i<=5; i++) {
                    System.out.print("Donnez la position en x du Bateau de taille " + i + " : ");
                    int xBat = sc.nextInt();
                    System.out.print("Donnez la position en y du Bateau de taille " + i + " : ");
                    int yBat = sc.nextInt();
                    System.out.print("Donnez l'oriation du Bateau de taille " + i + " (Horizontal (H) ou Vertical (V)) : ");
                    boolean orientation = false;
                    if (sc.nextLine().toUpperCase().compareTo("H") == 0) {
                        orientation = false;
                    } else {
                        if (sc.nextLine().toUpperCase().compareTo("V") == 0) {
                            orientation = true;
                        }
                    }
                    if (xBat>=0 && xBat<j.getGrille().getLargeur() && yBat>=0 && yBat<j.getGrille().getHauteur() && j.verifierPosBateau(g.gettCases()[xBat][yBat],orientation,i)){
                        j.ajouterBateau(g.gettCases()[xBat][yBat], orientation, i);
                    }else {
                        Object[] infoB= j.replacerBateau(i);
                        j.ajouterBateau((Case)(infoB[0]),(boolean)(infoB[1]),i);
                    }
                    System.out.println(g.toString());
                }
                System.out.println(j.getGrille().toString());
                while (!j.perdu()){
                    System.out.print("Joueur 1 : Entrez le x pour tirer : ");
                    int x1 = sc.nextInt();
                    System.out.print("Joueur 1 : Entrez le y pour tirer : ");
                    int y1 = sc.nextInt();
                    if (x1>=0 && x1<j.getGrille().getLargeur() && y1>=0 && y1<j.getGrille().getHauteur()){
                        j.tirer(j, j.getGrille().gettCases()[x1][y1]);
                    }else {
                        System.out.println("Case du tableau inaccessible : ");
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
                break;
            case 2:
                break;
            default:
                System.out.println("Vous ne pouvez jouer qu'à 1 ou 2 joueurs, relancez la partie !");
        }
    }
}

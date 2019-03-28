import java.util.Scanner;

public class Jeu {
    public static void main(String[] args) throws Exception {
        Grille g = new Grille(10, 10);
        Grille g2 = new Grille(10,10);
        Joueur j  = new Joueur(g);
        Joueur j2 = new Joueur(g2);

        j.ajouterBateau(g.gettCases()[1][1], true, 1);
        j.ajouterBateau(g.gettCases()[4][8], false, 5);
        j.ajouterBateau(g.gettCases()[2][2], false, 2);
        j.ajouterBateau(g.gettCases()[4][4], false, 4);
        j.ajouterBateau(g.gettCases()[0][0], false, 3);

        j2.ajouterBateau(g.gettCases()[1][1], true, 1);
        j2.ajouterBateau(g.gettCases()[4][8], false, 5);
        j2.ajouterBateau(g.gettCases()[2][2], false, 2);
        j2.ajouterBateau(g.gettCases()[4][4], false, 4);
        j2.ajouterBateau(g.gettCases()[0][0], false, 3);


        Scanner sc = new Scanner(System.in);


        while (!j.perdu() && !j2.perdu()){
            System.out.print("Joueur 1 : Entrez le x : ");
            int x1 = sc.nextInt();
            System.out.print("Joueur 1 : Entrez le y : ");
            int y1 = sc.nextInt();

            j.tirer(j2, j2.getGrille().gettCases()[x1][y1]);

            if (j2.perdu()){
                break;
            }

            System.out.print("Joueur 2 : Entrez le x : ");
            int x2 = sc.nextInt();
            System.out.print("Joueur 2 : Entrez le y : ");
            int y2 = sc.nextInt();

            j2.tirer(j, j.getGrille().gettCases()[x2][y2]);
        }


        if (j.perdu()){
            System.out.println("Le joueur 1 a perdu");
        }else {
            if (j2.perdu()){
                System.out.println("Le joueur 2 a perdu");
            }
        }

    }
}

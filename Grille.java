import java.io.Serializable;
import java.util.Arrays;


/**
 * Classe modelisant une grille
 */
public class Grille implements Serializable {


    /**
     * Largeur de la grille
     */
    private int largeur;


    /**
     * Hauteur de la grille
     */
    private int hauteur;


    /**
     * Tableau de cases de la grille
     */
    private Case[][] tCases;


    /**
     * Constructeur de Grille permettant de creer une grille de taille minimum
     * 10x10 (taille passee en paramatre) et remplie la liste de cases
     * @param pLargeur Largeur de la grille
     * @param pHauteur Hauteur de la grille
     * @throws GrilleException,CaseException
     */
    public Grille(int pLargeur, int pHauteur) throws GrilleException, CaseException {
        if(pLargeur < 10 || pHauteur < 10) {
            throw new GrilleException("La largeur et la hauteur doivent être superieures ou egales a 10");
        } else {
            this.largeur = pLargeur;
            this.hauteur = pHauteur;
        }

        tCases = new Case[pLargeur][pHauteur];
        for (int i=0; i<pLargeur; i++){
            for (int j=0; j<pHauteur; j++){
                tCases[i][j]=new Case(i,j);
            }
        }
    }


    /**
     * Methode permettant de retourner l'attribut Hauteur de la grille
     * @return Hauteur de la grille
     */
    public int getHauteur() {
        return this.hauteur;
    }


    /**
     * Methode permettant de retourner l'attribut Largeur de la grille
     * @return Largeur de la grille
     */
    public int getLargeur() {
        return this.largeur;
    }


    /**
     * Methode permettant de retourner l'attribut tCases de la grille
     * @return Tableau des cases de la grille
     */
    public Case[][] gettCases() {
        return tCases;
    }

    /**
     * Methode permettant de retourner un String contenant les cases de la grille
     * @return String contenant les cases de la grille
     */
    public String toString() {
        String res = "      ";
        for (int k = 0; k < this.getLargeur(); k++) {
            if(k >= 10) {
                res += "" + k + "  ";
            } else {
                res += " " + k + "  ";
            }
        }
        res += "\n";
        for (int i = 0; i < this.getHauteur(); i++) {
            if(i >= 10) {
                res += " " + i + " ";
            } else {
                res += " " + i + "  ";
            }
            for (int j = 0; j < this.getLargeur(); j++) {
                res += " | " + this.gettCases()[j][i].toString();
                if(j == this.getLargeur()-1) {
                    res += " |";
                }
            }
            res += "\n";
        }

        res += "\n               Legende :\n- : Case sans bateau qui n'est pas touchee\nO : Case avec un bateau pas touchee\nX : Case touchee avec un bateau\n* : Case touchee mais sans bateau\n";
        return res;
    }
}
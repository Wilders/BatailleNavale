import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Cette classe modélise une grille
 * de jeu, elle a comme attribut une
 * largeur, une longueur et une liste
 * de cases.
 */
public class Grille {

    /**
     * Largeur de la grille
     */
    private int largeur;

    /**
     * Longueur de la grille
     */
    private int hauteur;

    /**
     * Liste de cases
     */
    private Case[][] tCases;

    /**
     * Constructeur de Grille, créer une grille
     * de minimum 10x10 (taille passée en param)
     * et remplie la liste de cases.
     * @param pLargeur Largeur voulue
     * @param pHauteur Longueur voulue
     * @throws Exception La longueur et la largeur doivent être supérieur ou égal à 10
     */
    public Grille(int pLargeur, int pHauteur) throws Exception {
        if(pLargeur < 10 || pHauteur < 10) {
            throw new Exception("Longueur ou largeur doit être supérieur ou égal à 10");
        } else {
            this.largeur = pHauteur;
            this.hauteur = pLargeur;
        }

        tCases = new Case[pLargeur][pHauteur];
        for (int i=0; i<pLargeur; i++){
            for (int j=0; j<pHauteur; j++){
                tCases[i][j]=new Case(i,j);
            }
        }
    }

    /**
     * Getter pour l'attribut longueur
     * @return longueur
     */
    public int getHauteur() {
        return this.hauteur;
    }

    /**
     * Getter pour l'attribut largeur
     * @return largeur
     */
    public int getLargeur() {
        return this.largeur;
    }

    public Case[][] gettCases() {
        return tCases;
    }

    @Override
    public String toString() {
        return "Grille{" +
                "tCases=" + Arrays.toString(tCases) +
                '}';
    }
}
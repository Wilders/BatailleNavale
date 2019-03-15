import java.util.ArrayList;
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
    private int longueur;

    /**
     * Liste de cases
     */
    private ArrayList<Case> lCases;

    /**
     * Constructeur de Grille, créer une grille
     * de minimum 10x10 (taille passée en param)
     * et remplie la liste de cases.
     * @param pLargeur Largeur voulue
     * @param pLongueur Longueur voulue
     * @throws Exception La longueur et la largeur doivent être supérieur ou égal à 10
     */
    public Grille(int pLargeur, int pLongueur) throws Exception {
        if(pLargeur < 10 || pLongueur < 10) {
            throw new Exception("Longueur ou largeur doit être supérieur ou égal à 10");
        } else {
            this.largeur = pLongueur;
            this.longueur = pLargeur;
        }
        lCases = new ArrayList<Case>();
        for (int i = 0; i < this.getLargeur(); i++) {
            for (int j = 0; j < this.getLongueur(); j++) {
                lCases.add(new Case(i,j));
            }
        }
    }

    /**
     * Getter pour l'attribut longueur
     * @return longueur
     */
    public int getLongueur() {
        return this.longueur;
    }

    /**
     * Getter pour l'attribut largeur
     * @return largeur
     */
    public int getLargeur() {
        return this.largeur;
    }

    /**
     * Getter pour l'attribut lCases
     * @return lCases
     */
    public ArrayList<Case> getLCase() {
        return this.lCases;
    }
}
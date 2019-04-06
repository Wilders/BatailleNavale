import java.io.Serializable;

/**
 * Classe Croiseur heritant de la classe Bateau
 */
public class Croiseur extends Bateau implements Serializable {


    /**
     * Attribut correspondant au nom de la classe
     */
    private String nom;


    /**
     * Constructeur de la classe Croiseur prenant en parametres la grille dans laquelle
     * on souhaite placer le bateau, un booleen d'orientation du bateau et la case initiale a laquelle
     * on souhaite placer le bateau dans la grille
     * @param g Grille dans laquelle on place le bateau
     * @param o Booleen Orientation
     * @param c Case initiale du bateau
     * @throws BateauException
     */
    public Croiseur(Grille g, boolean o, Case c) throws BateauException{
        super(g, o);
        this.nom = "Croiseur";
        this.addMultipleCases(c);
    }


    /**
     * Methode permettant d'ajouter toutes les cases du bateau dans la liste de case de celui-ci
     * @param e Case initiale de la position du bateau les autres cases sont definies par l'orientation et la taille du bateau
     * @throws BateauException
     */
    protected void addMultipleCases(Case e) throws BateauException{
        int x = e.getX();
        int y = e.getY();
        if(x >= this.getGrille().getLargeur() || y >= this.getGrille().getHauteur()) {
            throw new BateauException("La case n'est pas dans la grille");
        }
        if(this.getOrientation()) {
            for (int i = 0; i < 4; i++) {
                this.addCase(grille.gettCases()[x][y+i]);
                grille.gettCases()[x][y+i].setDispo(false);
            }
        } else {
            for (int i = 0; i < 4; i++) {
                this.addCase(grille.gettCases()[x+i][y]);
                grille.gettCases()[x+i][y].setDispo(false);
            }
        }
    }


    /**
     * Methode permettant de retourner le nom du bateau
     * @return Nom du bateau
     */
    public String getNom() {
        return this.nom;
    }


    /**
     * Methode permettant de retourner un String contenant les coordonnees des differentes cases
     * d'un bateau ainsi que son nom
     * @return String des cases du bateau et son nom
     */
    public String toString() {
        return this.nom + super.toString();
    }
}
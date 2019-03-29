/**
 * Classe Torpilleur héritant de la classe Bateau
 */
public class Torpilleur extends Bateau {


    /**
     * Attribut correspondant au nom de la classe
     */
    private String nom;


    /**
     * Constructeur de la classe Torpilleur prenant en parametres la grille dans laquelle
     * on souhaite placer le bateau, un booleen d'orientation du bateau et la case initiale a laquelle
     * on souhaite placer le bateau dans la grille
     * @param g Grille dans laquelle on place le bateau
     * @param o Booleen Orientation
     * @param c Case initiale du bateau
     * @throws Exception
     */
    public Torpilleur(Grille g, boolean o, Case c) throws Exception{
        super(g, o);
        this.nom = "Torpilleur";
        this.addMultipleCases(c);
    }


    /**
     * Methode permettant d'ajouter la case du bateau dans la liste de case de celui-ci
     * @param e Case de la position du bateau
     * @throws Exception
     */
    protected void addMultipleCases(Case e) throws Exception{
        int x = e.getX();
        int y = e.getY();
        this.addCase(grille.gettCases()[x][y]);
        grille.gettCases()[x][y].setDispo(false);
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
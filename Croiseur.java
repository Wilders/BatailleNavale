/**
 * Classe Croiseur héritant de la classe Bateau
 */
public class Croiseur extends Bateau {
    public Croiseur(Grille g, int x, int y, boolean o){
        super(g, x, y, o);
        this.setTaille(4);
    }
}

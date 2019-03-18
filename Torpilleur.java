/**
 * Classe Torpilleur héritant de la classe Bateau
 */
public class Torpilleur extends Bateau {
    public Torpilleur(Grille g, int x, int y, boolean o){
        super(g, x, y, o);
        this.setTaille(2);
    }
}

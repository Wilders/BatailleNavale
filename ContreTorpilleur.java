/**
 * Classe ContreTorpilleur héritant de la classe Bateau
 */
public class ContreTorpilleur extends Bateau {
    public ContreTorpilleur(Grille g, int x, int y, boolean o){
        super(g, x, y, o);
        this.setTaille(3);
    }
}

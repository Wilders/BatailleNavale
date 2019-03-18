/**
 * Classe SousMarin héritant de la classe Bateau
 */
public class SousMarin extends Bateau {
    public SousMarin(Grille g, int x, int y, boolean o) throws Exception{
        super(g, x, y, o);
        this.setTaille(3);
    }
}

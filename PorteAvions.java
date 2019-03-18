/**
 * Classe PorteAvions héritant de la classe Bateau
 */
public class PorteAvions extends Bateau{
    public PorteAvions(Grille g, int x, int y, boolean o) throws Exception{
        super(g, x, y, o);
        this.setTaille(5);
    }
}

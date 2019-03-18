/**
 * Classe Croiseur héritant de la classe Bateau
 */
public class Croiseur extends Bateau {

    private String nom;
    
    public Croiseur(Grille g, boolean o, Case c) throws Exception{
        super(g, o);
        this.nom = "Croiseur";
        this.addMultipleCases(c);
    }

    protected void addMultipleCases(Case e) throws Exception{
        int x = e.getX();
        int y = e.getY();
        if(this.getOrientation()) {
            for (int i = 0; i < 4; i++) {
                this.addCase(new Case(x, y+i, false));
            }
        } else {
            for (int i = 0; i < 4; i++) {
                this.addCase(new Case(x+i, y, false));
            }
        }
    }

    public String toString() {
        return this.nom + super.toString();
    }
}
/**
 * Classe Torpilleur héritant de la classe Bateau
 */
public class Torpilleur extends Bateau {

    private String nom;

    public Torpilleur(Grille g, boolean o, Case c) throws Exception{
        super(g, o);
        this.nom = "Torpilleur";
        this.addMultipleCases(c);
    }

    protected void addMultipleCases(Case e) throws Exception{
        int x = e.getX();
        int y = e.getY();
        this.addCase(grille.gettCases()[x][y]);
        grille.gettCases()[x][y].setDispo(false);
    }

    public String getNom() {
        return this.nom;
    }

    public String toString() {
        return this.nom + super.toString();
    }
}
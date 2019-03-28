/**
 * Classe SousMarin héritant de la classe Bateau
 */
public class SousMarin extends Bateau {

    private String nom;

    public SousMarin(Grille g, boolean o, Case c) throws Exception {
        super(g, o);
        this.nom = "Sous Marin";
        this.addMultipleCases(c);
    }

    protected void addMultipleCases(Case e) throws Exception {
        int x = e.getX();
        int y = e.getY();
        if(this.getOrientation()) {
            for (int i = 0; i < 3; i++) {
                this.addCase(grille.gettCases()[x][y+i]);
                grille.gettCases()[x][y+i].setDispo(false);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                this.addCase(grille.gettCases()[x+i][y]);
                grille.gettCases()[x+i][y].setDispo(false);
            }
        }
    }

    public String getNom() {
        return this.nom;
    }

    public String toString() {
        return this.nom + super.toString();
    }
}
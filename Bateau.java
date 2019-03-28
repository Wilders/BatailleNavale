import javax.print.DocFlavor;
import java.util.ArrayList;
/**
 * Classe abstraite modélisant les bateaux.
 * Elle a comme attribut la taille du bateau (nombre de cases)
 * Deux entiers x et y représentant la position initiale du bateau (coin supérieur gauche)
 * Un booleen orientation (vaut False si le bateau est à l'horizontal et True si le bateau est à la verticale)
 */
public abstract class Bateau {

    protected Grille grille;
    protected ArrayList<Case> lPosition;
    protected boolean orientation;

    public Bateau(Grille g, boolean ori) {
        this.grille = g;
        this.orientation = ori;
        this.lPosition = new ArrayList<Case>();
    }

    protected void addCase(Case e) throws Exception {
        if(e.getX() > this.getGrille().getLargeur() || e.getY() > this.getGrille().getHauteur()) {
            throw new Exception("La case n'est pas dans la grille");
        }

        if(grille.gettCases()[e.getX()][e.getY()].getDispo()==false) {
            throw new Exception("La case n'est pas disponible");
        }

        this.lPosition.add(e);
        e.setDispo(false);
    }

    protected abstract void addMultipleCases(Case e) throws Exception;

    public double pourcentageTouche(){
        int taille = this.getTaille();
        int nbTouche=0;
        for (Case c : lPosition){
            if (c.getTouchee() == true){
                nbTouche++;
            }
        }
        return ((double)(nbTouche)/(double)(taille))*100;
    }

    protected boolean getOrientation() {
        return this.orientation;
    }

    public ArrayList<Case> getlPosition() {
        return lPosition;
    }

    protected Grille getGrille() {
        return this.grille;
    }

    public int getTaille(){
        return this.lPosition.size();
    }

    public abstract String getNom();

    public String toString() {
        String res = "";
        if(!this.lPosition.isEmpty()) {
            res = "[";
            for (Case c : this.lPosition) {
                res += "(" + c.getX() + "," + c.getY() + ")";
            }
            res+= "]";
        } else {
            res = "[Pas encore posé]";
        }
        return res;
    }
}
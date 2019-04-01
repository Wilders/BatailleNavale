import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe abstraite modelisant les bateaux.
 */
public abstract class Bateau implements Serializable {


    /**
     * Grille dans laquelle se situe le bateau
     */
    protected Grille grille;


    /**
     * Liste de cases (taille du bateau egale a la longueur de cette liste)
     */
    protected ArrayList<Case> lPosition;


    /**
     * Booleen orientation (egale a False si le bateau est a l'horizontal et True si le bateau est a la verticale)
     */
    protected boolean orientation;


    /**
     * Constructeur de la classe Bateau prenant en parametre la grille dans laquelle on place le bateau
     * ainsi qu'un booleen d'orientation
     * La liste de case est initialisee a null
     * @param g Grille dans laquelle placer le bateau
     * @param ori Booleen d'orientation pour savoir dans quel sens placer le bateau
     */
    public Bateau(Grille g, boolean ori) {
        this.grille = g;
        this.orientation = ori;
        this.lPosition = new ArrayList<Case>();
    }


    /**
     * Methode permettant d'ajouter une case a la liste de case du bateau (lPosition)
     * @param e Case que l'on veut ajouter dans la liste de case du bateau
     * @throws BateauException
     */
    protected void addCase(Case e) throws BateauException {
        if(e.getX() > this.getGrille().getLargeur() || e.getY() > this.getGrille().getHauteur()) {
            throw new BateauException("La case n'est pas dans la grille");
        }

        if(grille.gettCases()[e.getX()][e.getY()].getDispo()==false) {
            throw new BateauException("La case n'est pas disponible");
        }

        this.lPosition.add(e);
        e.setDispo(false);
    }


    /**
     * Methode abstraite permettant d'ajouter toutes les cases d'un bateau dans
     * la liste de case de celui-ci (Methode definie dans chaque classe heritant de Bateau)
     * @param e Case initiale de la position du bateau les autres cases sont definies par l'orientation et la taille du bateau
     * @throws BateauException
     */
    protected abstract void addMultipleCases(Case e) throws BateauException;


    /**
     * Methode permettant de connaitre le pourcentage d'impact sur un bateau
     * Si le bateau n'a pas ete touche la methode renvoit 0, s'il a ete entierement touche
     * la methode renvoit 100
     * @return Pourcentage d'impact du bateau
     */
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


    /**
     * Methode permettant de retourner l'attribut Orientation du bateau
     * @return Booleen Orientation
     */
    protected boolean getOrientation() {
        return this.orientation;
    }


    /**
     * Methode permettant de retourner l'attribut lPosition du bateau
     * @return Liste de cases du bateau
     */
    public ArrayList<Case> getlPosition() {
        return lPosition;
    }


    /**
     * Methode permettant de retourner l'attribut Grille du bateau
     * @return Grille dans laquelle est le bateau
     */
    protected Grille getGrille() {
        return this.grille;
    }


    /**
     * Methode permettant de retourner la taille d'un bateau
     * @return Longueur de sa liste de cases
     */
    public int getTaille(){
        return this.lPosition.size();
    }


    /**
     * Methode abstraite permettant de retourner l'attribut Nom d'une
     * instance de Bateau (methode definie dans les classes heritant de la classe Bateau)
     * @return Nom de l'objet heritant de la classe Bateau
     */
    public abstract String getNom();


    /**
     * Methode permettant de retourner un String contenant les coordonnees des differentes
     * cases d'un bateau
     * @return String des cases du bateau
     */
    public String toString() {
        String res = "";
        if(!this.lPosition.isEmpty()) {
            res = "[";
            for (Case c : this.lPosition) {
                res += "(" + c.getX() + "," + c.getY() + ")";
            }
            res+= "]";
        } else {
            res = "[Pas encore pose]";
        }
        return res;
    }
}
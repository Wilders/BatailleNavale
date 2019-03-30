/**
 * Classe modelisant une case
 */
public class Case {


    /**
     * X et Y correspondent aux coordonnees de la case dans la grille
     */
    private int x;
    private int y;


    /**
     *  Booleen Dispo permettant de savoir si un bateau est positionne sur cette case
     *  (Pas de bateau sur la case dispo=true, sinon dispo=false)
     */
    private boolean dispo;


    /**
     * Booleen Touchee permettant de savoir si la case a deja ete touchee par un tir
     * (Case touchee par un tir touchee=true, sinon touchee=false)
     */
    private boolean touchee;


    /**
     * Constructeur de la classe Case prenant en parametres les coordonnees de la case
     * La case est automatiquement initialisee a dispo=true car aucun bateau n'est present dessus initialement
     * La case est automatiquement initialisee a touchee=false car aucun joueur n'a tire sur la case initialement
     * @param x Abscisse de la case
     * @param y Ordonnee de la case
     * @throws CaseException
     */
    public Case(int x, int y) throws CaseException {
        if(x<0 || y<0) {
            throw new CaseException("La case ne peut pas être dans une position négative");
        } else {
            this.x = x;
            this.y = y;
        }
        this.dispo = true;
        this.touchee = false;
    }


    /**
     * Methode permettant de retourner l'attribut X d'une case
     * @return Abscisse de la case
     */
    public int getX() {
        return this.x;
    }


    /**
     * Methode permettant de retourner l'attribut Y d'une case
     * @return Ordonnee de la case
     */
    public int getY() {
        return this.y;
    }


    /**
     * Methode permettant de retourner l'attribut Dispo d'une case
     * @return Booleen Dispo de la case
     */
    public boolean getDispo() {
        return this.dispo;
    }


    /**
     * Methode permettant de retourner l'attribut Touchee d'une case
     * @return Booleen Touchee de la case
     */
    public boolean getTouchee() {
        return this.touchee;
    }


    /**
     * Methode permettant de changer la valeur de l'attribut Dispo d'une case
     * @param p Booleen que l'on souhaite attribuer a l'attribut Dispo de la case
     */
    public void setDispo(boolean p) {
        this.dispo = p;
    }


    /**
     * Methode permettant de changer la valeur de l'attribut Touchee d'une case
     * @param p Booleen que l'on souhaite attribuer a l'attribut Touchee de la case
     */
    public void setTouchee(boolean p) {
        this.touchee = p;
    }

    public String toString() {
        String res = "";
        if(this.getTouchee() && !this.getDispo()) {
            res = "X";
        } else if(this.getTouchee()) {
            res = "*";
        } else if(!this.getDispo()) {
            res = "O";
        } else {
            res = "-";
        }
        return res;
    }
}
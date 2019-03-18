/**
 * Classe abstraite modélisant les bateaux.
 * Elle a comme attribut la taille du bateau (nombre de cases)
 * Deux entiers x et y représentant la position initiale du bateau (coin supérieur gauche)
 * Un booleen orientation (vaut False si le bateau est à l'horizontal et True si le bateau est à la verticale)
 */
public abstract class Bateau {

    private int taille;
    private int x;
    private int y;
    private boolean orientation;


    /**
     * Constructeur de la classe Bateau, n'initialise pas la taille qui sera
     * initialiser dans la classe propre à chaque type de bateau
     * @param g Grille dans laquelle se situe le bateau
     * @param x Abscisse de la position du coin inférieur gauche du bateau
     * @param y Ordonnée de la position du coin inférieur gauche du bateau
     * @param o Orientation du bateau (Horizontal = False, Vertical = True)
     * @throws Exception
     */
    public Bateau(Grille g, int x, int y, boolean o) throws Exception{
        if (x<=g.getLargeur() && x>=0){
            this.x=x;
        }else {
            throw new Exception("L'abscisse n'est pas valide");
        }
        if (y<=g.getLongueur() && y>=0){
            this.y=y;
        }else {
            throw new Exception("L'ordonné n'est pas valide");
        }
        if (o){
            this.orientation=true;
        }else {
            this.orientation=false;
        }
    }

    /**
     * Getter de la taille du bateau
     * @return Taille du bateau
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Getter de l'ordonnee de la position du
     * coin supérieur gauche du bateau
     * @return Ordonnee de la position du coin supérieur gauche du bateau
     */
    public int getX() {
        return x;
    }

    /**
     * Getter de l'abscisse de la position du
     * coin supérieur gauche du bateau
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Getter du booleen de l'orientation du bateau
     * @return Booleen de l'orientation (False = Horizontal et True = Vertical)
     */
    public boolean isOrientation() {
        return orientation;
    }

    /**
     * Setter de la taille du bateau
     * @param taille
     */
    public void setTaille(int taille) {
        this.taille = taille;
    }

    /**
     * Setter de l'abscisse de la position du
     * coin supérieur gauche du bateau
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter de l'ordonnee de la position du
     * coin supérieur gauche du bateau
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Setter du booleen de l'orientation du bateau
     * @param orientation
     */
    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }
}

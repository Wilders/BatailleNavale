public abstract class Bateau {

    private int taille;
    private int x;
    private int y;
    private boolean orientation;


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


    public int getTaille() {
        return taille;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOrientation() {
        return orientation;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }
}

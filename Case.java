public class Case {
    
    private int x;
    private int y;
    private boolean dispo;
    private boolean touchee;

    public Case(int x, int y) throws Exception {
        if(x<0 || y<0) {
            throw new Exception("La case ne peut pas être dans une position négative");
        } else {
            this.x = x;
            this.y = y;
        }
        this.dispo = true;
        this.touchee = false;
    }

    public Case(int x, int y, boolean disp) throws Exception {
        if(x<0 || y<0) {
            throw new Exception("La case ne peut pas être dans une position négative");
        } else {
            this.x = x;
            this.y = y;
        }
        this.dispo = disp;
        this.touchee = false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getDispo() {
        return this.dispo;
    }

    public boolean getTouchee() {
        return this.touchee;
    }

    public void setDispo(boolean p) {
        this.dispo = p;
    }

    public void setTouchee(boolean p) {
        this.touchee = p;
    }
}
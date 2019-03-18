public class Case {
    private int x;
    private int y;
    private boolean bateau;
    private boolean touchee;

    public Case(int x, int y) throws Exception {
        if(x<0 || y<0) {
            throw new Exception();
        } else {
            this.x = x;
            this.y = y;
        }
        this.bateau = false;
        this.touchee = false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getBateau() {
        return this.bateau;
    }

    public boolean getTouchee() {
        return this.touchee;
    }

    public void setBateau(boolean p) {
        this.bateau = p;
    }

    public void setTouchee(boolean p) {
        this.touchee = p;
    }
}
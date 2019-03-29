public class InfoPosBateau {
    private int xB;
    private int yB;
    private boolean orientation;

    public InfoPosBateau(int x, int y, boolean o){
        xB=x;
        yB=y;
        orientation=o;
    }

    public int getxB() {
        return xB;
    }

    public int getyB() {
        return yB;
    }

    public boolean isOrientation() {
        return orientation;
    }

    public void setxB(int xB) {
        this.xB = xB;
    }

    public void setyB(int yB) {
        this.yB = yB;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }
}

import java.util.ArrayList;

public class Joueur {
    private Grille grille;
    private ArrayList<Bateau> lBateau;

    public Joueur(Grille g) {
        this.grille=g;
    }

    public boolean testCaseDispo(int x, int y) {
        boolean res = true;
        if(x >= 0 && x < this.grille.getLargeur() && y >= 0 && y < this.grille.getLongueur()) {
            for(Case c : this.grille.getLCase()) {
                if(c.getX() == x && c.getY() == y && !c.getDispo()) {
                    res = false;
                }
            }
        } else {
            res = false;
        }
        return res;
    }

    public void ajouterBateau(int x, int y, boolean orientation, int taille) throws Exception{
        switch (taille){
            case 1 :
                if(!this.testCaseDispo(x, y)) {
                    throw new Exception("Case non disponible");
                }
                for (Bateau b : lBateau){
                    if (b instanceof Torpilleur){
                        throw new Exception("Ce bateau est déjà dans la liste");
                    }
                }
                Torpilleur tp = new Torpilleur(grille, x, y, orientation);
                lBateau.add(tp);
                for(Case c : this.grille.getLCase()) {
                    if(c.getX() == x && c.getY() == y) {
                        c.setDispo(false);
                    }
                }
                break;
            case 2 :
                if(orientation) {
                    for (int i = 0; i < taille; i++) {
                        if(!this.testCaseDispo(x, y+i)) {
                            throw new Exception("Case non disponible");
                        }
                    }
                } else {
                    for (int i = 0; i < taille; i++) {
                        if(!this.testCaseDispo(x+i, y)) {
                            throw new Exception("Case non disponible");
                        }
                    }
                }
                for (Bateau b : lBateau){
                    if (b instanceof ContreTorpilleur){
                        throw new Exception("Ce bateau est déjà dans la liste");
                    }
                }
                ContreTorpilleur ctp = new ContreTorpilleur(grille, x, y, orientation);
                lBateau.add(ctp);
                for (int i = 0; i < taille; i++) {
                    for(Case c : this.grille.getLCase()) {
                        if(orientation) {
                            if(c.getX() == x && c.getY() == y+i) {
                                c.setDispo(false);
                            }
                        } else {
                            if(c.getX() == x+i && c.getY() == y) {
                                c.setDispo(false);
                            }
                        }
                    }
                }
                break;
            case 3 :
                if(orientation) {
                    for (int i = 0; i < taille; i++) {
                        if(!this.testCaseDispo(x, y+i)) {
                            throw new Exception("Case non disponible");
                        }
                    }
                } else {
                    for (int i = 0; i < taille; i++) {
                        if(!this.testCaseDispo(x+i, y)) {
                            throw new Exception("Case non disponible");
                        }
                    }
                }
                for (Bateau b : lBateau){
                    if (b instanceof SousMarin){
                        throw new Exception("Ce bateau est déjà dans la liste");
                    }
                }
                SousMarin sm = new SousMarin(grille, x, y, orientation);
                lBateau.add(sm);
                for (int i = 0; i < taille; i++) {
                    for(Case c : this.grille.getLCase()) {
                        if(orientation) {
                            if(c.getX() == x && c.getY() == y+i) {
                                c.setDispo(false);
                            }
                        } else {
                            if(c.getX() == x+i && c.getY() == y) {
                                c.setDispo(false);
                            }
                        }
                    }
                }
                break;
            case 4 :
                if(orientation) {
                    for (int i = 0; i < taille; i++) {
                        if(!this.testCaseDispo(x, y+i)) {
                            throw new Exception("Case non disponible");
                        }
                    }
                } else {
                    for (int i = 0; i < taille; i++) {
                        if(!this.testCaseDispo(x+i, y)) {
                            throw new Exception("Case non disponible");
                        }
                    }
                }
                for (Bateau b : lBateau){
                    if (b instanceof Croiseur){
                        throw new Exception("Ce bateau est déjà dans la liste");
                    }
                }
                Croiseur cr = new Croiseur(grille, x, y, orientation);
                lBateau.add(cr);
                for (int i = 0; i < taille; i++) {
                    for(Case c : this.grille.getLCase()) {
                        if(orientation) {
                            if(c.getX() == x && c.getY() == y+i) {
                                c.setDispo(false);
                            }
                        } else {
                            if(c.getX() == x+i && c.getY() == y) {
                                c.setDispo(false);
                            }
                        }
                    }
                }
                break;
            case 5 :
                if(orientation) {
                    for (int i = 0; i < taille; i++) {
                        if(!this.testCaseDispo(x, y+i)) {
                            throw new Exception("Case non disponible");
                        }
                    }
                } else {
                    for (int i = 0; i < taille; i++) {
                        if(!this.testCaseDispo(x+i, y)) {
                            throw new Exception("Case non disponible");
                        }
                    }
                }
                for (Bateau b : lBateau){
                    if (b instanceof PorteAvions){
                        throw new Exception("Ce bateau est déjà dans la liste");
                    }
                }
                PorteAvions pa = new PorteAvions(grille, x, y, orientation);
                lBateau.add(pa);
                for (int i = 0; i < taille; i++) {
                    for(Case c : this.grille.getLCase()) {
                        if(orientation) {
                            if(c.getX() == x && c.getY() == y+i) {
                                c.setDispo(false);
                            }
                        } else {
                            if(c.getX() == x+i && c.getY() == y) {
                                c.setDispo(false);
                            }
                        }
                    }
                }
                break;
            default:
                throw new Exception("Bateau invalide");
        }
    }
}
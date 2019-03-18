import java.util.ArrayList;

public class Joueur {
    private Grille grille;
    private ArrayList<Bateau> lBateau;

    public Joueur(Grille g, ArrayList<Bateau> lb) {
        this.grille=g;
        this.lBateau=lb;
    }

    public void ajouterBateauListe(int x, int y, boolean orientation, int taille) throws Exception{
        switch (taille){
            case 1 :
                for (Bateau b : lBateau){
                    if (b instanceof Torpilleur){
                        throw new Exception("Ce bateau est déjà dans la liste");
                    }
                }
                Torpilleur tp = new Torpilleur(grille, x, y, orientation);
                lBateau.add(tp);
                break;
            case 2 :
                for (Bateau b : lBateau){
                    if (b instanceof ContreTorpilleur){
                        throw new Exception("Ce bateau est déjà dans la liste");
                    }
                }
                ContreTorpilleur ctp = new ContreTorpilleur(grille, x, y, orientation);
                lBateau.add(ctp);
                break;
            case 3 :
                for (Bateau b : lBateau){
                    if (b instanceof SousMarin){
                        throw new Exception("Ce bateau est déjà dans la liste");
                    }
                }
                SousMarin sm = new SousMarin(grille, x, y, orientation);
                lBateau.add(sm);
                break;
            case 4 :
                for (Bateau b : lBateau){
                    if (b instanceof Croiseur){
                        throw new Exception("Ce bateau est déjà dans la liste");
                    }
                }
                Croiseur cr = new Croiseur(grille, x, y, orientation);
                lBateau.add(cr);
                break;
            case 5 :
                for (Bateau b : lBateau){
                    if (b instanceof PorteAvions){
                        throw new Exception("Ce bateau est déjà dans la liste");
                    }
                }
                PorteAvions pa = new PorteAvions(grille, x, y, orientation);
                lBateau.add(pa);
                break;
            default:
                throw new Exception("Bateau invalide");
        }
    }
}

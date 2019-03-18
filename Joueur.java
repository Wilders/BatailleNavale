import java.util.ArrayList;

public class Joueur {

    private Grille grille;
    private ArrayList<Bateau> lBateau;

    public Joueur(Grille g) {
        this.grille=g;
        this.lBateau = new ArrayList<Bateau>();
    }
    
    public void ajouterBateau(Case c, boolean orientation, int taille) throws Exception {
        switch (taille){
            case 1 :
                if(!this.lBateau.isEmpty()) {
                    for (Bateau b : lBateau){
                        if (b instanceof Torpilleur){
                            throw new Exception("Ce bateau est déjà dans la liste");
                        }
                    }
                }
                Torpilleur p = new Torpilleur(this.grille, orientation, c);
                lBateau.add(p);
                break;
            case 2 :
                if(!this.lBateau.isEmpty()) {
                    for (Bateau b : lBateau){
                        if (b instanceof ContreTorpilleur){
                            throw new Exception("Ce bateau est déjà dans la liste");
                        }
                    }
                }
                ContreTorpilleur ct = new ContreTorpilleur(this.grille, orientation, c);
                lBateau.add(ct);
                break;
            case 3 :
                if(!this.lBateau.isEmpty()) {
                    for (Bateau b : lBateau){
                        if (b instanceof SousMarin){
                            throw new Exception("Ce bateau est déjà dans la liste");
                        }
                    }
                }
                SousMarin s = new SousMarin(this.grille, orientation, c);
                lBateau.add(s);
                break;
            case 4 :
                if(!this.lBateau.isEmpty()) {
                    for (Bateau b : lBateau){
                        if (b instanceof Croiseur){
                            throw new Exception("Ce bateau est déjà dans la liste");
                        }
                    }
                }
                Croiseur cr = new Croiseur(this.grille, orientation, c);
                lBateau.add(cr);
                break;
            case 5 :
                if(!this.lBateau.isEmpty()) {
                    for (Bateau b : lBateau){
                        if (b instanceof PorteAvions){
                            throw new Exception("Ce bateau est déjà dans la liste");
                        }
                    }
                }
                PorteAvions po = new PorteAvions(this.grille, orientation, c);
                lBateau.add(po);
                break;
            default:
                throw new Exception("Bateau invalide");
        }
    }

    public String toString() {
        String res = "Ce joueur possede " + this.lBateau.size() + " bateaux:\n";
        for (Bateau b : this.lBateau) {
            res += "\t Un " + b.toString() + "\n";
        }
        return res;
    }

    public static void main(String[] args) {
        try {
            Grille g = new Grille(10, 10);
            Joueur j  = new Joueur(g);
            j.ajouterBateau(new Case(8,7), true, 1);
            j.ajouterBateau(new Case(5,5), true, 4);
            System.out.println(j.toString());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
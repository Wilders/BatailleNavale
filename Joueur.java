import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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




    protected ArrayList<Bateau> triBateauTaille(ArrayList<Bateau> lB){
        int tmp = lB.size();
        ArrayList<Bateau> res = new ArrayList<>(tmp);
        ArrayList<Bateau> temp = new ArrayList<>(lB);
        int min = 1;
        while(res.size()!= tmp){
            ListIterator<Bateau> it = temp.listIterator();
            while (it.hasNext()){
                Bateau b = it.next();
                if (b.getTaille()<=min){
                    res.add(b);
                    it.remove();
                    min=b.getTaille()+1;
                }
            }
        }
        return res;
    }




    /**
     * NE MARCHE PAS POUR LE MOMENT
     * @param lB
     * @return
     */
    protected ArrayList<Bateau> triBateauPourcentage(ArrayList<Bateau> lB){
        int tmp = lB.size();
        ArrayList<Bateau> res = new ArrayList<>(tmp);
        ArrayList<Bateau> temp = new ArrayList<>(lB);
        int min = 0;
        while(res.size()!= tmp){
            ListIterator<Bateau> it = temp.listIterator();
            while (it.hasNext()){
                Bateau b = it.next();
                if (b.pourcentageTouche()<=min){
                    res.add(b);
                    it.remove();
                    min=(int)(b.pourcentageTouche())+1;
                }
            }
        }
        return res;
    }




    public void toucher(Grille g, Case c) throws Exception{
        if (g.getLCase().contains(c)==true){
            if (g.getLCase().get(g.getLCase().indexOf(c)).getTouchee()==false){
                g.getLCase().get(g.getLCase().indexOf(c)).setTouchee(true);
            }else {
                throw new Exception("Case déjà touchée");
            }
        }else {
            throw new Exception("Case inexistante dans cette grille");
        }
    }




    public boolean couler(Bateau b){
        boolean res = false;
        if (b.pourcentageTouche()==100.0){
            res=true;
        }
        return res;
    }




    public boolean perdu(Joueur j){
        boolean res=false;
        int cpt=0;
        for (Bateau b : lBateau){
            if (couler(b)){
                cpt++;
            }
        }
        if (cpt==lBateau.size()){
            res=true;
        }
        return res;
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

            j.ajouterBateau(new Case(1,1), true, 1);
            j.ajouterBateau(new Case(4,8), false, 5);
            j.ajouterBateau(new Case(2,2), false, 2);
            j.ajouterBateau(new Case(4,4), false, 4);
            j.ajouterBateau(new Case(1,5), false, 3);

            System.out.println(j.toString());
            System.out.println("=============");
            System.out.println(j.lBateau.size());
            System.out.println("=============");
            System.out.println(j.triBateauTaille(j.lBateau));
            System.out.println("=============");
            System.out.println(j.triBateauTaille(j.lBateau).size());
            System.out.println("=============");
            System.out.println(j.triBateauPourcentage(j.lBateau));
            System.out.println("=============");
            //System.out.println(j.triBateauPourcentage(j.lBateau).size());

            //System.out.println("=============" );
            j.lBateau.get(1).getlPosition().get(1).setTouchee(true);
            System.out.println(j.triBateauPourcentage(j.lBateau));
            System.out.println("=============");
            //for (Case t : j.lBateau.get(1).getlPosition()){
            //    System.out.println(t.getTouchee());
            //}
            System.out.println(j.lBateau.get(1).pourcentageTouche());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
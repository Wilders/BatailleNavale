import java.util.ArrayList;
import java.util.ListIterator;


/**
 * Classe modelisant un Joueur
 */
public class Joueur {


    /**
     * Grille du joueur
     */
    private Grille grille;


    /**
     * Liste de bateaux du joueur
     */
    private ArrayList<Bateau> lBateau;


    /**
     * Constructeur de la classe Joueur prenant en paramètre la grille du joueur
     * et initialise automatique la liste de bateau a null
     * @param g Grille du joueur
     */
    public Joueur(Grille g) {
        this.grille=g;
        this.lBateau = new ArrayList<Bateau>();
    }


    /**
     * Methode permettant d'ajouter un bateau a la liste de bateau du joueur et prenant en parametres la case initiale
     * du bateau a ajouter, un booleen pour l'orientation et la taille de celui-ci
     * @param c Case initiale du bateau a ajouter
     * @param orientation Booleen d'orientation du bateau a ajouter
     * @param taille Taille du bateau a ajouter
     * @throws Exception
     */
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


    /**
     * Methode permettant de tirer sur un bateau ennemi prenant en parametre le joueur
     * sur qui on veut tirer et la case sur laquelle on veut tirer
     * @param j Joueur sur lequel on veut tirer
     * @param c Case sur laquelle on veut tirer
     * @throws Exception
     */
    public void tirer(Joueur j, Case c) throws Exception{
        if (c.getX()<=j.grille.getLargeur() || c.getY()<=j.grille.getHauteur() || c.getX()>=0 || c.getY()>=0){
            if (j.grille.gettCases()[c.getX()][c.getY()].getTouchee()==false){
                j.grille.gettCases()[c.getX()][c.getY()].setTouchee(true);
            }else {
                throw new Exception("Case déjà touchée");
            }
        }else {
            throw new Exception("Case inexistante dans cette grille");
        }
    }


    /**
     * Methode permettant de savoir si un bateau est coule (entierement touche) prenant en parametre le bateau
     * @param b Bateau a analyser
     * @return Booleen (Bateau coule=true, sinon coule=false)
     */
    public boolean couler(Bateau b){
        boolean res = false;
        if (b.pourcentageTouche()==100.0){
            res=true;
        }
        return res;
    }


    /**
     * Methode permettant de savoir si un joueur a perdu la partie (tous ses bateaux ont coule)
     * @return Booleen (Joueur a perdu=true, sinon perdu=false)
     */
    public boolean perdu(){
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


    /**
     * Methode permettant de retourner la liste de bateau du joueur triee par taille des bateaux
     * @param lB Liste des bateaux du joueur
     * @return Liste triee par taille des bateaux du joueur
     */
    public ArrayList<Bateau> triBateauTaille(ArrayList<Bateau> lB){
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
     * Methode permettant de retourner la liste de bateau du joueur triee par pourcentage d'impact des bateaux
     * @param lB Liste des bateaux du joueur
     * @return Liste triee par pourcentage d'impact des bateaux du joueur
     */
    public ArrayList<Bateau> triBateauPourcentage(ArrayList<Bateau> lB){
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


    /**
     * Methode permettant de retourner l'attribut lBateau du joueur
     * @return Liste des bateaux du joueur
     */
    public ArrayList<Bateau> getlBateau() {
        return lBateau;
    }


    /**
     * Methode permettant de retouner l'attribut Grille du joueur
     * @return Grille du joueur
     */
    public Grille getGrille() {
        return grille;
    }


    /**
     * Methode retournant un String contenant le nombre de bateaux du joueur
     * ainsi que les caracteristiques de ses bateaux
     * @return String avec les informations sur les bateaux que possede le joueur
     */
    public String toString() {
        String res = "Ce joueur possede " + this.lBateau.size() + " bateaux:\n";
        for (Bateau b : this.lBateau) {
            res += "\t Un " + b.toString() + "\n";
        }
        return res;
    }
}
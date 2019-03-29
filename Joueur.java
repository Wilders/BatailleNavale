import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;


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
    public void tirer(Joueur j, Case c){

        if (!j.grille.gettCases()[c.getX()][c.getY()].getTouchee()){
            j.grille.gettCases()[c.getX()][c.getY()].setTouchee(true);
        }else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Case déjà touchée :");
            retirer(j, sc);
        }

        if (!j.getGrille().gettCases()[c.getX()][c.getY()].getDispo()){
            System.out.println(bateauCase(c).getNom()+" touché !");
        }else {
            System.out.println("Aucun bateau touché !");
        }
        if (!grille.gettCases()[c.getX()][c.getY()].getDispo()){
            if (couler(bateauCase(c))){
                System.out.println(bateauCase(c).getNom()+" coulé !");
            }
        }
    }


    /**
     * Methode permettant de retirer si le tire ne s'est pas bien passé
     * @param j Joueur sur lequel on veut tirer
     * @param sc Scanner permettant la saisie au clavier
     */
    public void retirer(Joueur j, Scanner sc){
        System.out.print("Entrez une nouvelle valeur de x pour tirer : ");
        int x = sc.nextInt();
        System.out.print("Entrez une nouvelle valeur de y pour tirer : ");
        int y = sc.nextInt();
        tirer(j,j.getGrille().gettCases()[x][y]);
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
     * Methode permettant de retourner le bateau se situant sur une case
     * @param c Case ou l'on cherche le bateau present sur celle-ci
     * @return Bateau present sur la case ou null s'il n'y a pas de bateau sur la case
     */
    public Bateau bateauCase(Case c){
        try {
            if (!grille.gettCases()[c.getX()][c.getY()].getDispo()){
                for (Bateau b : lBateau){
                    for (int i=0; i<b.getlPosition().size(); i++){
                        if (b.getlPosition().get(i).getX()==c.getX() && b.getlPosition().get(i).getY()==c.getY()){
                            return b;
                        }
                    }

                }
            }
        }catch (Exception e){
            throw e;
        }
        return null;
    }


    /**
     * Methode permettant de verifier si on peut poser un bateau a partir d'une case et d'une orientation
     * @param c Case initiale du bateau
     * @param o Orientation du bateau
     * @return
     */
    public boolean verifierPosBateau(Case c, boolean o, int t){
        boolean res=false;
        int cpt=0;
        if (o){
            for (int i=0; i<t; i++){
                if (c.getX()>=0 && c.getX()<grille.getLargeur() && c.getY()+i>=0 && c.getY()+i<grille.getHauteur()){
                    if (grille.gettCases()[c.getX()][c.getY()+i].getDispo()){
                        cpt++;
                    }
                }
            }
            if (cpt==t){
                res=true;
            }
        }else{
            for (int i=0; i<t; i++){
                if (c.getX()+i>=0 && c.getX()+i<grille.getLargeur() && c.getY()>=0 && c.getY()<grille.getHauteur()){
                    if (grille.gettCases()[c.getX()+i][c.getY()].getDispo()){
                        cpt++;
                    }
                }
            }
            if (cpt==t){
                res=true;
            }
        }
        return res;
    }


    /**
     * Methode permettant de replacer un bateau lorsque les coordonnees de celui-ci sont mauvaises
     * @param taille Taille du bateau que l'on souhaite replacer
     * @return Informations sur le bateau a replacer
     * @throws Exception
     */
    public InfoPosBateau replacerBateau(int taille) throws Exception{
        boolean ok =false;
        InfoPosBateau infoBat = new InfoPosBateau(0,0,false);
        while(!ok){
            System.out.println("Impossible de poser le bateau a cet endroit, changez d'endroit : ");
            Scanner sc = new Scanner(System.in);
            boolean orientation=false;
            System.out.print("Donnez la position en x du Bateau de taille " + taille + " : ");
            int xBat = sc.nextInt();
            System.out.print("Donnez la position en y du Bateau de taille " + taille + " : ");
            int yBat = sc.nextInt();
            System.out.print("Donnez l'oriation du Bateau de taille " + taille + " (Horizontal (H) ou Vertical (V)) : ");
            if (sc.nextLine().toUpperCase().compareTo("H") == 0) {
                orientation = false;
            } else {
                if (sc.nextLine().toUpperCase().compareTo("V") == 0) {
                    orientation = true;
                }
            }
            if (verifierPosBateau(grille.gettCases()[xBat][yBat],orientation,taille)){
                ok=true;
                infoBat.setxB(xBat);
                infoBat.setyB(yBat);
                infoBat.setOrientation(orientation);
                break;
            }
        }
        return infoBat;
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
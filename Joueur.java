import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.Scanner;


/**
 * Classe modelisant un Joueur
 */
public class Joueur implements Serializable {


    /**
     * Grille du joueur
     */
    private Grille grille;


    /**
     * Liste de bateaux du joueur
     */
    private ArrayList<Bateau> lBateau;


    /**
     * Nom du joueur
     */
    private String nomJoueur;


    /**
     * Constructeur de la classe Joueur prenant en parametre la grille du joueur
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
     * @throws BateauException
     */
    public void ajouterBateau(Case c, boolean orientation, int taille) throws BateauException {
        switch (taille){
            case 1 :
                if(!this.lBateau.isEmpty()) {
                    for (Bateau b : lBateau){
                        if (b instanceof Torpilleur){
                            throw new BateauException("Ce bateau est deja dans la liste");
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
                            throw new BateauException("Ce bateau est deja dans la liste");
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
                            throw new BateauException("Ce bateau est deja dans la liste");
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
                            throw new BateauException("Ce bateau est deja dans la liste");
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
                            throw new BateauException("Ce bateau est deja dans la liste");
                        }
                    }
                }
                PorteAvions po = new PorteAvions(this.grille, orientation, c);
                lBateau.add(po);
                break;
            default:
                throw new BateauException("Bateau invalide");
        }
    }


    /**
     * Methode permettant de tirer sur un bateau ennemi prenant en parametre le joueur
     * sur qui on veut tirer et la case sur laquelle on veut tirer
     * @param j Joueur sur lequel on veut tirer
     * @param c Case sur laquelle on veut tirer
     * @throws BateauException 
     */
    public void tirer(Joueur j, Case c) throws BateauException{
        if(c.getX() >= j.getGrille().getLargeur() || c.getY() >= j.getGrille().getHauteur()) {
            throw new BateauException("La case n'est pas dans la grille");
        }
        if (!j.getGrille().gettCases()[c.getX()][c.getY()].getTouchee()){
            j.getGrille().gettCases()[c.getX()][c.getY()].setTouchee(true);
        }else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Case deja touchee :");
            retirer(j, sc);
        }

        if (!j.getGrille().gettCases()[c.getX()][c.getY()].getDispo()){
            System.out.println(bateauCase(j,c).getNom()+" touche !");
        }else {
            System.out.println("Aucun bateau touche !");
        }
        if (!j.getGrille().gettCases()[c.getX()][c.getY()].getDispo()){
            if (j.couler(bateauCase(j,c))){
                System.out.println(bateauCase(j,c).getNom()+" coule !");
            }
        }
    }


    /**
     * Methode permettant de retirer si le tire ne s'est pas bien passe
     * @param j Joueur sur lequel on veut tirer
     * @param sc Scanner permettant la saisie au clavier
     * @throws BateauException 
     */
    public void retirer(Joueur j, Scanner sc) throws BateauException{
        System.out.println("Case du tableau inaccessible : ");
        int x1=0;
        int y1=0;
        boolean ok = false;
        while (!ok){
            try {
                System.out.print(this.nomJoueur.toUpperCase()+" : Entrez le x pour tirer : ");
                x1 = sc.nextInt();
                sc.nextLine();
                System.out.print(this.nomJoueur.toUpperCase()+" : Entrez le y pour tirer : ");
                y1 = sc.nextInt();
                sc.nextLine();
                ok=true;
            }catch (InputMismatchException ime){
                System.out.println("Erreur, entrez deux entiers : ");
                sc.nextLine();
            }
        }
        if (x1>=0 && x1<j.grille.getLargeur() && y1>=0 && y1<j.grille.getHauteur()){
            this.tirer(j, j.getGrille().gettCases()[x1][y1]);
        }else {
            this.retirer(j,sc);
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
     * Methode permettant de retourner le bateau se situant sur une case
     * @param c Case ou l'on cherche le bateau present sur celle-ci
     * @return Bateau present sur la case ou null s'il n'y a pas de bateau sur la case
     */
    public Bateau bateauCase(Joueur j, Case c){
        try {
            if (!j.getGrille().gettCases()[c.getX()][c.getY()].getDispo()){
                for (Bateau b : j.getlBateau()){
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
     */
    public Object[] replacerBateau(int taille){
        boolean ok =false;
        Object[] res = new Object[2];
        while(!ok){
            System.out.println("Impossible de poser le bateau a cet endroit, changez d'endroit : ");
            Scanner sc = new Scanner(System.in);
            Object[] tmp=saisirBateau(sc, taille);
            int xBat = ((Case)(tmp[0])).getX();
            int yBat = ((Case)(tmp[0])).getY();
            boolean orientation = (boolean)(tmp[1]);
            if (verifierPosBateau(grille.gettCases()[xBat][yBat],orientation,taille)){
                ok=true;
                res[0]=grille.gettCases()[((Case)(tmp[0])).getX()][((Case)(tmp[0])).getY()];
                res[1]=(boolean)(tmp[1]);
                break;
            }
        }
        return res;
    }


    /**
     * Methode permettant la saisie de la position pour le positionner sur la grille
     * @param sc Scanner permettant les saisies clavier
     * @param taille Taille du bateau a placer
     * @return
     */
    public Object[] saisirBateau(Scanner sc, int taille){
        Object[] res = new Object[2];
        int xBat=0;
        int yBat=0;
        boolean ok=false;
        while (!ok){
            try {
                System.out.print("Donnez la position en x du Bateau de taille " + taille + " : ");
                xBat = sc.nextInt();
                sc.nextLine();
                System.out.print("Donnez la position en y du Bateau de taille " + taille + " : ");
                yBat = sc.nextInt();
                sc.nextLine();
                ok=true;
            }catch (InputMismatchException ime){
                System.out.println("Erreur, entrez deux entiers : ");
                sc.nextLine();
            }
        }
        System.out.print("Donnez l'oriation du Bateau de taille " + taille + " (Horizontal (H) ou Vertical (V)) : ");
        boolean orientation=false;
        String tmp=sc.nextLine();
        if (tmp.toUpperCase().compareTo("H") == 0) {
            orientation = false;
        } else {
            if (tmp.toUpperCase().compareTo("V") == 0) {
                orientation = true;
            }
        }
        if (xBat>=0 && xBat<grille.getLargeur() && yBat>=0 && yBat<grille.getHauteur() && verifierPosBateau(grille.gettCases()[xBat][yBat],orientation,taille)){
            res[0]=grille.gettCases()[xBat][yBat];
            res[1]=orientation;
        }else {
            System.out.println("Impossible de poser le bateau a cet endroit, changez d'endroit : ");
            res=saisirBateau(sc,taille);
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
    	ArrayList<Bateau> res = new ArrayList<>(lB);
        res.sort(new Compare());
        Collections.reverse(res);
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
     * Methode permettant de modifier l'attribut Nom du joueur
     * @param nomJoueur Nouveau nom du joueur
     */
    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }


    /**
     * Methode permettant de retourner l'attribut Nom du joueur
     * @return Nom du joueur
     */
    public String getNomJoueur() {
        return nomJoueur;
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
    
    /**
     * Classe de comparaison
     */
    static class Compare implements Comparator<Bateau> {
        @Override
        public int compare(Bateau o1, Bateau o2) {
            if (o1.pourcentageTouche() == o2.pourcentageTouche()) {
                return 0;
            }
            else if (o1.pourcentageTouche() > o2.pourcentageTouche()) {
                return 1;
            }
            return -1;
        }
    }
}
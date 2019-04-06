import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {

	/**
	 * Tests de la classe Case
	 */
	
	@org.junit.Test(expected = CaseException.class)
	public void testConstructeurCase1() throws CaseException {
		new Case(-1,-15);
	}

	@org.junit.Test
	public void testConstructeurCase2() throws CaseException {
		Case c = new Case(0, 7);
		assertEquals("Devrait etre 0", 0, c.getX());
		assertEquals("Devrait etre 7", 7, c.getY());
		assertEquals("Devrait etre true", true, c.getDispo());
		assertEquals("Devrait etre false", false, c.getTouchee());
	}
	
	@org.junit.Test
	public void testSetsCase() throws CaseException {
		Case c = new Case(12, 5);
		c.setDispo(false);
		c.setTouchee(true);
		assertEquals("Devrait etre 12", 12, c.getX());
		assertEquals("Devrait etre 5", 5, c.getY());
		assertEquals("Devrait etre false", false, c.getDispo());
		assertEquals("Devrait etre true", true, c.getTouchee());
	}
	
	@org.junit.Test
	public void testToStringCase() throws CaseException {
		Case c = new Case(180, 0);
		assertEquals("Devrait etre -", "-", c.toString());
		c.setDispo(false);
		assertEquals("Devrait etre O", "O", c.toString());
		c.setTouchee(true);
		assertEquals("Devrait etre X", "X", c.toString());
		c.setDispo(true);
		assertEquals("Devrait etre *", "*", c.toString());
	}
	
	/**
	 * Test de la classe Grille
	 */
	
	@org.junit.Test(expected = GrilleException.class)
	public void testConstructeurGrille1() throws GrilleException, CaseException {
		new Grille(-1, 18);
	}
	
	@org.junit.Test
	public void testConstructeurGrille2() throws GrilleException, CaseException {
		Grille g = new Grille(150, 200);
		Case[][] tab = new Case[150][200];
        for (int i = 0; i < 150; i++){
            for (int j = 0; j < 200; j++){
                tab[i][j] = new Case(i,j);
            }
        }
		assertEquals("Devrait etre 150", 150, g.getLargeur());
		assertEquals("Devrait etre 200", 200, g.getHauteur());
		assertEquals("Devrait etre un tableau 2d 150,200", tab.length, g.gettCases().length);
        for (int i = 0; i < 150; i++){
            for (int j = 0; j < 200; j++){
            	if(!(g.gettCases()[i][j] instanceof Case)) {
            		fail("Toutes les entrees du tableau devraient etre une case");
            	}
            }
        }
	}
	
	/**
	 * Test de la classe Torpilleur (les tests Constructeurs
	 * testent aussi les méthodes addCase et addMultipleCase
	 * car ils font appel à ces méthodes)
	 */
	
	@org.junit.Test(expected = BateauException.class)
	public void testConstructeurTorpilleur1() throws BateauException, CaseException, GrilleException {
		Grille g = new Grille(150, 200);
		Case c = new Case(111,1);
		Case c2 = new Case(1243, 123);
		Torpilleur t = new Torpilleur(g, true, c);
		Torpilleur t2 = new Torpilleur(g, true, c2);
	}
	
	@org.junit.Test
	public void testConstructeurTorpilleur2() throws BateauException, CaseException, GrilleException {
		Grille g = new Grille(150, 200);
		Case c = new Case(111,1);
		Torpilleur t = new Torpilleur(g, true, c);
		ArrayList<Case> list = new ArrayList<Case>();
		list.add(g.gettCases()[c.getX()][c.getY()]);
		assertEquals("Devrait etre Torpilleur", "Torpilleur", t.getNom());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()].getDispo());
		assertEquals("La liste devrait avoir les bonnes positions", list, t.getlPosition());
	}
	
	/**
	 * Test de la classe ContreTorpilleur (les tests Constructeurs
	 * testent aussi les méthodes addCase et addMultipleCase
	 * car ils font appel à ces méthodes)
	 */
	
	@org.junit.Test(expected = BateauException.class)
	public void testConstructeurContreTorpilleur1() throws BateauException, CaseException, GrilleException {
		Grille g = new Grille(150, 200);
		Case c = new Case(111,1);
		Case c2 = new Case(1243, 123);
		ContreTorpilleur ct = new ContreTorpilleur(g, true, c);
		ContreTorpilleur ct2 = new ContreTorpilleur(g, true, c2);
	}
	
	@org.junit.Test
	public void testConstructeurContreTorpilleur2() throws BateauException, CaseException, GrilleException {
		Grille g = new Grille(150, 200);
		Case c = new Case(111,1);
		Case c2 = new Case(70,50);
		ContreTorpilleur ct = new ContreTorpilleur(g, true, c);
		ContreTorpilleur ct2 = new ContreTorpilleur(g, false, c2);
		ArrayList<Case> list = new ArrayList<Case>();
		ArrayList<Case> list2 = new ArrayList<Case>();
		list2.add(g.gettCases()[c2.getX()][c2.getY()]);
		list2.add(g.gettCases()[c2.getX()+1][c2.getY()]);
		list.add(g.gettCases()[c.getX()][c.getY()]);
		list.add(g.gettCases()[c.getX()][c.getY()+1]);
		assertEquals("Devrait etre Contre Torpilleur", "Contre Torpilleur", ct.getNom());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()+1].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()][c2.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()+1][c2.getY()].getDispo());
		assertEquals("La liste devrait avoir les bonnes positions", list, ct.getlPosition());
		assertEquals("La liste devrait avoir les bonnes positions", list2, ct2.getlPosition());
	}
	
	/**
	 * Test de la classe SousMarin (les tests Constructeurs
	 * testent aussi les méthodes addCase et addMultipleCase
	 * car ils font appel à ces méthodes)
	 */
	
	@org.junit.Test(expected = BateauException.class)
	public void testConstructeurSousMarin1() throws BateauException, CaseException, GrilleException {
		Grille g = new Grille(150, 200);
		Case c = new Case(111,1);
		Case c2 = new Case(1243, 123);
		SousMarin sm = new SousMarin(g, true, c);
		SousMarin sm2 = new SousMarin(g, true, c2);
	}
	
	@org.junit.Test
	public void testConstructeurSousMarin2() throws BateauException, CaseException, GrilleException {
		Grille g = new Grille(150, 200);
		Case c = new Case(111,1);
		Case c2 = new Case(70,50);
		SousMarin sm = new SousMarin(g, true, c);
		SousMarin sm2 = new SousMarin(g, false, c2);
		ArrayList<Case> list = new ArrayList<Case>();
		ArrayList<Case> list2 = new ArrayList<Case>();
		list2.add(g.gettCases()[c2.getX()][c2.getY()]);
		list2.add(g.gettCases()[c2.getX()+1][c2.getY()]);
		list2.add(g.gettCases()[c2.getX()+2][c2.getY()]);
		list.add(g.gettCases()[c.getX()][c.getY()]);
		list.add(g.gettCases()[c.getX()][c.getY()+1]);
		list.add(g.gettCases()[c.getX()][c.getY()+2]);
		assertEquals("Devrait etre Sous Marin", "Sous Marin", sm.getNom());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()+1].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()+2].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()][c2.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()+1][c2.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()+2][c2.getY()].getDispo());
		assertEquals("La liste devrait avoir les bonnes positions", list, sm.getlPosition());
		assertEquals("La liste devrait avoir les bonnes positions", list2, sm2.getlPosition());
	}
	
	/**
	 * Test de la classe Croiseur (les tests Constructeurs
	 * testent aussi les méthodes addCase et addMultipleCase
	 * car ils font appel à ces méthodes)
	 */
	
	@org.junit.Test(expected = BateauException.class)
	public void testConstructeurCroiseur1() throws BateauException, CaseException, GrilleException {
		Grille g = new Grille(150, 200);
		Case c = new Case(111,1);
		Case c2 = new Case(1243, 123);
		Croiseur cr = new Croiseur(g, true, c);
		Croiseur cr2 = new Croiseur(g, true, c2);
	}
	
	@org.junit.Test
	public void testConstructeurCroiseur2() throws BateauException, CaseException, GrilleException {
		Grille g = new Grille(150, 200);
		Case c = new Case(111,1);
		Case c2 = new Case(70,50);
		Croiseur cr = new Croiseur(g, true, c);
		Croiseur cr2 = new Croiseur(g, false, c2);
		ArrayList<Case> list = new ArrayList<Case>();
		ArrayList<Case> list2 = new ArrayList<Case>();
		list2.add(g.gettCases()[c2.getX()][c2.getY()]);
		list2.add(g.gettCases()[c2.getX()+1][c2.getY()]);
		list2.add(g.gettCases()[c2.getX()+2][c2.getY()]);
		list2.add(g.gettCases()[c2.getX()+3][c2.getY()]);
		list.add(g.gettCases()[c.getX()][c.getY()]);
		list.add(g.gettCases()[c.getX()][c.getY()+1]);
		list.add(g.gettCases()[c.getX()][c.getY()+2]);
		list.add(g.gettCases()[c.getX()][c.getY()+3]);
		assertEquals("Devrait etre Croiseur", "Croiseur", cr.getNom());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()+1].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()+2].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()+3].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()][c2.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()+1][c2.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()+2][c2.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()+3][c2.getY()].getDispo());
		assertEquals("La liste devrait avoir les bonnes positions", list, cr.getlPosition());
		assertEquals("La liste devrait avoir les bonnes positions", list2, cr2.getlPosition());
	}	
	
	/**
	 * Test de la classe PorteAvions (les tests Constructeurs
	 * testent aussi les méthodes addCase et addMultipleCase
	 * car ils font appel à ces méthodes)
	 */
	
	@org.junit.Test(expected = BateauException.class)
	public void testConstructeurPorteAvions1() throws BateauException, CaseException, GrilleException {
		Grille g = new Grille(150, 200);
		Case c = new Case(111,1);
		Case c2 = new Case(1243, 123);
		PorteAvions pa = new PorteAvions(g, true, c);
		PorteAvions pa2 = new PorteAvions(g, true, c2);
	}
	
	@org.junit.Test
	public void testConstructeurPorteAvions2() throws BateauException, CaseException, GrilleException {
		Grille g = new Grille(150, 200);
		Case c = new Case(111,1);
		Case c2 = new Case(70,50);
		PorteAvions pa = new PorteAvions(g, true, c);
		PorteAvions pa2 = new PorteAvions(g, false, c2);
		ArrayList<Case> list = new ArrayList<Case>();
		ArrayList<Case> list2 = new ArrayList<Case>();
		list2.add(g.gettCases()[c2.getX()][c2.getY()]);
		list2.add(g.gettCases()[c2.getX()+1][c2.getY()]);
		list2.add(g.gettCases()[c2.getX()+2][c2.getY()]);
		list2.add(g.gettCases()[c2.getX()+3][c2.getY()]);
		list2.add(g.gettCases()[c2.getX()+4][c2.getY()]);
		list.add(g.gettCases()[c.getX()][c.getY()]);
		list.add(g.gettCases()[c.getX()][c.getY()+1]);
		list.add(g.gettCases()[c.getX()][c.getY()+2]);
		list.add(g.gettCases()[c.getX()][c.getY()+3]);
		list.add(g.gettCases()[c.getX()][c.getY()+4]);
		assertEquals("Devrait etre Porte avions", "Porte avions", pa.getNom());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()+1].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()+2].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()+3].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c.getX()][c.getY()+4].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()][c2.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()+1][c2.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()+2][c2.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()+3][c2.getY()].getDispo());
		assertEquals("La case devrait ne pas être disponible", false, g.gettCases()[c2.getX()+4][c2.getY()].getDispo());
		assertEquals("La liste devrait avoir les bonnes positions", list, pa.getlPosition());
		assertEquals("La liste devrait avoir les bonnes positions", list2, pa2.getlPosition());
	}
}

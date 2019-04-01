import static org.junit.Assert.*;

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
	
}

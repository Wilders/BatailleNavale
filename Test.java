import static org.junit.Assert.*;

public class Test {

	/**
	 * Tests de la classe Case
	 */
	
	@org.junit.Test(expected = CaseException.class)
	public void testConstructeur1() throws CaseException {
		new Case(-1,-15);
	}

	@org.junit.Test
	public void testConstructeur2() throws CaseException {
		Case c = new Case(0, 7);
		assertEquals("Devrait �tre 0", 0, c.getX());
		assertEquals("Devrait �tre 7", 7, c.getY());
		assertEquals("Devrait �tre true", true, c.getDispo());
		assertEquals("Devrait �tre false", false, c.getTouchee());
	}
	
	@org.junit.Test
	public void testSets() throws CaseException {
		Case c = new Case(12, 5);
		c.setDispo(false);
		c.setTouchee(true);
		assertEquals("Devrait �tre 12", 12, c.getX());
		assertEquals("Devrait �tre 5", 5, c.getY());
		assertEquals("Devrait �tre false", false, c.getDispo());
		assertEquals("Devrait �tre true", true, c.getTouchee());
	}
	
	@org.junit.Test
	public void testToString() throws CaseException {
		Case c = new Case(180, 0);
		assertEquals("Devrait �tre -", "-", c.toString());
		c.setDispo(false);
		assertEquals("Devrait �tre O", "O", c.toString());
		c.setTouchee(true);
		assertEquals("Devrait �tre X", "X", c.toString());
		c.setDispo(true);
		assertEquals("Devrait �tre *", "*", c.toString());
	}
}

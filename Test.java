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
		assertEquals("Devrait être 0", 0, c.getX());
		assertEquals("Devrait être 7", 7, c.getY());
		assertEquals("Devrait être true", true, c.getDispo());
		assertEquals("Devrait être false", false, c.getTouchee());
	}
	
	@org.junit.Test
	public void testSets() throws CaseException {
		Case c = new Case(12, 5);
		c.setDispo(false);
		c.setTouchee(true);
		assertEquals("Devrait être 12", 12, c.getX());
		assertEquals("Devrait être 5", 5, c.getY());
		assertEquals("Devrait être false", false, c.getDispo());
		assertEquals("Devrait être true", true, c.getTouchee());
	}
	
	@org.junit.Test
	public void testToString() throws CaseException {
		Case c = new Case(180, 0);
		assertEquals("Devrait être -", "-", c.toString());
		c.setDispo(false);
		assertEquals("Devrait être O", "O", c.toString());
		c.setTouchee(true);
		assertEquals("Devrait être X", "X", c.toString());
		c.setDispo(true);
		assertEquals("Devrait être *", "*", c.toString());
	}
}

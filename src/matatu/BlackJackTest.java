package matatu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BlackJackTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMain() {
		BlackJack.calculateOdds();
	}

	@Test
	public void testCalculateOdds() {
		    assertEquals("tokens", BlackJack.calculateOdds());
		    BlackJack.getBetAmount();
		    BlackJack.playBlackJack();
	}

	@Test
	public void testGetBettAmount() {
		assertEquals("amount", BlackJack.getBetAmount());
	}

	@Test
	public void testPlayBlackJack() {
		assertTrue("win", true);
		assertFalse("win", false);
	}

	@Test
	public void testBlackJackWinner() {
		assertTrue("winner", true);
		assertFalse("winner", false);
	}

}

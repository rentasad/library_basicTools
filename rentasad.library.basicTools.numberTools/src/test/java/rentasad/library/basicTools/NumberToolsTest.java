package rentasad.library.basicTools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumberToolsTest
{


	@Test
	public void testGetLeadingZero() throws Exception
	{
		assertEquals(NumberTools.getLeadingZero(2), "02");
	}

}

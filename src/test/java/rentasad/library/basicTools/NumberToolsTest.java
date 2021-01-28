package rentasad.library.basicTools;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberToolsTest
{


	@org.junit.jupiter.api.Test
	public void testGetLeadingZero() throws Exception
	{
		assertEquals(NumberTools.getLeadingZero(2), "02");
	}

}

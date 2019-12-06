package org.gustini.library.basicTools;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NumberToolsTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testGetLeadingZero() throws Exception
	{
		assertEquals(NumberTools.getLeadingZero(2), "02");
	}

}

package utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParserTest
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
	public void testParseUsers() throws Exception
	{
		Parser parser = new Parser();
		assertEquals(5, parser.getUsers().size());
	}

}

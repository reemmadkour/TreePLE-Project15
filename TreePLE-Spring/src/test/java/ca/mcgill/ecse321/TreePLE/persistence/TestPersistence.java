package ca.mcgill.ecse321.TreePLE.persistence;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Tree;
import ca.mcgill.ecse321.TreePLE.model.TreeManager;


public class TestPersistence {

	private TreeManager tm;
	
	@Before
	public void setUp() throws Exception {
		tm = new TreeManager();
		Municipality municipality = new Municipality("lel");
		// create trees
		Tree tree1 = new Tree(12, 23, 34, 34, municipality);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

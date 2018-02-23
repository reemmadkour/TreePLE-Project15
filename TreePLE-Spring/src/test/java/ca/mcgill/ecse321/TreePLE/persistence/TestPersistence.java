package ca.mcgill.ecse321.TreePLE.persistence;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Tree;
import ca.mcgill.ecse321.TreePLE.model.TreeManager;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;



public class TestPersistence {

	private TreeManager tm;
	
	@Before
	public void setUp() throws Exception {
		tm = new TreeManager();
		
		// create trees
		Municipality municipality1 = new Municipality("Westmount");
		Municipality municipality2 = new Municipality("Outremont");
		
		
		Tree tree1 = new Tree(12, 23, 34, 34, municipality1);
		Tree tree2 = new Tree(34, 24, 26, 34, municipality2);
		
		// manage trees
		tm.addTree(tree1);
		tm.addTree(tree2);
	}

	@After
	public void tearDown() throws Exception {
		tm.delete();
	}

	@Test
	public void test() {
		// initialize model file
	    PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
	    // save model that is loaded during test setup
	    if (!PersistenceXStream.saveToXMLwithXStream(tm))
	        fail("Could not save file.");

	    // clear the model in memory
	    tm.delete();
	    assertEquals(0, tm.getTrees().size());

	    // load model
	    tm = (TreeManager) PersistenceXStream.loadFromXMLwithXStream();
	    if (tm == null)
	        fail("Could not load file.");

	    // check event
	    /**assertEquals(1, tm.getEvents().size());
	    assertEquals("Concert", tm.getEvent(0).getName());
	    Calendar c = Calendar.getInstance();
	    c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
	    Date eventDate = new Date(c.getTimeInMillis());
	    Time startTime = new Time(c.getTimeInMillis());
	    c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
	    Time endTime = new Time(c.getTimeInMillis());
	    assertEquals(eventDate.toString(), tm.getEvent(0).getEventDate().toString());
	    assertEquals(startTime.toString(), tm.getEvent(0).getStartTime().toString());
	    assertEquals(endTime.toString(), tm.getEvent(0).getEndTime().toString());
	    */
	}
}



package ca.mcgill.ecse321.TreePLE.service;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Tree;
import ca.mcgill.ecse321.TreePLE.model.Tree.LandType;
import ca.mcgill.ecse321.TreePLE.model.Tree.TreeSpecies;
import ca.mcgill.ecse321.TreePLE.model.TreeManager;
import ca.mcgill.ecse321.TreePLE.model.Status.TreeState;
import ca.mcgill.ecse321.TreePLE.service.TreePLETreeService;
import ca.mcgill.ecse321.TreePLE.service.InvalidInputException;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ca.mcgill.ecse321.TreePLE.persistence.PersistenceXStream;


public class TestTreePLEService {

	private TreeManager tm;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		tm = new TreeManager();
	}

	@After
	public void tearDown() throws Exception {
		tm.delete();
	}

	@Test
	public void testPlantTree() {
		assertEquals(0,tm.getTrees().size());
		
		double height = 10;
		double diameter = 12;
		double longitude = 23;
		double latitude = 24;
		
		TreeSpecies species = TreeSpecies.Willow;
		LandType landtype = LandType.Institutional;
		//TreePLETreeService t = new TreePLETreeService(tm);
		
		//NOT WORKING PLEASE FIND DE WAY -- 
	
			//t.plantTree(landtype, species, height, diameter, longitude,latitude,municipality);
		
		
		
	  
		
	}

}

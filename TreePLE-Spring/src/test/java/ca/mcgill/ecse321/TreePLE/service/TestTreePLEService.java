package ca.mcgill.ecse321.TreePLE.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;

import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Municipality.MunicipalityName;
import ca.mcgill.ecse321.TreePLE.model.Status.TreeState;
import ca.mcgill.ecse321.TreePLE.model.Tree.LandType;
import ca.mcgill.ecse321.TreePLE.model.Tree.TreeSpecies;
import ca.mcgill.ecse321.TreePLE.model.TreeManager;
import ca.mcgill.ecse321.TreePLE.persistence.PersistenceXStream;
import ca.mcgill.ecse321.TreePLE.model.*;


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
	public void testcutDownTree() {
		double height = 10;
		double diameter = 12;
		double longitude = 23;
		double latitude = 24;
		TreeSpecies species = TreeSpecies.Willow;
		LandType landtype = LandType.Institutional;
		Municipality mun = new Municipality();
		mun.setMunicipalityName(MunicipalityName.Montreal);
		TreePLETreeService tree = new TreePLETreeService(tm);
		try {
			tree.plantTree(landtype, species, height, diameter, longitude, latitude, mun);
			tree.cutDownTree(tm.getTree(0));
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//check model in memory
		assertEquals(TreeState.Cut, tm.getTree(0).getCurrentStatus().getTreeState());
	}
	
	@Test
	public void testcutDownTreeNull() {
		TreePLETreeService tree = new TreePLETreeService(tm);
		String error = null;
		try {
			//tree.plantTree(landtype, species, height, diameter, longitude, latitude, mun);
			tree.cutDownTree(null);
		} catch (InvalidInputException e) {
			error = e.getMessage();
			}
		
		//check error
		assertEquals("Please fill in all missing information!", error);
		
		//check no change in memory
		assertEquals(0, tm.getTrees().size());
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
		TreePLETreeService tree = new TreePLETreeService(tm);
		Municipality mun = new Municipality();
		mun.setMunicipalityName(MunicipalityName.Montreal);
		try {
			tree.plantTree(landtype, species, height, diameter, longitude, latitude, mun);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		checkResultTree();
		
		tm = (TreeManager)
		PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultTree();
		
	}
	
	@Test
	public void testPlantTreeNull() {
		assertEquals(0,tm.getTrees().size());
		
		double height = 0;
		double diameter = 0;
		double longitude = 0;
		double latitude = 0;
		TreeSpecies species = null;
		LandType landtype = null;
		TreePLETreeService tree = new TreePLETreeService(tm);
		Municipality mun = new Municipality() ;
		mun.setMunicipalityName(null);
		String error = null;
		try {
			tree.plantTree(landtype, species, height, diameter, longitude, latitude, mun);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Missing information", error);
		
		//check no change in memory
		assertEquals(0, tm.getTrees().size());
		
	}
	
	@Test
	public void testFindAllTrees()
	{
	    assertEquals(0, tm.getTrees().size());

	    double height1 = 13;
		double diameter1 = 15;
		double longitude1 = 65;
		double latitude1 = 87;
		TreeSpecies species1 = TreeSpecies.Willow;
		LandType landtype1 = LandType.Institutional;
		Municipality mun1 = new Municipality() ;
		mun1.setMunicipalityName(MunicipalityName.Laval);
		
		double height2 = 12;
		double diameter2 = 19;
		double longitude2 = 76;
		double latitude2 = 54;
		TreeSpecies species2 = TreeSpecies.Willow;
		LandType landtype2 = LandType.Municipal;
		Municipality mun2 = new Municipality() ;
		mun2.setMunicipalityName(MunicipalityName.Montreal);
TreePLETreeService tree = new TreePLETreeService(tm);
	       
	    try {
				tree.plantTree(landtype1, species1, height1, diameter1, longitude1, latitude1, mun1);
				tree.plantTree(landtype2, species2, height2, diameter2, longitude2, latitude2, mun2);


	        } catch (InvalidInputException e) {
	            // Check that no error occured
	            fail();
	        }
	    

	    List<Tree> registeredTrees = tree.listAllTrees();

	    // check number of registered trees
	    assertEquals(2, registeredTrees.size());

	    // check each Tree
	    assertEquals(13, registeredTrees.get(0).getHeight(), 0);
		assertEquals(15, registeredTrees.get(0).getDiameter(), 0);
		assertEquals(65, registeredTrees.get(0).getLongitude(), 0);
		assertEquals(87, registeredTrees.get(0).getLatitude(), 0);
		assertEquals(LandType.Institutional, registeredTrees.get(0).getLandType());
		assertEquals(TreeSpecies.Willow, registeredTrees.get(0).getTreeSpecies());
		assertEquals(MunicipalityName.Laval, registeredTrees.get(0).getMunicipality().getMunicipalityName());
		assertEquals(12, registeredTrees.get(1).getHeight(), 0);
		assertEquals(19, registeredTrees.get(1).getDiameter(), 0);
		assertEquals(76, registeredTrees.get(1).getLongitude(), 0);
		assertEquals(54, registeredTrees.get(1).getLatitude(), 0);
		assertEquals(LandType.Municipal, registeredTrees.get(1).getLandType());
		assertEquals(TreeSpecies.Willow, registeredTrees.get(1).getTreeSpecies());
		assertEquals(MunicipalityName.Montreal, registeredTrees.get(1).getMunicipality().getMunicipalityName());
	}
	
	

	private void checkResultTree() {
		assertEquals(1, tm.getTrees().size());
		assertEquals(12, tm.getTree(0).getDiameter(), 0);
		assertEquals(10, tm.getTree(0).getHeight(), 0);
		assertEquals(23, tm.getTree(0).getLongitude(), 0);
		assertEquals(24, tm.getTree(0).getLatitude(), 0);
		assertEquals(LandType.Institutional, tm.getTree(0).getLandType());
		assertEquals(TreeSpecies.Willow, tm.getTree(0).getTreeSpecies());
		assertEquals(TreeState.Planted, tm.getTree(0).getCurrentStatus().getTreeState());
		assertEquals(MunicipalityName.Montreal, tm.getTree(0).getMunicipality().getMunicipalityName());
	}

}

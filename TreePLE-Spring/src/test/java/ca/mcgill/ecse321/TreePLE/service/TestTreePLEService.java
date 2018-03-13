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
	
	@Test
	public void testGetTreeByMunicipality() {
		assertEquals(0, tm.getTrees().size());
		
		
	    double height1 = 15;
		double diameter1 = 19;
		double longitude1 = 75;
		double latitude1 = 77;
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
		
		double height3 = 16;
		double diameter3 = 12;
		double longitude3 = 70;
		double latitude3 = 54;
		TreeSpecies species3 = TreeSpecies.Willow;
		LandType landtype3 = LandType.Municipal;
		Municipality mun3 = new Municipality() ;
		mun3.setMunicipalityName(MunicipalityName.Montreal);

	TreePLETreeService tree = new TreePLETreeService(tm);
	
    try {
				tree.plantTree(landtype1, species1, height1, diameter1, longitude1, latitude1, mun1);
				tree.plantTree(landtype2, species2, height2, diameter2, longitude2, latitude2, mun2);
				tree.plantTree(landtype3, species3, height3, diameter3, longitude3, latitude3, mun3);


	        } catch (InvalidInputException e) {
	            // Check that no error occured
	            fail();
	        }

   List<Tree> allTrees = tree.listAllTrees();
    
    // check number of registered participants
    assertEquals(3, allTrees.size());
    
    List<Tree> registeredTreesByMunicipality = tree.getTreeByMunicipality(mun1);
    List<Tree> registeredTreesByMunicipality2 = tree.getTreeByMunicipality(mun2);
    List<Tree> registeredTreesByMunicipality3 = tree.getTreeByMunicipality(mun3);
	//tree 1
    assertEquals(15, registeredTreesByMunicipality.get(0).getHeight(), 0);
	assertEquals(19, registeredTreesByMunicipality.get(0).getDiameter(), 0);
	assertEquals(75, registeredTreesByMunicipality.get(0).getLongitude(), 0);
	assertEquals(77, registeredTreesByMunicipality.get(0).getLatitude(), 0);
	assertEquals(LandType.Institutional, registeredTreesByMunicipality.get(0).getLandType());
	assertEquals(TreeSpecies.Willow, registeredTreesByMunicipality.get(0).getTreeSpecies());
	assertEquals(MunicipalityName.Laval, registeredTreesByMunicipality.get(0).getMunicipality().getMunicipalityName());
	//tree 2
    assertEquals(12, registeredTreesByMunicipality2.get(0).getHeight(), 0);
	assertEquals(19, registeredTreesByMunicipality2.get(0).getDiameter(), 0);
	assertEquals(76, registeredTreesByMunicipality2.get(0).getLongitude(), 0);
	assertEquals(54, registeredTreesByMunicipality2.get(0).getLatitude(), 0);
	assertEquals(LandType.Municipal, registeredTreesByMunicipality2.get(0).getLandType());
	assertEquals(TreeSpecies.Willow, registeredTreesByMunicipality2.get(0).getTreeSpecies());
	assertEquals(MunicipalityName.Montreal, registeredTreesByMunicipality2.get(0).getMunicipality().getMunicipalityName());
	//tree 3
	assertEquals(16, registeredTreesByMunicipality3.get(0).getHeight(), 0);
	assertEquals(12, registeredTreesByMunicipality3.get(0).getDiameter(), 0);
	assertEquals(70, registeredTreesByMunicipality3.get(0).getLongitude(), 0);
	assertEquals(54, registeredTreesByMunicipality3.get(0).getLatitude(), 0);
	assertEquals(LandType.Municipal, registeredTreesByMunicipality3.get(0).getLandType());
	assertEquals(TreeSpecies.Willow, registeredTreesByMunicipality3.get(0).getTreeSpecies());
	assertEquals(MunicipalityName.Montreal, registeredTreesByMunicipality3.get(0).getMunicipality().getMunicipalityName());
	
}
	//test updated and tested 
	//but having issues with pushing
	
	@Test
	public void testGetTreeBySpecies() throws InvalidInputException {
		
		assertEquals(0, tm.getTrees().size());
		
		    double height1 = 15;
			double diameter1 = 19;
			double longitude1 = 75;
			double latitude1 = 77;
			TreeSpecies species1 = TreeSpecies.Willow;
			LandType landtype1 = LandType.Institutional;
			Municipality mun1 = new Municipality() ;
			mun1.setMunicipalityName(MunicipalityName.Laval);
			
			double height2 = 15;
			double diameter2 = 19;
			double longitude2 = 76;
			double latitude2 = 54;
			TreeSpecies species2 = TreeSpecies.Willow;
			LandType landtype2 = LandType.Municipal;
			Municipality mun2 = new Municipality() ;
			mun2.setMunicipalityName(MunicipalityName.Montreal);
			
			double height3 = 22;
			double diameter3 = 12;
			double longitude3 = 70;
			double latitude3 = 54;
			TreeSpecies species3 = TreeSpecies.Willow;
			LandType landtype3 = LandType.Municipal;
			Municipality mun3 = new Municipality() ;
			mun3.setMunicipalityName(MunicipalityName.Montreal);

		TreePLETreeService tree = new TreePLETreeService(tm);
		
	    try {
					tree.plantTree(landtype1, species1, height1, diameter1, longitude1, latitude1, mun1);
					tree.plantTree(landtype2, species2, height2, diameter2, longitude2, latitude2, mun2);
					tree.plantTree(landtype3, species3, height3, diameter3, longitude3, latitude3, mun3);


		        } catch (InvalidInputException e) {
		            // Check that no error occured
		            fail();
		        }
		 
	    
	   // List<Tree> registeredTreesBySpecies = null;
	    
	    // Check that initially treesBySpecies is null
	    

	    List<Tree> allTrees = tree.listAllTrees();
	    
	    // check number of registered participants
	    assertEquals(3, allTrees.size());
	    
	    List<Tree> registeredTreesBySpecie = tree.getTreeBySpecies(species1);
	    //List<Tree> registeredTreesBySpecie2 = tree.getTreeBySpecies(species2);
	    //List<Tree> registeredTreesBySpecie3 = tree.getTreeBySpecies(species3);
	 
	
		//tree 1
	    assertEquals(15, registeredTreesBySpecie.get(0).getHeight(), 0);
		assertEquals(19, registeredTreesBySpecie.get(0).getDiameter(), 0);
		assertEquals(75, registeredTreesBySpecie.get(0).getLongitude(), 0);
		assertEquals(77, registeredTreesBySpecie.get(0).getLatitude(), 0);
		assertEquals(LandType.Institutional, registeredTreesBySpecie.get(0).getLandType());
		assertEquals(TreeSpecies.Willow, registeredTreesBySpecie.get(0).getTreeSpecies());
		assertEquals(MunicipalityName.Laval, registeredTreesBySpecie.get(0).getMunicipality().getMunicipalityName());
		//tree 2
		assertEquals(15, registeredTreesBySpecie.get(1).getHeight(), 0);
		assertEquals(19, registeredTreesBySpecie.get(1).getDiameter(), 0);
		assertEquals(76, registeredTreesBySpecie.get(1).getLongitude(), 0);
		assertEquals(54, registeredTreesBySpecie.get(1).getLatitude(), 0);
		assertEquals(LandType.Municipal, registeredTreesBySpecie.get(1).getLandType());
		assertEquals(TreeSpecies.Willow, registeredTreesBySpecie.get(1).getTreeSpecies());
		assertEquals(MunicipalityName.Montreal, registeredTreesBySpecie.get(1).getMunicipality().getMunicipalityName());
		//tree 3
		assertEquals(22, registeredTreesBySpecie.get(2).getHeight(), 0);
		assertEquals(12, registeredTreesBySpecie.get(2).getDiameter(), 0);
		assertEquals(70, registeredTreesBySpecie.get(2).getLongitude(), 0);
		assertEquals(54, registeredTreesBySpecie.get(2).getLatitude(), 0);
		assertEquals(LandType.Municipal, registeredTreesBySpecie.get(2).getLandType());
		assertEquals(TreeSpecies.Willow, registeredTreesBySpecie.get(2).getTreeSpecies());
		assertEquals(MunicipalityName.Montreal, registeredTreesBySpecie.get(2).getMunicipality().getMunicipalityName());
		
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

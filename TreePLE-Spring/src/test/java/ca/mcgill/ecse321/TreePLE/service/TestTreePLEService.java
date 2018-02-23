package ca.mcgill.ecse321.TreePLE.service;
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Municipality.MunicipalityName;
import ca.mcgill.ecse321.TreePLE.model.Status.TreeState;
import ca.mcgill.ecse321.TreePLE.model.Tree.LandType;
import ca.mcgill.ecse321.TreePLE.model.Tree.TreeSpecies;
import ca.mcgill.ecse321.TreePLE.model.TreeManager;
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
		TreePLETreeService tree = new TreePLETreeService(tm);
		Municipality mun = new Municipality();
		mun.setMunicipalityName(MunicipalityName.Montreal);
		try {
			tree.plantTree(landtype, species, height, diameter, longitude, latitude, mun);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//check model in memory
		assertEquals(1, tm.getTrees().size());
		assertEquals(12, tm.getTree(0).getDiameter(), 0);
		assertEquals(10, tm.getTree(0).getHeight(), 0);
		assertEquals(23, tm.getTree(0).getLongitude(), 0);
		assertEquals(24, tm.getTree(0).getLatitude(), 0);
		assertEquals(LandType.Institutional, tm.getTree(0).getLandType());
		assertEquals(TreeSpecies.Willow, tm.getTree(0).getTreeSpecies());
		assertEquals(TreeState.Planted, tm.getTree(0).getCurrentStatus().getTreeState());
		assertEquals(MunicipalityName.Montreal, tm.getTree(0).getMunicipality().getMunicipalityName());
		
		tm = (TreeManager)
		PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
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

package ca.mcgill.ecse321.TreePLE.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Municipality.MunicipalityName;
import ca.mcgill.ecse321.TreePLE.model.Person;


import ca.mcgill.ecse321.TreePLE.model.Status;

import ca.mcgill.ecse321.TreePLE.model.Status.TreeState;
import ca.mcgill.ecse321.TreePLE.model.Tree;
import ca.mcgill.ecse321.TreePLE.model.Tree.LandType;
import ca.mcgill.ecse321.TreePLE.model.Tree.TreeSpecies;
import ca.mcgill.ecse321.TreePLE.model.TreeManager;
import ca.mcgill.ecse321.TreePLE.persistence.PersistenceXStream;

public class TestTreePLEService {

	private TreeManager tm;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output" + File.separator + "data.xml");

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
	public void testMarkTreeToBeCutRegularCase(){
	double height = 10;
	double diameter = 12;
	double longitude = 23;
	double latitude = 24;
	String name = "Jon";
	TreeSpecies species = TreeSpecies.Willow;
	LandType landtype = LandType.Institutional;
	MunicipalityName mun= MunicipalityName.Montreal;
	Municipality m= new Municipality();
	m.setMunicipalityName(mun);
	Tree t= new Tree(height,diameter,longitude,latitude,m);
	t.setLandType(landtype);
	t.setTreeSpecies(species);
	Person user= new Person("Jon");
	tm.addPerson(user);
	tm.addTree(t);
	TreePLETreeService tree = new TreePLETreeService(tm);
	try{
		tree.MarkTreeToBeCutDown(t, name);}
	catch(InvalidInputException e){
		e.printStackTrace();
	}
	
	assertEquals(TreeState.ToBeCut, tm.getTree(0).getCurrentStatus().getTreeState());
	assertEquals("Jon", tm.getTree(0).getCurrentStatus().getPerson().getName());
}

@Test
public void testMarkTreeToBeCutNewUser(){
double height = 10;
double diameter = 12;
double longitude = 23;
double latitude = 24;
String name = "John";
TreeSpecies species = TreeSpecies.Willow;
LandType landtype = LandType.Institutional;
MunicipalityName mun= MunicipalityName.Montreal;
Municipality m= new Municipality();
m.setMunicipalityName(mun);
Tree t= new Tree(height,diameter,longitude,latitude,m);
t.setLandType(landtype);
t.setTreeSpecies(species);
//Person user= new Person("Jon");
//tm.addPerson(user);

TreePLETreeService tree = new TreePLETreeService(tm);
tm.addTree(t);
try{
	tree.MarkTreeToBeCutDown(t, name);}
catch(InvalidInputException e){
	e.printStackTrace();
}

assertEquals(TreeState.ToBeCut, tm.getTree(0).getCurrentStatus().getTreeState());

assertEquals(1, tm.getPerson().size());

}


//reem just did

public void testMarkTreeToBeCutEmptyUser(){
	double height = 10;
	double diameter = 12;
	double longitude = 23;
	double latitude = 24;
	String name = "   ";
	TreeSpecies species = TreeSpecies.Willow;
	LandType landtype = LandType.Institutional;
	MunicipalityName mun= MunicipalityName.Montreal;
	Municipality m= new Municipality();
	m.setMunicipalityName(mun);
	Tree t= new Tree(height,diameter,longitude,latitude,m);
	t.setLandType(landtype);
	t.setTreeSpecies(species);
	//Person user= null;
	//tm.addPerson(user);
	tm.addTree(t);
	String er= null;
	TreePLETreeService tree = new TreePLETreeService(tm);
	try{
		tree.MarkTreeToBeCutDown(t,name );}
	catch(InvalidInputException e){
		er=e.getMessage();
	}
	
	assertEquals("Please fill in all missing information!", er);

}



//reem just did

@Test
public void testMarkTreeToBeCutNullTree(){

	Tree t= null;
	String name= "jay";
	Person user= new Person("jay");
	tm.addPerson(user);
	//tm.addTree(t);
	String er= null;
	TreePLETreeService tree = new TreePLETreeService(tm);
	try{
		tree.MarkTreeToBeCutDown(t,name );}
	catch(InvalidInputException e){
		er=e.getMessage();
	}

assertEquals("Please fill in all missing information!", er);
}

@Test
public void testMarkTreeToBeCutNullUser(){
double height = 10;
double diameter = 12;
double longitude = 23;
double latitude = 24;
String name = null;
TreeSpecies species = TreeSpecies.Willow;
LandType landtype = LandType.Institutional;
MunicipalityName mun= MunicipalityName.Montreal;
Municipality m= new Municipality();
m.setMunicipalityName(mun);
Tree t= new Tree(height,diameter,longitude,latitude,m);
t.setLandType(landtype);
t.setTreeSpecies(species);
Person user= null;
tm.addPerson(user);
tm.addTree(t);
String er= null;
TreePLETreeService tree = new TreePLETreeService(tm);
try{
	tree.MarkTreeToBeCutDown(t,name );}
catch(InvalidInputException e){
	er=e.getMessage();
}

assertEquals("Please fill in all missing information!", er);
}


//reem just did2

@Test
public void testMarkTreeAsDiseasedEmptyUser(){
double height = 10;
double diameter = 12;
double longitude = 23;
double latitude = 24;
String name = "   ";
TreeSpecies species = TreeSpecies.Willow;
LandType landtype = LandType.Institutional;
MunicipalityName mun= MunicipalityName.Montreal;
Municipality m= new Municipality();
m.setMunicipalityName(mun);
Tree t= new Tree(height,diameter,longitude,latitude,m);
t.setLandType(landtype);
t.setTreeSpecies(species);
//Person user= null;
//tm.addPerson(user);
tm.addTree(t);
String er= null;
TreePLETreeService tree = new TreePLETreeService(tm);
try{
	tree.MarkTreeAsDiseased(t,name );}
catch(InvalidInputException e){
	er=e.getMessage();
}

assertEquals("Please fill in all missing information!", er);
}



//reem just did

@Test
public void testMarkTreeAsDiseasedNullTree(){

Tree t= null;
String name= "jay";
Person user= new Person("jay");
tm.addPerson(user);
//tm.addTree(t);
String er= null;
TreePLETreeService tree = new TreePLETreeService(tm);
try{
	tree.MarkTreeAsDiseased(t,name );}
catch(InvalidInputException e){
	er=e.getMessage();
}
}
	
	//reem just did 
@Test
	public void testMarkTreeAsDiseasedRegularCase(){
	double height = 10;
	double diameter = 12;
	double longitude = 23;
	double latitude = 24;
	String name = "Jon";
	TreeSpecies species = TreeSpecies.Willow;
	LandType landtype = LandType.Institutional;
	MunicipalityName mun= MunicipalityName.Montreal;
	Municipality m= new Municipality();
	m.setMunicipalityName(mun);
	Tree t= new Tree(height,diameter,longitude,latitude,m);
	t.setLandType(landtype);
	t.setTreeSpecies(species);
	Person user= new Person("Jon");
	tm.addPerson(user);
	tm.addTree(t);
	TreePLETreeService tree = new TreePLETreeService(tm);
	try{
		tree.MarkTreeAsDiseased(t, name);}
	catch(InvalidInputException e){
		e.printStackTrace();
	}
	
	assertEquals(TreeState.Diseased, tm.getTree(0).getCurrentStatus().getTreeState());
	assertEquals("Jon", tm.getTree(0).getCurrentStatus().getPerson().getName());
}

@Test
public void testMarkTreeAsDiseasedNewUser(){
double height = 10;
double diameter = 12;
double longitude = 23;
double latitude = 24;
String name = "John";
TreeSpecies species = TreeSpecies.Willow;
LandType landtype = LandType.Institutional;
MunicipalityName mun= MunicipalityName.Montreal;
Municipality m= new Municipality();
m.setMunicipalityName(mun);
Tree t= new Tree(height,diameter,longitude,latitude,m);
t.setLandType(landtype);
t.setTreeSpecies(species);
//Person user= new Person("Jon");
//tm.addPerson(user);

TreePLETreeService tree = new TreePLETreeService(tm);
tm.addTree(t);
try{
	tree.MarkTreeAsDiseased(t, name);}
catch(InvalidInputException e){
	e.printStackTrace();
}

assertEquals(TreeState.Diseased, tm.getTree(0).getCurrentStatus().getTreeState());

assertEquals(1, tm.getPerson().size());

}


//reem just did

@Test
public void testMarkTreeAsDiseasedNullUser(){
double height = 10;
double diameter = 12;
double longitude = 23;
double latitude = 24;
String name = null;
TreeSpecies species = TreeSpecies.Willow;
LandType landtype = LandType.Institutional;
MunicipalityName mun= MunicipalityName.Montreal;
Municipality m= new Municipality();
m.setMunicipalityName(mun);
Tree t= new Tree(height,diameter,longitude,latitude,m);
t.setLandType(landtype);
t.setTreeSpecies(species);
Person user= null;
tm.addPerson(user);
tm.addTree(t);
String er= null;
TreePLETreeService tree = new TreePLETreeService(tm);
try{
	tree.MarkTreeAsDiseased(t,name );}
catch(InvalidInputException e){
	er=e.getMessage();
}

assertEquals("Please fill in all missing information!", er);
}


//reem just did



	@Test
	public void testcutDownTree() {
		double height = 10;
		double diameter = 12;
		double longitude = 23;
		double latitude = 24;
		String name = "Jon";
		TreeSpecies species = TreeSpecies.Willow;
		LandType landtype = LandType.Institutional;
		MunicipalityName mun = MunicipalityName.Montreal;
		TreePLETreeService tree = new TreePLETreeService(tm);
		try {
			tree.plantTree(landtype, species, height, diameter, longitude, latitude, mun, name);
			tree.cutDownTree(tm.getTree(0), name);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		// check model in memory
		assertEquals(TreeState.Cut, tm.getTree(0).getCurrentStatus().getTreeState());
	}

	@Test
	public void testcutDownTreeNull() {
		TreePLETreeService tree = new TreePLETreeService(tm);
		String error = null;
		String name = null;
		try {
			tree.cutDownTree(null, name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Please fill in all missing information!", error);

		// check no change in memory
		assertEquals(0, tm.getTrees().size());
	}

	@Test
	public void testPlantTree() {
		assertEquals(0, tm.getTrees().size());

		double height = 10;
		double diameter = 12;
		double longitude = 23;
		double latitude = 24;
		String name = "Jon";
		TreeSpecies species = TreeSpecies.Willow;
		LandType landtype = LandType.Institutional;
		TreePLETreeService tree = new TreePLETreeService(tm);
		MunicipalityName mun = MunicipalityName.Montreal;
		try {
			tree.plantTree(landtype, species, height, diameter, longitude, latitude, mun, name);
		} catch (InvalidInputException e) {
			fail();
		}

		TreeManager tm1 = tm;
		checkResultTree(landtype, species, height, diameter, longitude, latitude, mun, name, tm1);

		TreeManager tm2 = (TreeManager) PersistenceXStream.loadFromXMLwithXStream();
		checkResultTree(landtype, species, height, diameter, longitude, latitude, mun, name, tm2);

		tm2.delete();

	}

	@Test
	public void testPlantTreeNull() {
		assertEquals(0, tm.getTrees().size());

		double height = 0;
		double diameter = 0;
		double longitude = 0;
		double latitude = 0;
		String name = "";
		TreeSpecies species = null;
		LandType landtype = null;
		TreePLETreeService tree = new TreePLETreeService(tm);
		MunicipalityName mun = null;
		String error = null;
		try {
			tree.plantTree(landtype, species, height, diameter, longitude, latitude, mun, name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Missing information", error);

		// check no change in memory
		assertEquals(0, tm.getTrees().size());

	}

	@Test
	public void testFindAllTrees() {
		assertEquals(0, tm.getTrees().size());

		double height1 = 13;
		double diameter1 = 15;
		double longitude1 = 65;
		double latitude1 = 87;
		String name1 = "Jon";
		TreeSpecies species1 = TreeSpecies.Willow;
		LandType landtype1 = LandType.Institutional;
		MunicipalityName mun1 = MunicipalityName.Laval;

		double height2 = 12;
		double diameter2 = 19;
		double longitude2 = 76;
		double latitude2 = 54;
		String name2 = "Jony";
		TreeSpecies species2 = TreeSpecies.Willow;
		LandType landtype2 = LandType.Municipal;
		MunicipalityName mun2 = MunicipalityName.Montreal;
		TreePLETreeService tree = new TreePLETreeService(tm);

		try {
			tree.plantTree(landtype1, species1, height1, diameter1, longitude1, latitude1, mun1, name1);
			tree.plantTree(landtype2, species2, height2, diameter2, longitude2, latitude2, mun2, name2);

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
	public void testgetTreeById() {
		assertEquals(0, tm.getTrees().size());
		double height1 = 13;
		double diameter1 = 15;
		double longitude1 = 65;
		double latitude1 = 87;
		String name1 = "Jon";
		TreeSpecies species1 = TreeSpecies.Willow;
		LandType landtype1 = LandType.Institutional;
		MunicipalityName mun1 = MunicipalityName.Montreal;

		double height2 = 12;
		double diameter2 = 19;
		double longitude2 = 76;
		double latitude2 = 54;
		String name2 = "Jony";
		TreeSpecies species2 = TreeSpecies.Willow;
		LandType landtype2 = LandType.Municipal;
		MunicipalityName mun2 = MunicipalityName.Montreal;

		String error = null;
		TreePLETreeService tree1 = new TreePLETreeService(tm);

		try {
			tree1.plantTree(landtype1, species1, height1, diameter1, longitude1, latitude1, mun1, name1);
			tree1.plantTree(landtype2, species2, height2, diameter2, longitude2, latitude2, mun2, name2);
			tree1.getTreeByID(0);
			tree1.getTreeByID(1);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		List<Tree> registeredTrees = tree1.listAllTrees();

		// check number of registered trees
		assertEquals(2, registeredTrees.size());

		assertEquals(5, registeredTrees.get(0).getTreeID());
		assertEquals(6, registeredTrees.get(1).getTreeID());
	}
	

	@Test
	public void testGetTreeByMunicipality() {
		assertEquals(0, tm.getTrees().size());

		double height1 = 15;
		double diameter1 = 19;
		double longitude1 = 75;
		double latitude1 = 77;
		String name1 = "Jon";
		TreeSpecies species1 = TreeSpecies.Willow;
		LandType landtype1 = LandType.Institutional;
		MunicipalityName mun1 = MunicipalityName.Laval;

		double height2 = 12;
		double diameter2 = 19;
		double longitude2 = 76;
		double latitude2 = 54;
		String name2 = "Jony";
		TreeSpecies species2 = TreeSpecies.Willow;
		LandType landtype2 = LandType.Municipal;
		MunicipalityName mun2 = MunicipalityName.Montreal;
		
		double height3 = 16;
		double diameter3 = 12;
		double longitude3 = 70;
		double latitude3 = 54;
		String name3 = "Jessy";
		TreeSpecies species3 = TreeSpecies.Willow;
		LandType landtype3 = LandType.Municipal;
		MunicipalityName mun3 = MunicipalityName.Montreal;
		TreePLETreeService tree = new TreePLETreeService(tm);

		try {
			tree.plantTree(landtype1, species1, height1, diameter1, longitude1, latitude1, mun1, name1);
			tree.plantTree(landtype2, species2, height2, diameter2, longitude2, latitude2, mun2, name2);
			tree.plantTree(landtype3, species3, height3, diameter3, longitude3, latitude3, mun3, name3);

		} catch (InvalidInputException e) {
			// Check that no error occurred
			fail();
		}

		List<Tree> allTrees = tree.listAllTrees();

		// check number of registered participants
		assertEquals(3, allTrees.size());

		List<Tree> registeredTreesByMunicipality = tree.getTreeByMunicipality(mun1);
		List<Tree> registeredTreesByMunicipality2 = tree.getTreeByMunicipality(mun2);
		List<Tree> registeredTreesByMunicipality3 = tree.getTreeByMunicipality(mun3);
		// tree 1
		assertEquals(15, registeredTreesByMunicipality.get(0).getHeight(), 0);
		assertEquals(19, registeredTreesByMunicipality.get(0).getDiameter(), 0);
		assertEquals(75, registeredTreesByMunicipality.get(0).getLongitude(), 0);
		assertEquals(77, registeredTreesByMunicipality.get(0).getLatitude(), 0);
		assertEquals(LandType.Institutional, registeredTreesByMunicipality.get(0).getLandType());
		assertEquals(TreeSpecies.Willow, registeredTreesByMunicipality.get(0).getTreeSpecies());
		assertEquals(MunicipalityName.Laval,
				registeredTreesByMunicipality.get(0).getMunicipality().getMunicipalityName());
		// tree 2
		assertEquals(12, registeredTreesByMunicipality2.get(0).getHeight(), 0);
		assertEquals(19, registeredTreesByMunicipality2.get(0).getDiameter(), 0);
		assertEquals(76, registeredTreesByMunicipality2.get(0).getLongitude(), 0);
		assertEquals(54, registeredTreesByMunicipality2.get(0).getLatitude(), 0);
		assertEquals(LandType.Municipal, registeredTreesByMunicipality2.get(0).getLandType());
		assertEquals(TreeSpecies.Willow, registeredTreesByMunicipality2.get(0).getTreeSpecies());
		assertEquals(MunicipalityName.Montreal,
				registeredTreesByMunicipality2.get(0).getMunicipality().getMunicipalityName());
		// tree 3
		assertEquals(16, registeredTreesByMunicipality3.get(0).getHeight(), 0);
		assertEquals(12, registeredTreesByMunicipality3.get(0).getDiameter(), 0);
		assertEquals(70, registeredTreesByMunicipality3.get(0).getLongitude(), 0);
		assertEquals(54, registeredTreesByMunicipality3.get(0).getLatitude(), 0);
		assertEquals(LandType.Municipal, registeredTreesByMunicipality3.get(0).getLandType());
		assertEquals(TreeSpecies.Willow, registeredTreesByMunicipality3.get(0).getTreeSpecies());
		assertEquals(MunicipalityName.Montreal,
				registeredTreesByMunicipality3.get(0).getMunicipality().getMunicipalityName());

	}
	
	

	// test updated and tested
	// testGetTreeBySpecies() works!!
	@Test
	public void testGetTreeBySpecies() throws InvalidInputException {

		assertEquals(0, tm.getTrees().size());

		double height1 = 15;
		double diameter1 = 19;
		double longitude1 = 75;
		double latitude1 = 77;
		String name1 = "Jon";
		TreeSpecies species1 = TreeSpecies.Willow;
		LandType landtype1 = LandType.Institutional;
		MunicipalityName mun1 = MunicipalityName.Laval;

		double height2 = 12;
		double diameter2 = 19;
		double longitude2 = 76;
		double latitude2 = 54;
		String name2 = "Jony";
		TreeSpecies species2 = TreeSpecies.Willow;
		LandType landtype2 = LandType.Municipal;
		MunicipalityName mun2 = MunicipalityName.Montreal;
		
		double height3 = 16;
		double diameter3 = 12;
		double longitude3 = 70;
		double latitude3 = 54;
		String name3 = "Jessy";
		TreeSpecies species3 = TreeSpecies.Willow;
		LandType landtype3 = LandType.Municipal;
		MunicipalityName mun3 = MunicipalityName.Montreal;

		TreePLETreeService tree = new TreePLETreeService(tm);

		try {
			tree.plantTree(landtype1, species1, height1, diameter1, longitude1, latitude1, mun1, name1);
			tree.plantTree(landtype2, species2, height2, diameter2, longitude2, latitude2, mun2, name2);
			tree.plantTree(landtype3, species3, height3, diameter3, longitude3, latitude3, mun3, name3);

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
		// List<Tree> registeredTreesBySpecie2 = tree.getTreeBySpecies(species2);
		// List<Tree> registeredTreesBySpecie3 = tree.getTreeBySpecies(species3);

		// tree 1
		assertEquals(15, registeredTreesBySpecie.get(0).getHeight(), 0);
		assertEquals(19, registeredTreesBySpecie.get(0).getDiameter(), 0);
		assertEquals(75, registeredTreesBySpecie.get(0).getLongitude(), 0);
		assertEquals(77, registeredTreesBySpecie.get(0).getLatitude(), 0);
		assertEquals(LandType.Institutional, registeredTreesBySpecie.get(0).getLandType());
		assertEquals(TreeSpecies.Willow, registeredTreesBySpecie.get(0).getTreeSpecies());
		assertEquals(MunicipalityName.Laval, registeredTreesBySpecie.get(0).getMunicipality().getMunicipalityName());
		// tree 2
		assertEquals(12, registeredTreesBySpecie.get(1).getHeight(), 0);
		assertEquals(19, registeredTreesBySpecie.get(1).getDiameter(), 0);
		assertEquals(76, registeredTreesBySpecie.get(1).getLongitude(), 0);
		assertEquals(54, registeredTreesBySpecie.get(1).getLatitude(), 0);
		assertEquals(LandType.Municipal, registeredTreesBySpecie.get(1).getLandType());
		assertEquals(TreeSpecies.Willow, registeredTreesBySpecie.get(1).getTreeSpecies());
		assertEquals(MunicipalityName.Montreal, registeredTreesBySpecie.get(1).getMunicipality().getMunicipalityName());
		// tree 3
		assertEquals(16, registeredTreesBySpecie.get(2).getHeight(), 0);
		assertEquals(12, registeredTreesBySpecie.get(2).getDiameter(), 0);
		assertEquals(70, registeredTreesBySpecie.get(2).getLongitude(), 0);
		assertEquals(54, registeredTreesBySpecie.get(2).getLatitude(), 0);
		assertEquals(LandType.Municipal, registeredTreesBySpecie.get(2).getLandType());
		assertEquals(TreeSpecies.Willow, registeredTreesBySpecie.get(2).getTreeSpecies());
		assertEquals(MunicipalityName.Montreal, registeredTreesBySpecie.get(2).getMunicipality().getMunicipalityName());

	}

	private void checkResultTree(LandType landtype, TreeSpecies species, double height, double diameter,
			double longitude, double latitude, MunicipalityName municipality, String userName, TreeManager tm) {
		assertEquals(1, tm.getTrees().size());
		assertEquals(diameter, tm.getTree(0).getDiameter(), 0);
		assertEquals(height, tm.getTree(0).getHeight(), 0);
		assertEquals(longitude, tm.getTree(0).getLongitude(), 0);
		assertEquals(latitude, tm.getTree(0).getLatitude(), 0);
		assertEquals(landtype, tm.getTree(0).getLandType());
		assertEquals(species, tm.getTree(0).getTreeSpecies());
		assertEquals(TreeState.Planted, tm.getTree(0).getCurrentStatus().getTreeState());
		assertEquals(municipality, tm.getTree(0).getMunicipality().getMunicipalityName());
		assertEquals(userName, tm.getTree(0).getCurrentStatus().getPerson().getName());
	}



	
	//test ran and works 
	//Assuming BioDiversity for all Planted
	@Test
	public void testCalculateBioDiversity() throws InvalidInputException {
		
		TreePLETreeService service = new TreePLETreeService(tm);


		double height1 = 15;
		double diameter1 = 19;
		double longitude1 = 75;
		double latitude1 = 77;
		Person person1 = new Person("John");
		TreeSpecies species1 = TreeSpecies.Willow;
		LandType landtype1 = LandType.Institutional;
		Date  date1 = new Date();
		Municipality mun1 = new Municipality();
		mun1.setMunicipalityName(MunicipalityName.Laval);
		Tree tree1 = new Tree(height1, diameter1, longitude1, latitude1, mun1);
		Status status1= new Status(date1, tree1, person1);
		status1.setTreeState(TreeState.Planted);
		tree1.addStatus(status1);
		tree1.setCurrentStatus(status1);
		tree1.setTreeSpecies(species1);

		
		double height2 = 12;
		double diameter2 = 19;
		double longitude2 = 76;
		double latitude2 = 54;
		Person person2 = new Person ("Jony");
		Date  date2 = new Date();
		TreeSpecies species2 = TreeSpecies.Willow;
		LandType landtype2 = LandType.Municipal;
		Municipality mun2 = new Municipality();
		mun2.setMunicipalityName(MunicipalityName.Laval);
		Tree tree2 = new Tree(height2, diameter2, longitude2, latitude2, mun2);
		Status status2= new Status(date2, tree2, person2);
		status2.setTreeState(TreeState.Planted);
		tree2.addStatus(status2);
		tree2.setCurrentStatus(status2);
		tree2.setTreeSpecies(species2);
		tree2.setLandType(landtype2);
		
		double height3 = 16;
		double diameter3 = 12;
		double longitude3 = 70;
		double latitude3 = 54;
		Person person3 = new Person ("Jessy");
		TreeSpecies species3 = TreeSpecies.Willow;
		LandType landtype3 = LandType.Municipal;
		Municipality mun3 = new Municipality();
		Date  date3 = new Date();
		mun3.setMunicipalityName(MunicipalityName.Montreal);
		Tree tree3 = new Tree(height3, diameter3, longitude3, latitude3, mun3);
		Status status3= new Status(date3, tree3, person3);
		status3.setTreeState(TreeState.Planted);
		tree3.addStatus(status3);
		tree3.setCurrentStatus(status3);
		tree3.setTreeSpecies(species3);
		
		double height4 = 16;
		double diameter4 = 12;
		double longitude4 = 70;
		double latitude4 = 54;
		Date  date4 = new Date();
		Person person4 = new Person ("Johnathan");
		TreeSpecies species4 = TreeSpecies.Willow;
		LandType landtype4 = LandType.Municipal;
		Municipality mun4= new Municipality();
		mun4.setMunicipalityName(MunicipalityName.Montreal);
		Tree tree4 = new Tree(height4, diameter4, longitude4, latitude4, mun4);
		Status status4= new Status(date4, tree4, person3);
		status4.setTreeState(TreeState.Planted);
		tree4.addStatus(status4);
		tree4.setCurrentStatus(status4);
		tree4.setTreeSpecies(species4);

		List <Tree> listOfTrees= new ArrayList <Tree>();
		listOfTrees.add(tree1);
		listOfTrees.add(tree2);
		listOfTrees.add(tree3);
		listOfTrees.add(tree4);


		service.CalculateBioDiversityIndexForTrees(listOfTrees);


		// check number of registered trees
		assertEquals(0.25, service.CalculateBioDiversityIndexForTrees(listOfTrees) , 0.2);
		
	}
	
	//test CalculateBioDiversity 
	// for Some Cut
	@Test
	public void testCalculateBioDiversityforCut() throws InvalidInputException {
		
		TreePLETreeService service = new TreePLETreeService(tm);


		double height1 = 15;
		double diameter1 = 19;
		double longitude1 = 75;
		double latitude1 = 77;
		Person person1 = new Person("John");
		TreeSpecies species1 = TreeSpecies.Willow;
		LandType landtype1 = LandType.Institutional;
		Date  date1 = new Date();
		Municipality mun1 = new Municipality();
		mun1.setMunicipalityName(MunicipalityName.Laval);
		Tree tree1 = new Tree(height1, diameter1, longitude1, latitude1, mun1);
		Status status1= new Status(date1, tree1, person1);
		status1.setTreeState(TreeState.Cut);
		tree1.addStatus(status1);
		tree1.setCurrentStatus(status1);
		tree1.setTreeSpecies(species1);

		
		double height2 = 12;
		double diameter2 = 19;
		double longitude2 = 76;
		double latitude2 = 54;
		Person person2 = new Person ("Jony");
		Date  date2 = new Date();
		TreeSpecies species2 = TreeSpecies.Willow;
		LandType landtype2 = LandType.Municipal;
		Municipality mun2 = new Municipality();
		mun2.setMunicipalityName(MunicipalityName.Laval);
		Tree tree2 = new Tree(height2, diameter2, longitude2, latitude2, mun2);
		Status status2= new Status(date2, tree2, person2);
		status2.setTreeState(TreeState.Cut);
		tree2.addStatus(status2);
		tree2.setCurrentStatus(status2);
		tree2.setTreeSpecies(species2);
		tree2.setLandType(landtype2);
		
		double height3 = 16;
		double diameter3 = 12;
		double longitude3 = 70;
		double latitude3 = 54;
		Person person3 = new Person ("Jessy");
		TreeSpecies species3 = TreeSpecies.Willow;
		LandType landtype3 = LandType.Municipal;
		Municipality mun3 = new Municipality();
		Date  date3 = new Date();
		mun3.setMunicipalityName(MunicipalityName.Montreal);
		Tree tree3 = new Tree(height3, diameter3, longitude3, latitude3, mun3);
		Status status3= new Status(date3, tree3, person3);
		status3.setTreeState(TreeState.Planted);
		tree3.addStatus(status3);
		tree3.setCurrentStatus(status3);
		tree3.setTreeSpecies(species3);
		
		double height4 = 16;
		double diameter4 = 12;
		double longitude4 = 70;
		double latitude4 = 54;
		Date  date4 = new Date();
		Person person4 = new Person ("Johnathan");
		TreeSpecies species4 = TreeSpecies.Willow;
		LandType landtype4 = LandType.Municipal;
		Municipality mun4= new Municipality();
		mun4.setMunicipalityName(MunicipalityName.Montreal);
		Tree tree4 = new Tree(height4, diameter4, longitude4, latitude4, mun4);
		Status status4= new Status(date4, tree4, person3);
		status4.setTreeState(TreeState.Planted);
		tree4.addStatus(status4);
		tree4.setCurrentStatus(status4);
		tree4.setTreeSpecies(species4);

		List <Tree> listOfTrees= new ArrayList <Tree>();
		listOfTrees.add(tree1);
		listOfTrees.add(tree2);
		listOfTrees.add(tree3);
		listOfTrees.add(tree4);


		service.CalculateBioDiversityIndexForTrees(listOfTrees);


		// check number of registered trees
		assertEquals(0.2, service.CalculateBioDiversityIndexForTrees(listOfTrees) , 0.2);
		
	}
	
	//Test if List is Null
	@Test
	public void testBioDiversityNull() {
		assertEquals(0, tm.getTrees().size());
		
		TreePLETreeService service = new TreePLETreeService(tm);


		double height = 0;
		double diameter = 0;
		double longitude = 0;
		double latitude = 0;
		String name = "";
		TreeSpecies species = null;
		LandType landtype = null;
		MunicipalityName mun= MunicipalityName.Montreal;
		Municipality m= new Municipality();
		String error = null;
		Date  date = new Date();
		Person person = new Person ("Johnathan");
		Tree tree = new Tree (height, diameter, longitude, latitude, m);
		Status status= new Status(date, tree, person);
		status.setTreeState(TreeState.Planted);
		
		tree.addStatus(status);
		tree.setCurrentStatus(status);
		tree.setTreeSpecies(species);

		List <Tree> listOfTrees= null;
		//istOfTrees.add(tree);
		
		try {
			service.CalculateBioDiversityIndexForTrees(listOfTrees);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Missing Information", error);

		// check no change in memory
		assertEquals(0, tm.getTrees().size());

	}
	
	@Test
	public void testTotalCanopyForTrees() throws InvalidInputException {
		TreePLETreeService service = new TreePLETreeService(tm);


		double height1 = 15;
		double diameter1 = 19;
		double longitude1 = 75;
		double latitude1 = 77;
		Person person1 = new Person("John");
		TreeSpecies species1 = TreeSpecies.Willow;
		LandType landtype1 = LandType.Institutional;
		Date  date1 = new Date();
		Municipality mun1 = new Municipality();
		mun1.setMunicipalityName(MunicipalityName.Laval);
		Tree tree1 = new Tree(height1, diameter1, longitude1, latitude1, mun1);
		Status status1= new Status(date1, tree1, person1);
		status1.setTreeState(TreeState.Planted);
		tree1.addStatus(status1);
		tree1.setCurrentStatus(status1);
		tree1.setTreeSpecies(species1);

		
		double height2 = 12;
		double diameter2 = 19;
		double longitude2 = 76;
		double latitude2 = 54;
		Person person2 = new Person ("Jony");
		Date  date2 = new Date();
		TreeSpecies species2 = TreeSpecies.Willow;
		LandType landtype2 = LandType.Municipal;
		Municipality mun2 = new Municipality();
		mun2.setMunicipalityName(MunicipalityName.Laval);
		Tree tree2 = new Tree(height2, diameter2, longitude2, latitude2, mun2);
		Status status2= new Status(date2, tree2, person2);
		status2.setTreeState(TreeState.Planted);
		tree2.addStatus(status2);
		tree2.setCurrentStatus(status2);
		tree2.setTreeSpecies(species2);
		tree2.setLandType(landtype2);
		
		double height3 = 16;
		double diameter3 = 12;
		double longitude3 = 70;
		double latitude3 = 54;
		Person person3 = new Person ("Jessy");
		TreeSpecies species3 = TreeSpecies.Willow;
		LandType landtype3 = LandType.Municipal;
		Municipality mun3 = new Municipality();
		Date  date3 = new Date();
		mun3.setMunicipalityName(MunicipalityName.Montreal);
		Tree tree3 = new Tree(height3, diameter3, longitude3, latitude3, mun3);
		Status status3= new Status(date3, tree3, person3);
		status3.setTreeState(TreeState.Cut);
		tree3.addStatus(status3);
		tree3.setCurrentStatus(status3);
		tree3.setTreeSpecies(species3);
		
		double height4 = 16;
		double diameter4 = 12;
		double longitude4 = 70;
		double latitude4 = 54;
		Date  date4 = new Date();
		Person person4 = new Person ("Johnathan");
		TreeSpecies species4 = TreeSpecies.Willow;
		LandType landtype4 = LandType.Municipal;
		Municipality mun4= new Municipality();
		mun4.setMunicipalityName(MunicipalityName.Montreal);
		Tree tree4 = new Tree(height4, diameter4, longitude4, latitude4, mun4);
		Status status4= new Status(date4, tree4, person3);
		status4.setTreeState(TreeState.Cut);
		tree4.addStatus(status4);
		tree4.setCurrentStatus(status4);
		tree4.setTreeSpecies(species4);

		List <Tree> listOfTrees= new ArrayList <Tree>();
		listOfTrees.add(tree1);
		listOfTrees.add(tree2);
		listOfTrees.add(tree3);
		listOfTrees.add(tree4);


		service.calculateTotalCanopyForTrees(listOfTrees);


		// check number of registered trees
		assertEquals(566.77, service.calculateTotalCanopyForTrees(listOfTrees) , 0.2);
		
	}
	
	@Test
	public void testTotalCanopyNull() {
		
		assertEquals(0, tm.getTrees().size());
		
		TreePLETreeService service = new TreePLETreeService(tm);


		double height = 0;
		double diameter = 0;
		double longitude = 0;
		double latitude = 0;
		String name = "";
		TreeSpecies species = null;
		LandType landtype = null;
		MunicipalityName mun= MunicipalityName.Montreal;
		Municipality m= new Municipality();
		String error = null;
		Date  date = new Date();
		Person person = new Person ("Johnathan");
		Tree tree = new Tree (height, diameter, longitude, latitude, m);
		Status status= new Status(date, tree, person);
		status.setTreeState(TreeState.Planted);
		
		tree.addStatus(status);
		tree.setCurrentStatus(status);
		tree.setTreeSpecies(species);

		List <Tree> listOfTrees= null;
		
		try {
			service.calculateTotalCanopyForTrees(listOfTrees);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Missing Information", error);

		// check no change in memory
		assertEquals(0, tm.getTrees().size());

	}
	
	
	
	@Test
	public void testCalculateCarbonSeqPerYear() {
		TreePLETreeService service = new TreePLETreeService(tm);


		double height1 = 15;
		double diameter1 = 19;
		double longitude1 = 75;
		double latitude1 = 77;
		Person person1 = new Person("John");
		TreeSpecies species1 = TreeSpecies.Willow;
		LandType landtype1 = LandType.Institutional;
		Date  date1 = new Date();
		Municipality mun1 = new Municipality();
		mun1.setMunicipalityName(MunicipalityName.Laval);
		Tree tree1 = new Tree(height1, diameter1, longitude1, latitude1, mun1);
		Status status1= new Status(date1, tree1, person1);
		status1.setTreeState(TreeState.Planted);
		tree1.addStatus(status1);
		tree1.setCurrentStatus(status1);
		tree1.setTreeSpecies(species1);

		
		double height2 = 12;
		double diameter2 = 19;
		double longitude2 = 76;
		double latitude2 = 54;
		Person person2 = new Person ("Jony");
		Date  date2 = new Date();
		TreeSpecies species2 = TreeSpecies.Willow;
		LandType landtype2 = LandType.Municipal;
		Municipality mun2 = new Municipality();
		mun2.setMunicipalityName(MunicipalityName.Laval);
		Tree tree2 = new Tree(height2, diameter2, longitude2, latitude2, mun2);
		Status status2= new Status(date2, tree2, person2);
		status2.setTreeState(TreeState.Planted);
		tree2.addStatus(status2);
		tree2.setCurrentStatus(status2);
		tree2.setTreeSpecies(species2);
		tree2.setLandType(landtype2);
		
		double height3 = 16;
		double diameter3 = 12;
		double longitude3 = 70;
		double latitude3 = 54;
		Person person3 = new Person ("Jessy");
		TreeSpecies species3 = TreeSpecies.Willow;
		LandType landtype3 = LandType.Municipal;
		Municipality mun3 = new Municipality();
		Date  date3 = new Date();
		mun3.setMunicipalityName(MunicipalityName.Montreal);
		Tree tree3 = new Tree(height3, diameter3, longitude3, latitude3, mun3);
		Status status3= new Status(date3, tree3, person3);
		status3.setTreeState(TreeState.Cut);
		tree3.addStatus(status3);
		tree3.setCurrentStatus(status3);
		tree3.setTreeSpecies(species3);
		
		double height4 = 16;
		double diameter4 = 12;
		double longitude4 = 70;
		double latitude4 = 54;
		Date  date4 = new Date();
		Person person4 = new Person ("Johnathan");
		TreeSpecies species4 = TreeSpecies.Willow;
		LandType landtype4 = LandType.Municipal;
		Municipality mun4= new Municipality();
		mun4.setMunicipalityName(MunicipalityName.Montreal);
		Tree tree4 = new Tree(height4, diameter4, longitude4, latitude4, mun4);
		Status status4= new Status(date4, tree4, person3);
		status4.setTreeState(TreeState.Planted);
		tree4.addStatus(status4);
		tree4.setCurrentStatus(status4);
		tree4.setTreeSpecies(species4);

		List <Tree> listOfTrees= new ArrayList <Tree>();
		listOfTrees.add(tree1);
		listOfTrees.add(tree2);
		listOfTrees.add(tree3);
		listOfTrees.add(tree4);


		service.CalculateCarbonSeqPerYear(listOfTrees);
		assertEquals(144, service.CalculateCarbonSeqPerYear(listOfTrees) , 0.2);

	}
	

	/*@Test
	public void testIntegration() {
		
		TreePLETreeService service = new TreePLETreeService(tm);
		Municipality montreal = new Municipality();
		Municipality laval = new Municipality();

		montreal.setMunicipalityName(MunicipalityName.Montreal);
		laval.setMunicipalityName(MunicipalityName.Montreal);

		
		//Replace every tree. with service. and remove all tree
		
		
		double height = 10;
		double diameter = 12;
		double longitude = 23;
		double latitude = 24;
		String userName = "M.ad";
		TreeSpecies species = TreeSpecies.Willow;
		LandType landtype = LandType.Institutional;
		try {
			service.plantTree(landtype, species, height, diameter, longitude, latitude, montreal, userName);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//change parameters
		height = 20;
		diameter = 44;
		longitude = 20;
		latitude = 42;
		userName="laura12";
		species = TreeSpecies.Willow;
		landtype = LandType.Institutional;
		try {
			service.plantTree(landtype, species, height, diameter, longitude, latitude, laval, userName);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//change parameters
		height = 30;
		diameter = 32;
		longitude = 48;
		latitude = 67;
		userName="laura12";
		species = TreeSpecies.Willow;
		landtype = LandType.Institutional;
		service = new TreePLETreeService(tm);
		try {
			service.plantTree(landtype, species, height, diameter, longitude, latitude, montreal, userName);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		height = 40;
		diameter = 10;
		longitude = 64;
		latitude = 35;
		userName="james";
		species = TreeSpecies.Willow;
		landtype = LandType.Institutional;
		service = new TreePLETreeService(tm);
		try {
			service.plantTree(landtype, species, height, diameter, longitude, latitude, montreal, userName);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		height = 16;
		diameter = 35;
		longitude = 27;
		latitude = 29;
		userName = "john";
		species = TreeSpecies.Willow;
		landtype = LandType.Institutional;
		service = new TreePLETreeService(tm);
		try {
			service.plantTree(landtype, species, height, diameter, longitude, latitude, laval, userName);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//check model in memory
		assertEquals(TreeState.Planted, tm.getTree(0).getCurrentStatus().getTreeState());
		assertEquals(10, tm.getTree(0).getHeight());
		assertEquals(TreeSpecies.Willow, tm.getTree(0).getTreeSpecies());
		assertEquals(LandType.Institutional, tm.getTree(0).getLandType());

        //pis continue for each field then for each tree

		//change all TreeState.PlantTree into .Planted
		assertEquals(TreeState.Planted, tm.getTree(1).getCurrentStatus().getTreeState());
		assertEquals(TreeState.Planted, tm.getTree(2).getCurrentStatus().getTreeState());
		assertEquals(TreeState.Planted, tm.getTree(3).getCurrentStatus().getTreeState());
		assertEquals(TreeState.Planted, tm.getTree(4).getCurrentStatus().getTreeState());

		
		service.cutDownTree(tm.getTree(0),"M.ad");
		assertEquals(TreeState.Cut, tm.getTree(0).getCurrentStatus().getTreeState());
		
		
		service.MarkTreeAsDiseased(tm.getTree(3), "laura12");
		assertEquals(TreeState.Diseased, tm.getTree(3).getCurrentStatus().getTreeState());
		
		//now for fun mark another as diseased then cut
		
       
		//test getTreeByID; repeat for every treeID 
		assertEquals(service.getTreeByID(1), tm.getTree(0).get);
		
		//repeat for Laval
		assertEquals(service.getMunicipalityByName(MunicipalityName.Montreal).getMunicipalityName(), MunicipalityName.Montreal);		
		assertEquals(service.getMunicipalityByName(MunicipalityName.Laval).getMunicipalityName(), MunicipalityName.Laval);

		//date won't check 

		//testing getTreeByMunicipality
		
		List<Tree>montrealTrees=service.getTreeByMunicipality(MunicipalityName.Montreal);
		assertEquals(montrealTrees.size(), 3); //means I have 3 in Mtl 
		//bioIndex
		assertEquals(service.CalculateBioDiversityIndexForTrees(montrealTrees), 4); //means I have 3 in Mtl 

	//getTreeBySpecies and getTreebyState is the same as getTreeByMuncipality

		//markToBeCutDown to be the same as .CUTdOWN
		

	}

		*/

}

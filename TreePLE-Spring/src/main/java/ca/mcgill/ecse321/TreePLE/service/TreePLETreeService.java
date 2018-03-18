package ca.mcgill.ecse321.TreePLE.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.TreePLE.model.Forecast;
//import ca.mcgill.ecse321.TreePLE.controller.RequestParam;
import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Municipality.MunicipalityName;
import ca.mcgill.ecse321.TreePLE.model.Person;
import ca.mcgill.ecse321.TreePLE.model.Report;
import ca.mcgill.ecse321.TreePLE.model.Status;
import ca.mcgill.ecse321.TreePLE.model.Status.TreeState;
import ca.mcgill.ecse321.TreePLE.model.Tree;
import ca.mcgill.ecse321.TreePLE.model.Tree.LandType;
import ca.mcgill.ecse321.TreePLE.model.Tree.TreeSpecies;
import ca.mcgill.ecse321.TreePLE.model.TreeManager;
import ca.mcgill.ecse321.TreePLE.persistence.PersistenceXStream;

@Service
public class TreePLETreeService {
	private TreeManager tm;
	

		public TreePLETreeService(TreeManager tm)
		{
		  this.tm = tm;
		  Municipality m1= new Municipality();
		  m1.setMunicipalityName(MunicipalityName.Montreal);
		  Municipality m2= new Municipality();
		  m2.setMunicipalityName(MunicipalityName.Laval);
		  tm.addMunicipality(m1);
		  tm.addMunicipality(m2);
		  
		}
		
		
		public Report generateReportForForecast(Forecast f) {
	int i=0;
	for(i=0; i<f.getCurrentTrees().size();i++) {
		f.removeCurrentTree(f.getCurrentTree(i));
	}
	for(i=0; i<f.getTreesToBePlanted().size();i++) {
		f.addCurrentTree(f.getTreesToBePlanted(i));
	}
	List<Tree> currentToAdd = new ArrayList<Tree>();
	currentToAdd = getNonCutTreesInList(tm.getTrees());
	for(i=0; i<currentToAdd.size();i++) {
		if(!(f.getTreesToBeCut().contains(currentToAdd.get(i)))){
			f.addCurrentTree(currentToAdd.get(i));}}
	double b=0;
	double c= 0;
	int s=0;
	b= CalculateBioDiversityIndexForTrees(f.getCurrentTrees());
	c= TotalCanopyForTrees(f.getCurrentTrees());
	s= CalculateCarbonSeqPerYear(f.getCurrentTrees());
	Report r= new Report(c,s,b,f);
	return r;
	
		}
		
		
		
		public Forecast createNewForecast(String userName) {
			List<Person> users= listAllUsers();
			  Person user =null;
			  for(Person p : users) {
				  if (p.getName()==userName) { user=p;}
			  }
			  if(user==null) {
				  user= new Person(userName);
			  }
			
			Forecast f= new Forecast(user);
			return f;
		}
		public Forecast PlantTreeForForecast(Forecast f,LandType landtype, TreeSpecies species, double height, double diameter, double longitude, double latitude, MunicipalityName munName) throws InvalidInputException{
			 if (species == null  || height ==0 || diameter ==0 || longitude ==0 || latitude ==0 || landtype == null  || munName == null) {
				    throw new InvalidInputException("Missing information");
				  }
			 Municipality m= new Municipality();
				  Tree t = new Tree(height, diameter, longitude, latitude, m);
				  Person user= f.getPerson();
				  Date date1= new Date();
				  Status s = new Status(date1,t,user);
				 
				  //change tree status to planted
				  s.setTreeState(TreeState.Planted);
				  t.setCurrentStatus(s);
				  
				  t.addStatus(s);
				  
				
				  
				  //set tree species
				  t.setTreeSpecies(species);
				  
				  //set Landtype
				  t.setLandType(landtype);
				  
				
				  
				  //set municipality
				  t.setMunicipality(m);
				  //MunicipalityName name = municipality.getMunicipalityName();
				  //municipality.setMunicipalityName(name);
				  f.addTreesToBePlanted(t);
				  tm.addForecast(f);
				  PersistenceXStream.saveToXMLwithXStream(tm);
				  return f ;
				  
				  
		}
		public Tree getTreeByID  (int id) throws InvalidInputException  {
			List<Tree> alltrees= listAllTrees();
		Tree tree= null;
		for (Tree tr : alltrees) {
			if(tr.getTreeID()==id) {
				tree=tr;
				break;
			}
		}
		if (tree == null) { throw new InvalidInputException("Tree doesn't exist");}
		else{return tree;}
	}
		
			
		public Municipality getMunicipalityByName  (MunicipalityName municipalityName) throws InvalidInputException  {
			List<Municipality>allMunicipalities= findAllMunicipalities();
		Municipality mun= null;
		for (Municipality n : allMunicipalities) {
			if(n.getMunicipalityName().equals(municipalityName)) {
				mun=n;
				break;
			}
		}
		
		if (mun==null) { /*Municipality mun2= new Municipality();
			mun2.setMunicipalityName(municipalityName);
		return mun2; */
			throw new InvalidInputException ("municipality does not exist");}
		
		else{return mun;}
	}
		
	
		
		//plant a tree method
		public Tree plantTree(LandType landtype, TreeSpecies species, double height, double diameter, double longitude, double latitude, MunicipalityName munName, String userName) throws InvalidInputException
		{
		  if (species == null  || height ==0 || diameter ==0 || longitude ==0 || latitude ==0 || landtype == null  || munName == null) {
		    throw new InvalidInputException("Missing information");
		  }
		  Municipality municipality= getMunicipalityByName(munName);
		  Tree t = new Tree(height, diameter, longitude, latitude, municipality);
		  List<Person> users= listAllUsers();
		  Person user =null;
		  for(Person p : users) {
			  if (p.getName()==userName) { user=p;}
		  }
		  if(user==null) {
			  user= new Person(userName);
		  }
		  Date date1= new Date();
		  Status s = new Status(date1,t,user);
		 
		  //change tree status to planted
		  s.setTreeState(TreeState.Planted);
		  t.setCurrentStatus(s);
		  
		  t.addStatus(s);
		  
		
		  
		  //set tree species
		  t.setTreeSpecies(species);
		  
		  //set Landtype
		  t.setLandType(landtype);
		  
		
		  
		  //set municipality
		  t.setMunicipality(municipality);
		  //MunicipalityName name = municipality.getMunicipalityName();
		  //municipality.setMunicipalityName(name);
		  municipality.addTree(t);
		  
		  //add tree to the list
		
		  tm.addStatus(s);
		  tm.addTree(t);
		  PersistenceXStream.saveToXMLwithXStream(tm);
		  return t;
		}
		
		//list all trees
		public List<Tree> listAllTrees()
		{
		 
			return tm.getTrees();
		}
		
		public List<Person> listAllUsers()
		{
		 
			return tm.getPerson();
		}
		public List<Tree> getNonCutTreesInList(List<Tree> treeList){
			List<Tree> ts= new ArrayList <Tree>();
			for (Tree tree: treeList) {
				if(!tree.getCurrentStatus().getTreeState().equals(TreeState.Cut)) {
				ts.add(tree);}}
				return ts;
				
				}
			
		
		public double CalculateBioDiversityIndexForTrees(List<Tree> treeList) {
			List<Tree> ts= new ArrayList <Tree>();
			ts= getNonCutTreesInList(treeList);
			List<TreeSpecies>Species= new ArrayList<TreeSpecies>();
			for (Tree tree: ts) {
				TreeSpecies n= tree.getTreeSpecies();
				if( Species.contains(n)){}
				else { Species.add(n);}
				}
			double numerator= Species.size();
			double denominator=treeList.size();
			double index= numerator/denominator;
			return index;
				
			}
			
public int CalculateCarbonSeqPerYear(List<Tree> treeList) {
	List<Tree> ts= new ArrayList <Tree>();
	ts= getNonCutTreesInList(treeList);
	return (ts.size()*48);
			
}

public double TotalCanopyForTrees(List<Tree> treeList) {
	double TotalCanopy=0;
	double area=0;
	List<Tree> ts= new ArrayList <Tree>();
	ts= getNonCutTreesInList(treeList);
	for (Tree tree: ts) {
		area=(3.14)*(tree.getDiameter()*0.5)*(tree.getDiameter()*0.5);
	TotalCanopy=TotalCanopy+area;
			
		}
	return TotalCanopy;
	
}
		
public double CurrentTotalCanopy() {
	List<Tree> treeList=listAllTrees();
	List<Tree> ts= new ArrayList <Tree>();
	ts= getNonCutTreesInList(treeList);
	double TotalCanopy=0;
	double  area=0;
	for (Tree tree: ts) {
		area=(3.14)*(tree.getDiameter()*0.5)*(tree.getDiameter()*0.5);
	TotalCanopy=TotalCanopy+area;
			
		}
	return TotalCanopy;
	
}
				
public double CalculateCurrentBioDiversityIndex() {
	List<Tree> treeList= listAllTrees();
	List<Tree> ts= new ArrayList <Tree>();
	ts= getNonCutTreesInList(treeList);
	List<TreeSpecies>Species= new ArrayList<TreeSpecies>();
	for (Tree tree: ts) {
		TreeSpecies n= tree.getTreeSpecies();
		if( Species.contains(n)){}
		else { Species.add(n);}
		}
	double numerator= Species.size();
	double denominator=treeList.size();
	double index= numerator/denominator;
	return index;
		
	}
	
public int CalculateCurrentCarbonSeqPerYear() {
	List<Tree> treeList= listAllTrees();
	List<Tree> ts= new ArrayList <Tree>();
	ts= getNonCutTreesInList(treeList);
return (ts.size()*48);
	
}
		
		
		//get tree by species
		public List <Tree> getTreeBySpecies(TreeSpecies species) throws InvalidInputException {
			List<Tree> alltrees= listAllTrees();
			List<Tree> treesBySpecies = new ArrayList<Tree>();
			
			for (Tree tr : alltrees) {
				if(tr.getTreeSpecies().equals(species)) {
					treesBySpecies.add(tr);
				}
			}
			if (treesBySpecies == null || treesBySpecies.size() ==0) { throw new InvalidInputException("Tree of this species doesn't exist");}
			
			
			else{return treesBySpecies;}
		}
		
		public List <Tree> getTreeByState(TreeState ts) throws InvalidInputException {
			List<Tree> alltrees= listAllTrees();
			List<Tree> treesByState =new ArrayList<Tree>();
			
			for (Tree tr : alltrees) {
				if(tr.getCurrentStatus().getTreeState().equals(ts)) {
					treesByState.add(tr);
				}
			}
			if (treesByState == null || treesByState.size() == 0) { throw new InvalidInputException("Tree of this species doesn't exist");}
			else{return treesByState;}
		}
	
		
		//get tree by municipality
		public List<Tree> getTreeByMunicipality(MunicipalityName municipalityName){
			
		Municipality municipality= new Municipality();
		
			try {
				municipality = getMunicipalityByName(municipalityName);
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return municipality.getTrees();
			
			
		}
		
		public Municipality getMunicipalityForTree(Tree t) {
			return t.getMunicipality();
		}
		public List<Municipality> findAllMunicipalities()
		{
		  // service stub
			return tm.getMunicipality();
		}
		
		
		
		public Forecast cutDownTreeForForecast(Tree t, Forecast f) throws InvalidInputException{
			if(t==null) {
				throw new InvalidInputException("fill in the missing information");
			}
			
			 
			  //change tree status to planted
			
			  f.addTreesToBeCut(t);
			  PersistenceXStream.saveToXMLwithXStream(tm);
			  return f ;
			  
			  	
			
		}
		
		//cut down tree
		public Tree cutDownTree(Tree t, String userName) throws InvalidInputException {
			if((t==null)) {
				throw new InvalidInputException("Please fill in all missing information!")	;
			}
			List<Person> users= listAllUsers();
			  Person user =null;
			  for(Person p : users) {
				  if (p.getName()==userName) { user=p;}
			  }
			  if(user==null) {
				  user= new Person(userName);
			  }
				Date date = new Date();
			  Status s = new Status(date,t,user);
			
		
		
			s.setTreeState(TreeState.Cut);
		
			t.addStatus(s);
			t.setCurrentStatus(s);
			
			tm.addStatus(s);
			PersistenceXStream.saveToXMLwithXStream(tm);
			  
			return t;
			
		}
		//mark as diseased
		
		public Tree MarkTreeAsDiseased(Tree t,String userName) throws InvalidInputException {
			if((t==null)) {
				throw new InvalidInputException("Please fill in all missing information!")	;
			}
			
		
		List<Person> users= listAllUsers();
		  Person user =null;
		  for(Person p : users) {
			  if (p.getName()==userName) { user=p;}
		  }
		  if(user==null) {
			  user= new Person(userName);
		  }
			Date date = new Date();
		  Status s = new Status(date,t,user);
		
	
	
		s.setTreeState(TreeState.Diseased);
	
		t.addStatus(s);
		t.setCurrentStatus(s);
		
		tm.addStatus(s);
		PersistenceXStream.saveToXMLwithXStream(tm);
		  
		return t;
		
	}
		
		public Tree MarkTreeToBeCutDown(Tree t, String userName) throws InvalidInputException {
			if((t==null)||(userName==null)||(userName.trim().length() == 0)) {
				throw new InvalidInputException("Please fill in all missing information!")	;
			}
		
		List<Person> users= listAllUsers();
		  Person user =null;
		  for(Person p : users) {
			  if (p.getName()==userName) { user=p;}
		  }
		  if(user==null) {
			  user= new Person(userName);
		  }
			Date date = new Date();
		  Status s = new Status(date,t,user);
		
	
	
		s.setTreeState(TreeState.ToBeCut);
	
		t.addStatus(s);
		t.setCurrentStatus(s);
		
		tm.addStatus(s);
		PersistenceXStream.saveToXMLwithXStream(tm);
		  
		return t;
		
	}
		

}

package ca.mcgill.ecse321.TreePLE.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;

//import ca.mcgill.ecse321.TreePLE.controller.RequestParam;
import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Municipality.MunicipalityName;
import ca.mcgill.ecse321.TreePLE.model.Status;
import ca.mcgill.ecse321.TreePLE.model.Status.TreeState;
import ca.mcgill.ecse321.TreePLE.model.SystemDate;
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
		
			
		public Municipality getMunicipalityByName  (MunicipalityName munName) throws InvalidInputException  {
			List<Municipality>allMunicipalities= tm.getMunicipality();
		Municipality mun= null;
		for (Municipality n : allMunicipalities) {
			if(n.getMunicipalityName()== munName) {
				mun=n;
				break;
			}
		}
		if (mun == null) { throw new InvalidInputException("municipality doesn't exist");}
		else{return mun;}
	}
		
		//plant a tree method
		public Tree plantTree(LandType landtype, TreeSpecies species, double height, double diameter, double longitude, double latitude, Municipality municipality) throws InvalidInputException
		{
		  if (species == null  || height ==0 || diameter ==0 || longitude ==0 || latitude ==0 || landtype == null  || municipality == null) {
		    throw new InvalidInputException("Missing information");
		  }
		  Tree t = new Tree(height, diameter, longitude, latitude, municipality);
		  Status s = new Status(t);
		 
		  //change tree status to planted
		  s.setTreeState(TreeState.Planted);
		  t.setCurrentStatus(s);
		  
		  t.addStatus(s);
		  
		  //add a date to the new status to keep track of tree history
		  Date date1= new Date();
		  SystemDate systemDate1= new SystemDate(date1);
		  s.addSystemDate(systemDate1);
		  
		  //set tree species
		  t.setTreeSpecies(species);
		  
		  //set Landtype
		  t.setLandType(landtype);
		  
		
		  
		  //set municipality
		  t.setMunicipality(municipality);
		  
		  //add tree to the list
		  tm.addSystemDate(systemDate1);
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
		
		//get tree by species
		public List <Tree> getTreeBySpecies(TreeSpecies species) throws InvalidInputException {
			List<Tree> alltrees= listAllTrees();
			List<Tree> treesBySpecies = null;
			
			for (Tree tr : alltrees) {
				if(tr.getTreeSpecies().equals(species)) {
					treesBySpecies.add(tr);
				}
			}
			if (treesBySpecies == null || treesBySpecies.size() ==0) { throw new InvalidInputException("Tree of this species doesn't exist");}
			else{return treesBySpecies;}
		}
		
		
		/*public Tree getTreeByMunicipality(Municipality municipality) throws InvalidInputException {
			List<Tree> alltrees= findAllTrees();
			Tree tree= null;
			for (Tree tr : alltrees) {
				if(tr.getMunicipality().getMunicipalityName().equals(municipality.getMunicipalityName())) {
					tree=tr;
					break;
				}
			}
			if (tree == null) { throw new InvalidInputException("Tree doesn't exist");}
			else{return tree;}
		}*/
		
		//get tree by municipality
		public List<Tree> getTreeByMunicipality(Municipality municipality) throws InvalidInputException{
			if (municipality.getTrees() == null) { 
				throw new InvalidInputException("Tree doesn't exist");}
			else{
				return municipality.getTrees();
			}
			
		}
		
		
		//cut down tree
		public Tree cutDownTree(Tree t) throws InvalidInputException {
			if((t==null)) {
				throw new InvalidInputException("Please fill in all missing information!")	;
			}
			
			Status s = new Status(t);
			
			//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			//LocalDate localDate = LocalDate.now();
			Date date = new Date();
			SystemDate systemDate = new SystemDate(date);
		
			s.setTreeState(TreeState.Cut);
			s.addSystemDate(systemDate);
			t.addStatus(s);
			t.setCurrentStatus(s);
			tm.addSystemDate(systemDate);
			tm.addStatus(s);
			PersistenceXStream.saveToXMLwithXStream(tm);
			  
			return t;
			
		}
		
		
			
		

}

package ca.mcgill.ecse321.TreePLE.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.TreePLE.model.Municipality;
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
		}
		
		//plant a tree method
		public Tree plantTree(LandType landtype, TreeSpecies species, double height, double diameter, double longitude, double latitude, Municipality municipality, Status status ) throws InvalidInputException
		{
		  if (species == null  || height ==0 || diameter ==0 || longitude ==0 || latitude ==0 || landtype == null  || municipality == null) {
		    throw new InvalidInputException("Missing information");
		  }
		  Tree t = new Tree(height, diameter, longitude, latitude, municipality);
		  Status s = new Status(t);
		  s.setTreeState(TreeState.Planted);
		  t.setTreeSpecies(species);
		  t.setLandType(landtype);
		  t.addStatus(s);
		  t.setMunicipality(municipality);
		  tm.addTree(t);
		  PersistenceXStream.saveToXMLwithXStream(tm);
		  return t;
		}
		
		//list all trees
		public List<Tree> findAllTrees()
		{
		 
			return tm.getTrees();
		}
		
		//get tree by species
		public Tree getTreeBySpecies(TreeSpecies species) throws InvalidInputException {
			List<Tree> alltrees= findAllTrees();
			Tree tree= null;
			for (Tree tr : alltrees) {
				if(tr.getTreeSpecies().equals(species)) {
					tree=tr;
					break;
				}
			}
			if (tree == null) { throw new InvalidInputException("Tree doesn't exist");}
			else{return tree;}
		}
		
		//get tree by municipality
		public Tree getTreeByMunicipality(String municipality) throws InvalidInputException {
			List<Tree> alltrees= findAllTrees();
			Tree tree= null;
			for (Tree tr : alltrees) {
				if(tr.getMunicipality().equals(municipality)) {
					tree=tr;
					break;
				}
			}
			if (tree == null) { throw new InvalidInputException("Tree doesn't exist");}
			else{return tree;}
		}
		
		//cut down tree
		public Tree cutDownTree(Tree t) throws InvalidInputException {
			if((t==null)) {
				throw new InvalidInputException("Oops! Please fill in all missing information!")	;
			}
			
			Status s = new Status(t);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			//LocalDate localDate = LocalDate.now();
			Date date = new Date();
			SystemDate systemDate = new SystemDate(date);
		
			s.setTreeState(TreeState.Cut);
			s.addSystemDate(systemDate);
			t.addStatus(s);
			
			
			return t;
			
		}
		
			
		

}

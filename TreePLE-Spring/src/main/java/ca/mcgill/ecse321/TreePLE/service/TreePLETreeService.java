package ca.mcgill.ecse321.TreePLE.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Status;
import ca.mcgill.ecse321.TreePLE.model.Status.TreeState;
import ca.mcgill.ecse321.TreePLE.model.Tree;
import ca.mcgill.ecse321.TreePLE.model.Tree.LandType;
import ca.mcgill.ecse321.TreePLE.model.Tree.TreeSpecies;
import ca.mcgill.ecse321.TreePLE.model.TreePLE;
import ca.mcgill.ecse321.TreePLE.persistence.PersistenceXStream;

@Service
public class TreePLETreeService {
	
public class TreePLEService {
		
		private TreePLE tm;

		public TreePLEService(TreePLE tm)
		{
		  this.tm = tm;
		}
		
		//plant a tree method
		public Tree plantTree(LandType landtype, TreeSpecies species, double height, int diameter, int longitude, int latitude, Municipality municipality, TreePLE treeple, Status status) throws InvalidInputException
		{
		  if (species == null  || height ==0 || diameter ==0 || longitude ==0 || latitude ==0 || landtype == null || status == null || municipality == null) {
		    throw new InvalidInputException("Missing information");
		  }
		  Tree t = new Tree(height, diameter, longitude, latitude, municipality, treeple);
		  t.setTreeSpecies(species);
		  t.setLandType(landtype);
		  t.addStatus(status);
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
		public Tree cutDownTree(Tree t, Date plantDate, Date cutDate) throws InvalidInputException {
			if((t==null)||(plantDate==null) ||(cutDate==null)) {
				throw new InvalidInputException("Oops! Please fill in all missing information!")	;
			}
			
			Status s = new Status(plantDate, cutDate, t);
			s.setTreeState(TreeState.Cut);
			t.addStatus(s);
			
			
			return t;
			
		}
		
			
		}

}

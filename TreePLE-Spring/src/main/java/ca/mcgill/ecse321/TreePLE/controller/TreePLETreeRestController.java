package ca.mcgill.ecse321.TreePLE.controller;

import java.util.ArrayList;
import java.util.List;

//import java.awt.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.TreePLE.dto.MunicipalityDto;
import ca.mcgill.ecse321.TreePLE.dto.StatusDto;
import ca.mcgill.ecse321.TreePLE.dto.TreeDto;
import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Municipality.MunicipalityName;
import ca.mcgill.ecse321.TreePLE.model.Status;
import ca.mcgill.ecse321.TreePLE.model.Status.TreeState;
import ca.mcgill.ecse321.TreePLE.model.Tree;
import ca.mcgill.ecse321.TreePLE.model.Tree.LandType;
import ca.mcgill.ecse321.TreePLE.model.Tree.TreeSpecies;
import ca.mcgill.ecse321.TreePLE.service.InvalidInputException;
import ca.mcgill.ecse321.TreePLE.service.TreePLETreeService;

@RestController
public class TreePLETreeRestController {

	@Autowired
	private TreePLETreeService service;

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping("/")
	public String index() {
		return "TreePLE application root. Web-based frontend is a TODO. Use the REST API to manage events and participants.\n";
	
}
	//conversion tree method
	private TreeDto convertToDto(Tree t) {
		 //mapper service
		  TreeDto treeDto = modelMapper.map(t,  TreeDto.class);
		 // treeDto.setMunicipality(createMunicipalityDtoForTree(t));
		  return treeDto;
		}

	
	//conversion municipality method
	private MunicipalityDto convertToDto(Municipality m)  {
			 
			 return modelMapper.map(m, MunicipalityDto.class);
		}
	
	//conversion status method
	private StatusDto convertToDto(Status s) {
			 StatusDto sDto = modelMapper.map(s, StatusDto.class);
			 
			 return sDto;
		}
	
	private MunicipalityDto createMunicipalityDtoForTree(Tree t){
		Municipality m = service.getMunicipalityForTree(t);
		return convertToDto(m);
	}
	
		
	/*private Municipality convertToDomainObject(MunicipalityDto mDto) {
		// Mapping DTO to the domain object without using the mapper
		List<Municipality> allMunicipalities = service.findAllMunicipalities();
		for (Municipality m: allMunicipalities) {
			if (m.getMunicipalityName().equals(mDto.getName())) {
				return m;
			}
		}
			return null;
		}*/
	
/*	private List<TreeDto> createTreesDtosForMunicipality(Municipality m){
		
		List<Tree> treesForMunicipality = service.getTreeByMunicipality(m);
		List<TreeDto> trees = new ArrayList<TreeDto>();
		for (Tree tree : treesForMunicipality) {
			trees.add(convertToDto(tree));
		}
		return trees;
	} */
	
	// get all trees
	
	

	
	
	@GetMapping(value = { "/trees", "/trees/" })
	public List<TreeDto> findAllTrees() throws InvalidInputException {
		List<TreeDto> trees = new ArrayList<TreeDto>();
		
		for (Tree tree : service.listAllTrees()) {
			trees.add(convertToDto(tree));
		}
		return trees;
	}
	
	
	
	@GetMapping(value = { "/CalculateCurrentBI/"})
	public int calculateCurrentBI() {
	int BI;	
	BI=service.CalculateCurrentBioDiversityIndex();
		
		return BI;
	}
	
	@GetMapping(value = { "/CalculateCurrentCarbonSEQ/"})
	public int calculateCurrentCarbonSEQ() {
	int seq;	
	seq=service.CalculateCurrentCarbonSeqPerYear();
		
		return seq;
	}
	
	
	@GetMapping(value = { "/CurrentTotalCanopy/"})
	public int currentTotalCanopy() {
	int can;	
	can=service.CalculateCurrentBioDiversityIndex();
		
		return can;
	}
	
	@GetMapping(value= {"/trees/{species}"})
	public List<TreeDto> getTreesBySpecies 
	(@PathVariable  (name="species") TreeSpecies species) throws InvalidInputException {
	
		List<TreeDto> trees= new ArrayList<TreeDto>();
		for(Tree tree: (service.getTreeBySpecies(species))) {
			trees.add(convertToDto(tree));
		}
	 return trees; }
	
	
	
	@GetMapping(value= {"/trees/{status}"})
	public List<TreeDto> getTreesBySpecies 
	(@PathVariable  (name="status") TreeState state) throws InvalidInputException {
	
		List<TreeDto> trees= new ArrayList<TreeDto>();
		for(Tree tree: (service.getTreeByState(state))) {
			trees.add(convertToDto(tree));
		}
	 return trees; }
	
	@GetMapping(value= {"/trees/{MunicipalityName}"})
	public List<TreeDto> getTreesByMunicipality 
	(@PathVariable  (name="mun") MunicipalityName munName) throws InvalidInputException {
	
		List<TreeDto> trees= new ArrayList<TreeDto>();
		for(Tree tree: (service.getTreeByMunicipality(munName))) {
			trees.add(convertToDto(tree));
		}
	 return trees; }
	//create municipality
/*	@PostMapping(value = {"/Municipality/{name}", "/Municipality/{name}/"})
	public MunicipalityDto NewMunicipality(@PathVariable("name") MunicipalityName munName) throws InvalidInputException {
		Municipality m = service.createMunicipality(munName);
		
		return convertToDto(m);
	}
	*/
	// Create a new tree
	
	@PostMapping(value = { "/PlantTree/{userName}"})
	public TreeDto plantTree(
			@PathVariable("userName") String userName,
			@RequestParam (name="landType") LandType landtype,
			@RequestParam  (name="species") TreeSpecies species,
			@RequestParam  (name="height") double height,
			@RequestParam (name="diameter") double diameter,
			@RequestParam (name="longitude") double longitude,
			@RequestParam  (name="latitude") double latitude,
			@RequestParam (name="municipality") MunicipalityName municipalityName
			
			) throws InvalidInputException {

	;
	Municipality mun= service.getMunicipalityByName(municipalityName);
		Tree tree= service.plantTree(landtype, species, height,diameter, longitude, latitude, mun,userName);
		
		return convertToDto(tree);
}
	
	
	
	
	@GetMapping(value = { "/municipalities/", "/municipalities" })
	public List<MunicipalityDto> findAllMunicipalities() {
		List<MunicipalityDto> municipalities = new ArrayList<MunicipalityDto>();
		for(Municipality m: service.findAllMunicipalities()) {
			municipalities.add(convertToDto(m));
		}
		return municipalities;
	}

	
	
	//Delete / Cut Tree

	@PostMapping(value = { "/cutDownTree/{treeID}/{userName}" })


	public TreeDto cutDownTree(
			@PathVariable("treeID") int treeID,
			@PathVariable("userName") String userName)
			throws InvalidInputException {
		
	Tree t = service.getTreeByID(treeID);

	service.cutDownTree(t,userName);
		return convertToDto(t);
	}
	
	
	
	@PostMapping(value = { "/MarktoBeCutDown/{treeID}/{userName}" })


	public TreeDto MarkToBeCutDown(
			@PathVariable("treeID") int treeID,
			@PathVariable("userName") String userName)
			throws InvalidInputException {
		
	Tree t = service.getTreeByID(treeID);

	service.MarkTreeToBeCutDown(t,userName);
		return convertToDto(t);
	}
	
	@PostMapping(value = { "/MarkAsDiseased/{treeID}/{userName}" })


	public TreeDto MarkAsDiseased(
			@PathVariable("treeID") int treeID,
			@PathVariable("userName") String userName)
			throws InvalidInputException {
		
	Tree t = service.getTreeByID(treeID);

	service.MarkTreeAsDiseased(t,userName);
		return convertToDto(t);
	}
	

	@PostMapping(value = { "/setTreeSpecies/{treeID}" })


	public TreeDto setTreeSpecies(
			@PathVariable("treeID") int treeID,
			@RequestParam  (name="species") TreeSpecies species) throws InvalidInputException {
		
	Tree t = service.getTreeByID(treeID);

	t.setTreeSpecies(species);
		return convertToDto(t);
	}
	
	@PostMapping(value = { "/setTreeLandType/{treeID}" })
	public TreeDto setTreeLandType(
			@PathVariable("treeID") int treeID,
			@RequestParam  (name="landType") LandType landType) throws InvalidInputException {
		
	Tree t = service.getTreeByID(treeID);

	t.setLandType(landType);
		return convertToDto(t);
	}
	
	
	
	@PostMapping(value = { "/setTreeHeight/{treeID}" })
	public TreeDto setTreeHeight(
			@PathVariable("treeID") int treeID,
			@RequestParam  (name="height") double height) throws InvalidInputException {
		
	Tree t = service.getTreeByID(treeID);

	t.setHeight(height);
		return convertToDto(t);
	}	

	
	
	@PostMapping(value = { "/setTreeDiameter/{treeID}" })
	public TreeDto setTreeDiameter(
			@PathVariable("treeID") int treeID,
			@RequestParam  (name="diameter") double diameter) throws InvalidInputException {
		
	Tree t = service.getTreeByID(treeID);

	t.setDiameter(diameter);
		return convertToDto(t);
	}

	

	@PostMapping(value = { "/setTreeLongitude/{treeID}" })
	public TreeDto setTreeLongitude(
			@PathVariable("treeID") int treeID,
			@RequestParam  (name="Longitude") double Longitude) throws InvalidInputException {
		
	Tree t = service.getTreeByID(treeID);

	t.setLongitude(Longitude);
		return convertToDto(t);
	}	
	

	
	
	@PostMapping(value = { "/setTreeLatitude/{treeID}" })
	public TreeDto setTreeLatitude(
			@PathVariable("treeID") int treeID,
			@RequestParam  (name="latitude") double latitude) throws InvalidInputException {
		
	Tree t = service.getTreeByID(treeID);

	t.setLatitude(latitude);
		return convertToDto(t);
	}	
	
	
}	


	
	


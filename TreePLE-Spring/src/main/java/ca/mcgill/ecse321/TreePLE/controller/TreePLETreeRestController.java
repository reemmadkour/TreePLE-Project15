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

import com.google.common.collect.Lists;

import ca.mcgill.ecse321.TreePLE.dto.MunicipalityDto;
import ca.mcgill.ecse321.TreePLE.dto.StatusDto;
import ca.mcgill.ecse321.TreePLE.dto.TreeDto;
import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Municipality.MunicipalityName;
import ca.mcgill.ecse321.TreePLE.model.Status;
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
		  treeDto.setMunicipality(createMunicipalityDtoForTree(t));
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
	
	private List<TreeDto> createTreesDtosForMunicipality(Municipality m){
		
		List<Tree> treesForMunicipality = service.getTreeByMunicipality(m);
		List<TreeDto> trees = new ArrayList<TreeDto>();
		for (Tree tree : treesForMunicipality) {
			trees.add(convertToDto(tree));
		}
		return trees;
	}
	
	// get all trees
	
	@GetMapping(value = { "/trees", "/trees/" })
	public List<TreeDto> findAllTrees() throws InvalidInputException {
		List<TreeDto> trees = new ArrayList<TreeDto>();
		
		for (Tree tree : service.listAllTrees()) {
			trees.add(convertToDto(tree));
		}
		return trees;
	}
	
	//create municipality
	@PostMapping(value = {"/Municipality/{name}", "/Municipality/{name}/"})
	public MunicipalityDto NewMunicipality(@PathVariable("name") MunicipalityName munName) throws InvalidInputException {
		Municipality m = service.createMunicipality(munName);
		
		return convertToDto(m);
	}
	
	// Create a new tree
	
	@PostMapping(value = { "/PlantTree/", "/PlantTree"})
	public TreeDto plantTree(
			@RequestParam (name="landType") Tree.LandType landtype,
			@RequestParam  (name="species") Tree.TreeSpecies species,
			@RequestParam  (name="height") double height,
			@RequestParam (name="diameter") double diameter,
			@RequestParam (name="longitude") double longitude,
			@RequestParam  (name="latitude") double latitude,
			@RequestParam ("municipality") MunicipalityDto mDto
			
			) throws InvalidInputException {

	;
	Municipality m= service.getMunicipalityByName(mDto.getName());
		Tree tree= service.plantTree(landtype, species, height,diameter, longitude, latitude, m);
		//convertToDto(m);
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

	@PostMapping(value = { "/cutDownTree/{tree}" })


	public TreeDto cutDownTree(
			@RequestParam(name="tree") TreeDto tree) throws InvalidInputException {
		
	Tree t = service.getTreeByID(tree.getTreeID());
	service.cutDownTree(t);
		return convertToDto(t);
	}
	
}	


	
	


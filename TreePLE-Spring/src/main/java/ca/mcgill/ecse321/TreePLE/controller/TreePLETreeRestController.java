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
		  TreeDto treeDto = modelMapper.map(t, TreeDto.class);
		 
		  return treeDto;
		}
	
	//conversion municipality method
	private MunicipalityDto convertToDto(Municipality m) throws InvalidInputException {
			 MunicipalityDto mDto = modelMapper.map(m, MunicipalityDto.class);
			 mDto.setTrees(createTreesDtosForMunicipality(m));
			 
			 return mDto;
		}
	
	//conversion status method
	private StatusDto convertToDto(Status s) {
			 StatusDto sDto = modelMapper.map(s, StatusDto.class);
			 
			 return sDto;
		}
		
	private Tree convertToDomainObject(Tree tDto) {
		// Mapping DTO to the domain object without using the mapper
		List<Tree> allTrees = service.listAllTrees();
		for (Tree tree: allTrees) {
			if (tree.getTreeID() == tDto.getTreeID()) {
				return tree;
			}
		}
			return null;
		}
	
	private List<TreeDto> createTreesDtosForMunicipality(Municipality m) throws InvalidInputException {
		
		List<Tree> treesForMunicipality = service.getTreeByMunicipality(m);
		List<TreeDto> trees = new ArrayList<TreeDto>();
		for (Tree tree : treesForMunicipality) {
			trees.add(convertToDto(tree));
		}
		return trees;
	}
	
	//plantTree(LandType landtype, TreeSpecies species,double height,double diam,double long,double lat, Municipalit
	
	@PostMapping(value = { "/trees/{landtype}/{species}/{height}/{diameter}/{longitude}/{latitude}/{municipality}/", "/trees/{landtype}/{species}/{height}/{diameter}/{longitude}/{latitude}/{municipality}"})
	public TreeDto createTree(
			@PathVariable("landtype") LandType landtype,
			@PathVariable("species") TreeSpecies s,
			@PathVariable("height") Double h,
			@PathVariable("diameter") Double d,
			@PathVariable("longitude") Double l,
			@PathVariable("latitude") Double lat,
			@PathVariable("municipality") Municipality m) throws InvalidInputException{
		Tree t = service.plantTree(landtype, s, h, d, l, lat,m);
		return convertToDto(t);
	}
	
	
	@GetMapping(value = { "/trees", "/trees/" })
	public List<TreeDto> findAllTrees() {
		List<TreeDto> trees = new ArrayList<TreeDto>();
		
		for (Tree tree : service.listAllTrees()) {
			trees.add(convertToDto(tree));
		}
		return trees;
	}
	
	
	
	
	
	
	@PostMapping(value = { "/PlantTree/"})
	public TreeDto plantTree(
			@RequestParam (name="landType") LandType landtype,
			@RequestParam  (name="species") TreeSpecies species,
			@RequestParam  (name="height") double height,
			@RequestParam (name="diameter") double diameter,
			@RequestParam (name="longitude") double longitude,
			@RequestParam  (name="latitude") double latitude,
			@RequestParam ("municipality") MunicipalityName municipalityName
			
			) throws InvalidInputException {

	;
	Municipality mun= service.getMunicipalityByName(municipalityName);
		Tree tree= service.plantTree(landtype, species, height,diameter, longitude, latitude, mun);
		
		return convertToDto(tree);
}
	
	
	

	@PostMapping(value = { "/cutDownTree/ID" })
	public TreeDto cutDownTree(
			@RequestParam(name="tree") TreeDto tree) throws InvalidInputException {
		
	Tree t = service.getTreeByID(tree.getTreeID());
	service.cutDownTree(t);
		return convertToDto(t);
	}
	
	
	
	
	
	}
	

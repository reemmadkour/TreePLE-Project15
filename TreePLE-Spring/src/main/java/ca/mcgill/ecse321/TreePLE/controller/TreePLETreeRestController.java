package ca.mcgill.ecse321.TreePLE.controller;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.TreePLE.dto.TreeDto;
import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Municipality.MunicipalityName;
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
	
	private TreeDto convertToDto(Tree t) {
		  TreeDto treeDto = modelMapper.map(t, TreeDto.class);
		 
		  return treeDto;
		}
	
	@PostMapping(value = { "/PlantTree/"})
	public TreeDto plantTree(
			@RequestParam (name="landType") LandType landtype,
			@RequestParam  (name="species") TreeSpecies species,
			@RequestParam  (name="height") double height,
			@RequestParam (name="diameter") double diameter,
			@RequestParam (name="longitude") double longitude,
			@RequestParam  (name="latitude") double latitude,
			@RequestParam ("municipality") MunicipalityName municipality
			
			) throws InvalidInputException {

	;
	Municipality mun= new Municipality();
	mun.setMunicipalityName(municipality);
		Tree tree= service.plantTree(landtype, species, height,diameter, longitude, latitude, mun);
		
		return convertToDto(tree);
}
	
	@PostMapping(value = { "/cutDownTree/" })
	public TreeDto cutDownTree(
			@RequestParam(name="tree") TreeDto tree) throws InvalidInputException {
		
	Tree t = service.getTreeByID(tree.getTreeID());
	service.cutDownTree(t);
		return convertToDto(t);
	}
	
	@GetMapping(value = { "/trees", "/trees/" })
	public ArrayList<TreeDto> listAllTrees() {
		ArrayList<TreeDto> trees = new ArrayList<TreeDto>();
		for (Tree tree : service.listAllTrees()) {
			trees.add(convertToDto(tree));
		}
		return trees;
	}
	
}
package ca.mcgill.ecse321.TreePLE.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping(value = { "/PlantTree/{landType}/{species}/{height}/{diameter}/{longitude}/{latitude}/{municipality}/"})
	public TreeDto plantTree(
			@PathVariable ("landType") LandType landtype,
			@PathVariable  ("species") TreeSpecies species,
			@PathVariable  ("height") double height,
			@PathVariable  ("longitude") double longitude,
			@PathVariable  ("latitude") double latitude,
			@PathVariable  ("diameter") double diameter,
			@PathVariable  ("municipality") MunicipalityName municipality
			
			) throws InvalidInputException {

	;
	Municipality mun= new Municipality();
	mun.setMunicipalityName(municipality);
		Tree tree= service.plantTree(landtype, species, height,diameter, longitude, latitude, mun);
		
		return convertToDto(tree);
}
	
}
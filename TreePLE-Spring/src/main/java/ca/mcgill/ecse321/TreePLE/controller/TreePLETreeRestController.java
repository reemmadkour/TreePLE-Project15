package ca.mcgill.ecse321.TreePLE.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.TreePLE.dto.TreeDto;
import ca.mcgill.ecse321.TreePLE.model.Tree;
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
	
	@PostMapping(value = { "/PlantTree/{landType}/{species}/{height}/{diameter}/{longitude}/{latidude}/{municipality}/"})
	public EventDto createEvent(
			@PathVariable ("landType") String name,
			@RequestParam Date date,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern="HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern="HH:mm") LocalTime endTime
			) throws InvalidInputException {
		@SuppressWarnings("deprecation")
		Time startTimeSql = new Time(startTime.getHour(),startTime.getMinute(), 0);
		@SuppressWarnings("deprecation")
		Time endTimeSql = new Time(endTime.getHour(),endTime.getMinute(), 0);
		Event event = service.createEvent(name, date, startTimeSql, endTimeSql);
		return convertToDto(event);
}
	
}
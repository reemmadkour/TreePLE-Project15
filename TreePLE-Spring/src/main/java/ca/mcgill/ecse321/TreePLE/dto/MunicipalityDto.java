package ca.mcgill.ecse321.TreePLE.dto;

import java.util.ArrayList;
import java.util.List;


public class MunicipalityDto {

	private String name;
	private List<MunicipalityDto> municipalities;
	private List<TreeDto> trees;
	
	
	public MunicipalityDto() {
		
	}
	
	public MunicipalityDto(String name) {
		this.name=name;
	}
	
	public MunicipalityDto(String name, ArrayList<TreeDto> arrayList) {
		this.name = name;
		this.trees = arrayList;
	}
	
	public String getName() {
		return name;
	}
	
	public List<MunicipalityDto> getMunicipalities(){
		return municipalities;
	}
	
	public List<TreeDto> getTrees(){
		return trees;
	}
	
	public void setTrees (List<TreeDto> trees) {
		this.trees = trees;
	}
}

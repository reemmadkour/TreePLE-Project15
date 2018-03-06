package ca.mcgill.ecse321.TreePLE.dto;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.TreePLE.model.Municipality.MunicipalityName;


public class MunicipalityDto {

	private MunicipalityName name;
	private List<MunicipalityDto> municipalities;
	private List<TreeDto> trees;
	
	
	public MunicipalityDto() {
		
	}
	
	public MunicipalityDto(MunicipalityName name) {
		this.name=name;
		this.trees = new ArrayList<TreeDto>();
	}
	
	
	public MunicipalityDto(MunicipalityName name, ArrayList<TreeDto> arrayList) {
		this.name = name;
		this.trees = arrayList;
	}
	
	public MunicipalityName getName() {
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

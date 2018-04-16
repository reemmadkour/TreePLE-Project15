package ca.mcgill.ecse321.TreePLE.dto;

import java.util.List;

import ca.mcgill.ecse321.TreePLE.model.Person;
import ca.mcgill.ecse321.TreePLE.model.Report;
import ca.mcgill.ecse321.TreePLE.model.Tree;

public class ForecastDto {
	private int fID;
	private PersonDto person;
	private List<ReportDto> report;
	  private List<TreeDto> treesToBePlanted;
	  private List<TreeDto> treesToBeCut;
	  private List<TreeDto> currentTrees;
	   
	
	 public List<TreeDto> getTreesToBePlanted(){
		  return treesToBePlanted;
	  }
	

	  public List<TreeDto> getTreesToBeCut(){
		  return treesToBeCut;
	  }
	
	  
	
	  public void setCurrentTrees(List<TreeDto>trees){
		  this.currentTrees=trees;
	  }
	
	 
	  
	  public void setTreesToBePlanted(List<TreeDto>trees){
		  this.treesToBePlanted=trees;
	  }
	

	  public void setTreesToBeCut(List<TreeDto>trees){
		  this.treesToBeCut=trees;
	  }


	public int getfID() {
		return fID;
	}


	public void setfID(int fID) {
		this.fID = fID;
	}


	public PersonDto getPerson() {
		return person;
	}


	public void setPerson(PersonDto person) {
		this.person = person;
	}


	public List<ReportDto> getReport() {
		return report;
	}


	public void setReport(List<ReportDto> report) {
		this.report = report;
	}
	  
	
	 
	  
	
}

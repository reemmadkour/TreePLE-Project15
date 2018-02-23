package ca.mcgill.ecse321.TreePLE.dto;

import java.util.*;

import ca.mcgill.ecse321.TreePLE.model.Status.TreeState; 

public class StatusDto {

	private TreeState treeState;
	
	public void StatusDto(TreeState treeState) {
		this.treeState=treeState;
	}
	
	public TreeState getTreeState() {
		return treeState;
	}
}

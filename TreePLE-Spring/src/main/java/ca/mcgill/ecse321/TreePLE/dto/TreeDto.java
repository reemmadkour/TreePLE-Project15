package ca.mcgill.ecse321.TreePLE.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Tree.LandType;
import ca.mcgill.ecse321.TreePLE.model.Tree.TreeSpecies;
import ca.mcgill.ecse321.TreePLE.model.Tree;


public class TreeDto {

	 private TreeSpecies treeSpecies;
	  private double height;
	  private double diameter;
	  private double longitude;
	  private double latitude;
	  private LandType landType;
	  private List<StatusDto> currentStatus;
	  private int treeID;
	  private MunicipalityDto municipality;
	  private int id;
	  
	  public TreeDto() {
		  
	  }
	  
	  public TreeDto(TreeSpecies treeSpecies,double height, double diameter, double longitude, double latitude, LandType landtype, int treeID, MunicipalityDto municipality) {
			this.treeSpecies = treeSpecies;
			this.height = height;
			this.diameter = diameter;
			this.longitude = longitude;
			this.latitude=latitude;
			this.landType=landtype;
			this.treeID=treeID;
			this.municipality=municipality;
		
			
		}
	  
	  public int getId()
		{
			return id;
		}
	  
	  public TreeSpecies getTreeSpecies() {
			return treeSpecies;
		}

		public double getHeight() {
			return height;
		}

		public double getDiameter() {
			return diameter;
		}

		public double getlongitude() {
			return longitude;
		}
		
		public double getLatitude() {
			return latitude;
		}

		public LandType getLandType() {
			return landType;
		}
		
		public List<StatusDto> getcurrentStatus(){
			return currentStatus;
		}
		
		public int getTreeID() {
			return treeID;
		}
		
		public MunicipalityDto getMunicipality() {
			return municipality;
		}
		
		public void setMunicipality (MunicipalityDto mun) {
			this.municipality=mun;
		}
		
}




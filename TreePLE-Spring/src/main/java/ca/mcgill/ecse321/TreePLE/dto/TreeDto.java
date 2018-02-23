package ca.mcgill.ecse321.TreePLE.dto;

import ca.mcgill.ecse321.TreePLE.model.Tree.LandType;
import ca.mcgill.ecse321.TreePLE.model.Tree.TreeSpecies;

public class TreeDto {

	 private TreeSpecies treeSpecies;
	  private double height;
	  private double diameter;
	  private double longitude;
	  private double latitude;
	  private LandType landType;
	  
	  public TreeDto() {
		  
	  }
	  
	  public TreeSpecies(TreeSpecies treeSpecies,double height, double diameter, double longitude, double latitude, LandType landtype) {
			this.treeSpecies = treeSpecies;
			this.height = height;
			this.diameter = diameter;
			this.longitude = longitude;
			this.latitude=latitude;
			this.landType=landtype;
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

}




package ca.mcgill.ecse321.TreePLE.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportDto {
	
	  private double canopy;
	  private int carbonSequestration;
	  private double bioDiversityIndex;
	  private Date date;
	  public String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm dd/MM/yyyy");
	    String dateString = formatter.format(new Date());
	    return dateString;
		
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getBioDiversityIndex() {
		return bioDiversityIndex;
	}
	public void setBioDiversityIndex(double bioDiversityIndex) {
		this.bioDiversityIndex = bioDiversityIndex;
	}
	public int getCarbonSequestration() {
		return carbonSequestration;
	}
	public void setCarbonSequestration(int carbonSequestration) {
		this.carbonSequestration = carbonSequestration;
	}
	public double getCanopy() {
		return canopy;
	}
	public void setCanopy(double canopy) {
		this.canopy = canopy;
	}

	
	  

}

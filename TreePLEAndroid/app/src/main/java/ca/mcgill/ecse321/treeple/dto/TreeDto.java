package ca.mcgill.ecse321.treeple.dto;

import java.util.List;

/**
 * This class represents the attributes that describe a tree in our system
 * Created by leaakkari on 2018-04-05.
 */

public class TreeDto {

    double height;
    double diameter;
    double longitude;
    double latitude;
    String landtype;
    String municipality;
    String treeSpecies;
    String currentStatus;

    private int treeID;

    List<String> Forecast;

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public int getTreeID() {
        return treeID;
    }

    public void setTreeID(int treeID) {
        this.treeID = treeID;
    }

    public List<String> getForecast() {
        return Forecast;
    }

    public void setForecast(List<String> forecast) {
        Forecast = forecast;
    }





    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLandtype() {
        return landtype;
    }

    public void setLandtype(String landtype) {
        this.landtype = landtype;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getTreeSpecies() {
        return treeSpecies;
    }

    public void setTreeSpecies(String treeSpecies) {
        this.treeSpecies = treeSpecies;
    }

        //what to display as tree parameters
    public String toString(){
        String s = "Height = " + getHeight() + "/n" + "Diameter = " + getDiameter() + "/n" + "Species = " + getTreeSpecies() + "/n" +
                "Land Type = " + getLandtype() + "/n" + "Municipality = " + getMunicipality() + "/n" + "Status = " + getCurrentStatus();
        return s;
    }

}

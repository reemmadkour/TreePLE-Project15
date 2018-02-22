/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePLE.model;
import java.util.*;
import java.sql.Date;

// line 11 "../../../../../TreePLE.ump"
public class Tree
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TreeSpecies { Willow }
  public enum LandType { Residential, Institutional, Park, Municipal }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextTreeID = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tree Attributes
  private TreeSpecies treeSpecies;
  private double height;
  private int diameter;
  private int longitude;
  private int latitude;
  private LandType landType;

  //Autounique Attributes
  private int treeID;

  //Tree Associations
  private List<Status> status;
  private Municipality municipality;
  private TreePLE treePLE;
  private List<Forecast> forecasts;
  private List<Survey> surveies;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tree(double aHeight, int aDiameter, int aLongitude, int aLatitude, Municipality aMunicipality, TreePLE aTreePLE)
  {
    height = aHeight;
    diameter = aDiameter;
    longitude = aLongitude;
    latitude = aLatitude;
    treeID = nextTreeID++;
    status = new ArrayList<Status>();
    boolean didAddMunicipality = setMunicipality(aMunicipality);
    if (!didAddMunicipality)
    {
      throw new RuntimeException("Unable to create tree due to municipality");
    }
    boolean didAddTreePLE = setTreePLE(aTreePLE);
    if (!didAddTreePLE)
    {
      throw new RuntimeException("Unable to create tree due to treePLE");
    }
    forecasts = new ArrayList<Forecast>();
    surveies = new ArrayList<Survey>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTreeSpecies(TreeSpecies aTreeSpecies)
  {
    boolean wasSet = false;
    treeSpecies = aTreeSpecies;
    wasSet = true;
    return wasSet;
  }

  public boolean setHeight(double aHeight)
  {
    boolean wasSet = false;
    height = aHeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setDiameter(int aDiameter)
  {
    boolean wasSet = false;
    diameter = aDiameter;
    wasSet = true;
    return wasSet;
  }

  public boolean setLongitude(int aLongitude)
  {
    boolean wasSet = false;
    longitude = aLongitude;
    wasSet = true;
    return wasSet;
  }

  public boolean setLatitude(int aLatitude)
  {
    boolean wasSet = false;
    latitude = aLatitude;
    wasSet = true;
    return wasSet;
  }

  public boolean setLandType(LandType aLandType)
  {
    boolean wasSet = false;
    landType = aLandType;
    wasSet = true;
    return wasSet;
  }

  public TreeSpecies getTreeSpecies()
  {
    return treeSpecies;
  }

  public double getHeight()
  {
    return height;
  }

  public int getDiameter()
  {
    return diameter;
  }

  public int getLongitude()
  {
    return longitude;
  }

  public int getLatitude()
  {
    return latitude;
  }

  public LandType getLandType()
  {
    return landType;
  }

  public int getTreeID()
  {
    return treeID;
  }

  public Status getStatus(int index)
  {
    Status aStatus = status.get(index);
    return aStatus;
  }

  public List<Status> getStatus()
  {
    List<Status> newStatus = Collections.unmodifiableList(status);
    return newStatus;
  }

  public int numberOfStatus()
  {
    int number = status.size();
    return number;
  }

  public boolean hasStatus()
  {
    boolean has = status.size() > 0;
    return has;
  }

  public int indexOfStatus(Status aStatus)
  {
    int index = status.indexOf(aStatus);
    return index;
  }

  public Municipality getMunicipality()
  {
    return municipality;
  }

  public TreePLE getTreePLE()
  {
    return treePLE;
  }

  public Forecast getForecast(int index)
  {
    Forecast aForecast = forecasts.get(index);
    return aForecast;
  }

  public List<Forecast> getForecasts()
  {
    List<Forecast> newForecasts = Collections.unmodifiableList(forecasts);
    return newForecasts;
  }

  public int numberOfForecasts()
  {
    int number = forecasts.size();
    return number;
  }

  public boolean hasForecasts()
  {
    boolean has = forecasts.size() > 0;
    return has;
  }

  public int indexOfForecast(Forecast aForecast)
  {
    int index = forecasts.indexOf(aForecast);
    return index;
  }

  public Survey getSurvey(int index)
  {
    Survey aSurvey = surveies.get(index);
    return aSurvey;
  }

  public List<Survey> getSurveies()
  {
    List<Survey> newSurveies = Collections.unmodifiableList(surveies);
    return newSurveies;
  }

  public int numberOfSurveies()
  {
    int number = surveies.size();
    return number;
  }

  public boolean hasSurveies()
  {
    boolean has = surveies.size() > 0;
    return has;
  }

  public int indexOfSurvey(Survey aSurvey)
  {
    int index = surveies.indexOf(aSurvey);
    return index;
  }

  public static int minimumNumberOfStatus()
  {
    return 0;
  }

  public Status addStatus(Date aPlantingDate, Date aCuttingDate)
  {
    return new Status(aPlantingDate, aCuttingDate, this);
  }

  public boolean addStatus(Status aStatus)
  {
    boolean wasAdded = false;
    if (status.contains(aStatus)) { return false; }
    Tree existingTree = aStatus.getTree();
    boolean isNewTree = existingTree != null && !this.equals(existingTree);
    if (isNewTree)
    {
      aStatus.setTree(this);
    }
    else
    {
      status.add(aStatus);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStatus(Status aStatus)
  {
    boolean wasRemoved = false;
    //Unable to remove aStatus, as it must always have a tree
    if (!this.equals(aStatus.getTree()))
    {
      status.remove(aStatus);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addStatusAt(Status aStatus, int index)
  {  
    boolean wasAdded = false;
    if(addStatus(aStatus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStatus()) { index = numberOfStatus() - 1; }
      status.remove(aStatus);
      status.add(index, aStatus);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStatusAt(Status aStatus, int index)
  {
    boolean wasAdded = false;
    if(status.contains(aStatus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStatus()) { index = numberOfStatus() - 1; }
      status.remove(aStatus);
      status.add(index, aStatus);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStatusAt(aStatus, index);
    }
    return wasAdded;
  }

  public boolean setMunicipality(Municipality aMunicipality)
  {
    boolean wasSet = false;
    if (aMunicipality == null)
    {
      return wasSet;
    }

    Municipality existingMunicipality = municipality;
    municipality = aMunicipality;
    if (existingMunicipality != null && !existingMunicipality.equals(aMunicipality))
    {
      existingMunicipality.removeTree(this);
    }
    municipality.addTree(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setTreePLE(TreePLE aTreePLE)
  {
    boolean wasSet = false;
    if (aTreePLE == null)
    {
      return wasSet;
    }

    TreePLE existingTreePLE = treePLE;
    treePLE = aTreePLE;
    if (existingTreePLE != null && !existingTreePLE.equals(aTreePLE))
    {
      existingTreePLE.removeTree(this);
    }
    treePLE.addTree(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfForecasts()
  {
    return 0;
  }

  public boolean addForecast(Forecast aForecast)
  {
    boolean wasAdded = false;
    if (forecasts.contains(aForecast)) { return false; }
    forecasts.add(aForecast);
    if (aForecast.indexOfTree(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aForecast.addTree(this);
      if (!wasAdded)
      {
        forecasts.remove(aForecast);
      }
    }
    return wasAdded;
  }

  public boolean removeForecast(Forecast aForecast)
  {
    boolean wasRemoved = false;
    if (!forecasts.contains(aForecast))
    {
      return wasRemoved;
    }

    int oldIndex = forecasts.indexOf(aForecast);
    forecasts.remove(oldIndex);
    if (aForecast.indexOfTree(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aForecast.removeTree(this);
      if (!wasRemoved)
      {
        forecasts.add(oldIndex,aForecast);
      }
    }
    return wasRemoved;
  }

  public boolean addForecastAt(Forecast aForecast, int index)
  {  
    boolean wasAdded = false;
    if(addForecast(aForecast))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfForecasts()) { index = numberOfForecasts() - 1; }
      forecasts.remove(aForecast);
      forecasts.add(index, aForecast);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveForecastAt(Forecast aForecast, int index)
  {
    boolean wasAdded = false;
    if(forecasts.contains(aForecast))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfForecasts()) { index = numberOfForecasts() - 1; }
      forecasts.remove(aForecast);
      forecasts.add(index, aForecast);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addForecastAt(aForecast, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSurveies()
  {
    return 0;
  }

  public Survey addSurvey(Date aDate, Person aPerson)
  {
    return new Survey(aDate, aPerson, this);
  }

  public boolean addSurvey(Survey aSurvey)
  {
    boolean wasAdded = false;
    if (surveies.contains(aSurvey)) { return false; }
    Tree existingTrees = aSurvey.getTrees();
    boolean isNewTrees = existingTrees != null && !this.equals(existingTrees);
    if (isNewTrees)
    {
      aSurvey.setTrees(this);
    }
    else
    {
      surveies.add(aSurvey);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSurvey(Survey aSurvey)
  {
    boolean wasRemoved = false;
    //Unable to remove aSurvey, as it must always have a trees
    if (!this.equals(aSurvey.getTrees()))
    {
      surveies.remove(aSurvey);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSurveyAt(Survey aSurvey, int index)
  {  
    boolean wasAdded = false;
    if(addSurvey(aSurvey))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSurveies()) { index = numberOfSurveies() - 1; }
      surveies.remove(aSurvey);
      surveies.add(index, aSurvey);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSurveyAt(Survey aSurvey, int index)
  {
    boolean wasAdded = false;
    if(surveies.contains(aSurvey))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSurveies()) { index = numberOfSurveies() - 1; }
      surveies.remove(aSurvey);
      surveies.add(index, aSurvey);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSurveyAt(aSurvey, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=status.size(); i > 0; i--)
    {
      Status aStatus = status.get(i - 1);
      aStatus.delete();
    }
    Municipality placeholderMunicipality = municipality;
    this.municipality = null;
    placeholderMunicipality.removeTree(this);
    TreePLE placeholderTreePLE = treePLE;
    this.treePLE = null;
    placeholderTreePLE.removeTree(this);
    ArrayList<Forecast> copyOfForecasts = new ArrayList<Forecast>(forecasts);
    forecasts.clear();
    for(Forecast aForecast : copyOfForecasts)
    {
      aForecast.removeTree(this);
    }
    for(int i=surveies.size(); i > 0; i--)
    {
      Survey aSurvey = surveies.get(i - 1);
      aSurvey.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "treeID" + ":" + getTreeID()+ "," +
            "height" + ":" + getHeight()+ "," +
            "diameter" + ":" + getDiameter()+ "," +
            "longitude" + ":" + getLongitude()+ "," +
            "latitude" + ":" + getLatitude()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "treeSpecies" + "=" + (getTreeSpecies() != null ? !getTreeSpecies().equals(this)  ? getTreeSpecies().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "landType" + "=" + (getLandType() != null ? !getLandType().equals(this)  ? getLandType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "municipality = "+(getMunicipality()!=null?Integer.toHexString(System.identityHashCode(getMunicipality())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "treePLE = "+(getTreePLE()!=null?Integer.toHexString(System.identityHashCode(getTreePLE())):"null");
  }
}
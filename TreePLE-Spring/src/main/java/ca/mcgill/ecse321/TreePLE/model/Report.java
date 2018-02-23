/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePLE.model;
import java.util.*;

// line 61 "../../../../../TreePLE.ump"
public class Report
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Report Attributes
  private int canopy;
  private int carbonSequestration;
  private double bioDiversityIndex;

  //Report Associations
  private List<Forecast> forecasts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Report(int aCanopy, int aCarbonSequestration, double aBioDiversityIndex)
  {
    canopy = aCanopy;
    carbonSequestration = aCarbonSequestration;
    bioDiversityIndex = aBioDiversityIndex;
    forecasts = new ArrayList<Forecast>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCanopy(int aCanopy)
  {
    boolean wasSet = false;
    canopy = aCanopy;
    wasSet = true;
    return wasSet;
  }

  public boolean setCarbonSequestration(int aCarbonSequestration)
  {
    boolean wasSet = false;
    carbonSequestration = aCarbonSequestration;
    wasSet = true;
    return wasSet;
  }

  public boolean setBioDiversityIndex(double aBioDiversityIndex)
  {
    boolean wasSet = false;
    bioDiversityIndex = aBioDiversityIndex;
    wasSet = true;
    return wasSet;
  }

  public int getCanopy()
  {
    return canopy;
  }

  public int getCarbonSequestration()
  {
    return carbonSequestration;
  }

  public double getBioDiversityIndex()
  {
    return bioDiversityIndex;
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

  public static int minimumNumberOfForecasts()
  {
    return 0;
  }

  public boolean addForecast(Forecast aForecast)
  {
    boolean wasAdded = false;
    if (forecasts.contains(aForecast)) { return false; }
    forecasts.add(aForecast);
    if (aForecast.indexOfReport(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aForecast.addReport(this);
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
    if (aForecast.indexOfReport(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aForecast.removeReport(this);
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

  public void delete()
  {
    ArrayList<Forecast> copyOfForecasts = new ArrayList<Forecast>(forecasts);
    forecasts.clear();
    for(Forecast aForecast : copyOfForecasts)
    {
      aForecast.removeReport(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "canopy" + ":" + getCanopy()+ "," +
            "carbonSequestration" + ":" + getCarbonSequestration()+ "," +
            "bioDiversityIndex" + ":" + getBioDiversityIndex()+ "]";
  }
}
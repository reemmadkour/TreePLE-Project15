/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePLE.model;

// line 65 "../../../../../TreePLE.ump"
public class Report
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Report Attributes
  private double canopy;
  private int carbonSequestration;
  private double bioDiversityIndex;

  //Report Associations
  private Forecast forecast;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Report(double aCanopy, int aCarbonSequestration, double aBioDiversityIndex, Forecast aForecast)
  {
    canopy = aCanopy;
    carbonSequestration = aCarbonSequestration;
    bioDiversityIndex = aBioDiversityIndex;
    boolean didAddForecast = setForecast(aForecast);
    if (!didAddForecast)
    {
      throw new RuntimeException("Unable to create report due to forecast");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCanopy(double aCanopy)
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

  public double getCanopy()
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

  public Forecast getForecast()
  {
    return forecast;
  }

  public boolean setForecast(Forecast aForecast)
  {
    boolean wasSet = false;
    if (aForecast == null)
    {
      return wasSet;
    }

    Forecast existingForecast = forecast;
    forecast = aForecast;
    if (existingForecast != null && !existingForecast.equals(aForecast))
    {
      existingForecast.removeReport(this);
    }
    forecast.addReport(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Forecast placeholderForecast = forecast;
    this.forecast = null;
    placeholderForecast.removeReport(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "canopy" + ":" + getCanopy()+ "," +
            "carbonSequestration" + ":" + getCarbonSequestration()+ "," +
            "bioDiversityIndex" + ":" + getBioDiversityIndex()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "forecast = "+(getForecast()!=null?Integer.toHexString(System.identityHashCode(getForecast())):"null");
  }
}
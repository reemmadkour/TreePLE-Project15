/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePLE.model;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

// line 68 "../../../../../TreePLE.ump"
public class SystemDate
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SystemDate Attributes
  private LocalDate date;

  //SystemDate Associations
  private List<Status> status;
  private List<Forecast> forecast;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SystemDate(LocalDate localDate)
  {
    date = localDate;
    status = new ArrayList<Status>();
    forecast = new ArrayList<Forecast>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(LocalDate aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public LocalDate getDate()
  {
    return date;
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

  public Forecast getForecast(int index)
  {
    Forecast aForecast = forecast.get(index);
    return aForecast;
  }

  public List<Forecast> getForecast()
  {
    List<Forecast> newForecast = Collections.unmodifiableList(forecast);
    return newForecast;
  }

  public int numberOfForecast()
  {
    int number = forecast.size();
    return number;
  }

  public boolean hasForecast()
  {
    boolean has = forecast.size() > 0;
    return has;
  }

  public int indexOfForecast(Forecast aForecast)
  {
    int index = forecast.indexOf(aForecast);
    return index;
  }

  public static int minimumNumberOfStatus()
  {
    return 0;
  }

  public boolean addStatus(Status aStatus)
  {
    boolean wasAdded = false;
    if (status.contains(aStatus)) { return false; }
    status.add(aStatus);
    if (aStatus.indexOfSystemDate(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aStatus.addSystemDate(this);
      if (!wasAdded)
      {
        status.remove(aStatus);
      }
    }
    return wasAdded;
  }

  public boolean removeStatus(Status aStatus)
  {
    boolean wasRemoved = false;
    if (!status.contains(aStatus))
    {
      return wasRemoved;
    }

    int oldIndex = status.indexOf(aStatus);
    status.remove(oldIndex);
    if (aStatus.indexOfSystemDate(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aStatus.removeSystemDate(this);
      if (!wasRemoved)
      {
        status.add(oldIndex,aStatus);
      }
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

  public static int minimumNumberOfForecast()
  {
    return 0;
  }

  public Forecast addForecast()
  {
    return new Forecast(this);
  }

  public boolean addForecast(Forecast aForecast)
  {
    boolean wasAdded = false;
    if (forecast.contains(aForecast)) { return false; }
    SystemDate existingSystemDate = aForecast.getSystemDate();
    boolean isNewSystemDate = existingSystemDate != null && !this.equals(existingSystemDate);
    if (isNewSystemDate)
    {
      aForecast.setSystemDate(this);
    }
    else
    {
      forecast.add(aForecast);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeForecast(Forecast aForecast)
  {
    boolean wasRemoved = false;
    //Unable to remove aForecast, as it must always have a systemDate
    if (!this.equals(aForecast.getSystemDate()))
    {
      forecast.remove(aForecast);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addForecastAt(Forecast aForecast, int index)
  {  
    boolean wasAdded = false;
    if(addForecast(aForecast))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfForecast()) { index = numberOfForecast() - 1; }
      forecast.remove(aForecast);
      forecast.add(index, aForecast);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveForecastAt(Forecast aForecast, int index)
  {
    boolean wasAdded = false;
    if(forecast.contains(aForecast))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfForecast()) { index = numberOfForecast() - 1; }
      forecast.remove(aForecast);
      forecast.add(index, aForecast);
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
    ArrayList<Status> copyOfStatus = new ArrayList<Status>(status);
    status.clear();
    for(Status aStatus : copyOfStatus)
    {
      aStatus.removeSystemDate(this);
    }
    for(int i=forecast.size(); i > 0; i--)
    {
      Forecast aForecast = forecast.get(i - 1);
      aForecast.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePLE.model;
import java.sql.Date;
import java.util.*;

// line 81 "../../../../../TreePLE.ump"
public class Survey
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Survey Attributes
  private Date date;

  //Survey Associations
  private List<Forecast> forecasts;
  private Person person;
  private Tree trees;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetPerson;
  private boolean canSetTrees;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Survey(Date aDate, Person aPerson, Tree aTrees)
  {
    cachedHashCode = -1;
    canSetPerson = true;
    canSetTrees = true;
    date = aDate;
    forecasts = new ArrayList<Forecast>();
    boolean didAddPerson = setPerson(aPerson);
    if (!didAddPerson)
    {
      throw new RuntimeException("Unable to create survey due to person");
    }
    boolean didAddTrees = setTrees(aTrees);
    if (!didAddTrees)
    {
      throw new RuntimeException("Unable to create survey due to trees");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
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

  public Person getPerson()
  {
    return person;
  }

  public Tree getTrees()
  {
    return trees;
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
    if (aForecast.indexOfSurvey(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aForecast.addSurvey(this);
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
    if (aForecast.indexOfSurvey(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aForecast.removeSurvey(this);
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

  public boolean setPerson(Person aPerson)
  {
    boolean wasSet = false;
    if (!canSetPerson) { return false; }
    if (aPerson == null)
    {
      return wasSet;
    }

    Person existingPerson = person;
    person = aPerson;
    if (existingPerson != null && !existingPerson.equals(aPerson))
    {
      existingPerson.removeSurvey(this);
    }
    if (!person.addSurvey(this))
    {
      person = existingPerson;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }

  public boolean setTrees(Tree aTrees)
  {
    boolean wasSet = false;
    if (!canSetTrees) { return false; }
    if (aTrees == null)
    {
      return wasSet;
    }

    Tree existingTrees = trees;
    trees = aTrees;
    if (existingTrees != null && !existingTrees.equals(aTrees))
    {
      existingTrees.removeSurvey(this);
    }
    if (!trees.addSurvey(this))
    {
      trees = existingTrees;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    Survey compareTo = (Survey)obj;
  
    if (getPerson() == null && compareTo.getPerson() != null)
    {
      return false;
    }
    else if (getPerson() != null && !getPerson().equals(compareTo.getPerson()))
    {
      return false;
    }

    if (getTrees() == null && compareTo.getTrees() != null)
    {
      return false;
    }
    else if (getTrees() != null && !getTrees().equals(compareTo.getTrees()))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (getPerson() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getPerson().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getTrees() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getTrees().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetPerson = false;
    canSetTrees = false;
    return cachedHashCode;
  }

  public void delete()
  {
    ArrayList<Forecast> copyOfForecasts = new ArrayList<Forecast>(forecasts);
    forecasts.clear();
    for(Forecast aForecast : copyOfForecasts)
    {
      aForecast.removeSurvey(this);
    }
    Person placeholderPerson = person;
    this.person = null;
    placeholderPerson.removeSurvey(this);
    Tree placeholderTrees = trees;
    this.trees = null;
    placeholderTrees.removeSurvey(this);
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "person = "+(getPerson()!=null?Integer.toHexString(System.identityHashCode(getPerson())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "trees = "+(getTrees()!=null?Integer.toHexString(System.identityHashCode(getTrees())):"null");
  }
}
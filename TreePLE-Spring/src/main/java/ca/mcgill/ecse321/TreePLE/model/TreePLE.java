/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePLE.model;
import java.util.*;

// line 3 "../../../../../TreePLE.ump"
public class TreePLE
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TreePLE Associations
  private List<Tree> trees;
  private List<Person> person;
  private List<Report> reports;
  private List<Municipality> municipality;
  private List<Forecast> forecast;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TreePLE()
  {
    trees = new ArrayList<Tree>();
    person = new ArrayList<Person>();
    reports = new ArrayList<Report>();
    municipality = new ArrayList<Municipality>();
    forecast = new ArrayList<Forecast>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Tree getTree(int index)
  {
    Tree aTree = trees.get(index);
    return aTree;
  }

  public List<Tree> getTrees()
  {
    List<Tree> newTrees = Collections.unmodifiableList(trees);
    return newTrees;
  }

  public int numberOfTrees()
  {
    int number = trees.size();
    return number;
  }

  public boolean hasTrees()
  {
    boolean has = trees.size() > 0;
    return has;
  }

  public int indexOfTree(Tree aTree)
  {
    int index = trees.indexOf(aTree);
    return index;
  }

  public Person getPerson(int index)
  {
    Person aPerson = person.get(index);
    return aPerson;
  }

  public List<Person> getPerson()
  {
    List<Person> newPerson = Collections.unmodifiableList(person);
    return newPerson;
  }

  public int numberOfPerson()
  {
    int number = person.size();
    return number;
  }

  public boolean hasPerson()
  {
    boolean has = person.size() > 0;
    return has;
  }

  public int indexOfPerson(Person aPerson)
  {
    int index = person.indexOf(aPerson);
    return index;
  }

  public Report getReport(int index)
  {
    Report aReport = reports.get(index);
    return aReport;
  }

  public List<Report> getReports()
  {
    List<Report> newReports = Collections.unmodifiableList(reports);
    return newReports;
  }

  public int numberOfReports()
  {
    int number = reports.size();
    return number;
  }

  public boolean hasReports()
  {
    boolean has = reports.size() > 0;
    return has;
  }

  public int indexOfReport(Report aReport)
  {
    int index = reports.indexOf(aReport);
    return index;
  }

  public Municipality getMunicipality(int index)
  {
    Municipality aMunicipality = municipality.get(index);
    return aMunicipality;
  }

  public List<Municipality> getMunicipality()
  {
    List<Municipality> newMunicipality = Collections.unmodifiableList(municipality);
    return newMunicipality;
  }

  public int numberOfMunicipality()
  {
    int number = municipality.size();
    return number;
  }

  public boolean hasMunicipality()
  {
    boolean has = municipality.size() > 0;
    return has;
  }

  public int indexOfMunicipality(Municipality aMunicipality)
  {
    int index = municipality.indexOf(aMunicipality);
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

  public static int minimumNumberOfTrees()
  {
    return 0;
  }

  public Tree addTree(double aHeight, int aDiameter, int aLongitude, int aLatitude, Municipality aMunicipality)
  {
    return new Tree(aHeight, aDiameter, aLongitude, aLatitude, aMunicipality, this);
  }

  public boolean addTree(Tree aTree)
  {
    boolean wasAdded = false;
    if (trees.contains(aTree)) { return false; }
    TreePLE existingTreePLE = aTree.getTreePLE();
    boolean isNewTreePLE = existingTreePLE != null && !this.equals(existingTreePLE);
    if (isNewTreePLE)
    {
      aTree.setTreePLE(this);
    }
    else
    {
      trees.add(aTree);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTree(Tree aTree)
  {
    boolean wasRemoved = false;
    //Unable to remove aTree, as it must always have a treePLE
    if (!this.equals(aTree.getTreePLE()))
    {
      trees.remove(aTree);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTreeAt(Tree aTree, int index)
  {  
    boolean wasAdded = false;
    if(addTree(aTree))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTrees()) { index = numberOfTrees() - 1; }
      trees.remove(aTree);
      trees.add(index, aTree);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTreeAt(Tree aTree, int index)
  {
    boolean wasAdded = false;
    if(trees.contains(aTree))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTrees()) { index = numberOfTrees() - 1; }
      trees.remove(aTree);
      trees.add(index, aTree);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTreeAt(aTree, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfPerson()
  {
    return 0;
  }

  public Person addPerson(String aName)
  {
    return new Person(aName, this);
  }

  public boolean addPerson(Person aPerson)
  {
    boolean wasAdded = false;
    if (person.contains(aPerson)) { return false; }
    TreePLE existingTreePLE = aPerson.getTreePLE();
    boolean isNewTreePLE = existingTreePLE != null && !this.equals(existingTreePLE);
    if (isNewTreePLE)
    {
      aPerson.setTreePLE(this);
    }
    else
    {
      person.add(aPerson);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePerson(Person aPerson)
  {
    boolean wasRemoved = false;
    //Unable to remove aPerson, as it must always have a treePLE
    if (!this.equals(aPerson.getTreePLE()))
    {
      person.remove(aPerson);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addPersonAt(Person aPerson, int index)
  {  
    boolean wasAdded = false;
    if(addPerson(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPerson()) { index = numberOfPerson() - 1; }
      person.remove(aPerson);
      person.add(index, aPerson);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePersonAt(Person aPerson, int index)
  {
    boolean wasAdded = false;
    if(person.contains(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPerson()) { index = numberOfPerson() - 1; }
      person.remove(aPerson);
      person.add(index, aPerson);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPersonAt(aPerson, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfReports()
  {
    return 0;
  }

  public Report addReport(int aCanopy, int aCarbonSequestration, double aBioDiversityIndex)
  {
    return new Report(aCanopy, aCarbonSequestration, aBioDiversityIndex, this);
  }

  public boolean addReport(Report aReport)
  {
    boolean wasAdded = false;
    if (reports.contains(aReport)) { return false; }
    TreePLE existingTreePLE = aReport.getTreePLE();
    boolean isNewTreePLE = existingTreePLE != null && !this.equals(existingTreePLE);
    if (isNewTreePLE)
    {
      aReport.setTreePLE(this);
    }
    else
    {
      reports.add(aReport);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReport(Report aReport)
  {
    boolean wasRemoved = false;
    //Unable to remove aReport, as it must always have a treePLE
    if (!this.equals(aReport.getTreePLE()))
    {
      reports.remove(aReport);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addReportAt(Report aReport, int index)
  {  
    boolean wasAdded = false;
    if(addReport(aReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReports()) { index = numberOfReports() - 1; }
      reports.remove(aReport);
      reports.add(index, aReport);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReportAt(Report aReport, int index)
  {
    boolean wasAdded = false;
    if(reports.contains(aReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReports()) { index = numberOfReports() - 1; }
      reports.remove(aReport);
      reports.add(index, aReport);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReportAt(aReport, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfMunicipality()
  {
    return 0;
  }

  public Municipality addMunicipality(String aName)
  {
    return new Municipality(aName, this);
  }

  public boolean addMunicipality(Municipality aMunicipality)
  {
    boolean wasAdded = false;
    if (municipality.contains(aMunicipality)) { return false; }
    TreePLE existingTreePLE = aMunicipality.getTreePLE();
    boolean isNewTreePLE = existingTreePLE != null && !this.equals(existingTreePLE);
    if (isNewTreePLE)
    {
      aMunicipality.setTreePLE(this);
    }
    else
    {
      municipality.add(aMunicipality);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMunicipality(Municipality aMunicipality)
  {
    boolean wasRemoved = false;
    //Unable to remove aMunicipality, as it must always have a treePLE
    if (!this.equals(aMunicipality.getTreePLE()))
    {
      municipality.remove(aMunicipality);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addMunicipalityAt(Municipality aMunicipality, int index)
  {  
    boolean wasAdded = false;
    if(addMunicipality(aMunicipality))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMunicipality()) { index = numberOfMunicipality() - 1; }
      municipality.remove(aMunicipality);
      municipality.add(index, aMunicipality);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMunicipalityAt(Municipality aMunicipality, int index)
  {
    boolean wasAdded = false;
    if(municipality.contains(aMunicipality))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMunicipality()) { index = numberOfMunicipality() - 1; }
      municipality.remove(aMunicipality);
      municipality.add(index, aMunicipality);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMunicipalityAt(aMunicipality, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfForecast()
  {
    return 0;
  }

  public Forecast addForecast(SystemDate aSystemDate)
  {
    return new Forecast(this, aSystemDate);
  }

  public boolean addForecast(Forecast aForecast)
  {
    boolean wasAdded = false;
    if (forecast.contains(aForecast)) { return false; }
    TreePLE existingTreePLE = aForecast.getTreePLE();
    boolean isNewTreePLE = existingTreePLE != null && !this.equals(existingTreePLE);
    if (isNewTreePLE)
    {
      aForecast.setTreePLE(this);
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
    //Unable to remove aForecast, as it must always have a treePLE
    if (!this.equals(aForecast.getTreePLE()))
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
    while (trees.size() > 0)
    {
      Tree aTree = trees.get(trees.size() - 1);
      aTree.delete();
      trees.remove(aTree);
    }
    
    while (person.size() > 0)
    {
      Person aPerson = person.get(person.size() - 1);
      aPerson.delete();
      person.remove(aPerson);
    }
    
    while (reports.size() > 0)
    {
      Report aReport = reports.get(reports.size() - 1);
      aReport.delete();
      reports.remove(aReport);
    }
    
    while (municipality.size() > 0)
    {
      Municipality aMunicipality = municipality.get(municipality.size() - 1);
      aMunicipality.delete();
      municipality.remove(aMunicipality);
    }
    
    while (forecast.size() > 0)
    {
      Forecast aForecast = forecast.get(forecast.size() - 1);
      aForecast.delete();
      forecast.remove(aForecast);
    }
    
  }

}
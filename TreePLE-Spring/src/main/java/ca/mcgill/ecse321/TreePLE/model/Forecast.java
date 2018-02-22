/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePLE.model;
import java.util.*;

// line 73 "../../../../../TreePLE.ump"
public class Forecast
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Forecast Associations
  private List<Report> report;
  private List<Survey> survey;
  private List<Tree> tree;
  private TreePLE treePLE;
  private SystemDate systemDate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Forecast(TreePLE aTreePLE, SystemDate aSystemDate)
  {
    report = new ArrayList<Report>();
    survey = new ArrayList<Survey>();
    tree = new ArrayList<Tree>();
    boolean didAddTreePLE = setTreePLE(aTreePLE);
    if (!didAddTreePLE)
    {
      throw new RuntimeException("Unable to create forecast due to treePLE");
    }
    boolean didAddSystemDate = setSystemDate(aSystemDate);
    if (!didAddSystemDate)
    {
      throw new RuntimeException("Unable to create forecast due to systemDate");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Report getReport(int index)
  {
    Report aReport = report.get(index);
    return aReport;
  }

  public List<Report> getReport()
  {
    List<Report> newReport = Collections.unmodifiableList(report);
    return newReport;
  }

  public int numberOfReport()
  {
    int number = report.size();
    return number;
  }

  public boolean hasReport()
  {
    boolean has = report.size() > 0;
    return has;
  }

  public int indexOfReport(Report aReport)
  {
    int index = report.indexOf(aReport);
    return index;
  }

  public Survey getSurvey(int index)
  {
    Survey aSurvey = survey.get(index);
    return aSurvey;
  }

  public List<Survey> getSurvey()
  {
    List<Survey> newSurvey = Collections.unmodifiableList(survey);
    return newSurvey;
  }

  public int numberOfSurvey()
  {
    int number = survey.size();
    return number;
  }

  public boolean hasSurvey()
  {
    boolean has = survey.size() > 0;
    return has;
  }

  public int indexOfSurvey(Survey aSurvey)
  {
    int index = survey.indexOf(aSurvey);
    return index;
  }

  public Tree getTree(int index)
  {
    Tree aTree = tree.get(index);
    return aTree;
  }

  public List<Tree> getTree()
  {
    List<Tree> newTree = Collections.unmodifiableList(tree);
    return newTree;
  }

  public int numberOfTree()
  {
    int number = tree.size();
    return number;
  }

  public boolean hasTree()
  {
    boolean has = tree.size() > 0;
    return has;
  }

  public int indexOfTree(Tree aTree)
  {
    int index = tree.indexOf(aTree);
    return index;
  }

  public TreePLE getTreePLE()
  {
    return treePLE;
  }

  public SystemDate getSystemDate()
  {
    return systemDate;
  }

  public static int minimumNumberOfReport()
  {
    return 0;
  }

  public boolean addReport(Report aReport)
  {
    boolean wasAdded = false;
    if (report.contains(aReport)) { return false; }
    report.add(aReport);
    if (aReport.indexOfForecast(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aReport.addForecast(this);
      if (!wasAdded)
      {
        report.remove(aReport);
      }
    }
    return wasAdded;
  }

  public boolean removeReport(Report aReport)
  {
    boolean wasRemoved = false;
    if (!report.contains(aReport))
    {
      return wasRemoved;
    }

    int oldIndex = report.indexOf(aReport);
    report.remove(oldIndex);
    if (aReport.indexOfForecast(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aReport.removeForecast(this);
      if (!wasRemoved)
      {
        report.add(oldIndex,aReport);
      }
    }
    return wasRemoved;
  }

  public boolean addReportAt(Report aReport, int index)
  {  
    boolean wasAdded = false;
    if(addReport(aReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReport()) { index = numberOfReport() - 1; }
      report.remove(aReport);
      report.add(index, aReport);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReportAt(Report aReport, int index)
  {
    boolean wasAdded = false;
    if(report.contains(aReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReport()) { index = numberOfReport() - 1; }
      report.remove(aReport);
      report.add(index, aReport);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReportAt(aReport, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSurvey()
  {
    return 0;
  }

  public boolean addSurvey(Survey aSurvey)
  {
    boolean wasAdded = false;
    if (survey.contains(aSurvey)) { return false; }
    survey.add(aSurvey);
    if (aSurvey.indexOfForecast(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSurvey.addForecast(this);
      if (!wasAdded)
      {
        survey.remove(aSurvey);
      }
    }
    return wasAdded;
  }

  public boolean removeSurvey(Survey aSurvey)
  {
    boolean wasRemoved = false;
    if (!survey.contains(aSurvey))
    {
      return wasRemoved;
    }

    int oldIndex = survey.indexOf(aSurvey);
    survey.remove(oldIndex);
    if (aSurvey.indexOfForecast(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSurvey.removeForecast(this);
      if (!wasRemoved)
      {
        survey.add(oldIndex,aSurvey);
      }
    }
    return wasRemoved;
  }

  public boolean addSurveyAt(Survey aSurvey, int index)
  {  
    boolean wasAdded = false;
    if(addSurvey(aSurvey))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSurvey()) { index = numberOfSurvey() - 1; }
      survey.remove(aSurvey);
      survey.add(index, aSurvey);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSurveyAt(Survey aSurvey, int index)
  {
    boolean wasAdded = false;
    if(survey.contains(aSurvey))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSurvey()) { index = numberOfSurvey() - 1; }
      survey.remove(aSurvey);
      survey.add(index, aSurvey);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSurveyAt(aSurvey, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTree()
  {
    return 0;
  }

  public boolean addTree(Tree aTree)
  {
    boolean wasAdded = false;
    if (tree.contains(aTree)) { return false; }
    tree.add(aTree);
    if (aTree.indexOfForecast(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTree.addForecast(this);
      if (!wasAdded)
      {
        tree.remove(aTree);
      }
    }
    return wasAdded;
  }

  public boolean removeTree(Tree aTree)
  {
    boolean wasRemoved = false;
    if (!tree.contains(aTree))
    {
      return wasRemoved;
    }

    int oldIndex = tree.indexOf(aTree);
    tree.remove(oldIndex);
    if (aTree.indexOfForecast(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTree.removeForecast(this);
      if (!wasRemoved)
      {
        tree.add(oldIndex,aTree);
      }
    }
    return wasRemoved;
  }

  public boolean addTreeAt(Tree aTree, int index)
  {  
    boolean wasAdded = false;
    if(addTree(aTree))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTree()) { index = numberOfTree() - 1; }
      tree.remove(aTree);
      tree.add(index, aTree);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTreeAt(Tree aTree, int index)
  {
    boolean wasAdded = false;
    if(tree.contains(aTree))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTree()) { index = numberOfTree() - 1; }
      tree.remove(aTree);
      tree.add(index, aTree);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTreeAt(aTree, index);
    }
    return wasAdded;
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
      existingTreePLE.removeForecast(this);
    }
    treePLE.addForecast(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setSystemDate(SystemDate aSystemDate)
  {
    boolean wasSet = false;
    if (aSystemDate == null)
    {
      return wasSet;
    }

    SystemDate existingSystemDate = systemDate;
    systemDate = aSystemDate;
    if (existingSystemDate != null && !existingSystemDate.equals(aSystemDate))
    {
      existingSystemDate.removeForecast(this);
    }
    systemDate.addForecast(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Report> copyOfReport = new ArrayList<Report>(report);
    report.clear();
    for(Report aReport : copyOfReport)
    {
      aReport.removeForecast(this);
    }
    ArrayList<Survey> copyOfSurvey = new ArrayList<Survey>(survey);
    survey.clear();
    for(Survey aSurvey : copyOfSurvey)
    {
      aSurvey.removeForecast(this);
    }
    ArrayList<Tree> copyOfTree = new ArrayList<Tree>(tree);
    tree.clear();
    for(Tree aTree : copyOfTree)
    {
      aTree.removeForecast(this);
    }
    TreePLE placeholderTreePLE = treePLE;
    this.treePLE = null;
    placeholderTreePLE.removeForecast(this);
    SystemDate placeholderSystemDate = systemDate;
    this.systemDate = null;
    placeholderSystemDate.removeForecast(this);
  }

}
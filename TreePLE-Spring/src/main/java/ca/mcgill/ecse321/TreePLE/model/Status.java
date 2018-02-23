/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePLE.model;
import java.util.*;

// line 36 "../../../../../TreePLE.ump"
public class Status
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TreeState { Planted, Diseased, ToBeCut, Cut }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Status Attributes
  private TreeState treeState;

  //Status Associations
  private Tree tree;
  private List<SystemDate> systemDates;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Status(Tree aTree)
  {
    boolean didAddTree = setTree(aTree);
    if (!didAddTree)
    {
      throw new RuntimeException("Unable to create status due to tree");
    }
    systemDates = new ArrayList<SystemDate>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTreeState(TreeState aTreeState)
  {
    boolean wasSet = false;
    treeState = aTreeState;
    wasSet = true;
    return wasSet;
  }

  public TreeState getTreeState()
  {
    return treeState;
  }

  public Tree getTree()
  {
    return tree;
  }

  public SystemDate getSystemDate(int index)
  {
    SystemDate aSystemDate = systemDates.get(index);
    return aSystemDate;
  }

  public List<SystemDate> getSystemDates()
  {
    List<SystemDate> newSystemDates = Collections.unmodifiableList(systemDates);
    return newSystemDates;
  }

  public int numberOfSystemDates()
  {
    int number = systemDates.size();
    return number;
  }

  public boolean hasSystemDates()
  {
    boolean has = systemDates.size() > 0;
    return has;
  }

  public int indexOfSystemDate(SystemDate aSystemDate)
  {
    int index = systemDates.indexOf(aSystemDate);
    return index;
  }

  public boolean setTree(Tree aTree)
  {
    boolean wasSet = false;
    if (aTree == null)
    {
      return wasSet;
    }

    Tree existingTree = tree;
    tree = aTree;
    if (existingTree != null && !existingTree.equals(aTree))
    {
      existingTree.removeStatus(this);
    }
    tree.addStatus(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfSystemDates()
  {
    return 0;
  }

  public boolean addSystemDate(SystemDate aSystemDate)
  {
    boolean wasAdded = false;
    if (systemDates.contains(aSystemDate)) { return false; }
    systemDates.add(aSystemDate);
    if (aSystemDate.indexOfStatus(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSystemDate.addStatus(this);
      if (!wasAdded)
      {
        systemDates.remove(aSystemDate);
      }
    }
    return wasAdded;
  }

  public boolean removeSystemDate(SystemDate aSystemDate)
  {
    boolean wasRemoved = false;
    if (!systemDates.contains(aSystemDate))
    {
      return wasRemoved;
    }

    int oldIndex = systemDates.indexOf(aSystemDate);
    systemDates.remove(oldIndex);
    if (aSystemDate.indexOfStatus(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSystemDate.removeStatus(this);
      if (!wasRemoved)
      {
        systemDates.add(oldIndex,aSystemDate);
      }
    }
    return wasRemoved;
  }

  public boolean addSystemDateAt(SystemDate aSystemDate, int index)
  {  
    boolean wasAdded = false;
    if(addSystemDate(aSystemDate))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSystemDates()) { index = numberOfSystemDates() - 1; }
      systemDates.remove(aSystemDate);
      systemDates.add(index, aSystemDate);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSystemDateAt(SystemDate aSystemDate, int index)
  {
    boolean wasAdded = false;
    if(systemDates.contains(aSystemDate))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSystemDates()) { index = numberOfSystemDates() - 1; }
      systemDates.remove(aSystemDate);
      systemDates.add(index, aSystemDate);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSystemDateAt(aSystemDate, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Tree placeholderTree = tree;
    this.tree = null;
    placeholderTree.removeStatus(this);
    ArrayList<SystemDate> copyOfSystemDates = new ArrayList<SystemDate>(systemDates);
    systemDates.clear();
    for(SystemDate aSystemDate : copyOfSystemDates)
    {
      aSystemDate.removeStatus(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "treeState" + "=" + (getTreeState() != null ? !getTreeState().equals(this)  ? getTreeState().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "tree = "+(getTree()!=null?Integer.toHexString(System.identityHashCode(getTree())):"null");
  }
}
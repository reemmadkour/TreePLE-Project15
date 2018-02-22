/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePLE.model;
import java.util.*;
import java.sql.Date;

// line 42 "../../../../../TreePLE.ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;

  //Person Associations
  private List<Role> roles;
  private TreePLE treePLE;
  private List<Survey> surveies;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName, TreePLE aTreePLE)
  {
    name = aName;
    roles = new ArrayList<Role>();
    boolean didAddTreePLE = setTreePLE(aTreePLE);
    if (!didAddTreePLE)
    {
      throw new RuntimeException("Unable to create person due to treePLE");
    }
    surveies = new ArrayList<Survey>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Role getRole(int index)
  {
    Role aRole = roles.get(index);
    return aRole;
  }

  public List<Role> getRoles()
  {
    List<Role> newRoles = Collections.unmodifiableList(roles);
    return newRoles;
  }

  public int numberOfRoles()
  {
    int number = roles.size();
    return number;
  }

  public boolean hasRoles()
  {
    boolean has = roles.size() > 0;
    return has;
  }

  public int indexOfRole(Role aRole)
  {
    int index = roles.indexOf(aRole);
    return index;
  }

  public TreePLE getTreePLE()
  {
    return treePLE;
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

  public static int minimumNumberOfRoles()
  {
    return 0;
  }

  public boolean addRole(Role aRole)
  {
    boolean wasAdded = false;
    if (roles.contains(aRole)) { return false; }
    roles.add(aRole);
    if (aRole.indexOfPerson(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRole.addPerson(this);
      if (!wasAdded)
      {
        roles.remove(aRole);
      }
    }
    return wasAdded;
  }

  public boolean removeRole(Role aRole)
  {
    boolean wasRemoved = false;
    if (!roles.contains(aRole))
    {
      return wasRemoved;
    }

    int oldIndex = roles.indexOf(aRole);
    roles.remove(oldIndex);
    if (aRole.indexOfPerson(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRole.removePerson(this);
      if (!wasRemoved)
      {
        roles.add(oldIndex,aRole);
      }
    }
    return wasRemoved;
  }

  public boolean addRoleAt(Role aRole, int index)
  {  
    boolean wasAdded = false;
    if(addRole(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRoleAt(Role aRole, int index)
  {
    boolean wasAdded = false;
    if(roles.contains(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRoleAt(aRole, index);
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
      existingTreePLE.removePerson(this);
    }
    treePLE.addPerson(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfSurveies()
  {
    return 0;
  }

  public Survey addSurvey(Date aDate, Tree aTrees)
  {
    return new Survey(aDate, this, aTrees);
  }

  public boolean addSurvey(Survey aSurvey)
  {
    boolean wasAdded = false;
    if (surveies.contains(aSurvey)) { return false; }
    Person existingPerson = aSurvey.getPerson();
    boolean isNewPerson = existingPerson != null && !this.equals(existingPerson);
    if (isNewPerson)
    {
      aSurvey.setPerson(this);
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
    //Unable to remove aSurvey, as it must always have a person
    if (!this.equals(aSurvey.getPerson()))
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
    ArrayList<Role> copyOfRoles = new ArrayList<Role>(roles);
    roles.clear();
    for(Role aRole : copyOfRoles)
    {
      aRole.removePerson(this);
    }
    TreePLE placeholderTreePLE = treePLE;
    this.treePLE = null;
    placeholderTreePLE.removePerson(this);
    for(int i=surveies.size(); i > 0; i--)
    {
      Survey aSurvey = surveies.get(i - 1);
      aSurvey.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "treePLE = "+(getTreePLE()!=null?Integer.toHexString(System.identityHashCode(getTreePLE())):"null");
  }
}
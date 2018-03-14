/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePLE.model;
import java.util.*;
import java.sql.Date;

// line 46 "../../../../../TreePLE.ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;

  //Person Associations
  private List<Status> status;
  private List<Role> roles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName)
  {
    name = aName;
    status = new ArrayList<Status>();
    roles = new ArrayList<Role>();
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

  public static int minimumNumberOfStatus()
  {
    return 0;
  }

  public Status addStatus(Date aDate, Tree aTree)
  {
    return new Status(aDate, aTree, this);
  }

  public boolean addStatus(Status aStatus)
  {
    boolean wasAdded = false;
    if (status.contains(aStatus)) { return false; }
    Person existingPerson = aStatus.getPerson();
    boolean isNewPerson = existingPerson != null && !this.equals(existingPerson);
    if (isNewPerson)
    {
      aStatus.setPerson(this);
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
    //Unable to remove aStatus, as it must always have a person
    if (!this.equals(aStatus.getPerson()))
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

  public void delete()
  {
    for(int i=status.size(); i > 0; i--)
    {
      Status aStatus = status.get(i - 1);
      aStatus.delete();
    }
    ArrayList<Role> copyOfRoles = new ArrayList<Role>(roles);
    roles.clear();
    for(Role aRole : copyOfRoles)
    {
      aRole.removePerson(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}
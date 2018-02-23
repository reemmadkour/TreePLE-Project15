/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePLE.model;
import java.util.*;

// line 51 "../../../../../TreePLE.ump"
public abstract class Role
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Role Associations
  private List<Person> persons;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Role()
  {
    persons = new ArrayList<Person>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Person getPerson(int index)
  {
    Person aPerson = persons.get(index);
    return aPerson;
  }

  public List<Person> getPersons()
  {
    List<Person> newPersons = Collections.unmodifiableList(persons);
    return newPersons;
  }

  public int numberOfPersons()
  {
    int number = persons.size();
    return number;
  }

  public boolean hasPersons()
  {
    boolean has = persons.size() > 0;
    return has;
  }

  public int indexOfPerson(Person aPerson)
  {
    int index = persons.indexOf(aPerson);
    return index;
  }

  public static int minimumNumberOfPersons()
  {
    return 0;
  }

  public boolean addPerson(Person aPerson)
  {
    boolean wasAdded = false;
    if (persons.contains(aPerson)) { return false; }
    persons.add(aPerson);
    if (aPerson.indexOfRole(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPerson.addRole(this);
      if (!wasAdded)
      {
        persons.remove(aPerson);
      }
    }
    return wasAdded;
  }

  public boolean removePerson(Person aPerson)
  {
    boolean wasRemoved = false;
    if (!persons.contains(aPerson))
    {
      return wasRemoved;
    }

    int oldIndex = persons.indexOf(aPerson);
    persons.remove(oldIndex);
    if (aPerson.indexOfRole(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPerson.removeRole(this);
      if (!wasRemoved)
      {
        persons.add(oldIndex,aPerson);
      }
    }
    return wasRemoved;
  }

  public boolean addPersonAt(Person aPerson, int index)
  {  
    boolean wasAdded = false;
    if(addPerson(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePersonAt(Person aPerson, int index)
  {
    boolean wasAdded = false;
    if(persons.contains(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPersonAt(aPerson, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Person> copyOfPersons = new ArrayList<Person>(persons);
    persons.clear();
    for(Person aPerson : copyOfPersons)
    {
      aPerson.removeRole(this);
    }
  }

}
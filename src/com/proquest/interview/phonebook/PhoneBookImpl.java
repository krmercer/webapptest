package com.proquest.interview.phonebook;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImpl implements PhoneBook
{

private static Connection dbConnection = null;

public List<Person> people = new ArrayList<Person>(5);

@Override
public Person findPerson(String firstName, String lastName)
{

  Person resultPerson = null;

  if (firstName == null)
  {
    firstName = "";
  }

  if (lastName == null)
  {
    lastName = "";
  }

  String searchString = firstName.trim() + " " + lastName.trim();

  // first hit taken duplicates not accounted for
  for (Person person : this.people)
  {
    if (person.getName().trim().equalsIgnoreCase(searchString))
    {
      resultPerson = person;
      break;
    }
  }

  return resultPerson;
}

@Override
public void addPerson(Person newPerson)
{

  if (newPerson != null)
  {
    people.add(newPerson);
  }

}


public void printPhoneBook()
{

  System.out.println("======================================");
  System.out.println(" Phone book list");
  System.out.println("======================================");
  for (Person person : this.people)
  {
    System.out.println(person.toString());
  }
  System.out.println("======================================");

}

public static void main(String[] args)
{
  DatabaseUtil.initDB();  //You should not remove this line, it creates the in-memory database

  PhoneBookImpl phoneBook = new PhoneBookImpl();

  ArrayList<Person> newPeople = new ArrayList<Person>(2);
  newPeople.add(new Person("John Smith", "(248) 123-4567", "1234 Sand Hill Dr, Royal Oak, MI"));
  newPeople.add(new Person("Cynthia Smith", "(824) 128-8758", "875 Main St, Ann Arbor, MI"));

		/* TODO: create person objects and put them in the PhoneBook and database
     * John Smith, (248) 123-4567, 1234 Sand Hill Dr, Royal Oak, MI
		 * Cynthia Smith, (824) 128-8758, 875 Main St, Ann Arbor, MI
		*/
  phoneBook.addPerson(newPeople.get(0));
  phoneBook.addPerson(newPeople.get(1));

  // TODO: print the phone book out to System.out  ==> KRM Assuming PhoneBook is in memory list
  phoneBook.printPhoneBook();

  // TODO: find Cynthia Smith and print out just her entry  ==> KRM DB Version available
  Person foundPerson = phoneBook.findPerson("Cynthia", "Smith");
  System.out.println("======================================");
  System.out.println("Looking for Cynthia Smith");
  System.out.println("======================================");
  if (foundPerson != null)
  {
    System.out.println(foundPerson.toString());
  }
  else
  {
    System.out.println("Person Not Found");
  }
  System.out.println("======================================");

  // TODO: insert the new person objects into the database
  try
  {

    dbConnection = DatabaseUtil.getConnection();

    System.out.println("======================================");
    System.out.println("Adding People to DB");
    System.out.println("======================================");

    for (Person person : newPeople)
    {
      if (!DatabaseUtil.addPerson(dbConnection, person))
      {
        System.out.println("Unable to add Person: " + person.toString());
      }
      else
      {
        System.out.println("Added: " + person.toString());
      }
    }
    System.out.println("======================================");

  }
  catch (SQLException e)
  {
    e.printStackTrace();
  }
  catch (ClassNotFoundException e)
  {
    e.printStackTrace();
  }
  finally
  {
    try
    {
      if (dbConnection != null)
      {
        dbConnection.close();
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  DatabaseUtil.shutdownDB();
}
}

package com.proquest.interview.phonebook;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PhoneBookImplTest
{

@Before
public void setUp() throws Exception
{


}

@Test
public void shouldAddFindPerson()
{

  Person notFoundPerson = new Person("John Smith", "(248) 123-4567", "1234 Sand Hill Dr, Royal Oak, MI");
  Person foundPerson = new Person("Cynthia Smith", "(824) 128-8758", "875 Main St, Ann Arbor, MI");

  PhoneBookImpl phoneBook = new PhoneBookImpl();

  Person person = phoneBook.findPerson("Cynthia", "Smith");
  Assert.assertNull(person);

  phoneBook.addPerson(notFoundPerson);
  phoneBook.addPerson(foundPerson);

  person = phoneBook.findPerson("Cynthia", "Smith");
  Assert.assertNotNull(person);

  Assert.assertEquals("Cynthia Smith", person.getName());

}
}

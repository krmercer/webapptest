package com.proquest.interview.phonebook;

public class Person
{

private String name;
private String phoneNumber;
private String address;

public Person(String name, String phoneNumber, String address)
{
  this.name = name;
  this.phoneNumber = phoneNumber;
  this.address = address;
}

public Person(String name, String phoneNumber)
{
  this.name = name;
  this.phoneNumber = phoneNumber;
  this.address = "";
}


public Person(String name)
{
  this.name = name;
  this.phoneNumber = "";
  this.address = "";
}

public String getName()
{
  return name;
}

public void setName(String name)
{
  this.name = name;
}

public String getPhoneNumber()
{
  return phoneNumber;
}

public void setPhoneNumber(String phoneNumber)
{
  this.phoneNumber = phoneNumber;
}

public String getAddress()
{
  return address;
}

public void setAddress(String address)
{
  this.address = address;
}

@Override
public String toString()
{
  return "Person{" +
      "name='" + name + '\'' +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", address='" + address + '\'' +
      '}';
}
}

package com.proquest.interview.util;

import com.proquest.interview.phonebook.Person;

import java.sql.*;

/**
 * This class is just a utility class, you should not have to change anything here
 *
 * @author rconklin
 */
public class DatabaseUtil
{
public static void initDB()
{
  try
  {
    Connection cn = getConnection();
    Statement stmt = cn.createStatement();
    stmt.execute("CREATE TABLE PHONEBOOK (NAME varchar(255), PHONENUMBER varchar(255), ADDRESS varchar(255))");
    stmt.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Chris Johnson','(321) 231-7876', '452 Freeman Drive, Algonac, MI')");
    stmt.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Dave Williams','(231) 502-1236', '285 Huron St, Port Austin, MI')");
    cn.commit();
    cn.close();
  }
  catch (Exception ex)
  {
    ex.printStackTrace();
  }

}


public static void shutdownDB()
{

  Statement stmt = null;
  Connection cn = null;
  try
  {
    cn = getConnection();
    stmt = cn.createStatement();

    stmt.execute("DROP TABLE PHONEBOOK");
    stmt.execute("SHUTDOWN");
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
    if (stmt != null)
    {
      try
      {
        stmt.close();
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }

    if (cn != null)
    {
      try
      {
        cn.close();
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }

  }


}

public static Connection getConnection() throws SQLException, ClassNotFoundException
{
  Class.forName("org.hsqldb.jdbcDriver");
  return DriverManager.getConnection("jdbc:hsqldb:mem", "sa", "");
}

public static boolean addPerson(Connection cn, Person person)
{

  boolean ok = false;

  PreparedStatement stm = null;
  try
  {
    stm = cn.prepareStatement("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS)  VALUES (?,?,?)");
    stm.setString(1, person.getName());
    stm.setString(2, person.getPhoneNumber());
    stm.setString(3, person.getAddress());
    stm.executeUpdate();

    ok = true;
  }
  catch (SQLException e)
  {
    e.printStackTrace();
  }
  finally
  {
    if (stm != null)
    {
      try
      {
        stm.close();
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }

  return ok;

}
}

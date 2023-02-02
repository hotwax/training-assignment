import java.util.InputMismatchException;
import java.io.ObjectOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

//Object of class which is going to be serialized should implements serializable
class Student implements Serializable {
  String name;
  // String DOB; //old format of student class
  Date DOB;//new format of student class
  Address a;

  // serialVersionUID, which is used during deserialization to verify that the sender and receiver of a serialized object have loaded class

  private static final long serialVersionUID = 10l;

  Student(String name, String dateOfBirth, Address a) //constructor to initialize
  {
    this.name = name;
    // this.DOB = dateOfBirth; //old format of student class
    this.DOB=new Date(""+dateOfBirth);//new format of student class
    this.a = a;
  }

  public String toString() // to print a meaningful output
  {
    return "[ Name = " + name + "\n DOB = " + DOB + "\n Address = " + a + "\n]";
  }
}

class Address implements Serializable //another class 
{
  String city;
  String state;
  Integer pinCode;
  String country;

  Address(String city, String state, Integer pinCode, String country) //constructor to initialize
  {
    this.city = city;
    this.state = state;
    this.pinCode = pinCode;
    this.country = country;
  }

  public String toString() //to print a meaningfull output
  {
    return "{ City = " + city + ", State = " + state + ", PinCode = " + pinCode + ", Country =" + country + " }";
  }

}

class SerializationTest // A class to serialize 
{
  String filename; //the file which need to serialize 

  SerializationTest(String s) //constructor to initialize
  {
    this.filename = s;
  }

  void serialize() throws Exception //function to serialize object 
  {
    Address a1 = new Address("Indore", "MP", 452001, "India");
    Student s1 = new Student("XXXX", "21/06/2002", a1);
    Address a2 = new Address("Bhopal", "MP", 452045, "India");
    Student s2 = new Student("AAA", "12/09/1998", a2);
    Address a3 = new Address("Ujjain", "MP", 452076, "India");
    Student s3 = new Student("BBB", "23/07/2013", a3);
    Address a4 = new Address("Dewas", "MP", 452087, "India");
    Student s4 = new Student("CCC", "14/08/2012", a4);

    ArrayList < Student > li = new ArrayList < > (4); //adding them in a list
    li.add(s1);
    li.add(s2);
    li.add(s3);
    li.add(s4);

    try {
      FileOutputStream fi = new FileOutputStream(filename);
      ObjectOutputStream os = new ObjectOutputStream(fi);
      os.writeObject(li); //writting objects in file
    } catch (IOException e) {
      System.out.println(e);
    }
    System.out.println("Data inserted\n");

  }

}

class DeserializationTest // class to deserialize objects
{
  String filename; //the file which need to serialize 

  DeserializationTest(String s) //constructor to initialize
  {
    filename = s;
  }

  void deserialize() //function to deserialize
  {

    System.out.println("Deserialization\n");
    try {
      FileInputStream fi = new FileInputStream(filename);
      ObjectInputStream os = new ObjectInputStream(fi);
      ArrayList li = (ArrayList) os.readObject(); //reading object from file and storing in a list
      for (int i = 0; i < li.size(); i++)
        System.out.println(li.get(i));
    } catch (IOException | ClassNotFoundException e) {
      System.out.println(e);
    }

  }

}
class Demo {
  public static void main(String[] args) throws Exception {
    while (true) {
      System.out.println("===========================");
      System.out.println("*****Enter your choice*****");
      System.out.println("1.Serialize (e.g ouput1.ser, ouput2.ser)");
      System.out.println("2.Deserialize");
      System.out.println("3.Exit");
      System.out.println("===========================");
      try {
        Scanner sc = new Scanner(System.in);
        int condition = sc.nextInt();
        switch (condition) {

        case 1:
          System.out.println("Enter file name");
          SerializationTest serial = new SerializationTest(sc.next());
          serial.serialize();
          break;

        case 2:
          System.out.println("Enter file name");
          DeserializationTest deserial = new DeserializationTest(sc.next());
          deserial.deserialize();
          break;

        case 3:
          System.out.println("Thank you");
          System.exit(0);
          break;

        default:
          System.out.println("Invalid");
          break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Enter valid option");
      }
    }

  }
}
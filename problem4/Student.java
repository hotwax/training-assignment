import java.io.*;
import java.util.*;
public class Student implements Serializable 
{
    private static final long serialVersionUID = 1L;
    private String firstName;
    private Date dateOfBirth;
    private Address address;
    public Student()
    {
        this.firstName="";
        this.dateOfBirth=null;
        this.address=null;
    }
    public Student(String firstName,Date dateOfBirth,Address address) 
    {
        this.firstName=firstName;
        this.dateOfBirth=dateOfBirth;
        this.address=address;
    }
    public void setFirstName(String firstName)
    {
        this.firstName=firstName;
    }
    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth=dateOfBirth;
    }
    public void setAddress(Address address)
    {
        this.address=address;
    }
    public String getFirstName()
    {
        return this.firstName;
    }
    public Date getDateOfBirth() 
    {
        return this.dateOfBirth;
    }
    public Address getAddress() 
    {
        return this.address;
    }
}
 
import java.io.*;
public class Address implements Serializable 
{
    private static final long serialVersionUID = 1L;
    private String city;
    private String state;
    private Integer pinCode;
    private String country;
    public Address()
    {
        this.city="";
        this.state="";
        this.pinCode=0;
        this.country="";
    }
    public Address(String city,String state,int pinCode,String country) 
    {
        this.city=city;
        this.state=state;
        this.pinCode=pinCode;
        this.country=country;
    }
    public void setCity(String city)
    {
        this.city=city;
    }
    public void setState(String state)
    {
        this.state=state;
    }
    public void setPinCode(int pinCode)
    {
        this.pinCode=pinCode;
    }
    public void setCountry(String country)
    {
        this.country=country;
    }
    public String getCity() 
    {
        return city;
    }
    public String getState() 
    {
        return state;
    }
    public int getPinCode() 
    {
        return pinCode;
    }
    public String getCountry() 
    {
        return country;
    }
};
 
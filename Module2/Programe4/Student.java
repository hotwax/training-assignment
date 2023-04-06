import java.util.Date;

public class Student implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public String firstname;
    // public Object dateofbirth;
    public String dateofbirth;
    Address address;

    public Student(String firstname, String dateofbirth, Address address) {
        this.firstname = firstname;
        // this.dateofbirth = new Date(dateofbirth);
        this.dateofbirth = dateofbirth;
        this.address = address;
    }

    public String tostring() {
        return firstname + " " + dateofbirth + " " + address.tostring();
    }

}

import java.io.*;
import java.text.*;
import java.util.*;
class Employee 
{
    private Integer empID;
    private String name;
    private String email;
    private int age;
    private Date dateOfBirth;
    public Employee()
    {
        this.empID=0;
        this.name="";
        this.email="";
        this.age=0;
        this.dateOfBirth=null;
    }
    public Employee(int empID,String name, String email, int age, Date dateOfBirth) 
    {
        this.empID=empID;
        this.name=name;
        this.email=email;
        this.age=age;
        this.dateOfBirth=dateOfBirth;
    }
    public int getEmpID() 
    {
        return this.empID;
    }
    public void setEmpID(int empID) 
    {
        this.empID=empID;
    }
    public String getName() 
    {
        return this.name;
    }
    public void setName(String name) 
    {
        this.name=name;
    }
    public String getEmail() 
    {
        return this.email;
    }
    public void setEmail(String email) 
    {
        this.email=email;
    }
    public int getAge() 
    {
        return this.age;
    }
    public void setAge(int age) 
    {
        this.age=age;
    }
    public Date getDateOfBirth() 
    {
        return this.dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) 
    {
        this.dateOfBirth=dateOfBirth;
    }
    public String toString() 
    {
        return "Employee {" +
                "empID=" + empID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", dateOfBirth='" + new SimpleDateFormat().format(dateOfBirth) + '\'' +
                '}';
    }
};

class eg1psp
{
    static ArrayList<Employee> employeeList=new ArrayList<>();
    static int id=101;
    static Scanner sc=new Scanner(System.in);
    static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
    public static void main(String gg[]) 
    {
        try 
        {
            File file = new File("E:\\Module2\\problem2\\Employee.txt");
            Scanner fileReader=new Scanner(file);
            while(fileReader.hasNextLine()) 
            {
                // Splitting Employee Details by comma
                String obj[]=fileReader.nextLine().split(",");
                // Parsing Employee Details
                int empid=Integer.parseInt(obj[0]);
                String name=obj[1];
                String email=obj[2];
                int age=Integer.parseInt(obj[3]);
                Date dob=simpleDateFormat.parse(obj[4]);
                Employee emp = new Employee(empid, name, email, age, dob);
                employeeList.add(emp);
                id=1+Math.max(emp.getEmpID(),id);
            }
        }catch(FileNotFoundException e) 
        {
            System.out.println("File not Found");
        }
        catch(ParseException e)
        {
            System.out.println("Given Proper Formatting");
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong");
        }
        while(true)
        {
            System.out.println("-----------MENU----------");
            System.out.println("Type 'Add'    : To Insert Entry");
            System.out.println("Type 'Delete' : To Delete Entry");
            System.out.println("Type 'Search' : To Search Entry");
            System.out.println("Type 'display': To Display Entries");
            System.out.print("Enter your choice : ");
            try 
            {
                Scanner sc=new Scanner(System.in);
                String choice=sc.next().toLowerCase();
                switch(choice) 
                {
                    case "add":
                        addEmployee();
                        System.out.println("Employee Added Successfully!!");
                        break;
                    case "delete":
                        System.out.println("Enter Employee ID: ");
                        System.out.println("Employee" + (deleteEmployee(sc.nextInt()) ? "Not Found" : "Deleted Successfully"));
                        break;
                    case "search":
                        search();
                        break;
                    case "display":
                        display();
                        break;
                    case "exit":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please Enter from the mentioned Commands Only....");
                        System.out.println();
                }
            } 
            catch(Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Enter Mentioned Command Only!!");
            }
        }
    }
    public static class OrderbyId implements Comparator<Employee> 
    {
        public int compare(Employee e1, Employee e2) 
        {
            return e1.getEmpID()-e2.getEmpID();
        }
    }
    public static class OrderbyName implements Comparator<Employee> 
    {
        public int compare(Employee e1, Employee e2) 
        {
            return e1.getName().compareTo(e2.getName());
        }
    }
    public static class OrderbyEmail implements Comparator<Employee> 
    {
        public int compare(Employee e1, Employee e2) 
        {
            return e1.getEmail().compareTo(e2.getEmail());
        }
    }
    public static class OrderbyAge implements Comparator<Employee> 
    {
        public int compare(Employee e1, Employee e2) 
        {
            return e1.getAge()-e2.getAge();
        }
    }
    public static class OrderbyDOB implements Comparator<Employee> 
    {
        public int compare(Employee e1, Employee e2) 
        {
            return e1.getDateOfBirth().compareTo(e2.getDateOfBirth());
        }
    }
    public static ArrayList<Employee> search() 
    {
        ArrayList<Employee> foundEmployee=new ArrayList<>();
        System.out.print("Enter Text to Search : ");
        BufferedReader queryReader=new BufferedReader(new InputStreamReader(System.in));
        try 
        {
            String query=queryReader.readLine().toLowerCase();
            for(Employee emp:employeeList)
            {
                String empString=emp.getEmpID()+emp.getName()+emp.getEmail()+emp.getAge()+ simpleDateFormat.format(emp.getDateOfBirth());
                if(empString.contains(query)) foundEmployee.add(emp);
            }

            System.out.println("------------Enter the Sorting Type---------- : ");
            System.out.println("Type id : To Sort by ID");
            System.out.println("Type name : To Sort by Name");
            System.out.println("Type email : To Sort by Email");
            System.out.println("Type age : To Sort by Age");
            System.out.println("Type dob : To Sort by Date of Birth");
            String choice = sc.next().toLowerCase();
        switch(choice)
        {
            case "id" :
               foundEmployee.sort(new OrderbyId());
                break;
            case "name":
              foundEmployee.sort(new OrderbyName());
                break;
            case "email":
                foundEmployee.sort(new OrderbyEmail());
                break;
            case "age":
                foundEmployee.sort(new OrderbyAge());
                break;
            case "dob":
                foundEmployee.sort(new OrderbyDOB());
                break;
            default:
                System.out.println("Please Enter Mentioned Field Only!!");
        }
            System.out.println("****Enter the Direction Type : ****");
            System.out.println("Type asc : For Ascending");
            System.out.println("Type desc : For Descending");
            choice = sc.next().toLowerCase();

            // Printing Employee Details
            if("asc".equals(choice))
            {
               for(Employee emp : foundEmployee) System.out.println(emp.toString());
            } 
            else 
            {
                Collections.reverse(foundEmployee);
                for(Employee emp:foundEmployee) System.out.printf(emp.toString());
            }
        return foundEmployee;
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Method to delete Employee
    private static boolean deleteEmployee(int empId) 
    {
        boolean flag=true;
        for(int index=0;index<employeeList.size();index++)
        {
            if(employeeList.get(index).getEmpID()==empId)
            {
                employeeList.remove(index);
                flag=!flag;
            }
        }
        writeFile();
        return flag;
    }
    public static void display()
    {
        // Printing Employee Details
        for(Employee emp:employeeList) System.out.println(emp.toString());
    }
    public static void addEmployee()
    {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(inputStreamReader);
        try 
        {
            System.out.print("Enter Name: ");
            String name = br.readLine();
            String stringRegex =  "^[\\p{L} .'-]+$";
            if(name!=null && !name.matches(stringRegex))
            {
                System.out.print("Please Enter String : ");
                System.exit(0);
            }
            System.out.print("Enter Email : ");
            String email=br.readLine();
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                                "[a-zA-Z0-9_+&*-]+)*@" +
                                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                                "A-Z]{2,7}$";
            if(email!=null && !email.matches(emailRegex))
            {
                System.out.print("Please Enter Valid Email Only");
                System.exit(0);
            }

            System.out.print("Enter age: ");
            int age = Integer.parseInt(br.readLine());
            // Checking if Age is valid
            System.out.print("Enter Date of Birth: ");
            String dateOfBirth = br.readLine();
            // Checking if Date of Birth is valid
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dobFormat=simpleDateFormat.parse(dateOfBirth);
            // Generating new Employee
            Employee newEmp=new Employee(id,name,email,age,dobFormat);
            employeeList.add(newEmp);
            writeFile();
        } catch (InputMismatchException e) 
        {
            System.out.println("Please Enter Specified Type Only!!");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Exception Occurred!!");
            e.printStackTrace();
        }
    }
    private static void writeFile() {
        try {
            // Writing Employee Details to File
            BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\Module2\\problem2\\employees.txt"));
            for(Employee emp :employeeList){
                writer.write(emp.getEmpID()+","+emp.getName()+","+emp.getEmail()+","+emp.getAge()+","+new SimpleDateFormat().format(emp.getDateOfBirth())+"\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }    
} 
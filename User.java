import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class User implements IUser{
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected int userType;

    protected DepartmentList departmentList;

    protected ArrayList<StudentSchedule> schedule;


    /*
    Access types
    0 Admin and Faculty
    1 Admin
    2 Faculty
    3 UndergraduateStudent
    4 GraduateStudent
    */

    public User(DepartmentList departmentList){
        this.id = -1;
        this.firstName = "";
        this.lastName = "";
        this.username = "";
        this.password = "";
        this.userType = -1;
        this.departmentList = departmentList;
        this.schedule = new ArrayList<>();
    };



    public void updatePassword()
    {
        Scanner sc = new Scanner(System.in);
        String password;

        boolean dataOk = false;

        while(!dataOk) {
            System.out.print("Enter new password: ");
            password = sc.nextLine();

            // FIXME falta agregar una mayuscula, minuscula, y cambiar username
            if (password.length() >= 8) {
                this.setPassword(password);
                System.out.println("Password successfully updated");
                dataOk = true;
            } else {
                System.out.println("Error: Password must be at least 8 characters. Please try again");
            }
        }

    };

    public ArrayList<ArrayList<String>> showCurrentSchedule(){
        return null;
    }

    public ArrayList<ArrayList<String>> showTranscript(){
        return null;
    }


    public void setSchedule(StudentSchedule studentSchedule)
    {

    }

    public void withdrawCourse(int sectionID){

    }
    public String getMajor()
    {
        return "";
    }

    public int getMentor(){return 0; }

    public String getThesis(){return ""; }

    public double getGpa(){return 0;}

    public int searchSchedule(int sectionId){return 0;}
    public ArrayList<StudentSchedule> getSchedule(){
        return null;
    }

    public void Menu(){}

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }


    public String toString(){
        return "";
    }

}

import java.util.ArrayList;
import java.util.Scanner;

public class Administrator extends User {
    private int facultyId; // Faculty id is optional
    private int adminId;   // If faculty id is set, then admin id is same as faculty id

    public Administrator(DepartmentList departmentList){
        super(departmentList);
        this.facultyId = -1;
        this.adminId = -1;
    };

    public void Menu()
    {
        int option;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("1. Manage courses");
            System.out.println("2. Assign a faculty to a course/section");
            System.out.println("3. Update a department chair");
            System.out.println("4. Withdraw a student from a course/section");
            System.out.println("5. Updating password");
            System.out.println("6. Unblock user");
            System.out.println("7. Exiting the system");
            System.out.print("Enter option: ");
            option = sc.nextInt();

            switch (option)
            {
                case 1:
                    manageCourses();
                    break;
                case 2:
                    assignSectionToFaculty();
                    break;
                case 3:
                    updateDeptChair();
                    break;
                case 4:
                    withdrawStudentFromSection();
                    break;
                case 5:
                    updatePassword();
                    break;
                case 6:
                    unblockUser();
                    break;
                case 7:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Error: Invalid option. Please try again");
            }

        }while(option != 7);
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void manageCourses()
    {
        System.out.println("This functionality of the system is currently under development. " +
                "Please check with OIT for more information. To do that, please contact the OIT " +
                "Help Desk at hotline@tamiu.edu or at ext. 2310. Sorry for the inconvenience!”");
    };

    public void assignSectionToFaculty()
    {
        System.out.println("This functionality of the system is currently under development. " +
                "Please check with OIT for more information. To do that, please contact the OIT " +
                "Help Desk at hotline@tamiu.edu or at ext. 2310. Sorry for the inconvenience!”");
    };

    public void updateDeptChair()
    {
        System.out.println("This functionality of the system is currently under development. " +
                "Please check with OIT for more information. To do that, please contact the OIT " +
                "Help Desk at hotline@tamiu.edu or at ext. 2310. Sorry for the inconvenience!”");
    };

    public void withdrawStudentFromSection()
    {
        System.out.println("This functionality of the system is currently under development. " +
                "Please check with OIT for more information. To do that, please contact the OIT " +
                "Help Desk at hotline@tamiu.edu or at ext. 2310. Sorry for the inconvenience!”");
    };

    public void unblockUser(){};


    public String toString() {
        return "";
    }
}


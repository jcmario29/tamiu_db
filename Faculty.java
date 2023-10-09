import java.util.ArrayList;
import java.util.Scanner;

public class Faculty extends User {
    private int departmentId;
    private ArrayList<Integer> gradStudentsIds; // Max 3 students
    private ArrayList<Integer> sectionIds;

    public Faculty(DepartmentList departmentList){
        super(departmentList);
        this.departmentId = -1;
        this.gradStudentsIds = null;
        this.sectionIds = null;
    };

    public void setSchedule(StudentSchedule studentSchedule)
    {
        if(this.schedule == null)
        {
            this.schedule = new ArrayList<>();
        }
        this.schedule.add(studentSchedule);
    }

    public ArrayList<StudentSchedule> getSchedule(){
        return this.schedule;
    }

    public void Menu()
    {
        int option;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("1. Showing current teaching assignment");
            System.out.println("2. Showing a section roster");
            System.out.println("3. Assigning a grade to a student");
            System.out.println("4. Updating password");
            System.out.println("5. Exiting the system");
            System.out.print("Enter option: ");
            option = sc.nextInt();

            switch (option)
            {
                case 1:
                    showCurrentSchedule();
                    break;
                case 2:
                    showSectionRoster();
                    break;
                case 3:
                    assignGrades();
                    break;
                case 4:
                    updatePassword();
                    break;
                case 5:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Error: Invalid option. Please try again");
            }

        }while(option != 5);
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public ArrayList<ArrayList<String>> showCurrentSchedule()
    {
        int k = 0;
        ArrayList<ArrayList<String>> dataTemp = new ArrayList<ArrayList<String>>();
        for(StudentSchedule studentSchedule : schedule) {
            if (studentSchedule.getClassTaken()) {
                int i = 0;
                boolean found = false;
                int sectionPosition = -1;

                while (i < departmentList.getDepartmentList().size() && !found) {
                    int j = 0;
                    while (j < departmentList.getDepartmentList().get(i).getCourses().size() && !found) {
                        sectionPosition = departmentList.getDepartmentList().get(i).getCourses().get(j).searchSection(studentSchedule.getSection());

                        if (sectionPosition != -1) {
                            found = true;
                        } else {
                            j++;
                        }
                    }
                    if (!found)
                    {
                        i++;
                    }
                    else
                    {
                        dataTemp.add(new ArrayList<>());
                        dataTemp.get(k).add(String.valueOf(departmentList.getDepartmentList().get(i).getCourses().get(j).getCourseId()));
                        dataTemp.get(k).add(departmentList.getDepartmentList().get(i).getCourses().get(j).getCourseName());
                        dataTemp.get(k).add(String.valueOf(departmentList.getDepartmentList().get(i).getCourses().get(j).getCredits()));
                        dataTemp.get(k).add(String.valueOf(departmentList.getDepartmentList().get(i).getCourses().get(j).getSections().get(sectionPosition).getSectionId()));
                        dataTemp.get(k).add(departmentList.getDepartmentList().get(i).getCourses().get(j).getSections().get(sectionPosition).getLocation());
                        dataTemp.get(k).add(departmentList.getDepartmentList().get(i).getCourses().get(j).getSections().get(sectionPosition).getSchedule());
                        k++;
                    }
                }
            }
        }
        return dataTemp;
    };


    public void showSectionRoster()
    {
        Scanner sc = new Scanner(System.in);
        int sectionID;
        int sectionPos = -1;
        int i = 0;

        showCurrentSchedule();
        System.out.println("Show Roster Section");
        System.out.print("Select Section ID: ");
        sectionID = sc.nextInt();
        this.departmentList.showRoster(sectionID);
    };

    // FIXME: Use java swing GUI
    public void assignGrades()
    {

    };

    public void updatePassword(){};


    public String toString() {
        return "";
    }
}



















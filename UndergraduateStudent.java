import java.util.ArrayList;
import java.util.Scanner;

public class UndergraduateStudent extends User {
    private String major;
    private double gpa;

    public UndergraduateStudent(DepartmentList departmentList){
        super(departmentList);
        this.major = "";
        this.gpa = 0;
    };

    public void Menu()
    {
        int option;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("1. Showing the current schedule");
            System.out.println("2. Showing transcript.");
            System.out.println("3. Enrolling in a course section");
            System.out.println("4. Updating password");
            System.out.println("5. Withdraw from a class.");
            System.out.println("6. Exiting the system");
            System.out.print("Enter option: ");
            option = sc.nextInt();

            switch (option)
            {
                case 1:
                    showCurrentSchedule();
                    break;
                case 2:
                    showTranscript();
                    break;
                case 3:
                    enrollCourse();
                    break;
                case 4:
                    updatePassword();
                    break;
                case 5:
                    //withdrawCourse();
                    break;
                case 6:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Error: Invalid option. Please try again");
            }

        }while(option != 6);
    }

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


    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void calculateGPA(){
        this.gpa = 0.0;
        int numberOfCourses = 0;
        for(StudentSchedule studentSchedule : schedule) {
            if (studentSchedule.getClassTaken()) {
                if (studentSchedule.getGrade().compareToIgnoreCase("A") == 0 || studentSchedule.getGrade().compareToIgnoreCase("A+") == 0)
                    this.gpa += 4.0;
                else if (studentSchedule.getGrade().compareToIgnoreCase("A-") == 0)
                    this.gpa += 3.7;
                else if (studentSchedule.getGrade().compareToIgnoreCase("B+") == 0)
                    this.gpa += 3.3;
                else if (studentSchedule.getGrade().compareToIgnoreCase("B") == 0)
                    this.gpa += 3.0;
                else if (studentSchedule.getGrade().compareToIgnoreCase("B-") == 0)
                    this.gpa += 2.7;
                else if (studentSchedule.getGrade().compareToIgnoreCase("C+") == 0)
                    this.gpa += 2.3;
                else if (studentSchedule.getGrade().compareToIgnoreCase("C") == 0)
                    this.gpa += 2.0;
                else if (studentSchedule.getGrade().compareToIgnoreCase("C-") == 0)
                    this.gpa += 1.7;
                else if (studentSchedule.getGrade().compareToIgnoreCase("D+") == 0)
                    this.gpa += 1.3;
                else if (studentSchedule.getGrade().compareToIgnoreCase("D") == 0)
                    this.gpa += 1.0;
                else if (studentSchedule.getGrade().compareToIgnoreCase("D-") == 0)
                    this.gpa += 0.7;
                else if (studentSchedule.getGrade().compareToIgnoreCase("F") == 0)
                    this.gpa += 0.0;
                numberOfCourses++;
            }
        }
        if(schedule.size() > 0){
            this.gpa /= numberOfCourses;
        }

    }

    public double getGpa() {
        calculateGPA();
        return gpa;
    }

    public ArrayList<ArrayList<String>> showCurrentSchedule()
    {
        int k = 0;
        ArrayList<ArrayList<String>> dataTemp = new ArrayList<ArrayList<String>>();
        for(StudentSchedule studentSchedule : schedule) {
            if (!studentSchedule.getClassTaken()) {
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


    public ArrayList<ArrayList<String>> showTranscript()
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
                        dataTemp.get(k).add(studentSchedule.getGrade());
                        k++;

                    }
                }
            }
        }
        return dataTemp;
    };

    public void enrollCourse()
    {
        int departmentCode = -1, courseCode = -1;
        int departmentPos, coursePos;
        int exitEnroll = 1;
        System.out.println("Enroll in Course");
        System.out.println("Available Departments");
        System.out.println("Department ID\tDepartment Name");

        if(departmentList.getDepartmentList().size() > 0)
        {
            for (Department department : departmentList.getDepartmentList())
            {
                System.out.println(department.getDepartmentId() + "\t" + department.getDepartmentName());
            }

            Scanner sc = new Scanner(System.in);

            while(exitEnroll != 0)
            {
                departmentCode = courseCode = coursePos = departmentPos = -1;
                while(departmentPos == -1)
                {
                    System.out.print("Enter department code: ");
                    departmentCode = sc.nextInt();
                    departmentPos = departmentList.searchDepartment(departmentCode);
                    if (departmentPos == -1)
                    {
                        System.out.println("Department not found. Please try again.");
                    }
                }
                departmentList.showDepartmentCourses(departmentPos);

                while(coursePos == -1)
                {
                    System.out.print("Select section ID to enroll: ");
                    courseCode = sc.nextInt();
                    ArrayList<Course> myCourses = departmentList.getDepartmentList().get(departmentPos).getCourses();
                    int i = 0;

                    while( (i < myCourses.size()) && (coursePos == -1) )
                    {
                        coursePos = myCourses.get(i).searchSection(courseCode);
                        if (coursePos == -1)
                        {
                            i++;
                        }
                    }

                    if (coursePos == -1)
                    {
                        System.out.println("Course not found. Please try again.");
                    }

                    if(departmentList.getDepartmentList().get(departmentPos).getCourses().get(i).getSections().get(coursePos).addStudent(this.id))
                    {
                        exitEnroll = 0;
                        StudentSchedule studentSchedule1 = new StudentSchedule();
                        studentSchedule1.setSection(courseCode);
                        studentSchedule1.setGrade("F");
                        studentSchedule1.setClassTaken(false);
                        this.schedule.add(studentSchedule1);
                        System.out.println("Student has been enrolled successfully");
                    }
                    else
                    {
                        System.out.println("Student was not enrolled");
                    }

                }
            }

        }
        else
        {
            System.out.println("There are no departments available.");
        }

    };

    public void withdrawCourse(int sectionID)
    {
//        Scanner sc = new Scanner(System.in);
//        showCurrentSchedule();
//        System.out.println("Withdraw from section");
//        System.out.print("Select Section ID to withdraw: ");
//        int sectionID = sc.nextInt();
        int schedulePos = searchSchedule(sectionID);

        if(schedulePos != -1)
        {
            this.schedule.remove(schedulePos);

            int sectionPos = -1;
            int i = 0;

            while( (i < departmentList.getDepartmentList().size()) && (sectionPos == -1) )
            {
                int j = 0;

                while( (j < departmentList.getDepartmentList().get(i).getCourses().size()) && (sectionPos == -1) )
                {
                    sectionPos = departmentList.getDepartmentList().get(i).getCourses().get(j).searchSection(sectionID);

                    if(sectionPos == -1)
                    {
                        j++;
                    }
                    else
                    {
                        departmentList.getDepartmentList().get(i).getCourses().get(j).getSections().get(sectionPos).removeStudent(this.getId());
                    }
                }
                if (sectionPos == -1)
                {
                    i++;
                }
            }


            System.out.println("Student removed from course section");
        }
        else
        {
            System.out.println("Error: Section ID was not found");
        }

    };

    public int searchSchedule(int sectionId)
    {
        int i = 0;
        boolean found = false;

        while ( (i < this.schedule.size()) && (!found) )
        {
            if (this.schedule.get(i).getSection() == sectionId)
            {
                found = true;
            }
            else
            {
                i++;
            }
        }
        if (found)
        {
            return i;
        }
        else
        {
            return -1;
        }
    }

    public String toString(){
        return "";
    }

}









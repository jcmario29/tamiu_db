import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentSystem {
    private UserList userList;
    private DepartmentList departmentList;
    private Validation validation;

    private int currentUser;
    private String path;

    public StudentSystem(){
        this.userList = new UserList();
        this.departmentList = new DepartmentList(userList);
        this.validation = new Validation();
        currentUser = -1;
        this.path = "C:\\Users\\nato9\\IdeaProjects\\TAMIU_System_Final\\src\\";
        
    };

    public Validation getValidation(){
        return this.validation;
    }
    public void setCurrentUser(int currentUser) {
        this.currentUser = currentUser;
    }

    public int getCurrentUser() {
        return currentUser;
    }

    public UserList getUserList(){
        return this.userList;
    }

    public DepartmentList getDepartmentList() {
        return departmentList;
    }

    public void loadUsers()
    {

        try {
            File myObj = new File(this.path + "UserDatabase.txt");
            Scanner sc = new Scanner(myObj);

            while (sc.hasNextLine())
            {
                String data = sc.nextLine();
                String delimiter = " ";
                String[] arr1  = data.split(delimiter);

                if(Integer.parseInt(arr1[0]) == 1)
                {
                    Administrator admin = new Administrator(this.departmentList);
                    admin.setUserType(1);
                    admin.setId(Integer.parseInt(arr1[1]));
                    admin.setFirstName(arr1[2]);
                    admin.setLastName(arr1[3]);
                    admin.setUsername(arr1[4]);
                    admin.setPassword(arr1[5]);
                    this.userList.getUsers().add(admin);
                }
                else if(Integer.parseInt(arr1[0]) == 2) {
                    Faculty faculty = new Faculty(this.departmentList);
                    faculty.setUserType(2);
                    faculty.setId(Integer.parseInt(arr1[1]));
                    faculty.setFirstName(arr1[2]);
                    faculty.setLastName(arr1[3]);
                    faculty.setUsername(arr1[4]);
                    faculty.setPassword(arr1[5]);
                    this.userList.getUsers().add(faculty);
                }
                else if(Integer.parseInt(arr1[0]) == 3) {
                    UndergraduateStudent undergraduateStudent = new UndergraduateStudent(this.departmentList);
                    undergraduateStudent.setUserType(3);
                    undergraduateStudent.setId(Integer.parseInt(arr1[1]));
                    undergraduateStudent.setFirstName(arr1[2]);
                    undergraduateStudent.setLastName(arr1[3]);
                    undergraduateStudent.setUsername(arr1[4]);
                    undergraduateStudent.setPassword(arr1[5]);
                    undergraduateStudent.setMajor(arr1[6]);
                    this.userList.getUsers().add(undergraduateStudent);
                }
                else if(Integer.parseInt(arr1[0]) == 4) {
                    GraduateStudent graduateStudent = new GraduateStudent(this.departmentList);
                    graduateStudent.setUserType(4);
                    graduateStudent.setId(Integer.parseInt(arr1[1]));
                    graduateStudent.setFirstName(arr1[2]);
                    graduateStudent.setLastName(arr1[3]);
                    graduateStudent.setUsername(arr1[4]);
                    graduateStudent.setPassword(arr1[5]);
                    graduateStudent.setMajor(arr1[6]);
                    graduateStudent.setMentor(Integer.parseInt(arr1[7]));
                    graduateStudent.setThesis(arr1[8]);
                    this.userList.getUsers().add(graduateStudent);
                }
                else if(Integer.parseInt(arr1[0]) == 0) {
                    Administrator admin = new Administrator(this.departmentList);
                    admin.setUserType(0);
                    admin.setId(Integer.parseInt(arr1[1]));
                    admin.setFirstName(arr1[2]);
                    admin.setLastName(arr1[3]);
                    admin.setUsername(arr1[4]);
                    admin.setPassword(arr1[5]);
                    this.userList.getUsers().add(admin);
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException ex){
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

    public void loadDepartment()
    {
        try {
            File myObj = new File(this.path + "DepartmentDatabase.txt");
            Scanner sc = new Scanner(myObj);

            while (sc.hasNextLine())
            {
                String data = sc.nextLine();
                String delimiter = " ";
                String[] arr1  = data.split(delimiter);
                Department dept = new Department();
                dept.setDepartmentId(Integer.parseInt(arr1[0]));
                dept.setDeptChair(Integer.parseInt(arr1[1]));
                dept.setDepartmentName(arr1[2]);
                dept.setLocation(arr1[3]);
                departmentList.getDepartmentList().add(dept);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException ex){
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

    public void loadCourses()
    {
        try {
            File myObj = new File(this.path + "CourseDatabase.txt");
            Scanner sc = new Scanner(myObj);

            while (sc.hasNextLine())
            {
                String data = sc.nextLine();
                String delimiter = " ";
                String[] arr1  = data.split(delimiter);
                Course course = new Course();
                course.setDepartmentId(Integer.parseInt(arr1[0]));
                course.setCourseId(Integer.parseInt(arr1[1]));
                course.setCourseName(arr1[2]);
                course.setCredits(Integer.parseInt(arr1[3]));
                int id = departmentList.searchDepartment(Integer.parseInt(arr1[0]));

                if(id != -1)
                {
                    departmentList.getDepartmentList().get(id).addCourse(course);
                }
                else
                {
                    System.out.println("Error: cannot load course " + arr1[1]);
                }

            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException ex){
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

    public void loadSections()
    {
        try {
            File myObj = new File(this.path + "SectionDatabase.txt");
            Scanner sc = new Scanner(myObj);

            while (sc.hasNextLine())
            {
                String data = sc.nextLine();
                String delimiter = " ";
                String[] arr1  = data.split(delimiter);
                Section section = new Section();
                section.setCourseId(Integer.parseInt(arr1[0]));
                section.setSectionId(Integer.parseInt(arr1[1]));
                section.setFaculty(Integer.parseInt(arr1[2]));
                section.setLocation(arr1[3]);
                section.setSchedule(arr1[4]);
                int i = 0;
                boolean found = false;
                int coursePosition = -1;

                while (i < departmentList.getDepartmentList().size() && !found)
                {
                    coursePosition = departmentList.getDepartmentList().get(i).searchCourse(Integer.parseInt(arr1[0]));
                    if (coursePosition != -1)
                    {
                        found = true;
                    }
                    else
                    {
                        i++;
                    }
                }

                if(found)
                {
                    departmentList.getDepartmentList().get(i).setSection(section, coursePosition);
                }
                else
                {
                    System.out.println("Error: cannot load section " + arr1[1]);
                }

            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException ex){
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

    public void loadStudentSchedule()
    {
        try {
            File myObj = new File(this.path + "Schedule.txt");
            Scanner sc = new Scanner(myObj);

            while (sc.hasNextLine())
            {
                String data = sc.nextLine();
                String delimiter = " ";
                String[] arr1  = data.split(delimiter);
                StudentSchedule studentSchedule = new StudentSchedule();
                studentSchedule.setSection(Integer.parseInt(arr1[1]));
                if(Boolean.parseBoolean(arr1[3])) {
                    studentSchedule.setGrade(arr1[2]);
                }
                else{
                    studentSchedule.setGrade("");
                }
                studentSchedule.setClassTaken(Boolean.parseBoolean(arr1[3]));
                int userPosition = userList.searchUser(Integer.parseInt(arr1[0]));

                if(userPosition != -1) {
                    userList.getUsers().get(userPosition).setSchedule(studentSchedule);

                    if (userList.getUsers().get(userPosition).getUserType() == 3 && !Boolean.parseBoolean(arr1[3])) {
                        int sectionPos = -1;
                        int i = 0;

                        while ((i < departmentList.getDepartmentList().size()) && (sectionPos == -1)) {
                            int j = 0;

                            while ((j < departmentList.getDepartmentList().get(i).getCourses().size()) && (sectionPos == -1)) {
                                sectionPos = departmentList.getDepartmentList().get(i).getCourses().get(j).searchSection(Integer.parseInt(arr1[1]));

                                if (sectionPos == -1) {
                                    j++;
                                } else {
                                    departmentList.getDepartmentList().get(i).getCourses().get(j).getSections().get(sectionPos).addStudent(Integer.parseInt(arr1[0]));
                                }
                            }
                            if (sectionPos == -1) {
                                i++;
                            }
                        }
                    }

                } else {
                    System.out.println("Error: Cannot load student schedule for user " + arr1[0]);
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException ex){
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }



    public void saveUsers()
    {
        try {
            FileWriter myOutputWrite = new FileWriter(this.path + "UserDatabase.txt");
            FileWriter myOutputWrite1 = new FileWriter(this.path + "Schedule.txt");

            for(User user : userList.getUsers()){
                myOutputWrite.write(user.getUserType() + " " + user.getId() + " " + user.getFirstName() + " " +
                        user.getLastName() + " " + user.getUsername() + " " + user.getPassword());
                if(user.getUserType() == 3 || user.getUserType() == 4) {
                    myOutputWrite.write(" " + user.getMajor());
                    if (user.getUserType() == 4)
                        myOutputWrite.write(" " + user.getMentor() + " " + user.getThesis());
                }
                myOutputWrite.write("\n");
                if(user.getSchedule() != null) {
                    for (StudentSchedule studentSchedule : user.getSchedule()) {
                        String grade = studentSchedule.getGrade();
                        if(grade.compareToIgnoreCase("") == 0){
                            studentSchedule.setGrade("G");
                        }
                        myOutputWrite1.write(user.getId() + " " + studentSchedule.getSection() + " " +
                                studentSchedule.getGrade() + " " + studentSchedule.getClassTaken() + "\n");
                    }
                }

            }
            myOutputWrite.close();
            myOutputWrite1.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException ex){
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

    public void saveDepartments()
    {
        try {
            FileWriter myOutputWrite = new FileWriter(this.path + "DepartmentDatabase.txt");
            for(Department department : departmentList.getDepartmentList()){
                myOutputWrite.write(department.getDepartmentId() + " " + department.getDeptChair() +
                        " " + department.getDepartmentName() + " " + department.getLocation() + "\n");
            }
            myOutputWrite.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException ex){
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

    public void saveCourses()
    {
        try {
            FileWriter myOutputWrite = new FileWriter(this.path + "CourseDatabase.txt");

            for(Department department : departmentList.getDepartmentList()){
                ArrayList<Course> courses = department.getCourses();
                for(Course course : courses)
                {
                    myOutputWrite.write(department.getDepartmentId() + " " + course.getCourseId() +
                            " " + course.getCourseName() + " " + course.getCredits() + "\n");
                }
            }

            myOutputWrite.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException ex){
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

    public void saveSections()
    {
        try {
            FileWriter myOutputWrite = new FileWriter(this.path + "SectionDatabase.txt");

            for(Department department : departmentList.getDepartmentList()){
                ArrayList<Course> courses = department.getCourses();
                for(Course course : courses)
                {
                    ArrayList<Section> sections = course.getSections();
                    for (Section section : sections) {
                        myOutputWrite.write(course.getCourseId() + " " + section.getSectionId() +
                                " " + section.getFaculty() + " " + section.getLocation() + " " +
                                section.getSchedule() + "\n");
                    }
                }
            }

            myOutputWrite.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException ex){
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

    public void loadData(){
        loadUsers();
        loadDepartment();
        loadCourses();
        loadSections();
        loadStudentSchedule();
    }

    public void saveData(){
        saveUsers();
        saveDepartments();
        saveCourses();
        saveSections();
    }


}

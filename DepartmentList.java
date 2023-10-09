import java.util.ArrayList;

public class DepartmentList {
    private ArrayList<Department> departmentList;
    private UserList userList;

    public DepartmentList(UserList userList)
    {
        this.userList = userList;
        this.departmentList = new ArrayList<>();
    }



    public ArrayList<ArrayList<String>> showRoster(int sectionID)
    {
        int ipos = 0;
        ArrayList<ArrayList<String>> dataTemp = new ArrayList<ArrayList<String>>();
        int i = 0, j, k;
        boolean found = false;
        ArrayList<Integer> studentsIDs = null;

        while( (i < this.departmentList.size()) && (!found) )
        {
            studentsIDs = this.departmentList.get(i).showRoster(sectionID);

            if(studentsIDs != null)
            {
                found = true;
            }
            else{
                i++;
            }
        }

        if(found)
        {
            j = k = 0;

            while (j < studentsIDs.size())
            {
                k = this.userList.searchUser(studentsIDs.get(j));
                dataTemp.add(new ArrayList<>());
                dataTemp.get(ipos).add(String.valueOf(userList.getUsers().get(k).getId()));
                dataTemp.get(ipos).add(userList.getUsers().get(k).getFirstName());
                dataTemp.get(ipos).add(userList.getUsers().get(k).getLastName());
                dataTemp.get(ipos).add(userList.getUsers().get(k).getMajor());
                ipos++;

                System.out.println(userList.getUsers().get(k).getFirstName() + "\t" + userList.getUsers().get(k).getLastName() +
                        "\t" + userList.getUsers().get(k).getId() + "\t" + userList.getUsers().get(k).getMajor());
                j++;
            }
        }
        return dataTemp;
    }

    public ArrayList<Department> getDepartmentList() {
        return departmentList;
    }

    public void showDepartmentCourses(int departmentPosition)
    {
        Department department = departmentList.get(departmentPosition);
        System.out.println("Courses for department " + department.getDepartmentName());
        if(department.getCourses() == null)
        {
            System.out.println("The department has no active courses.");
        }
        else
        {
            System.out.println("Section ID\tCourse name\tCourse code\tInstructor\tCredits\tSchedule");
            for(Course course : department.getCourses())
            {
                for (Section section : course.getSections()) {
                    int instructorId = section.getFaculty();
                    int instructorPos = userList.searchUser(instructorId);
                    String instructorName = "";
                    if(instructorPos != -1)
                    {
                        instructorName = userList.getUsers().get(instructorPos).getFirstName() +
                                " " + userList.getUsers().get(instructorPos).getFirstName();
                    }
                    else
                    {
                        instructorName = "Instructor not found.";
                    }
                    System.out.println(section.getSectionId() + " " + course.getCourseName() + " " +
                            course.getCourseId() + " " + instructorName +  " " + course.getCredits() +
                            " " + section.getSchedule());
                }
            }
        }
    }

    public int searchDepartment(int id)
    {
        boolean found = false;
        int i = 0;

        while(i < departmentList.size() && !found)
        {
            if(departmentList.get(i).getDepartmentId() == id)
                found = true;
            else
                i++;
        }

        if (!found)
            return -1;
        else
            return i;

    }
}

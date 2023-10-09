import java.util.ArrayList;

public class Department {
    private int departmentId;
    private String departmentName;
    private ArrayList<Course> courses;
    private String departmentLocation;

    private int deptChair;

    public Department(){
        this.departmentId = -1;
        this.departmentName = "";
        this.courses = null;
        this.departmentLocation = "";
        this.deptChair = -1;
    };

    public void setDeptChair(int deptChair) {
        this.deptChair = deptChair;
    }

    public int getDeptChair() {
        return deptChair;
    }

    public void setDepartmentId(int deptId) {
        this.departmentId = deptId;
    }

    public int getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentName(String deptName) {
        this.departmentName = deptName;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setLocation(String location) {
        this.departmentLocation = location;
    }

    public String getLocation() {
        return this.departmentLocation;
    }

    public ArrayList<Integer> showRoster(int sectionId)
    {
        int i = 0;
        boolean found = false;

        while ( (i < this.courses.size()) && (!found) )
        {
            if (this.courses.get(i).showRoster(sectionId) != null)
            {
                found = true;
            }
            else
            {
                i++;
            }
        }

        if (!found)
        {
            return null;
        }
        else{
            return this.courses.get(i).showRoster(sectionId);
        }
    }

    public int searchCourse(int id)
    {
        boolean found = false;
        int i = 0;

        while(i < courses.size() && !found)
        {
            if(courses.get(i).getCourseId() == id)
                found = true;
            else
                i++;
        }

        if (!found)
            return -1;
        else
            return i;

    }

    public void setSection(Section section, int coursePosition)
    {
        this.courses.get(coursePosition).addSection(section);
    }

    public ArrayList<Course> getCourses()
    {
        return this.courses;
    }

    public void addCourse(Course course)
    {
        if (this.courses == null)
        {
            this.courses = new ArrayList<>();
        }
        courses.add(course);
    }


    public void deleteCourse(){

    }

    public void updateCourse(){

    }



    public String toString(){
        return "";
    }


}

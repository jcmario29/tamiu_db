import java.util.ArrayList;

public class Course {

    private int courseId;
    private String courseName;
    private int credits;
    private int departmentId;

    private ArrayList<Section> sections;

    public Course(){
        this.courseId = -1;
        this.courseName = "";
        this.credits = -1;
        this.departmentId = -1;
        this.sections = new ArrayList<>();
    };

    public ArrayList<Section> getSections()
    {
        return this.sections;
    }

    public ArrayList<Integer> showRoster(int sectionId)
    {
        int i = searchSection(sectionId);

        if (i == -1)
        {
            return null;
        }
        else
        {
            return this.sections.get(i).showRoster();
        }
    }

    public int searchSection(int id)
    {
        boolean found = false;
        int i = 0;

        while(i < sections.size() && !found)
        {
            if(sections.get(i).getSectionId() == id)
                found = true;
            else
                i++;
        }

        if (!found)
            return -1;
        else
            return i;

    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCredits(int credits){
        this.credits = credits;
    }

    public int getCredits(){
        return this.credits;
    }

    public void setDepartmentId(int departmentId){
        this.departmentId = departmentId;
    }

    public int getDepartmentId(){
        return this.departmentId;
    }

    public void addSection(Section section)
    {
        this.sections.add(section);
    }

    public void deleteSection(){

    }

    public void updateSection(){

    }


    public String toString(){
        return "";
    }
}

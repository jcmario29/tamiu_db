import java.util.ArrayList;


public class Section {
    public final int MAX_STUDENTS = 30;
    private int sectionId;
    private int courseId;
    private int facultyID;
    private String location;
    private String schedule; // Can change to a Class
    private ArrayList<Integer> studentsId;

    public Section(){
        this.courseId = -1;
        this.facultyID = -1;
        this.location = "";
        this.schedule = "";
        this.studentsId = new ArrayList<>();
    };

    public int getRegisteredStudents()
    {
        return this.studentsId.size();
    }

    public boolean removeStudent(int studentID)
    {
        this.studentsId.remove(Integer.valueOf(studentID));
        return true;
    }

    public boolean addStudent(int studentId)
    {
        if(getRegisteredStudents() < MAX_STUDENTS)
        {
            this.studentsId.add(studentId);
            return true;
        }
        else
        {
            return false;
        }
    }

    public ArrayList<Integer> showRoster()
    {
        return this.studentsId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public void setFaculty(int facultyId){
        this.facultyID = facultyId;
    }

    public int getFaculty(){
        return facultyID;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getLocation(){
        return this.location;
    }

    public void setSchedule(String schedule){
        this.schedule = schedule;
    }

    public String getSchedule(){
        return schedule;
    }



    public String toString(){
        return "";
    }

}

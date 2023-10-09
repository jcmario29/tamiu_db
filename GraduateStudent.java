import java.util.ArrayList;

public class GraduateStudent extends UndergraduateStudent {

    private String thesis;
    private int mentorId; //Faculty Id

    public GraduateStudent(DepartmentList departmentList){
        super(departmentList);
        this.thesis = "";
        this.mentorId = -1;
    };


    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    public String getThesis() {
        return thesis;
    }

    public void setMentor(int mentor) {
        this.mentorId = mentor;
    }

    public int getMentor() {
        return mentorId;
    }

    public String toString(){
        return "";
    }
}

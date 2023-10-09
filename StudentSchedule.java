public class StudentSchedule {
    private int sectionId;
    private String grade;
    private boolean classTaken;

    public StudentSchedule(){
        this.sectionId = -1;
        this.grade = "";
        this.classTaken = false;
    }

    public void setClassTaken(boolean classTaken) {
        this.classTaken = classTaken;
    }

    public boolean getClassTaken(){
        return this.classTaken;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setSection(int section) {
        this.sectionId = section;
    }

    public int getSection() {
        return sectionId;
    }
}

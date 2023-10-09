import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


public class StudentMenu extends JFrame{
    private JPanel studentMenuPanel;
    private JTabbedPane tpStudentMenu;
    private JLabel lblStudentMenu;
    private JButton btnBack;
    private JTable tblSchedule;
    private JPasswordField txtPassword;
    private JButton btnUpdatePassword;
    private JPanel tabEnroll;
    private JPanel tabWithdraw;
    private JPanel tabUpdatePassword;
    private JComboBox cmbDept;
    private JButton btnSelectDept;
    private JComboBox cmbSection;
    private JButton btnEnroll;
    private JComboBox cmbCourse;
    private JButton btnSelectCourse;
    private JLabel lblSectionInfo;
    private JComboBox cmbWithdraw;
    private JButton btnWithdraw;
    private JLabel lblGPA;
    private JLabel lblStudentName;
    private StudentSystem studentSystem;
    private Login login;


    public StudentMenu(StudentSystem studentSystem, Login login) {
        this.studentSystem = studentSystem;
        this.login = login;
        setContentPane(studentMenuPanel);
        setSize(800,400);
        setLocationRelativeTo(null);
        setVisible(true);
        updateScheduleTable();
        updateTranscriptTable();
        updateDeptCombo();
        updateWithdrawCombo();
        lblGPA.setText("Student's GPA: " + String.format("%.3f",studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).getGpa()));
        String sStudentName = studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).getFirstName();
        sStudentName += " " + studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).getLastName();
        lblStudentName.setText("Welcome " + sStudentName);
        tpStudentMenu.setSelectedIndex(0);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.setVisible(true);
                dispose();
            }
        });
        btnSelectDept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCourseCombo();
            }
        });
        btnSelectCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSectionCombo();
            }
        });

        cmbSection.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateSectionInfo();
            }
        });
        btnEnroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrollInCourse();
            }
        });
        btnUpdatePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String input = new String(txtPassword.getPassword());
                //System.out.println(input);
                if(input.length() >= 8){
                    if(studentSystem.getValidation().validatePassword(input)){
                        //System.out.println("password ok");
                        studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).setPassword(input);
                        JOptionPane.showMessageDialog(new JFrame(), "Your password has changed", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), "Error: Password must have at least one upper case, one lower case", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(), "Error: Password must be 8 characters", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] data = cmbWithdraw.getSelectedItem().toString().split(" - ");
                studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).withdrawCourse(Integer.parseInt(data[0]));
                JOptionPane.showMessageDialog(new JFrame(), "Withdraw succesfull", "Success", JOptionPane.INFORMATION_MESSAGE);
                updateWithdrawCombo();
                updateScheduleTable();
            }
        });
    }

    public void enrollInCourse(){
        if (cmbSection.getItemCount() > 0 && cmbCourse.getItemCount() > 0 && cmbDept.getItemCount() > 0){
            String[] data = cmbDept.getSelectedItem().toString().split(" - ");
            String[] data1 = cmbCourse.getSelectedItem().toString().split(" - ");
            String[] data2 = cmbSection.getSelectedItem().toString().split(" - ");
            int deptPos = studentSystem.getDepartmentList().searchDepartment(Integer.parseInt(data[0]));
            int coursePos = studentSystem.getDepartmentList().getDepartmentList().get(deptPos).searchCourse(Integer.parseInt(data1[0]));
            int sectionPos = studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).searchSection(Integer.parseInt(data2[0]));
            int userId = studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).getId();
            if(studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).getSections().get(sectionPos).addStudent(userId))
            {
                StudentSchedule studentSchedule1 = new StudentSchedule();
                studentSchedule1.setSection(Integer.parseInt(data2[0]));
                studentSchedule1.setGrade("F");
                studentSchedule1.setClassTaken(false);
                studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).getSchedule().add(studentSchedule1);
                System.out.println("Student has been enrolled successfully");
                JOptionPane.showMessageDialog(new JFrame(), "Student has been enrolled successfully", "Enrolled", JOptionPane.INFORMATION_MESSAGE);
                tpStudentMenu.removeTabAt(0);
                updateScheduleTable();
                updateWithdrawCombo();
            }
            else
            {
                JOptionPane.showMessageDialog(new JFrame(), "Error: Student cannot be enrolled", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateScheduleTable(){
        String[] column = {"Course ID","Course Name","Credits","Section ID","Location","Schedule"};
        ArrayList<ArrayList<String>> data = studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).showCurrentSchedule();
        DefaultTableModel model = new DefaultTableModel(0,0);
        model.setColumnIdentifiers(column);
        JTable scheduleTable = new JTable();
        scheduleTable.setModel(model);
        scheduleTable.setBounds(10,10,300,300);

        for (ArrayList<String> row : data){
            model.addRow(row.toArray());
        }

        JScrollPane scheduleTab = new JScrollPane(scheduleTable);
        tpStudentMenu.insertTab("Current Schedule", null, scheduleTab ,"Current Schedule", 0);
    }

    public void updateWithdrawCombo(){
        cmbWithdraw.removeAllItems();
        ArrayList<ArrayList<String>> data = studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).showCurrentSchedule();
        for(ArrayList<String> row : data){
            String comboValue = row.get(2) + " - " + row.get(1);
            cmbWithdraw.addItem(comboValue);
        }
    }

    public void updateTranscriptTable(){
        String[] column = {"Course ID","Course Name","Credits","Section ID","Location","Schedule","Grade"};
        ArrayList<ArrayList<String>> data = studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).showTranscript();
        DefaultTableModel model = new DefaultTableModel(0,0);
        model.setColumnIdentifiers(column);
        JTable transcriptTable = new JTable();
        transcriptTable.setModel(model);
        transcriptTable.setBounds(10,10,300,300);

        for (ArrayList<String> row : data){
            model.addRow(row.toArray());
        }
        JScrollPane transcriptTab = new JScrollPane(transcriptTable);
        tpStudentMenu.insertTab("Transcript", null, transcriptTab ,"Transcript", 1);
    }

    public void updateDeptCombo(){

        if(studentSystem.getDepartmentList().getDepartmentList().size() > 0) {
            for (Department department : studentSystem.getDepartmentList().getDepartmentList()) {
                String comboValue = Integer.toString(department.getDepartmentId()) + " - " + department.getDepartmentName();
                cmbDept.addItem(comboValue);
            }
        }
    }

    public void updateCourseCombo(){
        cmbCourse.removeAllItems();
        if(cmbDept.getItemCount() > 0){
            String[] data = cmbDept.getSelectedItem().toString().split(" - ");
            int deptPos = studentSystem.getDepartmentList().searchDepartment(Integer.parseInt(data[0]));
            if(studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses() == null)
            {
                JOptionPane.showMessageDialog(new JFrame(), "Error: No courses for the selected department", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                System.out.println("Section ID\tCourse name\tCourse code\tInstructor\tCredits\tSchedule");
                for(Course course : studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses())
                {
                    String comboValue = Integer.toString(course.getCourseId()) + " - " + course.getCourseName();
                    cmbCourse.addItem(comboValue);
                }
            }
        }
    }

    public void updateSectionInfo(){
        String[] data = cmbDept.getSelectedItem().toString().split(" - ");
        String[] data1 = cmbCourse.getSelectedItem().toString().split(" - ");
        if (cmbSection.getItemCount() > 0) {
            String[] data2 = cmbSection.getSelectedItem().toString().split(" - ");
            int deptPos = studentSystem.getDepartmentList().searchDepartment(Integer.parseInt(data[0]));
            int coursePos = studentSystem.getDepartmentList().getDepartmentList().get(deptPos).searchCourse(Integer.parseInt(data1[0]));
            int sectionPos = studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).searchSection(Integer.parseInt(data2[0]));

            int instructorId = studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).getSections().get(sectionPos).getFaculty();
            int instructorPos = studentSystem.getUserList().searchUser(instructorId);
            String instructorName = "";
            if (instructorPos != -1) {
                instructorName = studentSystem.getUserList().getUsers().get(instructorPos).getFirstName() +
                        " " + studentSystem.getUserList().getUsers().get(instructorPos).getLastName();
            } else {
                instructorName = "Instructor not found.";
            }

            lblSectionInfo.setText(studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).getSections().get(sectionPos).getSectionId() +
                    " " + studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).getCourseName() + " " +
                    studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).getCourseId() +
                    " " + instructorName + " " + studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).getCredits() +
                    " " + studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).getSections().get(sectionPos).getSchedule());
        }
    }

    public void updateSectionCombo(){
        cmbSection.removeAllItems();
        if ( (cmbDept.getItemCount() > 0) && (cmbCourse.getItemCount() > 0) ){
            String[] data = cmbDept.getSelectedItem().toString().split(" - ");
            String[] data1 = cmbCourse.getSelectedItem().toString().split(" - ");
            int deptPos = studentSystem.getDepartmentList().searchDepartment(Integer.parseInt(data[0]));
            int coursePos = studentSystem.getDepartmentList().getDepartmentList().get(deptPos).searchCourse(Integer.parseInt(data1[0]));
            if(studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).getSections() == null)
            {
                JOptionPane.showMessageDialog(new JFrame(), "Error: No sections for the selected course", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                int i = 0;
                System.out.println("Section ID\tCourse name\tCourse code\tInstructor\tCredits\tSchedule");
                for (Section section : studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).getSections()) {
                    int instructorId = section.getFaculty();
                    int instructorPos = studentSystem.getUserList().searchUser(instructorId);
                    String instructorName = "";
                    if(instructorPos != -1)
                    {
                        instructorName = studentSystem.getUserList().getUsers().get(instructorPos).getFirstName() +
                                " " + studentSystem.getUserList().getUsers().get(instructorPos).getLastName();
                    }
                    else
                    {
                        instructorName = "Instructor not found.";
                    }
                    String comboValue = String.valueOf(section.getSectionId()) + " - " + studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).getCourseName();
                    cmbSection.addItem(comboValue);

                    if(i == 0){
                        lblSectionInfo.setText(section.getSectionId() + " " + studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).getCourseName() + " " +
                                studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).getCourseId() +
                                " " + instructorName +  " " + studentSystem.getDepartmentList().getDepartmentList().get(deptPos).getCourses().get(coursePos).getCredits() +
                                " " + section.getSchedule());
                    }
                    i++;
                }
            }
        }
    }
}





















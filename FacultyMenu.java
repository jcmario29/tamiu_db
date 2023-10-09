import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FacultyMenu extends JFrame{
    private JLabel lblFacultyMenu;
    private JTabbedPane tpFacultyMenu;
    private JButton btnBack;
    private JPanel FacultyMenuPanel;
    private JLabel lblFacultyName;
    private JPanel tabUpdatePassword;
    private JPasswordField txtPassword;
    private JButton btnUpdatePassword;
    private JPanel tabRoster;
    private JComboBox cmbRoster;
    private JButton btnRoster;
    private JScrollPane paneRoster;
    private JComboBox cmbGrades;
    private JButton btnGrades;
    private JScrollPane paneGrades;
    private JPanel tabGrades;
    private JButton btnSetGrades;

    private StudentSystem studentSystem;
    private Login login;

    private JTable gradesTable;

    public FacultyMenu(StudentSystem studentSystem, Login login) {
        this.studentSystem = studentSystem;
        this.login = login;
        setContentPane(FacultyMenuPanel);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        gradesTable = new JTable();

        updateScheduleTable();
        updateRoster();
        updateGrades();

        String sFacultyName = studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).getFirstName();
        sFacultyName += " " + studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).getLastName();
        lblFacultyName.setText("Welcome " + sFacultyName);
        tpFacultyMenu.setSelectedIndex(0);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.setVisible(true);
                dispose();
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
        btnRoster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRoster();
            }
        });
        btnGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGrades();
            }
        });
        btnSetGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGrades();
            }
        });
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
        tpFacultyMenu.insertTab("Current Schedule", null, scheduleTab ,"Current Schedule", 0);
    }

    public void updateRoster(){

        ArrayList<ArrayList<String>> dataSchedule = studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).showCurrentSchedule();

        for (ArrayList<String> row : dataSchedule){
            String cmbValue = row.get(3) + " - " + row.get(1);
            cmbRoster.addItem(cmbValue);
        }
    }

    public void updateGrades(){
        ArrayList<ArrayList<String>> dataSchedule = studentSystem.getUserList().getUsers().get(studentSystem.getCurrentUser()).showCurrentSchedule();

        for (ArrayList<String> row : dataSchedule){
            String cmbValue = row.get(3) + " - " + row.get(1);
            cmbGrades.addItem(cmbValue);
        }
    }

    public void showGrades(){
        String[] column = {"Student ID","Student Name","Student Lastname","Major","Grade"};
        String[] SectionIDs = cmbGrades.getSelectedItem().toString().split(" - ");
        int sectionID = Integer.parseInt(SectionIDs[0]);
        ArrayList<ArrayList<String>> data = studentSystem.getDepartmentList().showRoster(Integer.parseInt(SectionIDs[0]));
        for (ArrayList<String> row : data){
            int studentPos = studentSystem.getUserList().searchUser(Integer.parseInt(row.get(0)));
            int sectionPos = studentSystem.getUserList().getUsers().get(studentPos).searchSchedule(sectionID);
            row.add(studentSystem.getUserList().getUsers().get(studentPos).getSchedule().get(sectionPos).getGrade());
        }

        DefaultTableModel model = new DefaultTableModel(0,0);
        model.setColumnIdentifiers(column);

        gradesTable.setModel(model);
        gradesTable.setBounds(10,10,300,300);

        for (ArrayList<String> row : data){
            model.addRow(row.toArray());
        }
        JScrollPane GradesScrollPane = new JScrollPane(gradesTable);
        paneGrades.getViewport().add(GradesScrollPane);
    }

    public void setGrades(){
        String[] SectionIDs = cmbGrades.getSelectedItem().toString().split(" - ");
        int sectionID = Integer.parseInt(SectionIDs[0]);
        for (int i = 0; i < gradesTable.getRowCount(); i++) {  // Loop through the rows
            int studentPos = studentSystem.getUserList().searchUser(Integer.parseInt(gradesTable.getValueAt(i, 0).toString()));
            int sectionPos = studentSystem.getUserList().getUsers().get(studentPos).searchSchedule(sectionID);
            studentSystem.getUserList().getUsers().get(studentPos).getSchedule().get(sectionPos).setGrade(gradesTable.getValueAt(i, 4).toString());
            studentSystem.getUserList().getUsers().get(studentPos).getSchedule().get(sectionPos).setClassTaken(true);

            //System.out.println(gradesTable.getValueAt(i, 4));
        }
    }

    public void showRoster(){
        String[] column = {"Student ID","Student Name","Student Lastname","Major"};
        String[] SectionIDs = cmbRoster.getSelectedItem().toString().split(" - ");
        ArrayList<ArrayList<String>> data = studentSystem.getDepartmentList().showRoster(Integer.parseInt(SectionIDs[0]));
        DefaultTableModel model = new DefaultTableModel(0,0);
        model.setColumnIdentifiers(column);
        JTable scheduleTable = new JTable();
        scheduleTable.setModel(model);
        scheduleTable.setBounds(10,10,300,300);

        for (ArrayList<String> row : data){
            model.addRow(row.toArray());
        }
        JScrollPane RosterScrollPane = new JScrollPane(scheduleTable);
        paneRoster.getViewport().add(RosterScrollPane);
    }

}

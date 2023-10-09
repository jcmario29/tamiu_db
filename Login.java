import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Login extends JFrame {
    private JPanel LoginPanel;
    private JLabel lblWelcome;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JLabel lblUser;
    private JButton btnExit;
    private JButton btnLogin;
    private JLabel lblPassword;

    private StudentSystem studentSystem;
    private Login frame;

    public Login(StudentSystem studentSystem){
        this.studentSystem = studentSystem;
        setContentPane(LoginPanel);
        setTitle("TAMIU System");
        setSize(300,300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        frame = this;

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saving data...");
                studentSystem.saveData();
                System.out.println("Program ended...");

                //dispose();
                System.exit(0);
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginSystem();
            }
        });
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginSystem();
                }
            }
        });
    }

    public void loginSystem(){
        int currentUserID = Init();

        if(currentUserID != -1)
        {
            studentSystem.setCurrentUser(currentUserID);
            txtUser.setText("");
            txtPassword.setText("");
            int userType = studentSystem.getUserList().getUsers().get(currentUserID).getUserType();
            if(userType == 3 || userType == 4){
                StudentMenu studentMenu = new StudentMenu(studentSystem, frame);
            }
            else if(userType == 2){
                FacultyMenu facultyMenu = new FacultyMenu(studentSystem,frame);
            }
            else if(userType == 1){
                AdminMenu adminMenu = new AdminMenu(studentSystem, frame);
            }
            else if(userType == 0){
                AdminMenu adminMenu = new AdminMenu(studentSystem, frame);
            }

            setVisible(false);
        }
    }

    public int Init(){
        boolean accessGranted = false;
        int i = 0;

        i = 0;
        boolean found = false;

        while (i < studentSystem.getUserList().getUsers().size() && !found)
        {
            if (studentSystem.getUserList().getUsers().get(i).getUsername().compareToIgnoreCase(txtUser.getText()) == 0)
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
            JOptionPane.showMessageDialog(new JFrame(), "Error: User does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            i = -1;
        }
        else
        {
            if(studentSystem.getUserList().getUsers().get(i).getPassword().compareToIgnoreCase(String.valueOf(txtPassword.getPassword())) == 0)
            {
                accessGranted = true;
            }
            else
            {
                JOptionPane.showMessageDialog(new JFrame(), "Error: Password incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                i = -1;
            }
        }
        return i;
    }



}



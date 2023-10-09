import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu extends JFrame{
    private JPanel adminMenuPanel;
    private JLabel lblAdminMenu;
    private JButton btnBack;
    private JTabbedPane tpAdminMenu;
    private JPanel tbManageCourses;
    private JPanel tbAssignSection;
    private JPanel tbDeptChair;
    private JPanel tbWithdraw;
    private JPanel tbUblockUser;
    private JPanel tbUpdatePassword;
    private JPasswordField txtPassword;
    private JButton btnUpdatePassword;
    private JLabel lblNewPassword;
    private JTextPane thisFunctionalityOfTheTextPane;
    private JTextPane thisFunctionalityOfTheTextPane1;
    private JTextPane thisFunctionalityOfTheTextPane2;
    private JTextPane thisFunctionalityOfTheTextPane3;
    private JTextPane thisFunctionalityOfTheTextPane4;

    private StudentSystem studentSystem;
    private Login login;

    public AdminMenu(StudentSystem studentSystem, Login login) {
        this.studentSystem = studentSystem;
        this.login = login;
        setContentPane(adminMenuPanel);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setVisible(true);
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
    }
}

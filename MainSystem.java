import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.text.*;


public class MainSystem{

    public static StudentSystem studentSystem;
    public MainSystem() {

        Login login = new Login(studentSystem);

    }

    public static void main(String[] args) {
        studentSystem = new StudentSystem();
        studentSystem.loadData();
        MainSystem mainSystem = new MainSystem();
    }
}
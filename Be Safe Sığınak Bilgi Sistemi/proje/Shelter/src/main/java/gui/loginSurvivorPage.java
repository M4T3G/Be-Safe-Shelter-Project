package gui;

import Shelter.Shelter;
import Shelter.Survivor;
import Shelter.dbHelper;
import Shelter.loginClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class loginSurvivorPage extends JFrame {
    private JPasswordField passwordField;
    private JTextField TCField;
    private JPanel loginSurvivorPanel;
    private JButton loginButton;
    private JButton mainMenuButton;

    public loginSurvivorPage(dbHelper db_Helper) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Shelter/src/main/java/assets/appIcon.png");
        //ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("appIcon.png"));

        setIconImage(icon.getImage());

        //background operations----------------------------------------------------------
        backgroundPanel background = new backgroundPanel();
        loginSurvivorPanel.setOpaque(false);
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        // Yatayda ve dikeyde esneklik ekleyerek welcomePanel'i merkeze hizalama
        loginSurvivorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginSurvivorPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // welcomePanel'i backgroundPanel'e ekleme
        background.add(Box.createVerticalGlue()); // Üstteki esneklik
        background.add(loginSurvivorPanel);
        background.add(Box.createVerticalGlue()); // Altındaki esneklik

        add(background);

        //background operations----------------------------------------------------------

        TCField.setOpaque(false);
        passwordField.setOpaque(false);
        setTitle("Login Survivor");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int inputTC = Integer.parseInt(TCField.getText());
                    loginClass loginClass=new loginClass();
                    int tcControlSurvivor=loginClass.tcControlSurvivor(inputTC,db_Helper);
                    String passwordFromDatabase = loginClass.getSurvivorPassword(inputTC,db_Helper);

                    String inputPassword = String.valueOf(passwordField.getPassword());

                    if (tcControlSurvivor==1&&passwordFromDatabase.equals(inputPassword)) {
                        Survivor loggedInUser=new Survivor(loginClass.getSurvivorName(inputTC,db_Helper),passwordFromDatabase,loginClass.getSurvivorAge(inputTC,db_Helper),inputTC,loginClass.getSurvivorGender(inputTC,db_Helper),loginClass.getSurvivorPoint(inputTC,db_Helper),loginClass.getSurvivorShelterId(inputTC,db_Helper),loginClass.getSurvivorOccupation(inputTC,db_Helper),loginClass.getSurvivorHealthyCondition(inputTC,db_Helper));
                        String name = loggedInUser.getName();
                        JOptionPane.showMessageDialog(null, "Welcome " + name);

                        mainPageSurvivor mps = new mainPageSurvivor(loggedInUser,loginClass.getShelters(db_Helper),db_Helper);
                        mps.setVisible(true);


                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials. Please check your password or TC and try again ");
                    }

                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid TC");
                }


            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                welcomePage wp = new welcomePage();
                wp.setVisible(true);
                dispose();

            }
        });
    }
}

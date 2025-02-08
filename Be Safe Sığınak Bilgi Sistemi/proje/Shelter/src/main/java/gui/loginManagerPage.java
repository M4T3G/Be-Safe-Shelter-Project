package gui;

import Shelter.Manager;
import Shelter.Survivor;
import Shelter.dbHelper;
import Shelter.loginClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.lang.Integer.parseInt;

public class loginManagerPage extends JFrame {
    private JPasswordField passwordField;
    private JTextField TCField;
    private JPanel loginManagerPanel;
    private JButton loginButton;
    private JButton mainMenuButton;

    public loginManagerPage(dbHelper db_Helper) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //background operations----------------------------------------------------------
        backgroundPanel background = new backgroundPanel();
        loginManagerPanel.setOpaque(false);
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        // Yatayda ve dikeyde esneklik ekleyerek welcomePanel'i merkeze hizalama
        loginManagerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginManagerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // welcomePanel'i backgroundPanel'e ekleme
        background.add(Box.createVerticalGlue()); // Üstteki esneklik
        background.add(loginManagerPanel);
        background.add(Box.createVerticalGlue()); // Altındaki esneklik

        add(background);

        //background operations----------------------------------------------------------

        setTitle("Login Manager");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon icon = new ImageIcon("Shelter/src/main/java/assets/appIcon.png");
        //ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("appIcon.png"));
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        TCField.setOpaque(false);
        passwordField.setOpaque(false);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int inputTC = parseInt(TCField.getText());
                    loginClass loginClass=new loginClass();
                    int tcControlManager=loginClass.tcControlManager(inputTC,db_Helper);

                    String passwordFromDatabase = loginClass.getManagerPassword(inputTC,db_Helper);

                    String inputPassword = String.valueOf(passwordField.getPassword());

                    if (tcControlManager==1&&passwordFromDatabase.equals(inputPassword)) {
                        Manager loggedInManager=new Manager(loginClass.getManagerName(inputTC,db_Helper),inputPassword,loginClass.getManagerAge(inputTC,db_Helper),inputTC,loginClass.getManagerShelterid(inputTC,db_Helper));
                        String name = loggedInManager.getName();
                        JOptionPane.showMessageDialog(null, "Welcome " + name);

                        mainPageManager mpm = new mainPageManager(db_Helper,loggedInManager);
                        mpm.setVisible(true);
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

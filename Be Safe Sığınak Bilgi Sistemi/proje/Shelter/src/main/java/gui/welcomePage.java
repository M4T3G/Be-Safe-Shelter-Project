package gui;
import Shelter.dbHelper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class welcomePage extends JFrame {
    private JButton loginAsASurvivorButton;
    private JButton loginAsAManagerButton;
    private JButton signUpButton;
    private JPanel welcomePanel;
    private JPanel componentPanel;

    String url = "jdbc:mysql://127.0.0.1:3306/sistem_proje";
    String user = "root";
    String password = "Mysql123456*";



    dbHelper db_Helper = new dbHelper(url, user, password);

    public welcomePage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Shelter/src/main/java/assets/appIcon.png");
        //ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("appIcon.png"));
        setIconImage(icon.getImage());

        //background operations----------------------------------------------------------
        backgroundPanel background = new backgroundPanel();
        welcomePanel.setOpaque(false);
        componentPanel.setBackground(new Color(255, 255, 255, 0));
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        // Yatayda ve dikeyde esneklik ekleyerek welcomePanel'i merkeze hizalama
        welcomePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomePanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // welcomePanel'i backgroundPanel'e ekleme
        background.add(Box.createVerticalGlue()); // Üstteki esneklik
        background.add(welcomePanel);
        background.add(Box.createVerticalGlue()); // Altındaki esneklik


        add(background);

        //background operations----------------------------------------------------------

        setTitle("Welcome");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);



        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                registerPage rp = new registerPage(db_Helper);
                rp.setVisible(true);
                dispose();

            }
        });


        loginAsAManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginManagerPage lmp = new loginManagerPage(db_Helper);
                lmp.setVisible(true);
                dispose();
            }
        });

        loginAsASurvivorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                loginSurvivorPage lsp = new loginSurvivorPage(db_Helper);
                lsp.setVisible(true);
                dispose();
            }
        });



    }


}


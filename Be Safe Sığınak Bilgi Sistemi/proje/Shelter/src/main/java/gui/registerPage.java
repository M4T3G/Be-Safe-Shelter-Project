package gui;
import Shelter.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;


public class registerPage extends JFrame {
    private JButton saveButton;
    public JPanel registerPanel;
    private JTextField tcField;
    private JTextField nameField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField ageField;
    private JRadioButton femaleRadioButton;
    private JRadioButton maleRadioButton;
    private JComboBox occupationBox;
    private JComboBox healtBox;
    private JLabel label;
    private JButton mainMenuButton;

    public registerPage(dbHelper db_Helper) {
        ImageIcon icon = new ImageIcon("Shelter/src/main/java/assets/appIcon.png");
        //ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("appIcon.png"));
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register");
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        setLocationRelativeTo(null);
        //-------------------------------------------------- background operations
        registerPanel.setOpaque(false);
        backgroundPanel background = new backgroundPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        registerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        background.add(Box.createVerticalGlue()); // Üstteki esneklik
        background.add(registerPanel);
        background.add(Box.createVerticalGlue()); // Altındaki esneklik
        add(background);

        femaleRadioButton.setOpaque(false);
        maleRadioButton.setOpaque(false);

        ageField.setOpaque(false);
        nameField.setOpaque(false);
        passwordField1.setOpaque(false);
        passwordField2.setOpaque(false);
        tcField.setOpaque(false);

        healtBox.setOpaque(false);
        occupationBox.setOpaque(false);

//--------------------------------------------------


        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(femaleRadioButton);
        buttonGroup.add(maleRadioButton);
        femaleRadioButton.setActionCommand("Female");
        maleRadioButton.setActionCommand("Male");

        occupationBox.addItem("Doctor");
        occupationBox.addItem("Teacher");
        occupationBox.addItem("Engineer");
        occupationBox.addItem("Nurse");
        occupationBox.addItem("Architect");
        occupationBox.addItem("Police");
        occupationBox.addItem("Student");
        occupationBox.addItem("Unemployed");
        occupationBox.addItem("Other");
        occupationBox.setSelectedIndex(6);


        healtBox.addItem("Critical : In severe distress, requiring immediate medical attention.");
        healtBox.addItem("Serious: Is significantly compromised, requiring urgent medical intervention.");
        healtBox.addItem("Fair: Health is stable but may require ongoing monitoring or treatment.");
        healtBox.addItem("Good: Health is satisfactory, with minor issues that do not require immediate attention..");
        healtBox.addItem("Excellent: Has optimal health, with no significant health concerns.");


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean genderValidFlag = false;
                boolean passwordValidFlag = false;
                boolean ageValidFlag = false;
                boolean nameValidFlag = false;
                boolean TCValidFlag = false;
                int gender=0;


                ButtonModel selectedButtonModel = buttonGroup.getSelection();
                if (selectedButtonModel != null) { //cinsiyet seçili mi kontrol etmek için
                    String selectedButtonText = selectedButtonModel.getActionCommand(); // veya getLabel()
                    genderValidFlag = true;
                    if (Objects.equals(buttonGroup.getSelection().getActionCommand(), "Female")) { //cinsiyeti tespit etmek için
                        System.out.println("female");
                        gender = 0; //genderin atanması
                    } else if (selectedButtonText.equals("Male")) {
                        System.out.println("male");
                        gender = 1; //genderin atanması
                    }
                } else {
                    genderValidFlag = false;
                    JOptionPane.showMessageDialog(null, "Please choose your gender");
                }

                if (Arrays.equals(passwordField1.getPassword(), passwordField2.getPassword())) {  //sifre kontrolu
                    System.out.println(String.valueOf(passwordField1.getPassword()));
                    if (isValidPassword(String.valueOf(passwordField1.getPassword()))) {
                        passwordValidFlag = true;
                    }
                } else {
                    passwordValidFlag = false;
                    JOptionPane.showMessageDialog(null, "Passwords are not matching each other");
                }


                int TC = getValidTC(tcField.getText());
                if (TC != -1) {
                    TCValidFlag = true;
                } else {
                    TCValidFlag = false;
                }

                int age = getValidAge(ageField.getText());
                if (age != -1) {
                    ageValidFlag = true;
                } else {
                    ageValidFlag = false;
                }

                String name = nameField.getText();
                if (isValidName(name)) {
                    nameValidFlag = true;
                } else {
                    nameValidFlag = false;
                }
                if (nameValidFlag && genderValidFlag && passwordValidFlag && ageValidFlag && TCValidFlag) {
                    int societyPoint;
                    String occupation = occupationBox.getSelectedItem().toString();
                    int healthPoint = healtBox.getSelectedIndex();
                    societyPoint=calculateSocietyPoint(age,occupation,healthPoint);

                    //***********
                    if(societyPoint>50){
                        try {
                            db_Helper.createUser(name,age,TC,String.valueOf(passwordField1.getPassword()),societyPoint,occupation,gender,0,healthPoint);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        JOptionPane.showMessageDialog(null, "Welcome aboard " + name + ". You can login now");

                        welcomePage wp = new welcomePage();
                        wp.setVisible(true);
                        dispose();


                    }
                    else{
                        JOptionPane.showMessageDialog(null,"We are highly sorry to say that you were not able to get into the priority order to settle in the shelter in line with resource sharing. ");
                    }

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


    public boolean isValidName(String name) {
        // İsim null ya da boş ise geçersiz
        if (name == null) {
            JOptionPane.showMessageDialog(null, "Please enter your name");
            return false;
        }

        // İsimdeki her karakterin bir harf olup olmadığını kontrol et
        if (!name.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid name");
            return false;
        }

        return true; // Tüm karakterler harf ise, isim geçerlidir
    }


    public int getValidAge(String ageInput) {
        if (ageInput == null || ageInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your age");
            return -1;
        }

        // input'taki her karakterin sayı olup olmadığını kontrol et
        for (int i = 0; i < ageInput.length(); i++) {
            if (!Character.isDigit(ageInput.charAt(i))) {
                JOptionPane.showMessageDialog(null, "Please enter a valid age");
                return -1;
            }
        }

        int age = Integer.parseInt(ageInput);
        if (age > 100 || age < 0) {
            JOptionPane.showMessageDialog(null, "Please enter a valid age");
            return -1;
        } else {
            return age;
        }

    }


    public int getValidTC(String tcInput) {

        try {
            Integer TC = Integer.parseInt(tcInput);
            boolean isUserExist = true;
            if (tcInput.length() != 9) {
                JOptionPane.showMessageDialog(null, "Invalid TC");
            } else if (!isUserExist) {
                JOptionPane.showMessageDialog(null, "This user is already registered. Please login");
            }
            else {
                return TC;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "TC number must be a number");
        }
        return -1;
    }

    public static boolean isValidPassword(String passwordhere) {

        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");



        if (passwordhere.length() != 8) {
            JOptionPane.showMessageDialog(null,"Password lenght must have exactly 8 character");
            return false;
        }
        if (!specailCharPatten.matcher(passwordhere).find()) {
            JOptionPane.showMessageDialog(null,"Password must have at least one special character");
            return false;
        }
        if (!UpperCasePatten.matcher(passwordhere).find()) {
            JOptionPane.showMessageDialog(null,"Password must have at least one uppercase character");
            return false;
        }
        if (!lowerCasePatten.matcher(passwordhere).find()) {
            JOptionPane.showMessageDialog(null,"Password must have at least one lowercase character");
            return false;
        }
        if (!digitCasePatten.matcher(passwordhere).find()) {
            JOptionPane.showMessageDialog(null,"Password must have at least one number");
            return false;
        }

        return true;

    }
    public int calculateSocietyPoint(int age, String occupation, int healthPoint){
        int societyPoint,occupationPoint,agePoint;
        if(occupation.equals("Doctor")||occupation.equals("Nurse")||occupation.equals("Police"))
            occupationPoint=5;
        else if(occupation.equals("Teacher"))
            occupationPoint=3;
        else if(occupation.equals("Student"))
            occupationPoint=0;
        else if(occupation.equals("Unemployed"))
            occupationPoint=0;
        else if(occupation.equals("Architect")||occupation.equals("Engineer"))
            occupationPoint=2;
        else
            occupationPoint=1;

        //***********

        if(age>=80)
            return 0;
        else if(age<80&&age>=70)
            agePoint=2;
        else if(age<70&&age>=60)
            agePoint=3;
        else if(age<60&&age>=50)
            agePoint=4;
        else if(age<50&&age>=40)
            agePoint=6;
        else if(age<40&&age>=30)
            agePoint=8;
        else if(age<30&&age>=25)
            agePoint=9;
        else if(age<25&&age>=16)
            agePoint=10;
        else agePoint=3;

        //***********
        if (healthPoint==0)
            return 0;

        societyPoint=((healthPoint*3+agePoint*3+2*occupationPoint)*2)-2;

        return societyPoint;
    }
}
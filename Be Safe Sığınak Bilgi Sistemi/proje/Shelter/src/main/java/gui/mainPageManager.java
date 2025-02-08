package gui;

import Shelter.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class mainPageManager extends JFrame {


    private JPanel mainPageManagerPanel;
    private JPanel myShelterPanelComponentPanel;
    private JLabel myShelterWater;
    private JLabel myShelterCurrPop;
    private JLabel myShelterCapacity;
    private JLabel myShelterSocietyPoints;
    private JLabel myShelterFood;
    private JLabel myShelterMedicine;
    private JTextPane myShelterName;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTable survivorTable;
    private JLabel welcomeLabel;
    private JButton logOutButton;
    private JButton fireButton;
    private JButton requestSupplyButton;
    private JButton nextDayButton;

    public mainPageManager(dbHelper db_Helper,Manager loggedInManager) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //ImageIcon icon = new ImageIcon("Shelter/src/main/java/assets/appIcon.png");
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("appIcon.png"));

        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Settings for " + loggedInManager.getName());

        welcomeLabel.setText("Welcome " + loggedInManager.getName());


        Shelter loggedInShelter = db_Helper.getShelterById(loggedInManager.getManagingShelterid());
        List<Survivor> survivors= loggedInShelter.getSurvivorsByShelterId(db_Helper,loggedInShelter.getShelterid());


        //background operations----------------------------------------------------------
        panel2.setOpaque(false);
        backgroundPanel background = new backgroundPanel();
        mainPageManagerPanel.setOpaque(false);
        mainPageManagerPanel.setBackground(new Color(255, 255, 255, 0));
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        // Yatayda ve dikeyde esneklik ekleyerek welcomePanel'i merkeze hizalama
        mainPageManagerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPageManagerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // welcomePanel'i backgroundPanel'e ekleme
        background.add(Box.createVerticalGlue()); // Üstteki esneklik
        background.add(mainPageManagerPanel);
        background.add(Box.createVerticalGlue()); // Altındaki esneklik

        myShelterName.setOpaque(false);


        add(background);

        //background operations----------------------------------------------------------


        //---------------------------------------------Stat operations--------------------------------------------
        myShelterWater.setIcon(getScaledIcon("watericon.png"));
        myShelterFood.setIcon(getScaledIcon("foodicon.png"));
        myShelterMedicine.setIcon(getScaledIcon("medicineicon.png"));

        myShelterCapacity.setText(String.valueOf(loggedInShelter.getCapacity()));

        myShelterName.setText(loggedInShelter.getName());

        myShelterMedicine.setText(String.valueOf(loggedInShelter.getMedSupply()));
        myShelterFood.setText(String.valueOf(loggedInShelter.getFoodSupply()));
        myShelterWater.setText(String.valueOf(loggedInShelter.getWaterSupply()));
        myShelterCurrPop.setText(String.valueOf(loggedInShelter.getPersonCount()));
        myShelterSocietyPoints.setText(String.valueOf(50));

        myShelterWater.setForeground(colorStats(loggedInShelter.getWaterSupply(), loggedInShelter.getPersonCount()));
        myShelterFood.setForeground(colorStats(loggedInShelter.getFoodSupply(), loggedInShelter.getPersonCount()));
        myShelterMedicine.setForeground(colorStats(loggedInShelter.getMedSupply(), loggedInShelter.getPersonCount()));


        float occupancy = (float) (loggedInShelter.getPersonCount() * 100) / loggedInShelter.getCapacity();
        if (occupancy < 30.0) {
            myShelterCurrPop.setForeground(new Color(23, 163, 67));
        } else if (occupancy < 60.0) {
            myShelterCurrPop.setForeground(Color.ORANGE);
        } else if (occupancy < 100.0) {
            myShelterCurrPop.setForeground(new Color(212, 66, 25));
        } else {
            myShelterCurrPop.setForeground(new Color(191, 25, 13));
        }
        //---------------------------------------------Stat operations--------------------------------------------


        //----------------------------------------------------------Table data operations-------------------------


        survivorTable.setModel(makeTableData(survivors));

        //----------------------------------------------------------Table data operations-------------------------


        //----------------------------------------------------------Table decoration operations------------------------


        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font tableFont = new Font("Arial", Font.PLAIN, 18);
                component.setFont(tableFont);
                component.setEnabled(true);
                if (row >= 0 && row < survivors.size()) {
                   String health = (String) survivorTable.getValueAt(row,5);
                    int healthCondition = 0;
                   switch (health){
                       case "Excellent":
                           healthCondition = 4;
                           break;
                       case "Good":
                           healthCondition = 3;
                           break;
                       case "Fair":
                           healthCondition = 2;
                           break;
                       case "Serious":
                           healthCondition = 1;
                           break;
                       case "Critical":
                           healthCondition = 0;
                           break;

                   }

                    if (column == 5) {
                        Font populationFont = new Font("Consolas", Font.BOLD, 18);
                        component.setFont(populationFont);
                        if (healthCondition > 2) {
                            component.setForeground(new Color(23, 163, 67));
                        } else if (healthCondition == 2) {
                            component.setForeground(Color.ORANGE);
                        } else if (healthCondition == 1) {
                            component.setForeground(new Color(212, 66, 25));

                        } else {
                            component.setForeground(new Color(191, 25, 13));
                        }
                    } else {
                        component.setForeground(Color.BLACK);
                    }
                }


                setHorizontalAlignment(SwingConstants.CENTER);
                return component;
            }
        };

        survivorTable.setDefaultRenderer(Object.class, renderer);

        survivorTable.setPreferredScrollableViewportSize(new Dimension(700, 300));
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        survivorTable.setBorder(border);
        TableColumnModel columnModel = survivorTable.getColumnModel();
        TableColumn firstCol = columnModel.getColumn(0);
        firstCol.setPreferredWidth(150);
        TableColumn secondCol = columnModel.getColumn(1);
        secondCol.setPreferredWidth(300);
        TableColumn thirdCol = columnModel.getColumn(2);
        thirdCol.setPreferredWidth(50);
        TableColumn fourthCol = columnModel.getColumn(3);
        fourthCol.setPreferredWidth(150);
        TableColumn fifthCol = columnModel.getColumn(4);
        fifthCol.setPreferredWidth(120);
        TableColumn sixthCol = columnModel.getColumn(5);
        sixthCol.setPreferredWidth(150);

        survivorTable.setRowHeight(30);
        survivorTable.getTableHeader().setDefaultRenderer(new BoldTableHeaderRenderer());

        //----------------------------------------------------------Table decoration operations------------------------


        //-------------------------- BUTTONS
        requestSupplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JComboBox supplyBox = new JComboBox();
                ArrayList<String> needs = new ArrayList<>();
                supplyBox.addItem("Water");
                supplyBox.addItem("Food");
                supplyBox.addItem("Medicine");
                JLabel text = new JLabel("Please choose a supply and amount");
                JSpinner amountSpinner = new JSpinner();
                amountSpinner.setValue(100);


                JPanel request = new JPanel();

                text.setAlignmentX(Component.LEFT_ALIGNMENT);
                request.add(text);

                request.setLayout(new BoxLayout(request, BoxLayout.Y_AXIS));


                JPanel subrequest = new JPanel();
                subrequest.setLayout(new FlowLayout(FlowLayout.CENTER));


                subrequest.add(supplyBox);
                subrequest.add(amountSpinner);
                request.add(subrequest);

                request.setAlignmentX(Component.CENTER_ALIGNMENT);
                //JOptionPane.showMessageDialog(supplyBox, "You have not choose anyone yet.");

                int result = JOptionPane.showOptionDialog(null, request, "Supply and amount", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                if (result == JOptionPane.OK_OPTION) {
                    int needingSupplyType = supplyBox.getSelectedIndex();
                     int needingAmount = (int) amountSpinner.getValue();
                    int updatedAmount;
                    if (needingSupplyType==0)
                        updatedAmount=needingAmount+loggedInShelter.getWaterSupply();
                    else if(needingSupplyType==1)
                        updatedAmount=needingAmount+loggedInShelter.getFoodSupply();
                    else updatedAmount=needingAmount+loggedInShelter.getMedSupply();
                    loggedInManager.requestSupply(db_Helper,loggedInShelter.getShelterid(),needingSupplyType,updatedAmount);



                    //----------------- arayüzde güncelleme -------------------------------

                    switch (needingSupplyType){
                        case 0:
                            int newWater = loggedInShelter.getWaterSupply()+needingAmount;
                            loggedInShelter.setWaterSupply(newWater);
                            myShelterWater.setForeground(colorStats(loggedInShelter.getWaterSupply(), loggedInShelter.getPersonCount()));
                            myShelterWater.setText(String.valueOf(loggedInShelter.getWaterSupply()));
                            break;

                        case 1:
                            int newFood = loggedInShelter.getFoodSupply()+needingAmount;
                            loggedInShelter.setFoodSupply(newFood);
                            myShelterFood.setForeground(colorStats(loggedInShelter.getFoodSupply(), loggedInShelter.getPersonCount()));
                            myShelterFood.setText(String.valueOf(loggedInShelter.getFoodSupply()));
                            break;

                        case 2:
                            int newMed = loggedInShelter.getMedSupply()+needingAmount;
                            loggedInShelter.setMedSupply(newMed);
                            myShelterMedicine.setForeground(colorStats(loggedInShelter.getMedSupply(), loggedInShelter.getPersonCount()));
                            myShelterMedicine.setText(String.valueOf(loggedInShelter.getMedSupply()));
                            break;


                    }
                }

            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomePage wp = new welcomePage();
                wp.setVisible(true);
                dispose();
            }
        });


        fireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (survivorTable.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "You have not choose anyone yet.");
                } else {
                    System.out.println("before delete");
                    for(Survivor s: survivors){
                        System.out.println(s.getName());
                    }
                    List<Survivor> survivors= loggedInShelter.getSurvivorsByShelterId(db_Helper,loggedInShelter.getShelterid());

                    int deletingTC = (int) survivorTable.getValueAt(survivorTable.getSelectedRow(),0);
                    Survivor deletingSurvivor = null;
                    for(Survivor s: survivors){
                        if(s.getTc() == deletingTC) {
                            deletingSurvivor = s;
                        }
                    }

                    int confirmation = JOptionPane.showConfirmDialog(null,  deletingSurvivor.getName() +" will be fired from shelter. Are you sure?", "Confirm Fire", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {


                        loggedInManager.kickSurvivor(db_Helper,deletingSurvivor);
                        loggedInShelter.setPersonCount(loggedInShelter.getPersonCount()-1);
                        survivors= loggedInShelter.getSurvivorsByShelterId(db_Helper,loggedInShelter.getShelterid());

                        survivorTable.setModel(makeTableData(survivors));
                        survivorTable.setDefaultRenderer(Object.class, renderer);
                        myShelterCurrPop.setText(String.valueOf(Integer.parseInt(myShelterCurrPop.getText())-1));



                        JOptionPane.showMessageDialog(null,  deletingSurvivor.getName() +  " is fired.");
                    }

                }
            }
        });


        nextDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int newWater,newFood,newMed;

                newWater = loggedInShelter.getWaterSupply()-loggedInShelter.getPersonCount();
                newFood = loggedInShelter.getFoodSupply()-loggedInShelter.getPersonCount();
                newMed = loggedInShelter.getMedSupply()-loggedInShelter.getPersonCount();


                if(newWater > 0 && newFood > 0 && newMed > 0 ){


                    loggedInManager.advanceToNextDay(db_Helper,loggedInShelter.getShelterid(),newWater,newFood,newMed);

                    loggedInShelter.setFoodSupply(newFood);
                    loggedInShelter.setMedSupply(newMed);
                    loggedInShelter.setWaterSupply(newWater);

                    myShelterWater.setForeground(colorStats(loggedInShelter.getWaterSupply(), loggedInShelter.getPersonCount()));
                    myShelterFood.setForeground(colorStats(loggedInShelter.getFoodSupply(), loggedInShelter.getPersonCount()));
                    myShelterMedicine.setForeground(colorStats(loggedInShelter.getMedSupply(), loggedInShelter.getPersonCount()));

                    myShelterFood.setText(String.valueOf(loggedInShelter.getFoodSupply()));
                    myShelterMedicine.setText(String.valueOf(loggedInShelter.getMedSupply()));
                    myShelterWater.setText(String.valueOf(loggedInShelter.getWaterSupply()));

                }else{
                    JOptionPane.showMessageDialog(null, "You are out of supplies. Please request for new supplies");

                }


            }
        });
    }


    private DefaultTableModel makeTableData(List<Survivor> survivors) {
        DefaultTableModel survivorModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Collections.sort(survivors, (s1, s2) -> Integer.compare(s1.getTc(), s2.getTc()));
        //kullanıcıları kimliklerine göre sıralayıp arama işlemlerini kolaylaştırdım.

        survivorModel.addColumn("TC");
        survivorModel.addColumn("Name");
        survivorModel.addColumn("Age");
        survivorModel.addColumn("Occupation");
        survivorModel.addColumn("Gender");
        survivorModel.addColumn("Health Status");

        for (Survivor s : survivors) {
            int tempTc = s.getTc();
            String tempName = s.getName();
            int tempAge = s.getAge();
            String tempOccupation = s.getOccupation();

            String tempGender;
            if (s.getGender() == 1) {
                tempGender = "Male";
            } else {
                tempGender = "Female";
            }

            String tempHealthStatus;
            switch (s.getHealtyCondition()) {
                case 0:
                    tempHealthStatus = "Critical";
                    break;
                case 1:
                    tempHealthStatus = "Serious";
                    break;
                case 2:
                    tempHealthStatus = "Fair";
                    break;
                case 3:
                    tempHealthStatus = "Good";
                    break;
                case 4:
                    tempHealthStatus = "Excellent";
                    break;
                default:
                    tempHealthStatus = "No data";
            }

            Object[] tempRow = {tempTc, tempName, tempAge, tempOccupation, tempGender, tempHealthStatus};
            survivorModel.addRow(tempRow);
        }

        return survivorModel;

    }


    public static class BoldTableHeaderRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            label.setFont(label.getFont().deriveFont(Font.BOLD));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            Border border = BorderFactory.createLineBorder(Color.BLACK);
            label.setBorder(border);


            return label;
        }


    }

    private Icon getScaledIcon(String iconpath) {
        //ImageIcon icon = new ImageIcon(iconpath);
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(iconpath));

        Image image = icon.getImage();

        Image newImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        return new ImageIcon(newImage);
    }

    private Color colorStats(int stat, int population) {
        int necessarry_amount = population * 2;
        if (stat < necessarry_amount) {
            return new Color(191, 25, 13);
        } else if (stat < necessarry_amount * 2) {
            return (Color.ORANGE);
        } else {
            return new Color(23, 163, 67);
        }

    }
}

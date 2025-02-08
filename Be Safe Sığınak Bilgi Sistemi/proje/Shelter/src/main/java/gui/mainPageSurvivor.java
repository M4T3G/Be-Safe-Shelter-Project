package gui;
import Shelter.*;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class mainPageSurvivor extends JFrame {
    private JPanel mainPageSurvivorPanel;
    private JTabbedPane tabbedPane;
    private JPanel myShelterPanel;
    private JPanel componentPanel;
    private JPanel shelterList;
    private JTable shelterTable;
    private JPanel shelterDetailsPanel;
    private JLabel lblShelterName;
    private JLabel lblCapacity;
    private JLabel lblwater;
    private JLabel lblfood;
    private JLabel lblmedicine;
    private JTextPane lblShelterAdress;
    private JLabel lblCurrPop;
    private JButton logOutButton;
    private JButton applyToThisShelterButton;
    private JLabel welcomeLabel;
    private JLabel userSocietyPointlbl;
    private JLabel myShelterCapacity;
    private JLabel myShelterCurrPop;
    private JLabel myShelterName;
    private JLabel myShelterWater;
    private JLabel myShelterFood;
    private JLabel myShelterMedicine;
    private JPanel myShelterPanelComponentPanel;
    private JLabel myShelterIcon;
    private JLabel myShelterTitle;


    public mainPageSurvivor(Survivor loggedInUser, List<Shelter> shelters,dbHelper db_Helper) {
        //ImageIcon icon = new ImageIcon("Shelter/src/main/java/assets/appIcon.png");
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("appIcon.png"));

        setIconImage(icon.getImage());
        setTitle("Settings for " + loggedInUser.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        welcomeLabel.setText("Welcome " + loggedInUser.getName());
        setLocationRelativeTo(null);
        shelterTable.setRowHeight(30);


        userSocietyPointlbl.setText(String.valueOf(loggedInUser.getSocietyPoint()));


        //----------------------------------------table operations------------------------------------------------
        DefaultTableModel tableData = makeTableData(shelters);
        shelterTable.setModel(tableData);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setEnabled(true);
                if (row >= 0 && row < shelters.size()) {
                    Shelter s = shelters.get(row);
                    float occupancy = (float) (s.getPersonCount() * 100) / s.getCapacity();

                    if (column == 1) {
                        Font populationFont = new Font("Consolas", Font.BOLD, 22);
                        component.setFont(populationFont);
                        if (occupancy < 30.0) {
                            component.setForeground(new Color(23, 163, 67));
                        } else if (occupancy < 60.0) {
                            component.setForeground(Color.ORANGE);
                        } else if (occupancy < 100.0) {
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
        shelterTable.setDefaultRenderer(Object.class, renderer);
        lblwater.setPreferredSize(new Dimension(100, 20));
        lblfood.setPreferredSize(new Dimension(100, 20));
        lblmedicine.setPreferredSize(new Dimension(100, 20));
        lblCurrPop.setPreferredSize(new Dimension(100, 20));
        lblCapacity.setPreferredSize(new Dimension(100, 20));


        shelterTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Değişikliğin tamamlandığından emin ol
                    int selectedRow = shelterTable.getSelectedRow();
                    if (selectedRow != -1) {
                        Shelter selectedShelter = shelters.get(selectedRow);
                        lblShelterName.setText(selectedShelter.getName());
                        lblCapacity.setText(String.valueOf(selectedShelter.getCapacity()));
                        lblCurrPop.setText(String.valueOf(selectedShelter.getPersonCount()));
                        lblwater.setText(String.valueOf(selectedShelter.getWaterSupply()));
                        lblfood.setText(String.valueOf(selectedShelter.getFoodSupply()));
                        lblmedicine.setText(String.valueOf(selectedShelter.getMedSupply()));
                        if (loggedInUser.getSocietyPoint() < 50) {
                            userSocietyPointlbl.setForeground(new Color(191, 25, 13));
                        } else {
                            userSocietyPointlbl.setForeground(new Color(23, 163, 67));
                        }
                    }
                }
            }
        });

        shelterTable.getTableHeader().setDefaultRenderer(new BoldTableHeaderRenderer());

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        shelterTable.setBorder(border);
        TableColumnModel columnModel = shelterTable.getColumnModel();
        TableColumn firstCol = columnModel.getColumn(0);
        firstCol.setPreferredWidth(70);
        TableColumn secondCol = columnModel.getColumn(1);
        secondCol.setPreferredWidth(130);


        //----------------------------------------table operations------------------------------------------------

        //background operations----------------------------------------------------------
        backgroundPanel background = new backgroundPanel();
        mainPageSurvivorPanel.setOpaque(false);


        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        // Yatayda ve dikeyde esneklik ekleyerek welcomePanel'i merkeze hizalama
        mainPageSurvivorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPageSurvivorPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // welcomePanel'i backgroundPanel'e ekleme
        background.add(Box.createVerticalGlue()); // Üstteki esneklik
        background.add(mainPageSurvivorPanel);
        background.add(Box.createVerticalGlue()); // Altındaki esneklik

        lblwater.setIcon(getScaledIcon("watericon.png"));
        lblfood.setIcon(getScaledIcon("foodicon.png"));
        lblmedicine.setIcon(getScaledIcon("medicineicon.png"));

        add(background);

        //background operations----------------------------------------------------------

        //--------------------------------buttons---------------------------------------------

        applyToThisShelterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = shelterTable.getSelectedRow();
                Shelter selectedShelter = shelters.get(selectedRow);
                if (loggedInUser.getStayingShelterid() == 0) {
                    //kullanıcının kaldığı bir sığınak yoksa
                    if (selectedShelter == null) {
                        //sığınak seçilmemişse
                        JOptionPane.showMessageDialog(null, "Please choose a shelter");
                    } else if (selectedShelter.getCapacity() == selectedShelter.getPersonCount()) {
                        //sığınak doluysa
                        JOptionPane.showMessageDialog(null, "This shelter is full. Please choose another one");
                    } else {

                        loggedInUser.setStayingShelterid(selectedShelter.getShelterid(),db_Helper);
                        selectedShelter.setPersonCount(selectedShelter.getPersonCount()+1);
                        JOptionPane.showMessageDialog(null, "Your application has been received and will be reviewed as soon as possible. Please stay tuned for updates. ");
                        welcomePage wp = new welcomePage();
                        wp.setVisible(true);
                        dispose();
                    }
                } else {
                    //kullanıcının kaldığı bir sığınak varsa
                    JOptionPane.showMessageDialog(null, "A registration has already been made before.");
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

        //--------------------------------buttons---------------------------------------------


        //--------------------------------My shelter page---------------------------------------------

        if (loggedInUser.getStayingShelterid() != 0) {
              Shelter stayingShelter =db_Helper.getShelterById(loggedInUser.getStayingShelterid());

            myShelterWater.setIcon(getScaledIcon("watericon.png"));
            myShelterFood.setIcon(getScaledIcon("foodicon.png"));
            myShelterMedicine.setIcon(getScaledIcon("medicineicon.png"));

            myShelterName.setText(stayingShelter.getName());

            myShelterCapacity.setText(String.valueOf(stayingShelter.getCapacity()));
            myShelterFood.setText(String.valueOf(stayingShelter.getFoodSupply()));
            myShelterWater.setText(String.valueOf(stayingShelter.getWaterSupply()));
            myShelterMedicine.setText(String.valueOf(stayingShelter.getMedSupply()));
            myShelterCurrPop.setText(String.valueOf(stayingShelter.getPersonCount()));
        } else {
            myShelterPanel.setOpaque(false);
            Font noShelterFont = new Font("Consolas", Font.BOLD, 28);
            myShelterName.setText("You are not registered to any shelter yet. Please apply for a shelter.");
            myShelterName.setBackground(new Color(255, 255, 255, 0));
            myShelterPanel.setBackground(new Color(255, 255, 255, 0));
            componentPanel.setBackground(new Color(255, 255, 255, 0));

            myShelterName.setForeground(new Color(191, 25, 13));

            myShelterName.setFont(noShelterFont);
            myShelterIcon.setVisible(false);
            myShelterPanelComponentPanel.setVisible(false);

        }


        //--------------------------------My shelter page---------------------------------------------

        shelterList.setOpaque(false);
        shelterList.setBackground(new Color(255, 255, 255, 128));
        tabbedPane.setOpaque(false);
        tabbedPane.setBackground(new Color(255, 255, 255, 128));


    }


    private Icon getScaledIcon(String iconpath) {
        //ImageIcon icon = new ImageIcon(iconpath);
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(iconpath));

        Image image = icon.getImage();

        Image newImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        return new ImageIcon(newImage);
    }

    private DefaultTableModel makeTableData(List<Shelter> shelters) {
        DefaultTableModel shelterModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        shelterModel.addColumn("Shelter Name");
        shelterModel.addColumn("Shelter Capacity");

        for (Shelter s : shelters) {
            String tempName = s.getName();
            String tempCapacity = s.getPersonCount() + "/" + s.getCapacity();
            Object[] tempRow = {tempName, tempCapacity};
            shelterModel.addRow(tempRow);
        }

        return shelterModel;
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
}

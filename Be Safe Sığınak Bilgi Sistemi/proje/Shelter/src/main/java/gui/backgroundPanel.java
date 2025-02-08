package gui;

import javax.swing.*;
import java.awt.*;

public class backgroundPanel extends JPanel {

    String filepath;

    public backgroundPanel(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //ImageIcon obj = new ImageIcon("Shelter/src/main/java/assets/background.jpg");
        ImageIcon obj = new ImageIcon(getClass().getClassLoader().getResource("background.jpg"));

        Image pic = obj.getImage();
        g.drawImage(pic, 0, 0, getWidth(), getHeight(), this);
    }
}



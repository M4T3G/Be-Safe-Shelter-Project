package Shelter;

import gui.*;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/sistem_proje";
        String user = "root";
        String password = "Mysql123456*";


        dbHelper db_Helper = new dbHelper(url, user, password);

        SwingUtilities.invokeLater(new Runnable() {
            @Override

            public void run() {
                welcomePage form = new welcomePage();
                form.setVisible(true);
            }
        });
    }
}
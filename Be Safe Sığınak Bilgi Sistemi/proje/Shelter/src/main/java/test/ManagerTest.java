package test;

import Shelter.Manager;
import Shelter.Survivor;
import Shelter.dbHelper;
import org.junit.Before;
import org.junit.Test;

public class ManagerTest {

    private Manager manager;
    private StubDbHelper stubDbHelper;

    @Before
    public void setUp() {
        String url = "jdbc:mysql://127.0.0.1:3306/sistem_proje";
        String user = "root";
        String password = "Mysql123456*";
        stubDbHelper = new StubDbHelper(url,user,password);
        manager = new Manager("John Doe", "password", 30, 123456789, 1);
    }

    @Test
    public void testRequestSupply() {
        manager.requestSupply(stubDbHelper, 1, 0, 100);

    }

    @Test
    public void testKickSurvivor() {
        Survivor survivor = new Survivor("Jane Doe", "pass", 25, 987654321, 1, 50, 1, "Engineer", 1);
        manager.kickSurvivor(stubDbHelper, survivor);
    }

    @Test
    public void testAdvanceToNextDay() {
        manager.advanceToNextDay(stubDbHelper, 1, 50, 100, 30);
    }
}

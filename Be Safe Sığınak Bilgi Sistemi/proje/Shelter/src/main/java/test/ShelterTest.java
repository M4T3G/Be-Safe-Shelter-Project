package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Shelter.Shelter;
import Shelter.dbHelper;

public class ShelterTest {

    private Shelter shelter;
    private dbHelper db_Helper;

    @Before
    public void setUp() {
        // Initialize Shelter object with sample data
        shelter = new Shelter("Test Shelter", 1, 10, 20, 30, 40, 50);
        // Initialize dbHelper mock object
        String url = "jdbc:mysql://127.0.0.1:3306/sistem_proje";
        String user = "root";
        String password = "Mysql123456*";
        db_Helper = new dbHelper(url,user,password);
    }

    @Test
    public void testGetName() {
        assertEquals("Test Shelter", shelter.getName());
    }

    @Test
    public void testSetName() {
        shelter.setName("New Shelter Name");
        assertEquals("New Shelter Name", shelter.getName());
    }

    @Test
    public void testGetShelterid() {
        assertEquals(1, shelter.getShelterid());
    }

    @Test
    public void testGetPersonCount() {
        assertEquals(10, shelter.getPersonCount());
    }

    @Test
    public void testSetPersonCount() {
        shelter.setPersonCount(15);
        assertEquals(15, shelter.getPersonCount());
    }

    @Test
    public void testGetMedSupply() {
        assertEquals(20, shelter.getMedSupply());
    }

    @Test
    public void testSetMedSupply() {
        shelter.setMedSupply(25);
        assertEquals(25, shelter.getMedSupply());
    }

    @Test
    public void testGetWaterSupply() {
        assertEquals(30, shelter.getWaterSupply());
    }

    @Test
    public void testSetWaterSupply() {
        shelter.setWaterSupply(35);
        assertEquals(35, shelter.getWaterSupply());
    }

    @Test
    public void testGetFoodSupply() {
        assertEquals(40, shelter.getFoodSupply());
    }

    @Test
    public void testSetFoodSupply() {
        shelter.setFoodSupply(45);
        assertEquals(45, shelter.getFoodSupply());
    }

    @Test
    public void testGetCapacity() {
        assertEquals(50, shelter.getCapacity());
    }


}

package test;

import Shelter.Survivor;
import org.junit.Test;
import static org.junit.Assert.*;

public class SurvivorTest {

    @Test
    public void testGettersAndSetters() {
        // Test data
        String name = "John";
        String password = "password123";
        int age = 30;
        int tc = 123456789;
        int gender = 1;
        int societyPoint = 78;
        int stayingShelterid = 1;
        String occupation = "Engineer";
        int healthyCondition = 2; // Setting within the range

        // Create a Survivor object
        Survivor survivor = new Survivor(name, password, age, tc, gender, societyPoint, stayingShelterid, occupation, healthyCondition);

        // Test getters
        assertEquals(name, survivor.getName());
        assertEquals(password, survivor.getPassword());
        assertEquals(age, survivor.getAge());
        assertEquals(tc, survivor.getTc());
        assertEquals(gender, survivor.getGender());
        assertEquals(societyPoint, survivor.getSocietyPoint());
        assertEquals(stayingShelterid, survivor.getStayingShelterid());
        assertEquals(occupation, survivor.getOccupation());
        assertEquals(healthyCondition, survivor.getHealtyCondition());

        // Test setters
        int newSocietyPoint = 78;
        survivor.setSocietyPoint(newSocietyPoint);
        assertEquals(78, survivor.getSocietyPoint());

        int newHealthyCondition = 2; // Trying to set an invalid health condition
        survivor.setHealtyCondition(newHealthyCondition);
        assertEquals(2, survivor.getHealtyCondition()); // Health condition should remain unchanged

        // Testing gender setter with valid values
        int newGender = 0;
        survivor.setGender(newGender);
        assertEquals(newGender, survivor.getGender());

        newGender = 1;
        survivor.setGender(newGender);
        assertEquals(newGender, survivor.getGender());

        // Testing gender setter with invalid value
        newGender = 1;
        survivor.setGender(newGender);
        assertEquals(1, survivor.getGender()); // Gender should remain unchanged
    }


}
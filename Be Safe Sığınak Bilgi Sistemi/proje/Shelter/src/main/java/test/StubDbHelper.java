package test;

import Shelter.dbHelper;

public class StubDbHelper extends dbHelper {
    public StubDbHelper(String url, String user, String password) {
        super(url, user, password);
    }

    @Override
    public void updateSupply(int shelterId, int type, int amount) {
        System.out.println("Updating supply for shelterId: " + shelterId + ", type: " + type + ", amount: " + amount);
    }


}

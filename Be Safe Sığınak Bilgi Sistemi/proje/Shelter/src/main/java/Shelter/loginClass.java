package Shelter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class loginClass {

    public int tcControlSurvivor(int tc, dbHelper db_Helper) {
        List<Integer> list = db_Helper.getUserTcs();

        for (Integer item : list) {
            if (item == tc) {
                return 1; // Eşleşme bulundu, 1 döndür
            }
        }

        return 0; // Eşleşme bulunamadı, 0 döndür
    }

    public String getSurvivorPassword(int tc, dbHelper db_Helper){
        List<Integer> tcs = db_Helper.getUserTcs();
        List<String> passwords = db_Helper.getUserPasswords();

        for(int i=0;i<tcs.size();i++){
            if(tcs.get(i)==tc){
                return passwords.get(i);
            }
        }
        return null;
    }

    public String getSurvivorName(int tc, dbHelper db_Helper){
        List<Integer> tcs = db_Helper.getUserTcs();
        List<String> usernames=db_Helper.getUserNames();

        for(int i=0;i<tcs.size();i++){
            if(tcs.get(i)==tc){
                return usernames.get(i);
            }
        }
        return null;
    }
    public int getSurvivorAge(int tc, dbHelper db_Helper) {
        List<Integer> tcs = db_Helper.getUserTcs();
        List<Integer> ages = db_Helper.getUserAges();

        for (int i = 0; i < tcs.size(); i++) {
            if (tcs.get(i) == tc) {
                return ages.get(i);
            }
        }
        return -1; // Bulunamadı durumunu işaretlemek için -1 döndürülebilir.
    }
    public int getSurvivorPoint(int tc, dbHelper db_Helper) {
        List<Integer> tcs = db_Helper.getUserTcs();
        List<Integer> points = db_Helper.getUserPoints();

        for (int i = 0; i < tcs.size(); i++) {
            if (tcs.get(i) == tc) {
                return points.get(i);
            }
        }
        return -1; // Bulunamadı durumunu işaretlemek için -1 döndürülebilir.
    }
    public int getSurvivorGender(int tc, dbHelper db_Helper) {
        List<Integer> tcs = db_Helper.getUserTcs();
        List<Integer> genders = db_Helper.getUserGenders();

        for (int i = 0; i < tcs.size(); i++) {
            if (tcs.get(i) == tc) {
                return genders.get(i);
            }
        }
        return -1; // Bulunamadı durumunu işaretlemek için -1 döndürülebilir.
    }
    public int getSurvivorShelterId(int tc, dbHelper db_Helper) {
        List<Integer> tcs = db_Helper.getUserTcs();
        List<Integer> shelterIds = db_Helper.getShelterIds();

        for (int i = 0; i < tcs.size(); i++) {
            if (tcs.get(i) == tc) {
                return shelterIds.get(i);
            }
        }
        return -1; // Bulunamadı durumunu işaretlemek için -1 döndürülebilir.
    }
    public String getSurvivorOccupation(int tc, dbHelper db_Helper) {
        List<Integer> tcs = db_Helper.getUserTcs();
        List<String> occupations = db_Helper.getUserOccupations();

        for (int i = 0; i < tcs.size(); i++) {
            if (tcs.get(i) == tc) {
                return occupations.get(i);
            }
        }
        return null; // Bulunamadı durumunu işaretlemek için null döndürülebilir.
    }

    public static ArrayList<Integer> parseData(String data) {
        String[] parts = data.split(",\\s*"); // Virgül ile ayrılan parçaları ayır
        ArrayList<Integer> parsedData = new ArrayList<>();
        int i;
        for ( i = 0;i<5;i++) {
            try {
                parsedData.add(Integer.parseInt(parts[i]));
            } catch (NumberFormatException e) {
                e.getStackTrace();
            }

        }
        Double capacity = Double.parseDouble(parts[i]);
        parsedData.add(capacity.intValue());
        return parsedData;
    }

    public List<Shelter> getShelters(dbHelper db_Helper){
        List<Shelter> shelters = new ArrayList<>();
        int i=0;
        List<String> sheltherList=db_Helper.getShelter();
        List<String> shelterName=db_Helper.getShelterName();
        System.out.println(sheltherList.toString());
        System.out.println("here");
        while(i<sheltherList.size()){
            ArrayList<Integer> parsedData=parseData(sheltherList.get(i));
            System.out.println(parsedData);
            Shelter temp=new Shelter(shelterName.get(i),parsedData.get(0),parsedData.get(1),parsedData.get(2),parsedData.get(3),parsedData.get(4),parsedData.get(5));
            shelters.add(temp);
            i++;
        }
        return shelters;
    }

    public int getSurvivorHealthyCondition(int tc, dbHelper db_Helper) {
        List<Integer> tcs = db_Helper.getUserTcs();
        List<Integer> HealthyConditions = db_Helper.getHealthyCondition();

        for (int i = 0; i < tcs.size(); i++) {
            if (tcs.get(i) == tc) {
                return HealthyConditions.get(i);
            }
        }
        return -1; // Bulunamadı durumunu işaretlemek için -1 döndürülebilir.
    }

    public int tcControlManager(int tc, dbHelper db_Helper) {
        List<Integer> list = db_Helper.getAdminTcs();

        for (Integer item : list) {
            if (item == tc) {
                return 1; // Eşleşme bulundu, 1 döndür
            }
        }

        return 0; // Eşleşme bulunamadı, 0 döndür
    }

    public String getManagerPassword(int tc, dbHelper db_Helper){
        List<Integer> tcs = db_Helper.getAdminTcs();
        List<String> passwords = db_Helper.getAdminPasswords();

        for(int i=0;i<tcs.size();i++){
            if(tcs.get(i)==tc){
                return passwords.get(i);
            }
        }
        return null;
    }
    public String getManagerName(int tc, dbHelper db_Helper){
        List<Integer> tcs = db_Helper.getAdminTcs();
        List<String> adminnames=db_Helper.getAdminNames();

        for(int i=0;i<tcs.size();i++){
            if(tcs.get(i)==tc){
                return adminnames.get(i);
            }
        }
        return null;
    }
    public int getManagerAge(int tc, dbHelper db_Helper) {
        List<Integer> tcs = db_Helper.getAdminTcs();
        List<Integer> ages = db_Helper.getAdminAges();

        for (int i = 0; i < tcs.size(); i++) {
            if (tcs.get(i) == tc) {
                return ages.get(i);
            }
        }
        return -1; // Bulunamadı durumunu işaretlemek için -1 döndürülebilir.
    }
    public int getManagerShelterid(int tc, dbHelper db_Helper) {
        List<Integer> tcs = db_Helper.getAdminTcs();
        List<Integer> shelterids = db_Helper.getAdminShelterid();

        for (int i = 0; i < tcs.size(); i++) {
            if (tcs.get(i) == tc) {
                return shelterids.get(i);
            }
        }
        return -1; // Bulunamadı durumunu işaretlemek için -1 döndürülebilir.
    }
}

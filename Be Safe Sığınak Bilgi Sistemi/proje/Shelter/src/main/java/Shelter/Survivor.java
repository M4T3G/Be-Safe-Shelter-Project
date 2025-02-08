package Shelter;

public class Survivor extends Person {
    private int societyPoint,stayingShelterid;
    private int healthyCondition;
    private String occupation;
    private int gender;

    public Survivor(String name, String password, int age, int tc, int gender, int societyPoint, int stayingShelterid, String occupation, int healthyCondition) {
        super(name, password, age, tc);
        this.societyPoint=societyPoint;
        this.stayingShelterid=stayingShelterid;
        this.gender=gender;
        this.occupation=occupation;
        this.healthyCondition=healthyCondition;
    }

    public int getSocietyPoint() {
        return societyPoint;
    }

    public void setSocietyPoint(int societyPoint) {
        this.societyPoint = societyPoint;
    }

    public int getStayingShelterid() {
        return stayingShelterid;
    }

    public void setStayingShelterid(int stayingShelterid,dbHelper db_Helper) {
        this.stayingShelterid = stayingShelterid;
        db_Helper.updateShelterPersonCount(stayingShelterid,getTc());
}

    public int getHealtyCondition() {
        return healthyCondition;
    }

    public void setHealtyCondition(int healtyCondition) {
        this.healthyCondition = healtyCondition;
    }


    public String getOccupation() {
        return occupation;
    }


    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }


}

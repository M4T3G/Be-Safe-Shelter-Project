package Shelter;

public class Manager extends Person {
    private int managingShelterid;

    public Manager(String name, String password, int age, int tc, int managingShelterid) {
        super(name, password, age, tc);
        this.managingShelterid = managingShelterid;
    }


    public int getManagingShelterid() {
        return managingShelterid;
    }

    public void setManagingShelterid(int managingShelterid) {
        this.managingShelterid = managingShelterid;
    }
    public void requestSupply(dbHelper db_Helper,int requestedShelterid,int type,int updatedAmount){
        db_Helper.updateSupply(requestedShelterid,type,updatedAmount);
    }

    public void kickSurvivor(dbHelper db_Helper,Survivor deletingSurvivor){
        deletingSurvivor.setStayingShelterid(0,db_Helper);
    }

    public void advanceToNextDay(dbHelper db_Helper,int shelterId,int water, int food, int med){
        db_Helper.updateSupply(shelterId,0,water);
        db_Helper.updateSupply(shelterId,1,food);
        db_Helper.updateSupply(shelterId,2,med);
    }


}

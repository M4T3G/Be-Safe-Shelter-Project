package Shelter;

import java.util.ArrayList;
import java.util.List;

public class Shelter {
    private String name;
    private int shelterid,personCount,medSupply,waterSupply,foodSupply,capacity;

    public Shelter(String name, int shelterid, int personCount,int medSupply, int waterSupply, int foodSupply, int capacity) {
        this.name = name;
        this.shelterid = shelterid;
        this.personCount=personCount;
        this.foodSupply = foodSupply;
        this.waterSupply = waterSupply;
        this.medSupply = medSupply;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShelterid() {
        return shelterid;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getMedSupply() {
        return medSupply;
    }

    public void setMedSupply(int medSupply) {
        this.medSupply = medSupply;
    }

    public int getWaterSupply() {
        return waterSupply;
    }

    public void setWaterSupply(int waterSupply) {
        this.waterSupply = waterSupply;
    }

    public int getFoodSupply() {
        return foodSupply;
    }

    public void setFoodSupply(int foodSupply) {
        this.foodSupply = foodSupply;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Survivor> getSurvivorsByShelterId(dbHelper db_Helper,int shelterId){
        return db_Helper.getSurvivorsByShelterId(shelterId);
    }
}

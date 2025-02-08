package Shelter;

public class Person {
    private String name,password;
    private int age;
    private int tc;

    public Person(String name, String password, int age, int tc) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.tc = tc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTc() {
        return tc;
    }

    public void setTc(int tc) {
        this.tc = tc;
    }
}

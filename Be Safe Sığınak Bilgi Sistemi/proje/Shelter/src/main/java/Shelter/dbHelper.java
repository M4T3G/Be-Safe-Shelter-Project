package Shelter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dbHelper {

    private String url;
    private String user;
    private String password;

    public dbHelper(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    public List<String> getShelter() {
        List<String> shelterList = new ArrayList<>();


        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM shelter_info");

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();

                for (int i = 1; i <= columnCount; i++) {
                    row
                            .append(resultSet.getString(i))
                            .append(", ");
                }
                shelterList.add(row.substring(0, row.length() - 2));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shelterList;
    }

    public void createUser(String isim, int yas, double tc, String password, int toplumPuani, String meslek, int cinsiyet, int shelter_id, int healthy_point) throws SQLException {
        try {
            // INSERT INTO sorgusunu parametreli olarak oluştur
            String insertQuery = "INSERT INTO user (user_name, user_age, user_tc, user_password, user_point," +
                    " user_occupation, user_gender, shelter_id, user_healthy_condition ) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";

            // Connection bilgilerini kullanarak bağlantıyı oluştur
            //try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sistem_proje"+"user=root&password=Mysql123456*");
           try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sistem_proje?user=root&password=Mysql123456*");
                 // PreparedStatement oluştur
                 PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                // Parametre olarak kullanıcı bilgilerini ayarla
                preparedStatement.setString(1, isim);
                preparedStatement.setInt(2, yas);
                preparedStatement.setDouble(3, tc);
                preparedStatement.setString(4, password);
                preparedStatement.setInt(5, toplumPuani);
                preparedStatement.setString(6, meslek);
                preparedStatement.setInt(7, cinsiyet);
                preparedStatement.setInt(8, shelter_id);
                preparedStatement.setInt(9, healthy_point);

                // Sorguyu çalıştır
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted.");
                preparedStatement.close();
            }
        } catch (SQLException e) {
            // SQL istisnasını yakala ve ekrana yazdır
            e.printStackTrace();
            throw e; // İstisnayı tekrar fırlat, böylece bu metodu çağıran yerde de ele alınabilir
        }
    }


    public List<Integer> getUserTcs() {
        List<Integer> userTcs = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT user_tc FROM user";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int userTc = resultSet.getInt("user_tc");
                userTcs.add(userTc);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userTcs;
    }

    public List<String> getUserPasswords() {
        List<String> userPasswords = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT user_password FROM user";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String userPassword = resultSet.getString("user_password");
                userPasswords.add(userPassword);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userPasswords;
    }


    public List<String> getUserNames() {
        List<String> userNames = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT user_name FROM user";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String userName = resultSet.getString("user_name");
                userNames.add(userName);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userNames;
    }

    public List<Integer> getUserAges() {
        List<Integer> userAges = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT user_age FROM user";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int userAge = resultSet.getInt("user_age");
                userAges.add(userAge);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userAges;
    }

    public List<String> getUserOccupations() {
        List<String> userOccupations = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT user_occupation FROM user";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String userOccupation = resultSet.getString("user_occupation");
                userOccupations.add(userOccupation);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userOccupations;
    }

    public List<Integer> getUserPoints() {
        List<Integer> userPoints = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT user_point FROM user";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int userPoint = resultSet.getInt("user_point");
                userPoints.add(userPoint);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userPoints;
    }

    public List<Integer> getShelterIds() {
        List<Integer> shelterIds = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT shelter_id FROM user";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int shelterId = resultSet.getInt("shelter_id");
                shelterIds.add(shelterId);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shelterIds;
    }

    public List<Integer> getUserGenders() {
        List<Integer> userGenders = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT user_gender FROM user";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int userGender = resultSet.getInt("user_gender");
                userGenders.add(userGender);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userGenders;
    }

    public List<String> getShelterName() {
        List<String> ShelterNames = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT shelter_name FROM shelter";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String ShelterName = resultSet.getString("shelter_name");
                ShelterNames.add(ShelterName);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ShelterNames;
    }

    public List<Integer> getHealthyCondition() {
        List<Integer> healthyConditions = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT user_healthy_condition FROM user";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int healthyCondition = resultSet.getInt("user_healthy_condition");
                healthyConditions.add(healthyCondition);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return healthyConditions;
    }

    public List<Integer> getAdminTcs() {
        List<Integer> adminTcs = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT admin_tc FROM admin";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int adminTc = resultSet.getInt("admin_tc");
                adminTcs.add(adminTc);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return adminTcs;
    }

    public List<String> getAdminPasswords() {
        List<String> adminPasswords = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT admin_password FROM admin";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String adminPassword = resultSet.getString("admin_password");
                adminPasswords.add(adminPassword);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return adminPasswords;
    }

    public List<String> getAdminNames() {
        List<String> adminNames = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT admin_name FROM admin";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String adminName = resultSet.getString("admin_name");
                adminNames.add(adminName);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return adminNames;
    }

    public List<Integer> getAdminAges() {
        List<Integer> adminAges = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT admin_age FROM admin";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int adminAge = resultSet.getInt("admin_age");
                adminAges.add(adminAge);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return adminAges;
    }

    public List<Integer> getAdminShelterid() {
        List<Integer> shelterids = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT admin_shelter_number FROM admin";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int shelterid = resultSet.getInt("admin_shelter_number");
                shelterids.add(shelterid);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shelterids;
    }

    public Shelter getShelterById(int id) {
        Shelter shelter = null;
        String shelterName = null;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT shelter_name FROM shelter WHERE shelter_id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                shelterName = resultSet.getString("shelter_name");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM shelter_info WHERE shelter_id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int shelterId = resultSet.getInt("shelter_id");
                int personCount = resultSet.getInt("shelter_person_count");
                int medicine = resultSet.getInt("shelter_medicine");
                int water = resultSet.getInt("shelter_water");
                int food = resultSet.getInt("shelter_food");
                int capacity = resultSet.getInt("shelter_capacity");

                shelter = new Shelter(shelterName, shelterId, personCount, medicine, water, food, capacity);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shelter;
    }

    public List<Survivor> getSurvivorsByShelterId(int shelterId) {
        List<Survivor> users = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM user WHERE shelter_id = ?"; //user_shelter_id -> shelter_id ye dönüştürüldü
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, shelterId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String userName = resultSet.getString("user_name");
                int userAge = resultSet.getInt("user_age");
                int userTc = resultSet.getInt("user_tc");
                String userPassword = resultSet.getString("user_password");
                int userPoint = resultSet.getInt("user_point");
                String userOccupation = resultSet.getString("user_occupation");
                int userGender = resultSet.getInt("user_gender");
                int userShelterId = resultSet.getInt("shelter_id");  //user_shelter_id -> shelter_id ye dönüştürüldü
                int healthyCondition = resultSet.getInt("user_healthy_condition");

                // Kullanıcı nesnesini oluştur ve listeye ekle
                Survivor user = new Survivor(userName, userPassword, userAge, userTc, userGender, userPoint, userShelterId, userOccupation, healthyCondition);
                users.add(user);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public void updateSupply(int shelterId, int type, int amount) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String columnName = "";
            switch (type) {
                case 0:
                    columnName = "shelter_water";
                    break;
                case 1:
                    columnName = "shelter_food";
                    break;
                case 2:
                    columnName = "shelter_medicine";
                    break;
                default:
                    return;
            }

            String sql = "UPDATE shelter_info SET " + columnName + " = ? WHERE shelter_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, amount);
                statement.setInt(2, shelterId);

                int rowsAffected = statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setShelterid(int shelterId, int tc) {
        try {
            // Veritabanı bağlantısını oluştur
            Connection connection = DriverManager.getConnection(url, user, password);

            // Hazırlıklı bir sorgu oluştur
            String sqlQuery = "UPDATE user SET shelter_id = ? WHERE user_tc = ?";   //tc-> user_tc
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            // Parametreleri set et
            statement.setInt(1, shelterId);
            statement.setInt(2, tc);

            // Sorguyu çalıştır
            int affectedRows = statement.executeUpdate();

            // Bağlantıyı kapat
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incrementShelterPersonCount(int shelterId, int tc) {
        try {
            // Veritabanı bağlantısını oluştur
            Connection connection = DriverManager.getConnection(url, user, password);

            // Hazırlıklı bir sorgu oluştur
            String sqlQuery = "SELECT shelter_person_count, shelter_capacity FROM shelter_info WHERE shelter_id = ?";
            PreparedStatement selectStatement = connection.prepareStatement(sqlQuery);

            // Parametreyi set et
            selectStatement.setInt(1, shelterId);

            // Sorguyu çalıştır ve sonucu al
            ResultSet resultSet = selectStatement.executeQuery();

            // Sonuçları işle
            if (resultSet.next()) {
                int currentPersonCount = resultSet.getInt("shelter_person_count");
                int shelterCapacity = resultSet.getInt("shelter_capacity");

                // Kontrol et: Kapasite aşılmamalı
                if (currentPersonCount < shelterCapacity) {
                    // Hazırlıklı bir sorgu oluştur
                    String updateQuery = "UPDATE shelter_info SET shelter_person_count = shelter_person_count + 1 WHERE shelter_id = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);

                    // Parametreyi set et
                    updateStatement.setInt(1, shelterId);

                    // Sorguyu çalıştır
                    int affectedRows = updateStatement.executeUpdate();

                    // Güncellenen satır sayısını kontrol et
                    if (affectedRows > 0) {
                        System.out.println("shelter_person_count başarıyla artırıldı.");

                        // setShelterid fonksiyonunu çağır
                        setShelterid(shelterId, tc);
                    } else {
                        System.out.println("Belirtilen shelterId'ye sahip shelter bulunamadı.");
                    }

                    // Bağlantıyı kapat
                    updateStatement.close();
                } else {
                    System.out.println("Shelter kapasitesi aşıldı, artırma yapılamadı.");
                }
            } else {
                System.out.println("Belirtilen shelterId'ye sahip shelter bulunamadı.");
            }

            // Bağlantıyı kapat
            selectStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateShelterPersonCount(int shelterId, int tc) {
        try {
            // Veritabanı bağlantısını oluştur
            Connection connection = DriverManager.getConnection(url, user, password);

            // Eğer shelterId 0 ise
            if (shelterId == 0) {
                // Hazırlıklı bir sorgu oluştur
                String sqlQuery = "SELECT shelter_id FROM user WHERE user_tc = ?";
                PreparedStatement selectStatement = connection.prepareStatement(sqlQuery);
                selectStatement.setInt(1, tc);
                ResultSet resultSet = selectStatement.executeQuery();

                // Sonuçları işle
                if (resultSet.next()) {
                    int oldShelterId = resultSet.getInt("shelter_id");

                    // Eski shelterId'nin olduğu shelter_info tablosundaki person count'u bir azalt
                    String updateQuery = "UPDATE shelter_info SET shelter_person_count = shelter_person_count - 1 WHERE shelter_id = ?";
                    PreparedStatement decrementStatement = connection.prepareStatement(updateQuery);
                    decrementStatement.setInt(1, oldShelterId);
                    decrementStatement.executeUpdate();

                    // User tablosundaki shelterId'yi 0 yap
                    String updateUserQuery = "UPDATE user SET shelter_id = 0 WHERE user_tc = ?";
                    PreparedStatement updateUserStatement = connection.prepareStatement(updateUserQuery);
                    updateUserStatement.setInt(1, tc);
                    updateUserStatement.executeUpdate();

                    System.out.println("User tablosundaki shelterId 0 yapıldı ve shelter_info tablosundaki person count 1 azaltıldı.");
                } else {
                    System.out.println("Belirtilen TC ile bir kullanıcı bulunamadı.");
                }

                // Bağlantıyı kapat
                selectStatement.close();
            } else {
                // Eğer shelterId 0 değilse, mevcut işlemleri yap
                incrementShelterPersonCount(shelterId, tc);
            }

            // Bağlantıyı kapat
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
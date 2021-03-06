/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is2lab1;

/**
 *
 * @author Lokem
 */
public class User {
    static int usersNumber = 0;
    int userId;
    String userName;
    String userEmail;
    double balance = 0.0;
    boolean deleted = false;
    double moneySpent = 0.0;

    public User() 
    {
        usersNumber++;
        this.userId = usersNumber;
    }
    
    public double getMoneySpent() {
        return moneySpent;
    }

    public void increaseMoneySpent(double money) {
        this.moneySpent += money;
    }
    
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    String adress;
    String town;
    String province;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String toString()
    {
        String s;
        s = String.format("\nPROPIETARIO %d \nNombre del propietario: %s \nCorreo Electrónico: %s\n", this.userId, this.userName, this.userEmail);
        return s;
    }
}

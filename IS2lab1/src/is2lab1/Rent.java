/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is2lab1;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Lokem
 */
public class Rent {
    static int rentsNumber = 0;
    int rentID;
    int clientID;
    int objectID;
    LocalDate rentStart;
    LocalDate rentEnd;
    double startUpPrice;
    double totalPrice;

    public Rent() 
    {
        rentsNumber++;
        this.rentID = rentsNumber;
    }

    public double getStartUpPrice() {
        return startUpPrice;
    }

    public void setStartUpPrice(double startUpPrice) {
        this.startUpPrice = startUpPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public int getRentID() {
        return rentID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public LocalDate getRentStart() {
        return rentStart;
    }

    public void setRentStart(LocalDate rentStart) {
        this.rentStart = rentStart;
    }

    public LocalDate getRentEnd() {
        return rentEnd;
    }

    public void setRentEnd(LocalDate rentEnd) {
        this.rentEnd = rentEnd;
    }
    
    public String toString(ArrayList users)
    {
        Helper hp = new Helper();
        String s;
        s = String.format("\n\t\tPRÉSTAMOS DEL OBJETO %d \n\t\tNombre del cliente: %s \n\t\tFechas del préstamo: %d/%d/%d - %d/%d/%d"
                + "\n\t\tImporte del préstamo: %.2f \n\t\tImporte para la startup: %.2f\n", this.objectID, hp.getClientName(clientID, users), 
                this.rentStart.getDayOfMonth(), this.rentStart.getMonthValue(), this.rentStart.getYear(), this.rentEnd.getDayOfMonth(), 
                this.rentEnd.getMonthValue(), this.rentEnd.getYear(), this.totalPrice, this.startUpPrice);
        return s;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is2lab1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static java.util.concurrent.TimeUnit.DAYS;
 
public class Object {
   
    private int ownerNumber;
    private static int objectsNumber = 0;     //incremented by program
    private int codeNumber;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private double dailyCost;
    private long durationRent;
    private boolean available = true;
 
    public Object()
    {
        objectsNumber++;
        this.codeNumber = objectsNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setOwnerNumber(int ownerNumber) {
        this.ownerNumber = ownerNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setDailyCost(int dailyCost) {
        this.dailyCost = dailyCost;
    }
    
    public Object(int ownerNumber, int codeNumber, String description, LocalDate startDate, LocalDate endDate, int dailyCost) {
        this.ownerNumber = ownerNumber;
        this.codeNumber = codeNumber;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyCost = dailyCost;
        //durationRent = ChronoUnit.DAYS.between(startDate, endDate);
    }
 
    public int getOwnerNumber() {
        return ownerNumber;
    }
 
    public int getCodeNumber() {
        return codeNumber;
    }
 
    public String getDescription() {
        return description;
    }
 
    public LocalDate getStartDate() {
        return startDate;
    }
 
    public LocalDate getEndDate() {
        return endDate;
    }
 
    public double getDailyCost() {
        return dailyCost;
    }
   
    public long getDurationRent() {
        return durationRent;
    }
    
    public String toString()
    {
        String s;
        s = String.format("\n\tCódigo del objeto: %d \n\tDescripción: %s \n\tFecha de disponibilidad: %d/%d/%d - %d/%d/%d "
                + "\n\tCoste del préstamo por día: %.2f euros", this.codeNumber, this.description, this.startDate.getDayOfMonth(),
                this.startDate.getMonthValue(), this.startDate.getYear(), this.endDate.getDayOfMonth(), this.endDate.getMonthValue(), 
                this.endDate.getYear(), this.dailyCost);
        return s;
    }
}
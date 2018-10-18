/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is2lab1;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Lokem
 */
public class Helper {
  
    public static boolean isEmail(String userEmail)
    {
        return false;
    }
    
    public boolean isValidDate(LocalDate input, LocalDate start, LocalDate end)
    {
        if(input.isBefore(start))
            return false;
        else if(input.isAfter(end))
            return false;
        return true;
    }
    
    public boolean isEndDateValid(LocalDate input, LocalDate start)
    {
        if(input.isBefore(start) || input.isEqual(start))
            return false;
        return true;
    }
    
    public LocalDate validateDate()
    {
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do{
          try{
            LocalDate date = LocalDate.parse(input.nextLine(), dt);
            flag = false;
            return date;
            }catch(Exception e){
                System.out.print("\nFecha invalida.");
                System.out.print("\nIngrese nueva fecha: ");
            }  
        }while(flag);
        return null;
    }
    
    public int createUser(ArrayList users, int userCounter)
    {
        Scanner sc = new Scanner(System.in);
        User s = new User();
        System.out.print("\nNuevo Usuario");
        System.out.print("\nNombre: ");
        s.setUserName(sc.nextLine());
        System.out.print("\nEmail: ");
        s.setUserEmail(sc.nextLine());
        userCounter++;
        s.setUserId(userCounter);
        users.add(s);
        return userCounter;
    }
    
    public int createObject(ArrayList objects, ArrayList users, int objectCounter)
    {   
        Scanner sc = new Scanner(System.in);
        Object o = new Object();
        System.out.print("\nNuevo Objeto\n");
        for (Iterator<User> it = users.iterator(); it.hasNext();) {
            User us = it.next();
            String string;
            string = String.format("%d- %s\n", us.getUserId(), us.getUserName());
            System.out.print(string);
        }
        System.out.print("\nID de propietario: ");
        o.setOwnerNumber(Integer.parseInt(sc.nextLine()));
        System.out.print("\nDescripcion Objeto: ");
        o.setDescription(sc.nextLine());
        System.out.print("\nFecha de Inicio (dd/mm/yyyy): ");
        o.setStartDate(validateDate());    
        System.out.print("\nFecha de Termino (dd/mm/yyyy): ");           
        o.setEndDate(validateDate());
        System.out.print("\nCosto diario: ");
        o.setDailyCost(Integer.parseInt(sc.nextLine()));
        objectCounter++;
        o.setCodeNumber(objectCounter);
        objects.add(o);
        return objectCounter;
    }
    
    public boolean isOwner(ArrayList objects, int ownerID)
    {
        for(Iterator<Object> objit = objects.iterator(); objit.hasNext();)
        {
            Object o = objit.next();
            if(o.getOwnerNumber() == ownerID)
            {
                return true;
            }
        }
        return false;
    }
    
    public void showInformation(ArrayList users, ArrayList objects, ArrayList rents)
    {   
        int counterOwners = 0;
        int counterObjects = 0; 
        
        for (Iterator<User> it = users.iterator(); it.hasNext();)
        {
            User owner = it.next();
            counterOwners++;
            /*System.out.printf("\nPROPIETARIO " + owner.getUserId());
            System.out.printf("\nNombre del propietario: " + owner.getUserName());
            System.out.printf("\nCorreo Electrónico: " + owner.getUserEmail() + "\n");*/
            System.out.println(owner);
            System.out.printf("\n \tOBJETOS DEL PROPIETARIO " + counterOwners + "\n");
            
            if (!objects.isEmpty() && isOwner(objects, owner.getUserId()))
            {
                for (Iterator<Object> objit = objects.iterator(); objit.hasNext();)
                {   
                    Object o = objit.next();
                    if(o.getOwnerNumber() == owner.getUserId())
                    {
                        if(isRented(o.getCodeNumber(), rents))
                        {
                            counterObjects++;
                            /*System.out.printf("\n\tCódigo del objeto: " + o.getCodeNumber());
                            System.out.printf("\n\tDescripción: " + o.getDescription());
                            System.out.printf("\n\tFecha de disponibilidad: " + o.getStartDate().getDayOfMonth() + "/" + o.getStartDate().getMonth() + "/" 
                            + o.getStartDate().getYear() +" - " + o.getEndDate().getDayOfMonth() + "/" + o.getEndDate().getMonth() + "/" 
                            + o.getEndDate().getYear() + "\n");
                            System.out.printf("\n\tCoste del préstamo por día: " + o.getDailyCost() + " euros\n");*/
                            System.out.println(o);
                            showObjectRents(o.getCodeNumber(), rents, users, o);
                        } else
                            System.out.println("\n\t\tEl objeto " + o.getCodeNumber() + " no tiene préstamos asociados.");
                    }
                }
            }
            else 
                System.out.printf("El properietario " + counterOwners +" no tiene objetos asociados.\n");
        }
    }
    
    public void showBalances(ArrayList users, ArrayList objects, ArrayList rents)
    {   
        int counterOwners = 0;
        int counterObjects = 0;
        
        for (Iterator<User> it = users.iterator(); it.hasNext();)
        {
            User owner = it.next();
            counterOwners++;
            /*System.out.printf("\nPROPIETARIO " + owner.getUserId());
            System.out.printf("\nNombre del propietario: " + owner.getUserName());
            System.out.printf("\nCorreo Electrónico: " + owner.getUserEmail() + "\n");*/
            System.out.println(owner);
            
            
            if (!objects.isEmpty() && isOwner(objects, owner.getUserId()))
            {
                for (Iterator<Object> objit = objects.iterator(); objit.hasNext();)
                {   
                    Object o = objit.next();
                    if(o.getOwnerNumber() == owner.getUserId())
                    {
                        if(isRented(o.getCodeNumber(), rents))
                        {
                            counterObjects++;
                            showObjectRents(o.getCodeNumber(), rents, users, o);
                        }else
                            System.out.println("\n\t\tEl objeto " + o.getCodeNumber() + " no tiene préstamos asociados.");
                    }
                }
                System.out.print("Importe total acumulado para la startup: "+ owner.getBalance() + "euros\n");
            }
            else 
                System.out.printf("El properietario " + counterOwners +" no tiene objetos asociados.\n");
            
        }
    }
    
    public void showAllObjects(ArrayList objects)
    {
        for (Iterator<Object> objit = objects.iterator(); objit.hasNext();)
        {   
            Object o = objit.next();
            if(o.isAvailable())
            {
                /*System.out.printf("\n \tCódigo del objeto: " + o.getCodeNumber());
                System.out.printf("\n\tDescripción: " + o.getDescription());
                System.out.printf("\n\tFecha de disponibilidad: " + o.getStartDate().getDayOfMonth() + "/" + o.getStartDate().getMonth() + "/" 
                            + o.getStartDate().getYear() +" - " + o.getEndDate().getDayOfMonth() + "/" + o.getEndDate().getMonth() + "/" 
                            + o.getEndDate().getYear() + "\n");
                System.out.printf("\n\tCoste del préstamo por día: " + o.getDailyCost() + " euros\n");*/
                System.out.println(o);
            }
        }
    }
    
    public User getOwner(int objectID, ArrayList users)
    {
        for (Iterator<User> it = users.iterator(); it.hasNext();)
        {
            User us = it.next();
            if(objectID == us.getUserId())
                return us;
        }
        return null;
    }
    
    public Object getObject(int objectID, ArrayList objects)
    {
        for (Iterator<Object> it = objects.iterator(); it.hasNext();)
        {
            Object o = it.next();
            if(objectID == o.getCodeNumber())
                return o;
        }
        return null;
    }
    
    public void rentObject(ArrayList users, ArrayList objects, ArrayList rents, int rentCounter)
    {
        Rent r = new Rent();
        System.out.println("\nSeleccione usuario que requiere el alquiler\n");
        for (Iterator<User> it = users.iterator(); it.hasNext();) 
        {
            User us = it.next();
            String string;
            string = String.format("%d- %s\n", us.getUserId(), us.getUserName());
            System.out.print(string);
        }
        Scanner input = new Scanner(System.in);
        System.out.print("\nID de usuario: ");
        r.setClientID(Integer.parseInt(input.nextLine()));
        System.out.println("\nSeleccionar ID de objeto que desea alquilar");
        showAllObjects(objects);
        System.out.print("\nID de objeto: ");
        r.setObjectID(Integer.parseInt(input.nextLine()));
        System.out.print("\nFecha de inicio de alquiler (dd/mm/yyyy): ");
        r.setRentStart(validateDate());
        System.out.print("\nFecha de termino de alquiler (dd/mm/yyyy): ");
        r.setRentEnd(validateDate());
        Object o = getObject(r.getObjectID(), objects);
        r.setTotalPrice(getTotalPrice(r.getRentStart(), r.getRentEnd(), o.getDailyCost()));
        r.setStartUpPrice(getStartUpPrice(r.getRentStart(), r.getRentEnd(), o.getDailyCost()));
        rentCounter++;
        r.setRentID(rentCounter);
        rents.add(r);
        
        
        User us = getOwner(r.getObjectID(), users);
        us.setBalance(us.getBalance() + getStartUpPrice(r.getRentStart(), r.getRentEnd(), o.getDailyCost()));
    }
    
    public String getClientName(int clientID, ArrayList users)
    {
        for (Iterator<User> it = users.iterator(); it.hasNext();)
        {
            User us = it.next();
            if(us.getUserId() == clientID)
                return us.getUserName();
        }
        return "N/N";
    }
    
    public boolean isLeapYear(int year)
    {
        if(((year%4) == 0 && (year%100) != 0) || ((year%400) == 0))
        {
            return true;
        }else
            return false;          
    }
    
    public double getTotalPrice(LocalDate start, LocalDate end, double dailycost)
    {   
        if(start.getYear() == end.getYear())
            return (end.getDayOfYear() - start.getDayOfYear()) * dailycost;
        else
            return ((end.getDayOfYear() - start.getDayOfYear()) + ((end.getYear() - start.getYear()))*365) * dailycost;
    }
    
    public double getStartUpPrice(LocalDate start, LocalDate end, double dailycost)
    {
        return getTotalPrice(start, end, dailycost) * 0.1;
    }
    
    public User getClient(int clientID, ArrayList users)
    {
        for (Iterator<User> it = users.iterator(); it.hasNext();) 
        {
            User us = it.next();
            if(clientID == us.getUserId())
                return us;
        }
        return null;
    }
    
    public void showObjectRents(int objectID, ArrayList rents, ArrayList users, Object o)
    {
        for (Iterator<Rent> rentIt = rents.iterator(); rentIt.hasNext();) 
        {
            Rent r = rentIt.next();
            if(r.getObjectID() == objectID)
            {
                /*System.out.printf("\n\t\tPRÉSTAMOS DEL OBJETO " + objectID + "\n");
                System.out.printf("\n\t\tNombre del cliente: " + getClientName(r.getClientID(), users) + "\n");
                System.out.printf("\n\t\tFechas del préstamo: " + r.getRentStart().getDayOfMonth() + "/" + r.getRentStart().getMonth() + "/" 
                        + r.getRentStart().getYear() +" - " + r.getRentEnd().getDayOfMonth() + "/" + r.getRentEnd().getMonth() + "/" 
                        + r.getRentEnd().getYear() + "\n");
                System.out.printf("\n\t\tImporte del préstamo: " + r.getTotalPrice());
                System.out.printf("\n\t\tImporte para la startup: " + r.getStartUpPrice() + "\n");*/
                System.out.println(r.toString(users));
            }
        }
    }
    
    public void unsubscribeObject(ArrayList objects)
    {
        showAllObjects(objects);
        System.out.print("\nIngrese el ID del objeto que desea dar de baja: ");
        Scanner input = new Scanner(System.in);
        int objectID = Integer.parseInt(input.nextLine());
        for (Iterator<Object> objit = objects.iterator(); objit.hasNext();)
        {   
            Object o = objit.next();
            if(objectID == o.getCodeNumber())
                o.setAvailable(false);
        }
    }
    
    public boolean isRented(int objectID, ArrayList rents)
    {
        for (Iterator<Rent> rentIt = rents.iterator(); rentIt.hasNext();)
        {   
            Rent r = rentIt.next();
            if(objectID == r.getObjectID())
                return true;
        }
        return false;
    }
    
    public void editObject(ArrayList objects)
    {
        Scanner sc = new Scanner(System.in);
        showAllObjects(objects);
        System.out.print("\nThe objectID to change: ");
        int objectID = Integer.parseInt(sc.nextLine());
        Object ob = getObject(objectID, objects);
        System.out.print("\nNuevo costo diario: ");
        ob.setDailyCost(Integer.parseInt(sc.nextLine()));
    }
}

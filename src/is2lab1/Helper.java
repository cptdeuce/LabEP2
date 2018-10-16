/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is2lab1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lokem
 */
public class Helper {
  
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
            System.out.printf("\nPROPIETARIO " + owner.getUserId());
            System.out.printf("\nNombre del propietario: " + owner.getUserName());
            System.out.printf("\nCorreo Electrónico: " + owner.getUserEmail() + "\n");
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
                            System.out.printf("\n \tCódigo del objeto: " + o.getCodeNumber());
                            System.out.printf("\n\tDescripción: " + o.getDescription());
                            System.out.printf("\n\tFecha de disponibilidad: " + o.getStartDate().getDayOfMonth() + "/" + o.getStartDate().getMonth() + "/" 
                            + o.getStartDate().getYear() +" - " + o.getEndDate().getDayOfMonth() + "/" + o.getEndDate().getMonth() + "/" 
                            + o.getEndDate().getYear() + "\n");
                            System.out.printf("\n\tCoste del préstamo por día: " + o.getDailyCost() + " euros\n");
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
            System.out.printf("\nPROPIETARIO " + owner.getUserId());
            System.out.printf("\nNombre del propietario: " + owner.getUserName());
            System.out.printf("\nCorreo Electrónico: " + owner.getUserEmail() + "\n");
            
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
                System.out.printf("\n \tCódigo del objeto: " + o.getCodeNumber());
                System.out.printf("\n\tDescripción: " + o.getDescription());
                System.out.printf("\n\tFecha de disponibilidad: " + o.getStartDate().getDayOfMonth() + "/" + o.getStartDate().getMonth() + "/" 
                            + o.getStartDate().getYear() +" - " + o.getEndDate().getDayOfMonth() + "/" + o.getEndDate().getMonth() + "/" 
                            + o.getEndDate().getYear() + "\n");
                System.out.printf("\n\tCoste del préstamo por día: " + o.getDailyCost() + " euros\n");
            }
        }
    }
    
    public User getOwner(int objectID, ArrayList users)
    {
        for (Iterator<User> it = users.iterator(); it.hasNext();)
        {
            User us = it.next();
            //System.out.println("\nobjectID = " + objectID + " and ownerID = " + us.getUserId());
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
        System.out.print("\nFecha de inicio de alquiler (yyyy-mm-dd): ");
        r.setRentStart(LocalDate.parse(input.nextLine()));
        System.out.print("\nFecha de termino de alquiler (yyyy-mm-dd): ");
        r.setRentEnd(LocalDate.parse(input.nextLine()));
        rentCounter++;
        r.setRentID(rentCounter);
        Object o = getObject(r.getObjectID(), objects);
        r.setTotalPrice(getTotalPrice(r.getRentStart() , r.getRentEnd() , o.getDailyCost()));
        r.setStartUpPrice(getStartUpPrice(r.getRentStart() , r.getRentEnd() , o.getDailyCost()));
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
                System.out.printf("\n \t\tPRÉSTAMOS DEL OBJETO " + objectID + "\n");
                System.out.printf("\n\t\tNombre del cliente: " + getClientName(r.getClientID(), users) + "\n");
                System.out.printf("\n\t\tFechas del préstamo: " + r.getRentStart().getDayOfMonth() + "/" + r.getRentStart().getMonth() + "/" 
                        + r.getRentStart().getYear() +" - " + r.getRentEnd().getDayOfMonth() + "/" + r.getRentEnd().getMonth() + "/" 
                        + r.getRentEnd().getYear() + "\n");
                System.out.printf("\n\t\tImporte del préstamo: " + r.getTotalPrice());
                System.out.printf("\n\t\tImporte para la startup: " + r.getStartUpPrice() + "\n");
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
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is2lab1;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Lokem
 */
public class Helper {
  
    public static boolean isEmail(String userEmail)
    {
        Pattern emailRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailRegex.matcher(userEmail);
        return matcher.find();
    }
    
    public void validateEmail(User s, Scanner sc)
    {
        String emailTemp;
        do{
            emailTemp = sc.nextLine();
            if(isEmail(emailTemp))
            {
                s.setUserEmail(emailTemp);
            }else{
                System.out.print("\nEmail invalido, ingrese un email valido.");
                System.out.print("\nEmail: ");
            } 
        }while(!isEmail(emailTemp));
    }
    
    public boolean isValidDate(LocalDate input, LocalDate start, LocalDate end)
    {
        return (!input.isBefore(start) && !input.isAfter(end)) && !isStartDateValid(input);
    }
    
    public boolean isEndDateValid(LocalDate input, LocalDate start)
    {
        return !(input.isBefore(start) || input.isEqual(start));
    }
    
    public boolean isStartDateValid(LocalDate input)
    {
        return !input.isBefore(LocalDate.now());
    }
    public LocalDate validateDate()
    {
        Scanner input = new Scanner(System.in);
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do{
          try{
            LocalDate date = LocalDate.parse(input.nextLine(), dt);
            //if(isStartDateValid(date))
            //{
                return date;
            //}
            
            }catch(Exception e){
                System.out.print("\nFecha invalida.");
                System.out.print("\nIngrese nueva fecha: ");
            }  
        }while(true);
    }
    
    public LocalDate validateDate(LocalDate start)
    {
        Scanner input = new Scanner(System.in);
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do{
          try{
            LocalDate date = LocalDate.parse(input.nextLine(), dt);
            //if(isStartDateValid(date) && isEndDateValid(date, start))
            //{
                return date;
            //}
            
            }catch(Exception e){
                System.out.print("\nFecha invalida.");
                System.out.print("\nIngrese nueva fecha: ");
            }  
        }while(true);
    }

    public LocalDate validateDate(LocalDate start, LocalDate end)
    {
        Scanner input = new Scanner(System.in);
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do{
          try{
            LocalDate date = LocalDate.parse(input.nextLine(), dt);
            //if(isValidDate(date, start, end))
            //{
                return date;
            //}
            
            }catch(Exception e){
                System.out.print("\nFecha invalida.");
                System.out.print("\nIngrese nueva fecha: ");
            }  
        }while(true);
    }
    
    public void createUser(ArrayList users)
    {
        Scanner sc = new Scanner(System.in);
        User s = new User();
        System.out.print("\nNuevo Usuario");
        System.out.print("\nNombre: ");
        s.setUserName(sc.nextLine());
        System.out.print("\nEmail: ");
        validateEmail(s, sc);
        System.out.print("\nDirección: ");
        s.setAdress(sc.nextLine());
        System.out.print("\nPoblación: ");
        s.setTown(sc.nextLine());
        System.out.print("\nProvincia: ");
        s.setProvince(sc.nextLine());
        users.add(s);
    }
    
    public void createObject(ArrayList objects, ArrayList users)
    {   
        Scanner sc = new Scanner(System.in);
        Object o = new Object();  
        if(!users.isEmpty())
        {
            System.out.print("\nNuevo Objeto\n");
            for (Iterator<User> it = users.iterator(); it.hasNext();) {
                User us = it.next();
                System.out.println(us);
            }
            System.out.print("\nID de propietario: ");
            o.setOwnerNumber(Integer.parseInt(sc.nextLine()));
            System.out.print("\nDescripcion Objeto: ");
            o.setDescription(sc.nextLine());
            System.out.print("\nFecha de Inicio (dd/mm/yyyy): ");
            o.setStartDate(validateDate());    
            System.out.print("\nFecha de Termino (dd/mm/yyyy): ");           
            o.setEndDate(validateDate(o.getStartDate()));
            System.out.print("\nCosto diario: ");
            o.setDailyCost(Integer.parseInt(sc.nextLine()));
            objects.add(o);
        }else
            System.out.print("\nNo hay usuarios en el sistema.\n");
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
        
        for (Iterator<User> it = users.iterator(); it.hasNext();)
        {
            User owner = it.next();
            counterOwners++;
            /*System.out.printf("\nPROPIETARIO " + owner.getUserId());
            System.out.printf("\nNombre del propietario: " + owner.getUserName());
            System.out.printf("\nCorreo Electrónico: " + owner.getUserEmail() + "\n");*/
            System.out.println(owner);
            System.out.printf("\tOBJETOS DEL PROPIETARIO " + counterOwners + "\n");
            
            if (!objects.isEmpty() && isOwner(objects, owner.getUserId()))
            {
                for (Iterator<Object> objit = objects.iterator(); objit.hasNext();)
                {   
                    Object o = objit.next();
                    if(o.getOwnerNumber() == owner.getUserId())
                    {
                        if(isRented(o.getCodeNumber(), rents))
                        {
                            /*System.out.printf("\n\tCódigo del objeto: " + o.getCodeNumber());
                            System.out.printf("\n\tDescripción: " + o.getDescription());
                            System.out.printf("\n\tFecha de disponibilidad: " + o.getStartDate().getDayOfMonth() + "/" + o.getStartDate().getMonth() + "/" 
                            + o.getStartDate().getYear() +" - " + o.getEndDate().getDayOfMonth() + "/" + o.getEndDate().getMonth() + "/" 
                            + o.getEndDate().getYear() + "\n");
                            System.out.printf("\n\tCoste del préstamo por día: " + o.getDailyCost() + " euros\n");*/
                            System.out.println(o);
                            System.out.print(showObjectRents(o.getCodeNumber(), rents, users, o));
                        } else
                            System.out.println("\n\n\t\tEl objeto " + o.getCodeNumber() + " no tiene préstamos asociados.");
                    }
                }
            }
            else 
                System.out.printf("\nEl propietario " + counterOwners +" no tiene objetos asociados.\n");
        }
    }
    
    public String showBalances(ArrayList users, ArrayList objects, ArrayList rents)
    {   
        int counterOwners = 0;
        String s = "";
                
        for (Iterator<User> it = users.iterator(); it.hasNext();)
        {
            User owner = it.next();
            counterOwners++;
            /*System.out.printf("\nPROPIETARIO " + owner.getUserId());
            System.out.printf("\nNombre del propietario: " + owner.getUserName());
            System.out.printf("\nCorreo Electrónico: " + owner.getUserEmail() + "\n");*/
            s = String.format(s + owner.toString());
            //System.out.println(owner);
            
            
            if (!objects.isEmpty() && isOwner(objects, owner.getUserId()))
            {
                for (Iterator<Object> objit = objects.iterator(); objit.hasNext();)
                {   
                    Object o = objit.next();
                    if(o.getOwnerNumber() == owner.getUserId())
                    {
                        if(isRented(o.getCodeNumber(), rents))
                        {
                            s = String.format(s + showObjectRents(o.getCodeNumber(), rents, users, o));
                        }else
                        {
                            //System.out.println("\n\t\tEl objeto " + o.getCodeNumber() + " no tiene préstamos asociados.");
                            s = String.format(s + "\n\n\t\tEl objeto " + o.getCodeNumber() + " no tiene préstamos asociados.");
                        }
                    }
                }
                //System.out.println("Importe total acumulado para la startup: "+ owner.getBalance() + "euros\n");
                s = String.format(s + "\nImporte total acumulado para la startup: %.2f euros\n", owner.getBalance());
            }
            else 
            {
                //System.out.printf("El properietario " + counterOwners +" no tiene objetos asociados.\n");
                s = String.format(s + "\nEl propietario " + counterOwners +" no tiene objetos asociados.\n");
            }
        }
        System.out.print(s);
        return s;
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
    
    public void rentObject(ArrayList users, ArrayList objects, ArrayList rents)
    {
        Rent r = new Rent();
        if(!objects.isEmpty())
        {
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
            Object o = getObject(r.getObjectID(), objects);
            System.out.print("\nFecha de inicio de alquiler (dd/mm/yyyy): ");
            r.setRentStart(validateDate(o.getStartDate(), o.getEndDate()));
            System.out.print("\nFecha de termino de alquiler (dd/mm/yyyy): ");
            r.setRentEnd(validateDate(o.getStartDate(), o.getEndDate()));
            
            double totPrice = getTotalPrice(r.getRentStart(), r.getRentEnd(), o.getDailyCost());
            r.setTotalPrice(totPrice);
            r.setStartUpPrice(getStartUpPrice(r.getRentStart(), r.getRentEnd(), o.getDailyCost()));
            rents.add(r);


            User us = getOwner(r.getObjectID(), users);
            us.setBalance(us.getBalance() + getStartUpPrice(r.getRentStart(), r.getRentEnd(), o.getDailyCost()));
            User ren = getOwner(r.getClientID(), users);
            ren.increaseMoneySpent(totPrice);
        }else
            System.out.print("\nNo hay objetos para alquilar.\n");
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
        return ((year%4) == 0 && (year%100) != 0) || ((year%400) == 0);          
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
    
    public String showObjectRents(int objectID, ArrayList rents, ArrayList users, Object o)
    {
        String s = "";
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
                s = String.format(s + r.toString(users));
                //System.out.println(s);
            }
        }
        return s;
    }
    
    public void unsubscribeObject(ArrayList objects)
    {
        showAllObjects(objects);
        System.out.print("\nIngrese el ID del objeto que desea dar de baja: ");
        Scanner input = new Scanner(System.in);
        int objectID = Integer.parseInt(input.nextLine());
        unsubscribeFinder(objectID, objects);
    }
    public void unsubscribeFinder(int objectID, ArrayList objects)
    {
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
    public void putInfoInTextFile(ArrayList users, ArrayList objects, ArrayList rents)
    {
        String str = showBalances(users, objects, rents);
        str = str.replace("\n", System.lineSeparator());
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                  new FileOutputStream("info_saldos.txt"), "utf-8"));
            writer.write(str);
        } catch (IOException ex) {
            // Report
        } finally {
           try {writer.close();} catch (IOException ex) {/*ignore*/}
        }
    }
    
    public void showAllUsers(ArrayList users)
    {
        for (Iterator<User> it = users.iterator(); it.hasNext();) 
        {
            User us = it.next();
            System.out.println("\n" + us.getUserId() + " " + us.getUserName());
        }
    }
    
    public void deleteUser(ArrayList objects, ArrayList users)
    {   
        System.out.println("\nUserID to delete: \n");
        showAllUsers(users);
        Scanner input = new Scanner(System.in);
        int userID = Integer.parseInt(input.nextLine()); 
        for (Iterator<Object> objit = objects.iterator(); objit.hasNext();)
            {   
                Object o = objit.next();
                if(o.getOwnerNumber() == userID)
                {
                    unsubscribeFinder(o.getOwnerNumber(), objects);
                }
            }
        for (Iterator<User> it = users.iterator(); it.hasNext();)
        {
            User owner = it.next();
            if (owner.getUserId() == userID)
                owner.setDeleted(true);
        }
    }
    
    public void showRegulars(ArrayList users)
    {
        System.out.println("\nEl usuarios mas regular: \n");
        int counter = 0;
        ArrayList temp = orderedUsersAmountRentals(users);
        for (Iterator<User> it = temp.iterator(); it.hasNext();)
        {
            User us = it.next();
            if (us.getMoneySpent() > 0)
            {   
                counter++;
                System.out.println(counter + ". " + us.getUserName() + "\nTotal importe de alquiler pagado:\t" +
                    us.getMoneySpent() + "\nDirección:\t" + us.getAdress() + "\n");
            }
        }
    }
    
    public ArrayList orderedUsersAmountRentals(ArrayList users)
    {
        ArrayList temp = users;
        Collections.sort(temp, (User u1, User u2) -> {
            return Double.compare(u1.getMoneySpent(), u2.getMoneySpent());
        });
        //Collections.sort(temp, Collections.reverseOrder());
        Collections.reverse(temp);
        return temp;
    }
    
}

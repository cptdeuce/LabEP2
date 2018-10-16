/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is2lab1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Lokem
 */
public class IS2lab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int userCounter = 0;
        int objectCounter = 0;
        int rentCounter = 0;
        ArrayList users = new ArrayList();
        ArrayList objects = new ArrayList();
        ArrayList rents = new ArrayList();
        Helper hp = new Helper();
        
        String S = String.format("\n1-Alta de Usuario\n" + "2-Alta de objeto\n" +
                "3-Alquiler de objeto\n" + "4-Listar todos los objetos\n" +
                "5-Baja de objeto\n" + "6-Mostrar saldos\n" + "7-Editar costo diario de objeto\n" + "8-Salir\n");
        Scanner sc = new Scanner(System.in);
        int menu = 0;
        do{
            switch(menu){
                case 0:
                    System.out.print(S);
                    Scanner reader = new Scanner(System.in);
                    menu = reader.nextInt();
                    break;
                case 1:  
                    User s = new User();
                    System.out.print("\nNuevo Usuario");
                    System.out.print("\nNombre: ");
                    s.setUserName(sc.nextLine());
                    System.out.print("\nEmail: ");
                    s.setUserEmail(sc.nextLine());
                    userCounter++;
                    s.setUserId(userCounter);
                    users.add(s);
                    menu = 0;
                    break;
                case 2:
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
                    System.out.print("\nFecha de Inicio (yyyy-mm-dd): ");
                    o.setStartDate(LocalDate.parse(sc.nextLine()));
                    System.out.print("\nFecha de Termino (yyyy-mm-dd): ");
                    o.setEndDate(LocalDate.parse(sc.nextLine()));
                    System.out.print("\nCosto diario: ");
                    o.setDailyCost(Integer.parseInt(sc.nextLine()));
                    objectCounter++;
                    o.setCodeNumber(objectCounter);
                    objects.add(o);
                    menu = 0;
                    break;
                case 3:
                    hp.rentObject(users, objects, rents, rentCounter);
                    menu = 0;
                    break;
                case 4:
                    hp.showInformation(users, objects, rents);
                    menu = 0;
                    break;
                case 5:
                    hp.unsubscribeObject(objects);
                    menu = 0;
                    break;
                case 6:
                    hp.showBalances(users, objects, rents);
                    menu = 0;
                    break;
                case 7:
                    hp.showAllObjects(objects);
                    System.out.print("\nThe objectID to change: ");
                    int objectID = Integer.parseInt(sc.nextLine());
                    Object ob = hp.getObject(objectID, objects);
                    System.out.print("\nNuevo costo diario: ");
                    ob.setDailyCost(Integer.parseInt(sc.nextLine()));
                    menu = 0;
                    break;
                case 8:
                    break;
                default:
                    System.out.print(S);
            }
        }while(menu != 8);
    }
    
}

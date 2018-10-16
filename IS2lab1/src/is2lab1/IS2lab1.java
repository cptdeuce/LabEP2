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
                "5-Baja de objeto\n" + "6-Mostrar saldos\n" + "" +"7-Salir\n");
        int menu = 0;
        do{
            switch(menu){
                case 0:
                    System.out.print(S);
                    Scanner reader = new Scanner(System.in);
                    menu = reader.nextInt();
                    break;
                case 1:  
                    hp.createUser(users, userCounter);
                    menu = 0;
                    break;
                case 2:
                    hp.createObject(objects, users, objectCounter);
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
                    break;
                default:
                    System.out.print(S);
            }
        }while(menu != 7);
    }
    
}

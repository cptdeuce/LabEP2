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
        
        int rentCounter = 0;
        ArrayList users = new ArrayList();
        ArrayList objects = new ArrayList();
        ArrayList rents = new ArrayList();
        Helper hp = new Helper();
        
        String S = String.format("\n1-Alta de Usuario\n" + "2-Alta de objeto\n" +
                "3-Alquiler de objeto\n" + "4-Listar todos los objetos\n" +
                "5-Baja de objeto\n" + "6-Mostrar saldos\n" + "7-Editar costo diario de objeto\n" 
                + "8-Recibir textfile de saldos\n" + "9-Eliminar usuario\n" + "10-Listar más asiduos\n" + "11-Salir\n");
              
        int menu = 0;
        do{
            switch(menu){
                case 0:
                    System.out.print(S);
                    Scanner reader = new Scanner(System.in);
                    menu = reader.nextInt();
                    break;
                case 1:  
                    hp.createUser(users);
                    menu = 0;
                    break;
                case 2:
                    hp.createObject(objects, users);
                    menu = 0;
                    break;
                case 3:
                    hp.rentObject(users, objects, rents);
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
                    hp.editObject(objects);
                    menu = 0;
                    break;
                case 8:
                    hp.putInfoInTextFile(users, objects, rents);
                    menu = 0;
                    break;
                case 9:
                    hp.deleteUser(objects, users);
                    menu = 0;
                    break;
                case 10:
                    hp.showRegulars(users);
                    menu = 0;
                    break;
                case 11:
                    break;
                default:
                    System.out.print(S);
            }
        }while(menu != 11);
    }
    
}

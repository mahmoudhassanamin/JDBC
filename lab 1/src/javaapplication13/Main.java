/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication13;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author eng. mahmoud hassan
 */
public class Main {
     public static void main(String[] args) {
        DAO obj = new DAO();
//        obj.getContacts().forEach((e)->{
//            System.out.println( e.getEmail());
//        });
         try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse("9/8/1950");
            // id must be uniqe
            ContactOfPerson person = new ContactOfPerson("bassant","basbosa","elharam","3247823","2331231","01287","bosbos@gmail.com",date,"basbosa.com","el bos");
            obj.insertContactPerson(person);
         } catch (ParseException ex) {
             Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         }
//         obj.getContactOfPerson("bassant").forEach((e)->{
//            System.out.println( e.getName()+" "+e.getNick_name());
//        });
         
         Date date;
         try {
             date = new SimpleDateFormat("dd/MM/yyyy").parse("9/8/1950");
             ContactOfPerson person = new ContactOfPerson("hamada","basbosa","elharam","3247823","2331231","01287","bosbos@gmail.com",date,"basbosa.com","el bos");
             person.setId(1);
             person.setHome_phone("03437744");
             obj.editContactOfPersonById(person);
         } catch (ParseException ex) {
             Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
         
         obj.closeConnection();
        
        
    }
                }

    
    


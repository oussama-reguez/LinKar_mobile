/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.utils;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Label;
import com.linkar.entities.Membre;
import java.io.IOException;






/**
 *
 * @author Oussama Reguez
 */
public class SqlLite {
    
     public static Database db;
    public static void createDb(){
        
    }
    public static void dropDataBase(){
         try {
             Database.delete("membre");
         } catch (IOException ex) {
            
         }
    }
    public static void initDb(){
         try {
        if(Database.exists("membre")){
          
                Database.delete("membre");
           
                System.err.println("deleted");
           
        }
          Database.openOrCreate("membre");
          SqlLite.createTableMember();
          
         } catch (IOException ex) {
               
            }
      
    }
    public static void clearMemberTable(){
         try {
            db=Database.openOrCreate("membre");
          db.execute(" DELETE FROM membre; ");
                                
            
        } catch (IOException ex) {
           
        }
    }
    public static Membre getMember(){
         Cursor c;
           Membre m = null;
         try {
             if(!Database.exists("membre")){
                 return null;
             }
              db = Database.openOrCreate("membre");
             c = db.executeQuery("select * from membre ");
          
             while (c.next()) {
                            Row r = c.getRow();
                            m = new Membre();
                            m.setId_member(r.getInteger(0));
                            m.setLast_name(r.getString(1));
                            m.setFirst_name(r.getString(2));
                            m.setPassword(r.getString(3));
                            m.setEmail(r.getString(4));
                             m.setUsername(r.getString(5));
                             m.setUrl_picture(r.getString(6));
                            
                          System.err.println("");
                           ;
                             }
         } catch (IOException ex) {
            
         }
         return m;
                       
    }
    public static void UpdateMember(Membre membre){
         try {
            db=Database.openOrCreate("membre");
          db.execute(" update membre set "
                                + " last_name='"
                                +membre.getLast_name()+"',first_name='"
                                +membre.getFirst_name()+"',password='"+membre.getPassword()+"',email='"+membre.getEmail()+"',username='"+membre.getUsername()+"',url_picture='"+membre.getUrl_picture()+"' where id="+membre.getId_member());
            
        } catch (IOException ex) {
           
        }
    }
    public static void createTableMember(){
        try {
          boolean created = Database.exists("membre");
            
            db = Database.openOrCreate("membre");
           
                db.execute("CREATE TABLE `membre` (\n" +
"  `id` int(11) NOT NULL,\n" +
"  `last_name` varchar(50) DEFAULT NULL,\n" +
"  `first_name` varchar(50) DEFAULT NULL,\n" +
"  `password` varchar(255) DEFAULT NULL,\n" +
"  `email` varchar(180) DEFAULT NULL ,\n" +
"  `username` varchar(180) DEFAULT NULL ,\n" +
"  `url_picture` varchar(180) DEFAULT NULL \n" +
"  );");
            
            
        } catch (IOException ex) {
            
        }
    }
    public static void insertMember(  Membre membre){
        try {
            db=Database.openOrCreate("membre");
          db.execute("insert into membre "
                                + " values ("+membre.getId_member()+",'"
                                +membre.getLast_name()+"','"
                                +membre.getFirst_name()+"','"+membre.getPassword()+"','"+membre.getEmail()+"','"+membre.getUsername()+"','"+membre.getUrl_picture()+"')");
            
           
        
        } catch (IOException ex) {
         System.out.println(ex.getMessage());
        }
    }
   // public static void loggOutMember(Member)
}

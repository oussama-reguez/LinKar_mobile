/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;

import com.codename1.components.Accordion;
import com.codename1.db.Database;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.facebook.User;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.linkar.entities.Membre;
import static com.linkar.main.MyApplication.LOGIN_URL;
import static com.linkar.main.MyApplication.connectedMember;
import com.linkar.utils.Json;
import com.linkar.utils.SqlLite;
import java.io.IOException;




/**
 *
 * @author Oussama Reguez
 */

public class SplashScreen {
    Form f ;
     public static String TOKEN;
     public static final String VERIFY_PASSWORD_URL="http://localhost/linkar_web/web/app_dev.php/rest/verifyPassword";
     boolean  verifyPassword=false;
     public boolean verifyPassword(Membre m){
        ConnectionRequest con = new ConnectionRequest();
   
            con.setUrl(VERIFY_PASSWORD_URL+"?id="+m.getId_member()+"&password="+m.getPassword());
        
            con.addResponseListener((evt) -> {
             String response = new String (con.getResponseData());
               if(response.equals("error")){
                verifyPassword=false;
               }
               else{
                   verifyPassword=true;
               }
            });
          
            
            
            NetworkManager.getInstance().addToQueueAndWait(con);
        return verifyPassword;
    }
     
     public static boolean isTokenExist() {
        return Storage.getInstance().readObject("token") == null;
    }
    public SplashScreen(Resources theme){
        f = new Form();
        Label l = new Label("linkar");
        Button b = new Button("start");
        b.addActionListener((evt) -> {
           // handleLoginProcess();
           // test2();
           test3();
        });
        Container c = new Container();
        c.add(l);
        c.add(b);
        f.add(c);
        f.show();
              
    }
    boolean expired = false;
    void test(){
     
        
      
          FaceBookAccess.setClientId("428671887491341");
        FaceBookAccess.setClientSecret("29f3fd42e1050079087e92657920cf23");
        FaceBookAccess.setRedirectURI("http://localhost/linkar/web/app_dev.php/");
        FaceBookAccess.setPermissions(new String[]{"user_location", "user_photos","user_birthday","user_about_me","email"});
   
        FaceBookAccess.setToken("EAAGF3ZCOUQQ0BAJHxHn952FGuDiyslAU8qgD1VI9NRhOXVu6wGz8cpJ2ood5McW9JHttQaUSKfyY7irfR7Op39ZADvxe5NpBweCYXdZA1bWArLXE03DJxRbx1sYbCpApG1s0rY79JlnEN4NytuBJfUOd1j5u2sZD");
        try {
            User  me =    FaceBookAccess.getInstance().getUser(null);
            System.err.println("");
        } catch (IOException ex) {
             System.err.println("");
        }
    }
    public Membre handeFacebookLogin(){
        Membre m  = null;
        TOKEN = (String) Storage.getInstance().readObject("token");
        if(TOKEN!= null){        
            FaceBookAccess.setToken(TOKEN);
            //in case token has expired re-authenticate
            FaceBookAccess.getInstance().addResponseCodeListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent evt) {
                    NetworkEvent ne = (NetworkEvent) evt;
                    int code = ne.getResponseCode();                    
                    if (code == 400) {
                  expired=true;
                    }                    
                }
            });
            if(expired){
                return null;
            }
            
            
              //token exist  let's get the connected user Object 
                try {
            
    
            System.err.println("");  
       User  me =    FaceBookAccess.getInstance().getUser(null);
       //convert it to Member object 
                 m=userToMembre(me);
                 return m;
        } catch (IOException ex) {
            return null;  
        }
            }
      
        
            return null;
    }
    
    public Membre userToMembre(User f){
        Membre m = new Membre();
        m.setId_member(Integer.parseInt(f.getId()));
        m.setFirst_name(f.getFirst_name());
        m.setLast_name(f.getLast_name());
        m.setEmail(f.getEmail());
        return m;
    }
    void initMember(){
         connectedMember = new Membre();
         connectedMember.setId_member(14);
         connectedMember.setFirst_name("sou");
         connectedMember.setLast_name("regez");
         connectedMember.setEmail("oussamareguez@gmail.com");
         connectedMember.setPassword("sdsdfs");
         connectedMember.setUsername("oussama");
         connectedMember.setUrl_picture("ddd");
     }
    public void test2(){
initMember();
        SqlLite.initDb();

SqlLite.insertMember(connectedMember);
  // Membre m= SqlLite.getMember();
 Membre m =SqlLite.getMember();
        System.err.println("");
    }
    public void handleLoginProcess(){
        //check is a user exist in sqlite
        if(!Database.exists("membre")){
            
            connectedMember=handeFacebookLogin();
            if(connectedMember==null){
                //user is not connected to facebook
                //check sql lite 
                //verify local password 
              connectedMember=SqlLite.getMember();
              if(!verifyPassword(connectedMember)){
                  //signin form
              }
              else{
                  // show main app
              }
                
            }else{
//user is logged in with facebook                 
//show main app
            }
        }
        else{
             //show main app
        }
//test();
//  handeFacebookLogin();
        /*
            if(Database.exists("membre")){
 //verify if user has a token for facebook 
    if (isTokenExist()) {
              //check if token has expired and get user fb object 
                //token exists no need to authenticate
            
              //convert fb object to member object 
              
              //update member object in database 
              
              //update member object  in sqlLite 
              
          }
            
//verify if password match in data base !
            
            
           
            
        }
        
          
        
   */
    }
    public Form getForm(){
return f;
}
    
 public void test3(){
     LocalNotification n = new LocalNotification();
                n.setAlertBody("dfd");
                n.setAlertTitle("dd");
                n.setId("dd");
                n.setBadgeNumber(1);

                int repeatType = LocalNotification.REPEAT_NONE;
                 Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis() + 10 * 1000, repeatType);
 }   
}

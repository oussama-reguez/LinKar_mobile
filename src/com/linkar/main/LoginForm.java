/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;

import com.codename1.components.ImageViewer;
import com.codename1.db.Database;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.AccessToken;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.linkar.entities.Membre;
import static com.linkar.main.MyApplication.LOGIN_URL;
import com.linkar.utils.Json;
import java.io.IOException;

/**
 *
 * @author Oussama Reguez
 */

public class LoginForm {
  private   Form form;
   public static String TOKEN;
    private Membre connectedMember =null;
    private Resources theme;
   
    private static void signIn(final Form main) {
         FaceBookAccess.setClientId("428671887491341");
        FaceBookAccess.setClientSecret("29f3fd42e1050079087e92657920cf23");
        FaceBookAccess.setRedirectURI("http://localhost/linkar/web/app_dev.php/");
       FaceBookAccess.setPermissions(new String[]{"user_location", "user_photos", "friends_photos", "publish_stream", "read_stream", "user_relationships", "user_birthday",
                    "friends_birthday", "friends_relationships", "read_mailbox", "user_events", "friends_events", "user_about_me"});
       
       
        FaceBookAccess.getInstance().showAuthentication(new ActionListener() {
            
            public void actionPerformed(ActionEvent evt) {
                System.err.println("");
                if (evt.getSource() instanceof  AccessToken) {
                    AccessToken aToken =( AccessToken) evt.getSource();
                     String token = aToken.getToken();
                    String expires = aToken.getExpires();
                    TOKEN = token;
                    System.out.println("recived a token " + token + " which expires on " + expires);
                    //store token for future queries.
                    Storage.getInstance().writeObject("token", token);
                    if (main != null) {
                        main.show();
                    }
                    else{
                        
                    }
                } else {
                    Exception err = (Exception) evt.getSource();
                    err.printStackTrace();
                    Dialog.show("Error", "An error occurred while logging in: " + err, "OK", null);
                }
            }
        });
    }
    public static boolean isFbLogin() {
        return Storage.getInstance().readObject("token") == null;
    }
    public void validateFbLogin(){
        if(Database.exists("membre")){
 //verify if user is logged in with facebook 
    if (isFbLogin()) {
              //check if token has expired and get user fb object 
                //token exists no need to authenticate
            TOKEN = (String) Storage.getInstance().readObject("token");
            FaceBookAccess.setToken(TOKEN);
            //in case token has expired re-authenticate
            FaceBookAccess.getInstance().addResponseCodeListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent evt) {
                    NetworkEvent ne = (NetworkEvent) evt;
                    int code = ne.getResponseCode();
                    
                    if (code == 400) {
                        displayLoginForm(theme);
                    }                    
                }
            });
              //convert fb object to member object 
              
              //update member object in database 
              
              //update member object  in sqlLite 
              
          }
            
//verify if password match in data base !
            
            
           
            
        }
        
          
        
          if (isFbLogin()) {
              
              
              
     //check sql lite
     //check fb
     //sign in then !
     
           
        } else {
            //token exists no need to authenticate
            TOKEN = (String) Storage.getInstance().readObject("token");
            FaceBookAccess.setToken(TOKEN);
            //in case token has expired re-authenticate
            FaceBookAccess.getInstance().addResponseCodeListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent evt) {
                    NetworkEvent ne = (NetworkEvent) evt;
                    int code = ne.getResponseCode();
                    //token has expired
                    if (code == 400) {
                       //change here !
                    }                    
                }
            });
        }
    }
    public void validateLoginFromDb(String login ,String password){
        ConnectionRequest con = new ConnectionRequest();
            con.setUrl(LOGIN_URL+"?username="+login+"&password="+password);
            con.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {
               String response = new String (con.getResponseData());
               if(response.equals("error")){
                     Dialog.show("Autentication", "Username or password incorect", "OK", "Cancel");
               }
               else{
                   connectedMember =Json.jsonToMember(response);
                  
                     Dialog.show("Autentication", connectedMember.getFirst_name(), "OK", "Cancel");
               }
               
            //  spanLabel.setText(response);
              // f.refreshTheme();
               
                }
            });
            
            
            NetworkManager.getInstance().addToQueue(con);
   }
    public LoginForm(Resources theme){
this.theme = theme;
//   validateFbLogin()
        
        
         form=generateLoginForm(theme);
        
    }
       public LoginForm( Resources theme, String Username){
this.theme = theme;
//   validateFbLogin()
        
        
         form=generateLoginForm(theme);
        
    }

  
    
    private Form generateLoginForm(Resources theme){
       UIBuilder ui = new UIBuilder();
         UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
       Form form = ui.createContainer(theme, "login").getComponentForm();
        TextField login = (TextField) ui.findByName("txtUsername",form);
        TextField password = (TextField) ui.findByName("txtPassword",form);
      
      Button btnLogin   = (Button) ui.findByName("btnLogin",form);
      btnLogin.addActionListener((evt) -> {
        //  validateLogin(login.getText(),password.getText());
      VerifyCinForm f = new VerifyCinForm(theme);
      
      //    signIn(f.getForm());
      });
      return form;
    }

   private void displayLoginForm(Resources theme){
       UIBuilder ui = new UIBuilder();
         UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
       form = ui.createContainer(theme, "login").getComponentForm();
        TextField login = (TextField) ui.findByName("txtUsername",form);
        TextField password = (TextField) ui.findByName("txtPassword",form);
      
      Button btnLogin   = (Button) ui.findByName("btnLogin",form);
      btnLogin.addActionListener((evt) -> {
        //  validateLogin(login.getText(),password.getText());
      VerifyCinForm f = new VerifyCinForm(theme);
      
        //  signIn(f.getForm());
      });
     form.show();
    }    
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Membre getConnectedMember() {
        return connectedMember;
    }

    public void setConnectedMember(Membre connectedMember) {
        this.connectedMember = connectedMember;
    }
    
    
}

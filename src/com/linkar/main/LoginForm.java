/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.linkar.entities.Membre;
import static com.linkar.main.MyApplication.LOGIN_URL;
import com.linkar.utils.Json;

/**
 *
 * @author Oussama Reguez
 */
public class LoginForm {
  private   Form form;
     private Membre connectedMember =null;
      public void validateLogin(String login ,String password){
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
         UIBuilder ui = new UIBuilder();
         UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        form = ui.createContainer(theme, "login").getComponentForm();
        TextField login = (TextField) ui.findByName("txtUsername",form);
        TextField password = (TextField) ui.findByName("txtPassword",form);
      
      Button btnLogin   = (Button) ui.findByName("btnLogin",form);
      btnLogin.addActionListener((evt) -> {
          validateLogin(login.getText(),password.getText());
      });
        
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

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
import static com.linkar.main.MyApplication.CHANGE_PASSWORD;

/**
 *
 * @author Oussama Reguez
 */
public class NewPasswordForm {
      private   Form f;
        void applyChangePassword(int id,String password ,String newPassword){
         ConnectionRequest con = new ConnectionRequest();
            con.setUrl(CHANGE_PASSWORD+"?id="+id+"&password="+password+"&newPassword="+newPassword);
            con.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {
               String response = new String (con.getResponseData());
               if(response.equals("error")){
                     Dialog.show("Autentication", "Username or password incorect", "OK", "Cancel");
               }
               else{
                  // connectedMember =Json.jsonToMember(response);
                 //  List<Reclamation> rec= Json.jsonToReclammations(response);
                    Dialog.show("success", "password changed successfully", "OK", "Cancel");
               }
               
            //  spanLabel.setText(response);
              // f.refreshTheme();
               
                }
            });
            
            
            NetworkManager.getInstance().addToQueue(con);
    }
        public Form getForm(){
            return f;
        }
    public NewPasswordForm(Resources theme){
         UIBuilder ui = new UIBuilder();
         UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
       f = ui.createContainer(theme, "changePassword").getComponentForm();
        TextField newPassword= (TextField) ui.findByName("txtNewPassword",f);
        TextField password = (TextField) ui.findByName("txtPassword",f);
      
      Button btnChange   = (Button) ui.findByName("btnChange",f);
      btnChange.addActionListener((evt) -> {
          applyChangePassword(14, password.getText(),newPassword.getText());
      });
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.Format;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static com.linkar.main.EditInfoForm.EDIT_USER_URL;
import static com.linkar.main.MyApplication.connectedMember;
/**
 *
 * @author Oussama Reguez
 */
public class ChangePasswordForm  extends Form{
    public ChangePasswordForm(Resources theme){
        setLayout(BoxLayout.y());
        Label lblPassword = new Label("Mot de passe");
       lblPassword.setUIID("centeredLabel");
        Label lblNewPassword = new Label("Nouveau mot de passe");
       lblNewPassword.setUIID("centeredLabel");
        TextField txtPassword = new TextField("");
       txtPassword.setUIID("signUpField");
        TextField txtNewPassword = new TextField("");
       txtNewPassword.setUIID("signUpField");
        Button save = new Button("Changer");
         save.setUIID("btnRed");
         save.addActionListener((evt) -> {
             
             ChangePassword(connectedMember.getId_member(), txtPassword.getText(),txtNewPassword.getText());
             
             
         });
       add(lblPassword);
          add(txtPassword);
             add(lblNewPassword);
             add(txtNewPassword);
             add(save);
    }
    public static final String CHANGE_PASSWORD="http://localhost/linkar_web/web/app_dev.php/rest/changePassword";
    void ChangePassword(int id,String password ,String newPassword){
        ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl(CHANGE_PASSWORD);
            r.addArgument("id", String.valueOf(id));
              r.addArgument("password", password);
               r.addArgument("newPassword", newPassword);
         
            NetworkManager.getInstance().addToQueueAndWait(r);
            String response = new String (r.getResponseData());
            System.err.println("");
    }
    
    
}

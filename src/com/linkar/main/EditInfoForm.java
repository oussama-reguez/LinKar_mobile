/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;

import com.codename1.ui.Form;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.Format;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.linkar.entities.Membre;
import com.linkar.entities.Message;
import static com.linkar.main.DiscussionForm.SEND_MESSAGE_URL;
import static com.linkar.main.ListMemberForm.LIST_MEMBERS_URL;
import static com.linkar.main.MyApplication.connectedMember;
import com.linkar.utils.Json;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Oussama Reguez
 */
public class EditInfoForm extends Form {
   boolean isMale=true;
    Label lblNom =null ;
       
       Label lblPrenom = null;
       
       Label lblEmail =null ;
      
       
       Label lblDate = null; ;
      
       Label lblAdresse = new Label("Adresse");
    
       TextField txtNom = null;
     
    TextField  txtPrenom = null;
   
       TextField txtEmail = null;
      
    
    
       TextArea txtAdresse =null;
     
            Picker datePicker =null;
      
    boolean getGender(){
        if(connectedMember.getGender().equals("Homme")){
            return true;
        }
        return false;
    }
    public EditInfoForm(Resources theme){
        setLayout(BoxLayout.y());
       isMale =getGender();
        /////////////////////:gender ////////////
         Container  genderContainer =  new Container (new GridLayout(1, 2));
          genderContainer.setUIID("ddd");
          
          Button male = new Button(theme.getImage("man_selected.png"));
           Button female = new Button(theme.getImage("women_unselected.png"));
         
           if(!isMale){
               male.setIcon(theme.getImage("man_unselected.png"));
               female.setIcon(theme.getImage("women_selected.png"));
               //male = new Button(theme.getImage("man_selected.png"));
          // female = new Button(theme.getImage("women_unselected.png"));
           }
           
           male.setUIID("kk");
        
          
          
          
          male.addActionListener((evt) -> {
            
                   male.setIcon(theme.getImage("man_selected.png"));
              female.setIcon(theme.getImage("women_unselected.png"));
              isMale=true;
          });
         
          female.addActionListener((evt) -> {
            
                   male.setIcon(theme.getImage("man_unselected.png"));
              female.setIcon(theme.getImage("women_selected.png"));
              isMale=false;
             
             
          });
         female.setUIID("kk");
          genderContainer.add(male);
          genderContainer.add(female);
        //  add(genderContainer);
        
        Label lblGender = new Label("Genre");
         lblGender.setUIID("centeredLabel");
         
        lblNom = new Label("Nom");
       lblNom.setUIID("centeredLabel");
       lblPrenom = new Label("Prenom");
       lblPrenom.setUIID("centeredLabel");
       lblEmail = new Label("Email");
       lblEmail.setUIID("centeredLabel");;
       
       lblDate = new Label("Date de naissance");
       lblDate.setUIID("centeredLabel");
        lblAdresse = new Label("Adresse");
       lblAdresse.setUIID("centeredLabel");
       txtNom = new TextField(connectedMember.getLast_name());
       txtNom.setUIID("signUpField");
        txtPrenom = new TextField(connectedMember.getFirst_name());
       txtPrenom.setUIID("signUpField");
        txtEmail = new TextField(connectedMember.getEmail());
       txtEmail.setUIID("signUpField");
    
    
        txtAdresse = new TextArea(4, 100);
       txtAdresse.setText(connectedMember.getAddress());
       txtAdresse.setUIID("signUpField");
           datePicker = new Picker();
       datePicker.setUIID("signUpField");
       datePicker.setDate(connectedMember.getBirth());
       
datePicker.setType(Display.PICKER_TYPE_DATE);

 Button save = new Button("Enregistrer");
         save.setUIID("btnSave");
         save.addActionListener((evt) -> {
             EditUser();
             EditUserFromDb(connectedMember);
             
             
             
         });
        add(lblGender);
        add(genderContainer);
       add(lblNom);
       add(txtNom);
       add(lblPrenom);
       add(txtPrenom);
       add(lblEmail);
       add(txtEmail);
      add(lblDate);
       add(datePicker);
       add(lblAdresse);
       add(txtAdresse);
       add(FlowLayout.encloseCenter(save));
  

    
       
        
       
     
      
      /////////gender/////////////////////////
      
      
    }
    public static final String EDIT_USER_URL="http://localhost/linkar_web/web/app_dev.php/rest/editProfil";
    void EditUser(){
        if(isMale){
                 connectedMember.setGender("Homme");
             }
             else{
                 connectedMember.setGender("Femme");
             }
        connectedMember.setFirst_name(txtPrenom.getText());
        connectedMember.setLast_name(txtNom.getText());
        connectedMember.setBirth(datePicker.getDate());
        connectedMember.setAddress(txtAdresse.getText());
        connectedMember.setEmail(txtEmail.getText());
    }
    void EditUserFromDb(Membre m){
         ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl(EDIT_USER_URL);
            r.addArgument("id", String.valueOf(m.getId_member()));
              r.addArgument("firstName", m.getFirst_name());
               r.addArgument("lastName", m.getLast_name());
                r.addArgument("email", m.getEmail());
                Format formatter = new SimpleDateFormat("d/m/y");
String s = formatter.format(m.getBirth());
               //    r.addArgument("birth", s);
                   r.addArgument("gender", m.getGender());
                   r.addArgument("adresse", m.getAddress());
         
            NetworkManager.getInstance().addToQueueAndWait(r);
            String response = new String (r.getResponseData());
            System.err.println("");
    }
}

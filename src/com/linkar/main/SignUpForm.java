/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Oussama Reguez
 */
public class SignUpForm extends Form{
   
    
     
       private Resources theme;
       
       private boolean male =true;
       Container generateTab1(){
         Container north  = new Container(new FlowLayout(CENTER));
      Container center  = new Container(new FlowLayout(CENTER));
       Container south  = new Container(new FlowLayout(CENTER));
            //////tab1//////////////////
        
         Container tab1 = new Container(new BorderLayout());
       
          tab1.addComponent(com.codename1.ui.layouts.BorderLayout.NORTH, north);
        //  center.setUIID("test");
         Container  northHolder =  new Container (new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
            Container imagecontainer = new Container(new FlowLayout(CENTER));
          Label imgCreate = new Label();
          imgCreate.setIcon(theme.getImage("create_account.png"));
          imagecontainer.add(imgCreate);
         Label txtCreateAccount = new Label("create Account");
         txtCreateAccount.setUIID("signUpTitle");
          Container descriptionContainer = new Container(new FlowLayout(CENTER));
          Label description = new Label("desc");
          description.setUIID("signUpDescription");
          descriptionContainer.add(description);
         northHolder.add(imagecontainer);
         northHolder.add(txtCreateAccount);
         northHolder.add(descriptionContainer);
         north.add(northHolder);
         
         
         tab1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, center);
         Container  centerHolder =  new Container (new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
         
          Container userNamecontainer = new Container (new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
          Container passwordContainer = new Container (new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
           Container emailContainer = new Container (new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
      
           userNamecontainer.setUIID("signUpFieldContainer");
            passwordContainer.setUIID("signUpFieldContainer");
            emailContainer.setUIID("signUpFieldContainer");
           TextField username = new TextField();
        
         username.setHint("username");
         username.setColumns(2);
         username.setUIID("signUpField");
         username.getHintLabel().setUIID("signUpHint");
        
         TextField password = new TextField();
         password.setHint("password");
         
         password.setUIID("signUpField");
          password.getHintLabel().setUIID("signUpHint");
          TextField email = new TextField();
          email.setUIID("signUpField");
           email.setHint("email");
           email.getHintLabel().setUIID("signUpHint");                
         Button register = new Button("register");
         register.setUIID("btnRegister");
       //  userNamecontainer.add(username);
        // passwordContainer.add(password);
        // emailContainer.add(email);
         centerHolder.add(username);
         centerHolder.add(email);
         centerHolder.add(password);
         centerHolder.add(register);
         Container c = new Container(new GridLayout(2, 2));
         center.add(centerHolder);
         center.setScrollableY(true);
         
           Container  southHolder =  new Container (new BoxLayout(com.codename1.ui.layouts.BoxLayout.X_AXIS));
    
           Label signIn= new Label("Already have an account");
           signIn.setUIID("signUpFooter");
           Label btnSign = new Label("sign in");
           btnSign.setUIID("signUpSignIn");
           southHolder.add(signIn);
           southHolder.add(btnSign);
           south.add(southHolder);
             tab1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, south);
           return tab1;
       }
    Container generateTab2(){
          Container north  = new Container(new FlowLayout(CENTER));
      Container center  = new Container(new FlowLayout(CENTER));
       Container south  = new Container(new FlowLayout(CENTER));
            //////tab1//////////////////
        
         Container tab = new Container(new BorderLayout());
        tab. addComponent(com.codename1.ui.layouts.BorderLayout.NORTH, north);
        //  center.setUIID("test");
         Container  northHolder =  new Container (new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
         
         
       
         Label txtCreateAccount = new Label("create Account");
         txtCreateAccount.setUIID("signUpTitle");
          Container descriptionContainer = new Container(new FlowLayout(CENTER));       
      
         northHolder.add(txtCreateAccount);
         northHolder.add(descriptionContainer);
         north.add(northHolder);
         
         
       tab. addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, center);
         Container  centerHolder =  new Container (new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
           Container  genderContainer =  new Container (new GridLayout(1, 2));
           genderContainer.setUIID("dd");
          
          Button male = new Button(theme.getImage("man_selected.png"));
           Button female = new Button(theme.getImage("women_unselected.png"));
          male.setUIID("kk");
          male.addActionListener((evt) -> {
            
                   male.setIcon(theme.getImage("man_selected.png"));
              female.setIcon(theme.getImage("women_unselected.png"));
              this.male=true;
          });
         
          female.addActionListener((evt) -> {
            
                   male.setIcon(theme.getImage("man_unselected.png"));
              female.setIcon(theme.getImage("women_selected.png"));
              this.male=false;
             
             
          });
         female.setUIID("kk");
          genderContainer.add(male);
          genderContainer.add(female);
          
           TextField firstName = new TextField();
        
         firstName.setHint("firstName");
         firstName.setColumns(2);
        firstName.setUIID("signUpField");
         firstName.getHintLabel().setUIID("signUpHint");
        
         TextField lastName = new TextField();
         lastName.setHint("Password");
         
         lastName.setUIID("signUpField");
          lastName.getHintLabel().setUIID("signUpHint");
          TextField birthDay = new TextField();
          birthDay.setUIID("signUpField");
           birthDay.setHint("Birthday");
           birthDay.getHintLabel().setUIID("signUpHint");                
         Button register = new Button("register");
         register.setUIID("btnRegister");
       //  userNamecontainer.add(username);
        // passwordContainer.add(password);
        // emailContainer.add(email);
      
         centerHolder.add(genderContainer);
         centerHolder.add(firstName);
         centerHolder.add(lastName);
         centerHolder.add( birthDay);
         centerHolder.add(register);
         Container c = new Container(new GridLayout(2, 2));
         center.add(centerHolder);
         center.setScrollableY(true);
         
           Container  southHolder =  new Container (new BoxLayout(com.codename1.ui.layouts.BoxLayout.X_AXIS));
    
           Label signIn= new Label("Already have an account");
           signIn.setUIID("signUpFooter");
           Label btnSign = new Label("sign in");
           btnSign.setUIID("signUpSignIn");
           southHolder.add(signIn);
           southHolder.add(btnSign);
           south.add(southHolder);
           tab. addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, south);
        return tab;
    }
       
       public SignUpForm(Resources theme){
        this.theme=theme;
        setUIID("signUpForm");
         setLayout(BoxLayout.y());
          Tabs tabs = new Tabs();
          tabs.setUIID("");
        
             
             tabs.addTab("", generateTab1());
             tabs.addTab("", generateTab2());
             add(tabs);
    }
    void initComponents(){
        
    }
    
}

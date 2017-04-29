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
public class SignUpForm1 extends Form{
    boolean male = true;
     private Container north  = new Container(new FlowLayout(CENTER));
      private Container center  = new Container(new FlowLayout(CENTER));
       private Container south  = new Container(new FlowLayout(CENTER));
    public SignUpForm1(Resources theme){
        setUIID("signUpForm");
         setLayout(new BorderLayout());
          addComponent(com.codename1.ui.layouts.BorderLayout.NORTH, north);
        //  center.setUIID("test");
         Container  northHolder =  new Container (new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
         
         
       
         Label txtCreateAccount = new Label("create Account");
         txtCreateAccount.setUIID("signUpTitle");
          Container descriptionContainer = new Container(new FlowLayout(CENTER));       
      
         northHolder.add(txtCreateAccount);
         northHolder.add(descriptionContainer);
         north.add(northHolder);
         
         
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, center);
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
            addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, south);
    }
    void initComponents(){
        
    }
    
}

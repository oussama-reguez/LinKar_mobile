/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

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
public class SignUpFormBackup extends Form{
    
    
     private Container north  = new Container(new FlowLayout(CENTER));
      private Container center  = new Container(new FlowLayout(CENTER));
       private Container south  = new Container(new FlowLayout(CENTER));
    public SignUpFormBackup(Resources theme){
        setUIID("signUpForm");
         setLayout(new BorderLayout());
          addComponent(com.codename1.ui.layouts.BorderLayout.NORTH, north);
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
         
         
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, center);
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
            addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, south);
    }
    void initComponents(){
        
    }
    
}

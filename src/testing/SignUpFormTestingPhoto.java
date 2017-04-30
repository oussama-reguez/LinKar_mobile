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
public class SignUpFormTestingPhoto extends Form{
    boolean male = true;
     private Container north  = new Container(new FlowLayout(CENTER));
      private Container center  = new Container(new FlowLayout(CENTER));
       private Container south  = new Container(new FlowLayout(CENTER));
    public SignUpFormTestingPhoto(Resources theme){
        setUIID("signUpForm");
         setLayout(new BorderLayout());
     
         
         
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, center);
        FlowLayout f= new FlowLayout(CENTER);
        f.setValign(CENTER);
        f.setAlign(CENTER);
         Container  centerHolder =  new Container ( f);
         Container  dialog =   new Container (new FlowLayout(CENTER));
          Container  dialogcontainer =  new Container (new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
      
          dialog.setUIID("photoDialog");
             Container  imgContainer =   new Container (new FlowLayout(CENTER));
             Container  lblContainer =   new Container (new FlowLayout(CENTER));
            Container lblContainer2= new Container (new FlowLayout(CENTER));
         Label img = new Label();
         img.setIcon(theme.getImage("camera.png"));
         Label d = new Label("Profil!");
        Label dd = new Label("dsfsdfsfsfsfdsfdsfsf");
        imgContainer.add(img);
        lblContainer.add(d);
        lblContainer2.add(dd);
          dialogcontainer.add(imgContainer);
          dialogcontainer.add(lblContainer);
          dialogcontainer.add(lblContainer2);
          dialog. addComponent( dialogcontainer);
         
     // dialog.setUIID("photoDialog");
        // centerHolder.add(dialog);
         center.add(dialog);
         center.setScrollableY(false);
         
    }
    void initComponents(){
        
    }
    
}

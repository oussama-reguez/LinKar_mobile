/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;

import com.codename1.components.Accordion;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Oussama Reguez
 */

public class SplashScreen {
    Form f ;
    public SplashScreen(Resources theme){
        f = new Form();
        Label l = new Label("linkar");
        Button b = new Button("start");
        b.addActionListener((evt) -> {
            handleLoginProcess();
        });
        Container c = new Container();
        c.add(l);
        c.add(b);
        f.add(c);
        f.show();
              
    }
    
    public void handleLoginProcess(){
        
    }
    public Form getForm(){
return f;
}
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Oussama Reguez
 */
public class VerifyCinForm {
    private Form f ;
    public VerifyCinForm(Resources theme){
       f = new Form("Hi World", new BoxLayout(BoxLayout.Y_AXIS));
        Image cin = theme.getImage("id-card.png");
        ImageViewer v = new ImageViewer(cin);
        Container mainContainer = BoxLayout.encloseX(v);
        f.add(mainContainer);
        f.setScrollableY(true);
    }
    public Form getForm(){
        return f;
    }
}

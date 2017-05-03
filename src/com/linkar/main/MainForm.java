/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
/**
 *
 * @author Oussama Reguez
 */
public class MainForm extends Form {
    
    public MainForm(Resources theme){
        super(BoxLayout.y());
      Toolbar tb = new Toolbar(true);
      
        setToolbar(tb);
        tb.setTitleCentered(false);
         getToolbar().addCommandToLeftBar(" ",null ,(e) -> System.err.println(""));
        getToolbar().addCommandToLeftBar(" ", theme.getImage("menu.png"), (evt) -> {
           ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null);
        });
         getToolbar().addCommandToLeftBar(" ",null ,(e) -> System.err.println(""));
        getToolbar().addCommandToLeftBar(" ", theme.getImage("left-arrow.png"), (evt) -> {
           ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null);
        });
      
        setupSideMenu(theme);
    }
    
       public void setupSideMenu(Resources res) {
           
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  Jennifer Wilson", profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Dashboard", FontImage.MATERIAL_DASHBOARD,  e -> System.err.println(""));
        getToolbar().addMaterialCommandToSideMenu("  Activity", FontImage.MATERIAL_TRENDING_UP,  e -> System.err.println(""));
        getToolbar().addMaterialCommandToSideMenu("  Message", FontImage.MATERIAL_ACCESS_TIME,  e -> new ListMemberForm(res,this).show());
        getToolbar().addMaterialCommandToSideMenu("  Parametre du compte", FontImage.MATERIAL_SETTINGS,  e -> System.err.println(""));
        getToolbar().addMaterialCommandToSideMenu("  Se deconnecter", FontImage.MATERIAL_EXIT_TO_APP,  e -> System.err.println(""));
    }
}

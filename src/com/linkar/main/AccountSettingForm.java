/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;
import com.codename1.components.ScaleImageLabel;
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
public class AccountSettingForm extends Form {
      Tabs tabs;
            public void setupSideMenu(Resources res) {
                 getToolbar().addCommandToLeftBar(" ", null, e -> {});
                 getToolbar().addCommandToLeftBar(" ",res.getImage("menu.png"),(evt) -> {
           ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null);
        });
        getToolbar().addCommandToRightBar("", null, e -> {});
           
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
        getToolbar().addMaterialCommandToSideMenu("  Tasks", FontImage.MATERIAL_ACCESS_TIME,  e -> System.err.println(""));
        getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS,  e -> System.err.println(""));
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> System.err.println(""));
    }
    public AccountSettingForm(Resources theme){
        setLayout(new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setupSideMenu(theme);
          Image img = theme.getImage("account-background.png");
          
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        
        sl.setUIID("profilBackground");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        //////////////////profile image///////////////////////////////////
        Image profilePic = theme.getImage("user-picture.jpg");
        Image mask = theme.getImage("round2.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic);
        Button btnProfil = new Button();
       
       
        
        
        
        profilePicLabel.setMask(mask.createMask());
      //   profilePicLabel.getStyle().setMargin(TOP, 10);
         Label name = new Label("oussama reguez");
         Container imageHolder = BoxLayout.encloseY(name);
         Container profileHeader=LayeredLayout.encloseIn(
                sl,
                BorderLayout.center(
                   imageHolder
                )
        );
          profileHeader.setUIID("profileHeader");
         profileHeader.getStyle().setMargin(0,0,0,0);
         profileHeader.getStyle().setPadding(0,0,0,0);
        
         add(profileHeader);
         
         //menu 
         ButtonGroup barGroup = new ButtonGroup();
        RadioButton info = RadioButton.createToggle("Info", barGroup);
        info.setUIID("SelectBar");
        info.setName("info");
        RadioButton compte = RadioButton.createToggle("Compte", barGroup);
        compte.setName("compte");
        compte.setUIID("SelectBar");
     
        Label arrow = new Label(theme.getImage("news-tab-down-arrow.png"), "Container");
        bindButtonSelection(info, arrow);
        bindButtonSelection(compte, arrow);
      
        arrow.getStyle().setMargin(LEFT, 60);
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, info, compte),
                FlowLayout.encloseBottom(arrow)
        ));
        
        tabs =new Tabs();
         tabs.setUIID("");
           Container tab1 = new Container(new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
       // tabs.add();
        
            Label l = new Label("information personnel") {

            public void paint(Graphics g) {
                super.paint(g);
                g.drawLine(getX(), getY() + getHeight() - 1, getX() + getWidth(), getY() + getHeight() - 1);
            }
        };
        l.setUIID("Separator");
       
        tab1.add(l);
        
      
        
       Container grid1= new Container(new GridLayout(2,1));
       Label named = new Label("nom:");
       named.setUIID("");
       Label l2 = new Label("reguez");
       
       Label prenom = new Label("prenom:");
       prenom.setUIID("");
       Label pr = new Label("oussama");
       
       Label email = new Label("email:");
       email.setUIID("");
       Label emailValue = new Label("oussamareguez@gmail.com");
       
      
        grid1.add(BoxLayout.encloseX(named,l2));
        grid1.add(BoxLayout.encloseX(prenom,pr));
         grid1.add(BoxLayout.encloseX(email,emailValue));
        tab1.add(grid1);
         Button voirPlus = new Button("afficher plus");
        voirPlus.setUIID("link");
        tab1.add(voirPlus);
           Label l3 = new Label("voitures") {

            public void paint(Graphics g) {
                super.paint(g);
                g.drawLine(getX(), getY() + getHeight() - 1, getX() + getWidth(), getY() + getHeight() - 1);
            }
        };
        l3.setUIID("Separator");
        
        Button afficherVoiture = new Button("afficher mes voitures");
        afficherVoiture.setUIID("link");
      
        tab1.add(l3);
         tab1. add(afficherVoiture);
          
         tabs.addTab("", tab1);
        Container tab2 = new Container(new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
          Label preference = new Label("preference") {

            public void paint(Graphics g) {
                super.paint(g);
                g.drawLine(getX(), getY() + getHeight() - 1, getX() + getWidth(), getY() + getHeight() - 1);
            }
        };
        preference.setUIID("Separator");
       
        tab2.add(preference);
        Button changePassword = new Button("changer mot de passe");
        changePassword.setUIID("link");
       tab2. add(changePassword );
          Label verification = new Label("Verification") {

            public void paint(Graphics g) {
                super.paint(g);
                g.drawLine(getX(), getY() + getHeight() - 1, getX() + getWidth(), getY() + getHeight() - 1);
            }
        };
        verification.setUIID("Separator");
        Button verifierCin = new Button("verifier carte d'identité");
        verifierCin.setUIID("link");
         Button verifierEmail = new Button("verifier Email");
        verifierEmail.setUIID("link");
       
     tab2. add(verification);
       tab2. add(verifierCin);
        tab2. add(verifierEmail);
        
        tabs.addTab("", tab2);
        
        add(tabs);
    }
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
      // b.get
      if(b.getName().equals("compte")){
           tabs.setSelectedIndex(1,true);
      }
      else{
          tabs.setSelectedIndex(0, true);
      }
       
        
    }
}

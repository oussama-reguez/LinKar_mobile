/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.linkar.main;

import com.codename1.components.FloatingActionButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.linkar.entities.Membre;
import com.linkar.utils.Json;
import java.util.List;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class ListMemberForm extends Form {

   

    
    protected boolean isCurrentInbox() {
        return true;
    }
    
    Resources resourceObjectInstance;
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
        getToolbar().addMaterialCommandToSideMenu("  Tasks", FontImage.MATERIAL_ACCESS_TIME,  e -> System.err.println(""));
        getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS,  e -> System.err.println(""));
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> System.err.println(""));
    }
    public ListMemberForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        
        
       this.resourceObjectInstance=resourceObjectInstance;
      List<Membre>membres=initMembers(14);
       
       initGuiBuilderComponents(resourceObjectInstance);
        
      
      
      //  UIManager.getInstance().addThemeProps(UIManager.initNamedTheme("/theme","Theme2").get);
         setupSideMenu(UIManager.initNamedTheme("/theme","Theme2"));
      //Resources k= UIManager.initNamedTheme("/theme_material","Theme");
      //  installSidemenu(resourceObjectInstance);
          getToolbar().addCommandToLeftBar("hi ",resourceObjectInstance.getImage("left-arrow.png"),(evt) -> {
           ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null);
        });
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {});
        
    
        
     
        
        
        
        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            fab.setUIID("FloatingActionButtonClose");
            Image oldImage = fab.getIcon();
            FontImage image = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "FloatingActionButton", 3.8f);
            fab.setIcon(image);
            Dialog popup = new Dialog();
            popup.setDialogUIID("Container");
            popup.setLayout(new LayeredLayout());
            
            Button c1 = new Button(resourceObjectInstance.getImage("contact-a.png"));
            Button c2 = new Button(resourceObjectInstance.getImage("contact-b.png"));
            Button c3 = new Button(resourceObjectInstance.getImage("contact-c.png"));
            Button trans = new Button(" ");
            trans.setUIID("Container");
            c1.setUIID("Container");
            c2.setUIID("Container");
            c3.setUIID("Container");
            Style c1s = c1.getAllStyles();
            Style c2s = c2.getAllStyles();
            Style c3s = c3.getAllStyles();
            
            c1s.setMarginUnit(Style.UNIT_TYPE_DIPS);
            c2s.setMarginUnit(Style.UNIT_TYPE_DIPS);
            c3s.setMarginUnit(Style.UNIT_TYPE_DIPS);

            c1s.setMarginBottom(16);
            c1s.setMarginLeft(12);
            c1s.setMarginRight(3);

            c2s.setMarginLeft(4);
            c2s.setMarginTop(5);
            c2s.setMarginBottom(10);
            c3s.setMarginRight(14);
            
            c3s.setMarginTop(12);
            c3s.setMarginRight(16);

            popup.add(trans).
                    add(FlowLayout.encloseIn(c1)).
                    add(FlowLayout.encloseIn(c2)).
                    add(FlowLayout.encloseIn(c3));
            
            ActionListener a = ee -> popup.dispose();
            
            trans.addActionListener(a);
            c1.addActionListener(a);
            c2.addActionListener(a);
            c3.addActionListener(a);
            
            popup.setTransitionInAnimator(CommonTransitions.createEmpty());
            popup.setTransitionOutAnimator(CommonTransitions.createEmpty());
            popup.setDisposeWhenPointerOutOfBounds(true);
            int t = ListMemberForm.this.getTintColor();
            ListMemberForm.this.setTintColor(0);
            popup.showPopupDialog(new Rectangle(ListMemberForm.this.getWidth() - 10, ListMemberForm.this.getHeight() - 10, 10, 10));
            ListMemberForm.this.setTintColor(t);
            fab.setUIID("FloatingActionButton");
            fab.setIcon(oldImage);
        });
    }


public static final String LIST_MEMBERS_URL="http://localhost/linkar_web/web/app_dev.php/rest/inboxMembers";
    
    List<Membre> initMembers(int idUser){
         ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
           r.setUrl(LIST_MEMBERS_URL);
           r.addArgument("id", String.valueOf(idUser));
           NetworkManager.getInstance().addToQueueAndWait(r);
           String response = new String (r.getResponseData());
          return  Json.jsonToListMembers(response);
    }
    List<Membre>membres;
    void initListMembersComponents(){
        membres=initMembers(14);
        for(Membre m : membres){
           addComponent(generateMembre(m));
        }
    }
    Component generateMembre(Membre m){
      com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
       gui_Container_1.setName("Container_1");
       com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);
        gui_Container_2.setName("Container_2");
        com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
         gui_Label_1.setText(dateFormat.format(m.getLastDate()));
        gui_Label_1.setUIID("SmallFontLabel");
        gui_Label_1.setName("Label_1");
        gui_Container_2.addComponent(gui_Label_1);
        com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
       gui_Container_4.setName("Container_4");
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
       com.codename1.ui.Label gui_Label_4 = new com.codename1.ui.Label();
        gui_Label_4.setUIID("Padding2");
        gui_Label_4.setName("Label_4");
        gui_Label_4.setIcon(resourceObjectInstance.getImage("label_round.png"));
        gui_Container_4.addComponent(gui_Label_4);
        
        
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4);
        com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
       //essential
       gui_Container_3.setName("Container_3");
       com.codename1.ui.Label gui_Label_3 = new com.codename1.ui.Label();
        gui_Label_3.setText(m.getFirst_name()+" "+m.getLast_name());
        gui_Label_3.setName("Label_3");
        com.codename1.ui.Label gui_Label_2 = new com.codename1.ui.Label();
        gui_Label_2.setText(m.getEmail());
        gui_Label_2.setUIID("RedLabel");
        gui_Label_2.setName("Label_2");
        com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
         gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setEditable(false);
        gui_Text_Area_1.setText(m.getLastMessage());
        gui_Text_Area_1.setUIID("SmallFontLabel");
        gui_Text_Area_1.setName("Text_Area_1");
        
        gui_Container_3.addComponent(gui_Label_3);
        gui_Container_3.addComponent(gui_Label_2);
        gui_Container_3.addComponent(gui_Text_Area_1);
        
        
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
      return gui_Container_1;
    }

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("InboxForm");
        setName("InboxForm");
      initListMembersComponents();
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label(String.valueOf(membres.size()), "InboxNumber")
                )
        );
       
        
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}

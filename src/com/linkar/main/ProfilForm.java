/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;

import com.codename1.components.ScaleImageLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.linkar.utils.Json;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 *
 * @author Oussama Reguez
 */
public class ProfilForm extends Form {
    
    String firsName;
    String lastName;
    String email;
    String nbrAnnonce;
    String nbrDemande;
    String status;
    String urlPicture;
    Date createdTime;
    
    int idMember;
    public static final String PROFIL_DATA_URL="http://localhost/linkar_web/web/app_dev.php/rest/profilData";
    void initProfilData(int idMember){
        Map<String,Object> data=getProfilData(idMember);
        firsName=(String)data.get("firstName");
   lastName=(String)data.get("lastName");
    email=(String)data.get("email");
   nbrAnnonce=(String)data.get("nbrAnnonce");
   nbrDemande=(String)data.get("nbrDemande");
    status=(String)data.get("statut");
    urlPicture=(String)data.get("urlPicture");
    LinkedHashMap<String,Object>  dateData= (LinkedHashMap<String,Object> ) data.get("createdTime");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    Date date=null;
 
             
                  
        try {
            date = df.parse((String)dateData.get("date"));
        } catch (ParseException ex) {
           
        }
        createdTime=date;
  
    }
      Map<String,Object> getProfilData(int idMember){
      
       ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl(PROFIL_DATA_URL);
           
            r.addArgument("id", String.valueOf(idMember));
            
            NetworkManager.getInstance().addToQueueAndWait(r);
            String response = new String (r.getResponseData());
           
         
            return Json.jsonToProfilData(response);
  }
    public ProfilForm(Resources theme,int idMember){
        this.idMember=idMember;
        initProfilData(idMember);
        /*
       Label l = new Label("check");
        Container ll=FlowLayout.encloseRight(l);
     Container footer=   BorderLayout.south(ll);
     Label img = new Label();
      img.setIcon(theme.getImage("camera.png"));
      Container header = LayeredLayout.encloseIn(img,footer);
        add(header);
       */
        setLayout(new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
          Image img = theme.getImage("profile-background.jpg");
          
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        
        sl.setUIID("profilBackground");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
        
        //////////////////profile image///////////////////////////////////
        Image profilePic = theme.getImage("user-picture.jpg");
        Image mask = theme.getImage("round.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic);
         profilePicLabel.setMask(mask.createMask());
        /////////////////////////////////////////////////////////////
       // add(sl);
       
        /////
       
        Container photoContainer = GridLayout.encloseIn(3, 
                            new Label(),
                            FlowLayout.encloseCenter(
                               profilePicLabel,
                             new Label()
                    ));
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                   photoContainer
                )
        ));
        
        //add full name + email
        Container mainContainer= new Container (new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        Label fullName= new Label(firsName+" "+lastName);
        fullName.setUIID("profilfullName");
        Label email = new Label(this.email);
        fullName.getStyle().setPadding(0, 0, 0, 0);
         fullName.getStyle().setMargin(0, 0, 0, 0);
        email.setUIID("profilEmail");
        email.getStyle().setPadding(0, 0, 0, 0);
         email.getStyle().setMargin(0, 0, 0, 0);
        //email.getStyle().setMargin(TOP, 20);
        Container nameHolder=FlowLayout.encloseCenter(fullName);
        Container emailHolder=FlowLayout.encloseCenter(email);
        nameHolder.getStyle().setMargin(0, 0, 0, 0);
        nameHolder.getStyle().setPadding(0, 0, 0, 0);
        emailHolder.getStyle().setMargin(0, 0, 0, 0);
        emailHolder.getStyle().setPadding(0, 0, 0, 0);
        Container profilTitle=BoxLayout.encloseY(nameHolder,emailHolder);
        Container serapator1= new Container(new FlowLayout(CENTER));
        Container serapator2= new Container(new FlowLayout(CENTER));
        Container serapator3= new Container(new FlowLayout(CENTER));
        serapator1.add("info");
        serapator2.add("voitures");
        //generation de voiture ;
        Container one= new Container(new FlowLayout(CENTER));
        Container two= new Container(new FlowLayout(CENTER));
        Container three= new Container(new FlowLayout(CENTER));
        Container four= new Container(new FlowLayout(CENTER));
        Container five= new Container(new FlowLayout(CENTER));
        Container six= new Container(new FlowLayout(CENTER));
        
        Container seven= new Container(new FlowLayout(CENTER));
        Container eight= new Container(new FlowLayout(CENTER));
        Container nine= new Container(new FlowLayout(CENTER));
        one.add(new Label("membre depuis "));
        two.add(new Label("statut: "+this.status));
        three.add(new Label("annonces: "+nbrAnnonce));
        four.add(new Label("demandes: "+nbrDemande));
        
// membre depuis 
        // annonces: 50
        // demandes : 10
        
        
        
       
        
        
        Container grid = new Container( new GridLayout(3,2));
     grid.add(one); grid.add(two); grid.add(three); grid.add(four);
     
          mainContainer.add(profilTitle);
          mainContainer.add(serapator1);
          
         mainContainer.add(grid);
          mainContainer.add(serapator2);
          add(mainContainer);
    }
    
}

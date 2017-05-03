/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;

import com.codename1.components.ImageViewer;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.linkar.entities.Membre;
import static com.linkar.main.MyApplication.connectedMember;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Oussama Reguez
 */
public class VerifyCinForm extends Form {
   Resources theme;
    public static final String CIN_URL="http://localhost/linkar_web/web/app_dev.php/rest/uploadCin";
        public void UploadPicture(InputStream image) {
    //String picture = "";
       
    
      
        MultipartRequest request = new MultipartRequest() {
            private Object IOUtils;
           protected void readResponse(InputStream inpute) throws IOException  {
 byte[] b = Util.readInputStream(inpute);
 String myString = new String(b);
               System.err.println(myString);
           }
        };
        request.setUrl(CIN_URL+"?id="+MyApplication.connectedMember.getId_member());
        try {
            
          //  request.addData("upfile", picture, "image/jpeg");
            request.addData("upfile", image,image.available(),"image/jpeg");
            request.setFilename("upfile", "myPicture.jpg");
            NetworkManager.getInstance().addToQueueAndWait(request);
        } catch(Exception err) {
            err.printStackTrace();
        }
    
}
 void uploadCin(){
     
     ActionListener callback = e->{
   if (e != null && e.getSource() != null) {
       String filePath = (String)e.getSource();
       FileSystemStorage fs = FileSystemStorage.getInstance();
               try {
                   InputStream fis = fs.openInputStream(filePath);
                   UploadPicture(fis);
                   String k = null;
                //   hi.addComponent(new SpanLabel(Util.readToString(fis)));
               } catch (Exception ex) {
                   Log.e(ex);
               }

      System.out.println("done");
   }
};

if (FileChooser.isAvailable()) {
    FileChooser.showOpenDialog("image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);
} else {
    Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
}
 }
 void validateCin(){
     if( connectedMember.getUrl_cin()==null){
         
        
      setLayout(new BorderLayout());
      
        Image cin = theme.getImage("id-card.png");
        ImageViewer v = new ImageViewer(cin);
        Label text = new Label("some text here");
    
        Button upload = new Button ("upload");
        upload.addActionListener((evt) -> {
            uploadCin();
        });
         Container mainContainer = BoxLayout.encloseY(v,text,upload);
         FlowLayout layout = new FlowLayout();
        layout.setAlign(Component.CENTER);
        layout.setValign(CENTER);
        Container main = new Container(layout);
        main.add(mainContainer);
        // mainContainer.setUIID("main");
         // Container lin = BoxLayout.encloseY(v);
      add(CENTER,main);
          setScrollableY(true);
         return;
     }
     if(connectedMember.isVerif_cin()){
            
      setLayout(new BorderLayout());
      
        Image cin = theme.getImage("id-card.png");
        ImageViewer v = new ImageViewer(cin);
        Label text = new Label("votre cin est verifié");
    
       
         Container mainContainer = BoxLayout.encloseY(v,text);
         FlowLayout layout = new FlowLayout();
        layout.setAlign(Component.CENTER);
        layout.setValign(CENTER);
        Container main = new Container(layout);
        main.add(mainContainer);
        // mainContainer.setUIID("main");
         // Container lin = BoxLayout.encloseY(v);
      add(CENTER,main);
          setScrollableY(true);
     }
     else{
           setLayout(new BorderLayout());
      
        Image cin = theme.getImage("id-card.png");
        ImageViewer v = new ImageViewer(cin);
        Label text = new Label("l'administrateur n'a pas encore verifier votre compte");
    
       
         Container mainContainer = BoxLayout.encloseY(v,text);
         FlowLayout layout = new FlowLayout();
        layout.setAlign(Component.CENTER);
        layout.setValign(CENTER);
        Container main = new Container(layout);
        main.add(mainContainer);
        // mainContainer.setUIID("main");
         // Container lin = BoxLayout.encloseY(v);
      add(CENTER,main);
          setScrollableY(true);
     }
 }
    public VerifyCinForm(Resources theme){
        this.theme=theme;
        validateCin();
    }
  
}

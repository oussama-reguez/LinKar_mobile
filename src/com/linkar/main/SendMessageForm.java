/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.NORTH;
import static com.codename1.ui.layouts.BorderLayout.SOUTH;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.linkar.entities.Membre;
import com.linkar.entities.Message;
import static com.linkar.main.DiscussionForm.SEND_MESSAGE_URL;
import static com.linkar.main.MyApplication.connectedMember;
import com.linkar.utils.Json;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 *
 * @author Oussama Reguez
 */

public class SendMessageForm extends Form {
   Form backForm;
public final String AUTO_COMPLETE_URL="http://localhost/linkar_web/web/app_dev.php/rest/autoComplete";
    final DefaultListModel<String> options = new DefaultListModel<>();
    HashMap<String,Integer>autoCompletes=new HashMap<>();
    
    
    void fillData(List<Membre>membres){
        autoCompletes.clear();
        for(Membre m : membres){
            String label=m.getFirst_name()+" "+m.getLast_name()+" ("+m.getEmail()+")";
            autoCompletes.put(label,m.getId_member());
            options.addItem(label);
            
        }
    }
    public SendMessageForm (Resources theme,Form backForm){
        this.backForm=backForm;
        setLayout(new BorderLayout());
       
  AutoCompleteTextField ac = new AutoCompleteTextField(options) {
      @Override
      protected boolean filter(String text) {
          if(text.length() == 0) {
              return false;
          }
          List<Membre> membres=searchAction(text);
          if(membres == null ) {
              return false;
          }
  
          options.removeAll();
          fillData(membres);
          return true;
      }

           
  
  };
  ac.setMinimumElementsShownInPopup(5);
  
  Button send = new Button("send");
  Container buttonContainer = FlowLayout.encloseCenter(send);
 
 TextArea lbl = new TextArea("", 10, 100);
 
lbl.setEditable(true);

lbl.setUIID("Label");
  Container textFieldContainer = FlowLayout.encloseCenter(ac);
  Container messageContainer = FlowLayout.encloseCenter(lbl);
       messageContainer.setUIID("messageContainer");
          add(NORTH,textFieldContainer);
          add(BorderLayout.CENTER,messageContainer);
   add(SOUTH, buttonContainer);
   
     send.addActionListener((evt) -> {
     int id = autoCompletes.get(ac.getText());
   sendMessage(lbl.getText(),id);
   System.err.println("");
   backForm.showBack();
  });
    }
    
     private List<Membre> searchAction(String text) {
            try {
        if(text.length() > 0) {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl(AUTO_COMPLETE_URL);
            r.addArgument("name", text);
            NetworkManager.getInstance().addToQueueAndWait(r);
            String response = new String (r.getResponseData());
            return Json.jsonToListMembers(response);
        }
    } catch(Exception err) {
        Log.e(err);
    }
    return null;
         
         
            }

  void sendMessage(String message,int idReceiver){
      
       ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl(SEND_MESSAGE_URL);
            r.addArgument("receiver", String.valueOf(idReceiver));
            int id=connectedMember.getId_member();
            r.addArgument("sender", String.valueOf(connectedMember.getId_member()));
             r.addArgument("message", message);
            NetworkManager.getInstance().addToQueueAndWait(r);
            String response = new String (r.getResponseData());
           
          
            // return Json.jsonToListMembers(response);
  }
}

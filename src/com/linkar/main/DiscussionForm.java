/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.main;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.linkar.entities.Membre;
import com.linkar.entities.Message;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oussama Reguez
 */
public class DiscussionForm  extends Form{
    Resources theme;
    private String userName;
      private Image userPicture ;
    public DiscussionForm(Resources theme ){
        this.theme=theme;
       userPicture = theme.getImage("duke_iphone.png");;
        
    }
    List<Message>messages ;
    Membre connected ;
   Container discussion;
    void initMessages(){
       connected = new Membre();
        connected.setId_member(14);
        connected.setFirst_name("oussama");
        connected.setLast_name("reguez");
        messages = new ArrayList<Message> ();
        Message m = new Message();
        m.setText("hello");
        Membre sender = new Membre();
        sender.setId_member(2);
        sender.setFirst_name("dali");
        sender.setLast_name("reguez");
          m.setSender(sender);
          m.setReceiver(connected);
          
           Message m2 = new Message();
        m2.setText("hello");
        m2.setSender(connected);
        messages.add(m);
         messages.add(m);
        
    }
    void initDiscussion (List<Message>messages){
         SpanLabel lastLabel=new SpanLabel("oops");
         discussion.removeAll();
        for(Message m : messages){
             SpanLabel t = new SpanLabel(m.getText());
        t.setWidth(discussion.getWidth());
        t.setX(0);
        t.setHeight(t.getPreferredH());
        lastLabel=t;
        if(connected.getId_member()==m.getSender().getId_member()) {
            t.setY(Display.getInstance().getDisplayHeight());
            t.setTextUIID("BubbleUser");
            t.setIconPosition(BorderLayout.EAST);
            t.setIcon(userPicture);
            t.setTextBlockAlign(Component.RIGHT);
        } else {
            t.setY(0);
            t.setTextUIID("BubbleSbaitso");
            t.setTextBlockAlign(Component.LEFT);
        }
        
        discussion.addComponent(t);
       
        }
        // destination.animateLayoutAndWait(400);
      
    }
    void say(Container destination, String text, boolean question) {
        SpanLabel t = new SpanLabel(text);
        t.setWidth(destination.getWidth());
        t.setX(0);
        t.setHeight(t.getPreferredH());
        
        if(question) {
            t.setY(Display.getInstance().getDisplayHeight());
            t.setTextUIID("BubbleUser");
            t.setIconPosition(BorderLayout.EAST);
            t.setIcon(userPicture);
            t.setTextBlockAlign(Component.RIGHT);
        } else {
            t.setY(0);
            t.setTextUIID("BubbleSbaitso");
            t.setTextBlockAlign(Component.LEFT);
        }
        destination.addComponent(t);
        destination.animateLayoutAndWait(400);
        destination.scrollComponentToVisible(t);
    }
  public  void showSbaitso() {
        Form sb = new Form();
        sb.setFormBottomPaddingEditingMode(true);
        Toolbar t = new Toolbar();
        sb.setToolBar(t);
        final TextField searchField = new TextField();
        searchField.setHint("Search For Answers...");
        t.setTitleComponent(searchField);
        sb.setLayout(new BorderLayout());
        final TextField ask = new TextField();
        ask.setHint("Ask The Dr.");
        Container askContainer = new Container(new BorderLayout());
        askContainer.addComponent(BorderLayout.CENTER, ask);
        Button askButton = new Button("Ask");
        askContainer.addComponent(BorderLayout.EAST, askButton);        
        sb.addComponent(BorderLayout.SOUTH, askContainer);
       discussion = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        sb.addComponent(BorderLayout.CENTER, discussion);
        discussion.setScrollableY(true);
        initMessages();
        initDiscussion( messages);
        
        sb.show();
        new Thread() {
    public void run() {
         // readAndParseFile();
          while(true){
              try {
                  Thread.sleep(5000);
                 Message m = new Message();
        m.setText("hello");
        Membre sender = new Membre();
        sender.setId_member(2);
        sender.setFirst_name("dali");
        sender.setLast_name("reguez");
          m.setSender(sender);
          m.setReceiver(connected);
          messages = new ArrayList<Message>();
          messages.add(m);
          messages.add(m);
          messages.add(m);
          messages.add(m);
          messages.add(m);messages.add(m);
          messages.add(m);messages.add(m);messages.add(m);messages.add(m);messages.add(m);
          messages.add(m);messages.add(m);messages.add(m);messages.add(m);messages.add(m);
          messages.add(m);messages.add(m);messages.add(m);messages.add(m);messages.add(m);
          
                  
                 Display.getInstance().callSerially(new Runnable() {
               public void run() {
                   initDiscussion(messages);
                  int  count =discussion.getComponentCount();
       
        
         discussion.revalidate();
            discussion.scrollComponentToVisible(discussion.getComponentAt(count-1));
//say(discussion, "sdfsdfsfs", true);
                   //  updateUIWithContentOfFile();
                 
               }
          });
              } catch (Exception e) {
              }
              
          }
    }
}.start();
        int count = 0; 
        
        count =discussion.getComponentCount();
       
          discussion.scrollComponentToVisible(discussion.getComponentAt(count-1));
        /*
        Display.getInstance().callSerially(new Runnable() {
            public void run() {
                String w = "HELLO " + userName +", MY NAME IS DOCTOR SBAITSO.\n\nI AM HERE TO HELP YOU.\n" +
                                            "SAY WHATEVER IS IN YOUR MIND FREELY," +
                                            "OUR CONVERSATION WILL BE KEPT IN STRICT CONFIDENCE.\n" +
                                            "MEMORY CONTENTS WILL BE WIPED OFF AFTER YOU LEAVE.";
                say(discussion, w, false);
                
            }
        });
*/
        searchField.addDataChangeListener(createSearchListener(searchField, discussion, askButton));
        ActionListener askEvent = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String t = ask.getText();
                if(t.length() > 0) {
                    ask.setText("");
                    say(discussion, "testing", true);
                    answer(t, discussion);
                }
            }
        };
        ask.setDoneListener(askEvent);
        askButton.addActionListener(askEvent);
    }
    
    
    void answer(String question, Container dest) {
       
        
    }
      private DataChangedListener createSearchListener(final TextField searchField, final Container discussion, final Button ask) {
        return new DataChangedListener() {
            private boolean animateLock;
            public void dataChanged(int type, int index) {
                String t = searchField.getText();
                int count = discussion.getComponentCount();
                if(t.length() == 0) {
                    ask.setEnabled(true);
                    for(int iter = 0 ; iter < count ; iter++) {
                        Component c = discussion.getComponentAt(iter);
                        c.setPreferredSize(null);
                        c.setVisible(true);
                    }
                    animateChanage();
                    return;
                }              
                t = t.toLowerCase();
                ask.setEnabled(false);
                for(int iter = 0 ; iter < count ; iter++) {
                    SpanLabel tt = (SpanLabel)discussion.getComponentAt(iter);
                    if(tt.getText().toLowerCase().indexOf(t) < 0) {
                        tt.setPreferredSize(new Dimension(1, 1));
                        tt.setVisible(false);
                    } else {
                        tt.setPreferredSize(null);
                        tt.setVisible(true);
                    }
                }
                animateChanage();
            }
            private void animateChanage() {
                if(!animateLock) {
                    animateLock = true;
                    discussion.animateLayoutAndWait(300);
                    animateLock = false;
                }
            }
        };        
    }
    
}

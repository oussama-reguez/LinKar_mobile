package com.linkar.main;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Database;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.push.PushCallback;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import java.io.IOException;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.Callback;
import com.linkar.entities.Membre;
import com.linkar.entities.Message;
import com.linkar.utils.Json;
import com.linkar.utils.SqlLite;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;


import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication implements LocalNotificationCallback {

    private Form current;
   
    private Resources theme;
    public static final String LOGIN_URL ="http://localhost/linkar_web/web/app_dev.php/rest/login";
     public static final String MESSAGE_MEMBERS_URL ="http://localhost/linkar_web/web/app_dev.php/rest/inboxMembers?id=14";
     public static final String CONVERSATION_URL="http://localhost/linkar_web/web/app_dev.php/rest/conversation?idSender=14";
     public static final String CHANGE_PASSWORD="http://localhost/linkar_web/web/app_dev.php/rest/changePassword";
    
     
     public static  Membre connectedMember =null;
     ArrayList<Membre> contacts =  null;
     void testDataBase(){
      
         SqlLite.clearMemberTable();
        // SqlLite.createTableMember();
        SqlLite.insertMember(connectedMember);
         //SqlLite.getMember(14);
         System.err.println("");
         
     }
   public  static void initMember(){
         connectedMember = new Membre();
         connectedMember.setId_member(14);
         connectedMember.setFirst_name("sou");
         connectedMember.setLast_name("regez");
         connectedMember.setEmail("oussamareguez@gmail.com");
         connectedMember.setPassword("sdsdfs");
     }
     void showInboxMembersForm(){  
         Form hi = new Form("Hi World", new BoxLayout(BoxLayout.Y_AXIS));
        hi.setScrollableY(true);
          ConnectionRequest con = new ConnectionRequest();
            con.setUrl(MESSAGE_MEMBERS_URL);
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
               String response = new String (con.getResponseData());
               if(response.equals("error")){
                     Dialog.show("Autentication", "ERROR", "OK", "Cancel");
               }
               else{
                   contacts =(ArrayList<Membre>) Json.jsonToListMembers(response); 
                //     Dialog.show("Autentication", connectedMember.getFirst_name(), "OK", "Cancel");
               }
               
            //  spanLabel.setText(response);
              // f.refreshTheme();
               
                }
            });
            
            
            NetworkManager.getInstance().addToQueueAndWait(con);
       
       
        
        
       for(Membre c : contacts ){
            hi.add(generateMemberContainer(c));
       } 
       
       ;
       
        hi.show();
    }
     public Container generateMemberContainer(Membre c){
        
         final Label image = new Label(theme.getImage("user.png")); 
         Label fullName  =new Label(c.getFirst_name()+" "+ c.getLast_name());
         
         Button btn = new Button();
         
           btn.addActionListener((e)
                   -> {
               System.err.println("you clicked");
           });
         
       // Container container5 = BoxLayout.encloseY(nom,btn);
       Container container = BoxLayout.encloseX(image,fullName);
       container.setLeadComponent(btn);
       
       
        return container ;
    }
     int IdMember=14;
      public Container generateChatResponse(Message m){
        
         final Label image = new Label(theme.getImage("user.png")); 
         Label fullName  =new Label(m.getSender().getFirst_name()+" "+ m.getSender().getLast_name()+":");
         Label text = new Label(m.getText());
         
         Button btn = new Button();
         
           btn.addActionListener((e)
                   -> {
               System.err.println("you clicked");
           });
         if(m.getSender().getId_member()==IdMember){
               Container response = FlowLayout.encloseRight(text,image);
               return response;
         }
         else{
              Container response = FlowLayout.encloseIn(image,text);
              return response;
         }
       // Container container5 = BoxLayout.encloseY(nom,btn);
   
     
    //   container.setLeadComponent(btn);
       
       
      
    }
   public void validateLogin(String login ,String password){
        ConnectionRequest con = new ConnectionRequest();
            con.setUrl(LOGIN_URL+"?username="+login+"&password="+password);
            con.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {
               String response = new String (con.getResponseData());
               if(response.equals("error")){
                     Dialog.show("Autentication", "Username or password incorect", "OK", "Cancel");
               }
               else{
                   connectedMember =Json.jsonToMember(response);
                 //  List<Reclamation> rec= Json.jsonToReclammations(response);
                     Dialog.show("Autentication", connectedMember.getFirst_name(), "OK", "Cancel");
               }
               
            //  spanLabel.setText(response);
              // f.refreshTheme();
               
                }
            });
            
            
            NetworkManager.getInstance().addToQueue(con);
   }
    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
   List<Message>conversations;
    public void showConversationForm(int idMember){
       /*
        Form hi = new Form("messages", new BoxLayout(BoxLayout.Y_AXIS));
        
        // send dialog
        TextField t= new TextField();
       t.setWidth(80);
        Button send = new Button("send");
        Container container5 = BoxLayout.encloseXNoGrow(send,t);  
          hi.add(container5);
           hi.show();
        */
      Form myForm = new Form();
    myForm.setLayout(new LayeredLayout());
    myForm.setTitle("Floating Action Button");

    SpanLabel lbl = new SpanLabel("some long text ");

    Container conBottom = new Container();
    conBottom.setScrollableY(true);
    conBottom.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    ConnectionRequest con = new ConnectionRequest();
            con.setUrl(CONVERSATION_URL+"&idReceiver="+idMember);
            con.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {
               String response = new String (con.getResponseData());
               if(response.equals("error")){
                     Dialog.show("Autentication", "ERROR", "OK", "Cancel");
               }
               else{
                
                   conversations=Json.jsonToListMessages(response);
                  
                //     Dialog.show("Autentication", connectedMember.getFirst_name(), "OK", "Cancel");
               }
               
            //  spanLabel.setText(response);
              // f.refreshTheme();
               
                }
            });                       
            NetworkManager.getInstance().addToQueueAndWait(con);
       for(Message c : conversations ){
            conBottom.addComponent(generateChatResponse(c));
       } 
        Container empty = new Container();
       Label l = new Label();
       l.setIcon(theme.getImage("user.png"));
       l.setVisible(false);
        conBottom.addComponent(l);
       ;
   
    
    
    FlowLayout flow = new FlowLayout(Component.RIGHT);
    flow.setValign(Component.BOTTOM);
    Container conUpper = new Container(flow);
    
     TextField t= new TextField();
       t.setWidth(80);
        Button send = new Button("send");
        Container container5 = BoxLayout.encloseXNoGrow(send,t);  
    conUpper.addComponent(container5);
    conUpper.setScrollable(false);

    myForm.addComponent(conBottom);
    myForm.addComponent(conUpper);
    myForm.setScrollable(false);
    myForm.show();
    
    
    
    }
    void showSplashScreen(){
        SplashScreen s = new SplashScreen(theme);
      //  s.getForm().show();
    }
    void showForm(){
        Form myForm = new Form();
   Container c  = new Container();
   c.setName("MyContainer");
   Label k = new Label("dsfsf");
   c.add(k);
   myForm.add(c);
    myForm.show();
    }
    void displayUpload(){
        Form f = new Form("df");
        Button b = new Button() ;
        b.addActionListener((evt) -> {
        });
        f.add(b);
        f.show();
    }
    void displayLoginForm(){
        
          UIBuilder ui = new UIBuilder();
         UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        Form f = ui.createContainer(theme, "login").getComponentForm();
        TextField login = (TextField) ui.findByName("txtUsername",f);
        TextField password = (TextField) ui.findByName("txtPassword",f);
      
      Button btnLogin   = (Button) ui.findByName("btnLogin",f);
      btnLogin.addActionListener((evt) -> {
         // validateLogin(login.getText(),password.getText());
      test3();
      });
        f.show();

      //  LoginForm l = new LoginForm(theme);
      //l.getForm().show();
    }
  void showVerifyCinForm(){
      VerifyCinForm f = new VerifyCinForm(theme);
      f.getForm().show();
  }
    void showChangePasswordForm(){
        NewPasswordForm form = new NewPasswordForm(theme);
        form.getForm().show();
       
    }
    public void start() {
        if(current != null){
            current.show();
            return;
      
        
        
        }
        initMember();
        
    //  InputStream in = Display.getInstance().getResourceAsStream(getClass(), "/oussama.jpg");
      //  pictureUpload(in);
              
  //  String json="[{\"idMessage\":16,\"text\":\"hello\",\"date\":{\"date\":\"2017-03-24 19:16:53.000000\",\"timezone_type\":3,\"timezone\":\"UTC\"},\"sender\":{\"id\":12,\"firstName\":\"Elon\",\"lastName\":\"Musk\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/detective.png\"},\"receiver\":{\"id\":14,\"firstName\":\"oussamaa\",\"lastName\":\"reguez2\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/croupier.png\"}},{\"idMessage\":17,\"text\":\"hi\",\"date\":{\"date\":\"2017-03-24 19:17:21.000000\",\"timezone_type\":3,\"timezone\":\"UTC\"},\"sender\":{\"id\":14,\"firstName\":\"oussamaa\",\"lastName\":\"reguez2\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/croupier.png\"},\"receiver\":{\"id\":12,\"firstName\":\"Elon\",\"lastName\":\"Musk\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/detective.png\"}},{\"idMessage\":18,\"text\":\"how are you?\",\"date\":{\"date\":\"2017-03-24 19:18:07.000000\",\"timezone_type\":3,\"timezone\":\"UTC\"},\"sender\":{\"id\":12,\"firstName\":\"Elon\",\"lastName\":\"Musk\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/detective.png\"},\"receiver\":{\"id\":14,\"firstName\":\"oussamaa\",\"lastName\":\"reguez2\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/croupier.png\"}},{\"idMessage\":19,\"text\":\"fine \",\"date\":{\"date\":\"2017-03-24 19:18:38.000000\",\"timezone_type\":3,\"timezone\":\"UTC\"},\"sender\":{\"id\":14,\"firstName\":\"oussamaa\",\"lastName\":\"reguez2\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/croupier.png\"},\"receiver\":{\"id\":12,\"firstName\":\"Elon\",\"lastName\":\"Musk\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/detective.png\"}},{\"idMessage\":20,\"text\":\"test a \",\"date\":{\"date\":\"2017-03-24 19:18:58.000000\",\"timezone_type\":3,\"timezone\":\"UTC\"},\"sender\":{\"id\":14,\"firstName\":\"oussamaa\",\"lastName\":\"reguez2\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/croupier.png\"},\"receiver\":{\"id\":12,\"firstName\":\"Elon\",\"lastName\":\"Musk\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/detective.png\"}},{\"idMessage\":21,\"text\":\"dd\",\"date\":{\"date\":\"2017-03-24 19:22:59.000000\",\"timezone_type\":3,\"timezone\":\"UTC\"},\"sender\":{\"id\":14,\"firstName\":\"oussamaa\",\"lastName\":\"reguez2\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/croupier.png\"},\"receiver\":{\"id\":12,\"firstName\":\"Elon\",\"lastName\":\"Musk\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/detective.png\"}},{\"idMessage\":22,\"text\":\"salut\",\"date\":{\"date\":\"2017-04-02 23:07:32.000000\",\"timezone_type\":3,\"timezone\":\"UTC\"},\"sender\":{\"id\":14,\"firstName\":\"oussamaa\",\"lastName\":\"reguez2\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/croupier.png\"},\"receiver\":{\"id\":12,\"firstName\":\"Elon\",\"lastName\":\"Musk\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/detective.png\"}},{\"idMessage\":24,\"text\":\"fdf\",\"date\":{\"date\":\"2017-04-03 10:13:30.000000\",\"timezone_type\":3,\"timezone\":\"UTC\"},\"sender\":{\"id\":14,\"firstName\":\"oussamaa\",\"lastName\":\"reguez2\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/croupier.png\"},\"receiver\":{\"id\":12,\"firstName\":\"Elon\",\"lastName\":\"Musk\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/detective.png\"}},{\"idMessage\":26,\"text\":\"cc\",\"date\":{\"date\":\"2017-04-04 19:57:25.000000\",\"timezone_type\":3,\"timezone\":\"UTC\"},\"sender\":{\"id\":14,\"firstName\":\"oussamaa\",\"lastName\":\"reguez2\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/croupier.png\"},\"receiver\":{\"id\":12,\"firstName\":\"Elon\",\"lastName\":\"Musk\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/detective.png\"}},{\"idMessage\":27,\"text\":\"yo\",\"date\":{\"date\":\"2017-04-04 20:06:23.000000\",\"timezone_type\":3,\"timezone\":\"UTC\"},\"sender\":{\"id\":14,\"firstName\":\"oussamaa\",\"lastName\":\"reguez2\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/croupier.png\"},\"receiver\":{\"id\":12,\"firstName\":\"Elon\",\"lastName\":\"Musk\",\"urlPicture\":\"http:\\/\\/localhost\\/upload\\/uploads\\/detective.png\"}}]";
    // List<Message> messages= Json.jsonToListMessages(json);
    //  displayLoginForm();
      //showInboxMembersForm();
     // showConversationForm(12);
    //showForm();
 // showChangePasswordForm();
  //Resources theme = UIManager.initFirstTheme("/style.css");
     // VerifyCinForm f = new VerifyCinForm(theme);
      // f.getForm().show();
   // showVerifyCinForm();
  //  capture();
 // testDataBase();
      // showSplashScreen();
     // new SignInForm(UIManager.initFirstTheme("/theme_pheonix")).show();
   //  new SignUpForm(theme).show();
//new ProfilForm(theme).show();
 //new AccountSettingForm(theme).show();
 new DiscussionForm(UIManager.initFirstTheme("/theme")).show();
//new ListMemberForm(UIManager.initNamedTheme("/theme","Theme1")).show();
//new SendMessageForm(theme).show();
//new SplashScreen(theme).show();
//new SignInForm(UIManager.initFirstTheme("/theme_pheonix")).show();
//new MainForm(UIManager.initNamedTheme("/theme","Theme1")).show();
    }
public void capture(){
    
    Toolbar.setGlobalToolbar(true);
Form hi = new Form("Rounder", new BorderLayout());
Label picture = new Label("", "Container");
hi.add(BorderLayout.CENTER, picture);
hi.getUnselectedStyle().setBgColor(0xff0000);
hi.getUnselectedStyle().setBgTransparency(255);
Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
Image camera = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, s);
hi.getToolbar().addCommandToRightBar("", camera, (ev) -> {
    try {
        int width = Display.getInstance().getDisplayWidth();
        Image capturedImage = Image.createImage(Capture.capturePhoto(width, -1));
        Image roundMask = Image.createImage(width, capturedImage.getHeight(), 0xff000000);
        Graphics gr = roundMask.getGraphics();
        gr.setColor(0xffffff);
        gr.fillArc(0, 0, width, width, 0, 360);
        Object mask = roundMask.createMask();
        capturedImage = capturedImage.applyMask(mask);
        picture.setIcon(capturedImage);
        hi.revalidate();
    } catch(IOException err) {
        Log.e(err);
    }
});
hi.show();
}
    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }
 public void test3(){
     LocalNotification n = new LocalNotification();
                n.setAlertBody("dfd");
                n.setAlertTitle("dd");
                n.setId("dd");
                n.setBadgeNumber(1);
AutoCompleteTextField ac = new AutoCompleteTextField();

                int repeatType = LocalNotification.REPEAT_NONE;
                 Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis() + 10 * 1000, repeatType);
 }   
    @Override
    public void localNotificationReceived(String notificationId) {
        System.err.println("notif received");        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}

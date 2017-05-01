/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.utils;

import com.codename1.io.JSONParser;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.linkar.entities.Membre;
import com.linkar.entities.Message;
import com.linkar.entities.Notification;
import com.linkar.entities.Reclamation;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Oussama Reguez
 */
public class Json {
    
        public static Membre jsonToMember(String jsonData){
         Membre m=null;
        InputStream is = new ByteArrayInputStream(  jsonData.getBytes());
        Reader r = new InputStreamReader(is);
        JSONParser jsonParser = new JSONParser();
         Map<String, Object> data;
        try {
            data = jsonParser.parseJSON(r);
             m = new Membre();
             m.setFirst_name((String) data.get("firstName"));
             m.setLast_name((String) data.get("lastName"));
             m.setEmail( (String) data.get("email"));
           
            
     
        } catch (IOException ex) {
           return null;
        }
        
        
        return m;
    }
        
        public static  List<Reclamation>  jsonToReclammations(String jsonData) {
            List<Reclamation> reclamations = new ArrayList<>();
            InputStream is = new ByteArrayInputStream(  jsonData.getBytes());
        Reader r = new InputStreamReader(is);
        JSONParser jsonParser = new JSONParser();
         Map<String, Object> data;
        try {
            data = jsonParser.parseJSON(r);
        
           ArrayList data2= (ArrayList) data.get("root");
            for (Object object : data2) {
            // LinkedHashMap<String,Object>  row =  (LinkedHashMap<String,Object>) object;
              LinkedHashMap<String,Object> te=( LinkedHashMap<String,Object>)object;
              Reclamation reclamation = new Reclamation();
            int id = ((Double) te.get("idREclamation")).intValue();
             reclamation.setId_reclamation(id);
              reclamation.setText((String) te.get("text"));            
              reclamations.add(reclamation);
          
            }
         
               
        } catch (IOException ex) {
           
        }
            
            
        return reclamations;
    
}
        
         public static List<Membre> jsonToListMembers(String jsonData){
             List<Membre> membres = new ArrayList<>();
            InputStream is = new ByteArrayInputStream(  jsonData.getBytes());
        Reader r = new InputStreamReader(is);
        JSONParser jsonParser = new JSONParser();
         Map<String, Object> data;
        try {
            data = jsonParser.parseJSON(r);
        
           ArrayList data2= (ArrayList) data.get("root");
            for (Object object : data2) {
            // LinkedHashMap<String,Object>  row =  (LinkedHashMap<String,Object>) object;
              LinkedHashMap<String,Object> te=( LinkedHashMap<String,Object>)object;
            Membre m = listToMember(te);
             
      
   
              membres.add(m);
          
            }
         
               
        } catch (IOException ex) {
           
        }
            
            
        return membres;
        }
        
        public static Membre listToMember(LinkedHashMap<String,Object> list){
            Membre m = new Membre();
              int idSender = ((Double) list.get("id")).intValue();
                    m.setId_member(idSender);
                    m.setFirst_name((String) list.get("firstName"));
                    m.setLast_name((String) list.get("lastName"));
                    m.setUrl_picture((String) list.get("urlPicture"));
                   m.setEmail((String) list.get("email"));
                   m.setPassword((String) list.get("password"));
                    LinkedHashMap<String,Object>  dateData= (LinkedHashMap<String,Object> ) list.get("lastDate");
            LinkedHashMap<String,Object>  birthDayData= (LinkedHashMap<String,Object> ) list.get("lastDate");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    Date startDate;
   if(dateData!=null){
                try {
                    String date=(String)dateData.get("date");
                    startDate = df.parse(date);
                   m.setLastDate(startDate);
       
                } catch (ParseException ex) {
                    
                }
   }
    if(birthDayData!=null){
                try {
                    String date=(String)birthDayData.get("date");
                    startDate = df.parse(date);
                   m.setBirth(startDate);
       
                } catch (ParseException ex) {
                    
                }
   }
   if(list.get("lastMessage")!=null){
        m.setLastMessage((String) list.get("lastMessage"));  
   }
            return m;
        }
        public static List<Message> jsonToListMessages(String jsonData){
             List<Message> messages = new ArrayList<>();
            InputStream is = new ByteArrayInputStream(  jsonData.getBytes());
        Reader r = new InputStreamReader(is);
        JSONParser jsonParser = new JSONParser();
         Map<String, Object> data;
        try {
            data = jsonParser.parseJSON(r);
        
           ArrayList data2= (ArrayList) data.get("root");
            for (Object object : data2) {
            // LinkedHashMap<String,Object>  row =  (LinkedHashMap<String,Object>) object;
              LinkedHashMap<String,Object> te=( LinkedHashMap<String,Object>)object;
            Message m = new Message();
            
            int id = ((Double) te.get("idMessage")).intValue();
           m.setIdMessage(id);
            // m.setFirst_name((String) te.get("firstName"));     
             m.setText((String) te.get("text"));
             LinkedHashMap<String,Object>  senderData= (LinkedHashMap<String,Object> ) te.get("sender");
             Membre sender = listToMember(senderData);
            
               
              
               LinkedHashMap<String,Object> receiverData= ( LinkedHashMap<String,Object>) te.get("receiver");
             Membre receiver = listToMember(receiverData);
             
              
                   

                m.setSender(sender);
                m.setReceiver(receiver);
              
             
              messages.add(m);
          
            }
         
               
        } catch (IOException ex) {
           
        }
            
            
        return messages;
        }        
        public static List<Notification> jsonToListNotifications(String jsonData){
               List<Notification> notifications = new ArrayList<>();
            InputStream is = new ByteArrayInputStream(  jsonData.getBytes());
        Reader r = new InputStreamReader(is);
        JSONParser jsonParser = new JSONParser();
         Map<String, Object> data;
        try {
            data = jsonParser.parseJSON(r);
        
           ArrayList data2= (ArrayList) data.get("root");
            for (Object object : data2) {
            // LinkedHashMap<String,Object>  row =  (LinkedHashMap<String,Object>) object;
              LinkedHashMap<String,Object> te=( LinkedHashMap<String,Object>)object;
              Notification notification = new Notification();
            int id = ((Double) te.get("id")).intValue();
             notification.setId_notification(id);
              Membre sender = new Membre();
             LinkedHashMap<String,Object>  senderData= (LinkedHashMap<String,Object> ) te.get("sender");
           
               sender=listToMember(senderData);
             
              
               LinkedHashMap<String,Object> receiverData= ( LinkedHashMap<String,Object>) te.get("member");
                        
              Membre receiver =listToMember(receiverData);
              

                notification.setSender(sender);
                notification.setMembre(receiver);
                notifications.add(notification);
          
            }
         
               
        } catch (IOException ex) {
           
        }
            
            
            return notifications;
        }
}

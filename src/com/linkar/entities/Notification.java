/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.entities;

/**
 *
 * @author Oussama Reguez
 */
public class Notification {
    //(`id_notification`, `text_notification`, `id_member`, `notification_seen`) 
    private int id_notification ;
    private String text_notification; 
    private Membre membre;
    private Membre Sender;

    public Membre getSender() {
        return Sender;
    }

    public void setSender(Membre Sender) {
        this.Sender = Sender;
    }
    
  
    private boolean seen;
    // type demande'd'ajout // message recu ,demande , reponse,d'avis ,
      private String type ;

    public Notification(int id_notification, String text_notification, Membre membre, boolean seen, String type) {
        this.id_notification = id_notification;
        this.text_notification = text_notification;
        this.membre = membre;
        this.seen = seen;
        this.type = type;
    }
    public Notification(){
        
    }

    @Override
    public String toString() {
        return "Notification{" + "id_notification=" + id_notification + ", text_notification=" + text_notification + ", membre=" + membre + ", Sender=" + Sender + ", seen=" + seen + ", type=" + type + '}';
    }

    public Notification(int id_notification, Membre membre, Membre Sender, boolean seen, String type) {
        this.id_notification = id_notification;
        this.membre = membre;
        this.Sender = Sender;
        this.seen = seen;
        this.type = type;
    }

    
    public int getId_notification() {
        return id_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public String getText_notification() {
        return text_notification;
    }

    public void setText_notification(String text_notification) {
        this.text_notification = text_notification;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
      
      
}

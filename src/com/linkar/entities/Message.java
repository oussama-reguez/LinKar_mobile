/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.entities;

import java.util.Date;

/**
 *
 * @author Oussama Reguez
 */
public class Message {
    private int idMessage;
    private String text ;
    private Membre sender ;
    private Membre receiver ;
    private Date date ;

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Membre getSender() {
        return sender;
    }

    public void setSender(Membre sender) {
        this.sender = sender;
    }

    public Membre getReceiver() {
        return receiver;
    }

    public void setReceiver(Membre receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
public Message(){
    
}   
}

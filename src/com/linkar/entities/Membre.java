/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkar.entities;


import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Rishya
 */
public class Membre {
    private int id_annonce;
    private String username;
    private Date lastDate;
    private String lastMessage;
   private String facebookId;

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }
    private Date createdTime;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    private int id_member;
    private String last_name;
    private String first_name;
    private Date birth;
    private String email;
    private String password;
    private double  phone_number;
    private String gender;
    private boolean statut;

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

   
    private boolean verif_number;
    private boolean verif_cin;
    private boolean verif_email;
    private boolean role;
    private String url_cin;
    private String url_picture;
    private String address;

    public Membre()
    {}
 
    public Membre(int id_member, String last_name, String first_name, Date birth, String email, String password, double phone_number, String gender, boolean verif_number, boolean verif_cin, boolean verif_email, boolean role, String url_cin, String url_picture,String address) {
        this.id_member = id_member;
        this.last_name = last_name;
        this.first_name =first_name;
        this.birth = birth;
        this.email = email;
        this.password = password;
        this.phone_number =  phone_number;
        this.gender = gender;
        this.verif_number = verif_number;
        this.verif_cin = verif_cin;
        this.verif_email = verif_email;
        this.role = role;
        this.url_cin = url_cin;
        this.url_picture = url_picture;
        this.address = address;
    }
    
     public Membre(int id_member, String last_name, String first_name, Date birth, String email, String password, double phone_number, String gender, boolean verif_number, boolean verif_cin, boolean verif_email, boolean role, String url_cin, String url_picture,String address,boolean statut) {
        this.id_member = id_member;
        this.last_name = last_name;
        this.first_name =first_name;
        this.birth = birth;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.gender = gender;
        this.verif_number = verif_number;
        this.verif_cin = verif_cin;
        this.verif_email = verif_email;
        this.role = role;
        this.url_cin = url_cin;
        this.url_picture = url_picture;
        this.address = address;
        this.statut=statut;
    }
    public Membre(int id_member){
         this.id_member = id_member;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   

    public int getId_member() {
        return id_member;
    }

    

    public Date getBirth() {
        return birth;
    }

   

    public String getPassword() {
        return password;
    }

    

   

    public boolean isVerif_number() {
        return verif_number;
    }

    public boolean isVerif_cin() {
        return verif_cin;
    }

    public boolean isVerif_email() {
        return verif_email;
    }


    public String getUrl_cin() {
        return url_cin;
    }

    public String getUrl_picture() {
        return url_picture;
    }

    public void setId_member(int id_member) {
        this.id_member = id_member;
    }

    

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    

    public void setPassword(String password) {
        this.password = password;
    }

   


    public void setVerif_number(boolean verif_number) {
        this.verif_number = verif_number;
    }

    public void setVerif_cin(boolean verif_cin) {
        this.verif_cin = verif_cin;
    }

    public void setVerif_email(boolean verif_email) {
        this.verif_email = verif_email;
    }

   

    public void setUrl_cin(String url_cin) {
        this.url_cin = url_cin;
    }

    public void setUrl_picture(String url_picture) {
        this.url_picture = url_picture;
    }

    @Override
    public String toString() {
        return "Membre{" + "id_member=" + id_member + ", last_name=" + last_name + ", first_name=" + first_name + ", birth=" + birth + ", email=" + email + ", password=" + password + ", phone_number=" + phone_number + ", gender=" + gender + ", statut=" + statut + ", verif_number=" + verif_number + ", verif_cin=" + verif_cin + ", verif_email=" + verif_email + ", role=" + role + ", url_cin=" + url_cin + ", url_picture=" + url_picture + ", address=" + address + '}';
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(double phone_number) {
        this.phone_number = phone_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    
  


    
}

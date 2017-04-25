package com.linkar.entities;

import java.util.Date;



public class Annonce {
    private int id_annonce;
    private String depart;
    private String destination;
    private boolean fumeur;
    private boolean bavard;
    private boolean men_only;
    private boolean women_only;
    private boolean animaux;
    private Date date_annonce;
    private boolean regulier;
    private String horaire_depart;
    private int places;
    private int tarif;
    private String description;
    private Membre m;
    private Voiture v;

    public Annonce() {
    }

    
    
    public Annonce(String depart, String destination, boolean fumeur, boolean bavard, boolean men_only, boolean women_only, boolean animaux, Date date_annonce, boolean regulier, String horaire_depart, int places, int tarif, String description, Membre m, Voiture v) { 
       // this.id_annonce = id_annonce;
        this.depart = depart;
        this.destination = destination;
        this.fumeur = fumeur;
        this.bavard = bavard;
        this.men_only = men_only;
        this.women_only = women_only;
        this.animaux = animaux;
        this.date_annonce = date_annonce;
        this.regulier = regulier;
        this.horaire_depart = horaire_depart;
        this.places = places;
        this.tarif = tarif;
        this.description = description;
        this.m = m;
        this.v = v;
    }

    
    
    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isFumeur() {
        return fumeur;
    }

    public void setFumeur(boolean fumeur) {
        this.fumeur = fumeur;
    }

    public boolean isBavard() {
        return bavard;
    }

    public void setBavard(boolean bavard) {
        this.bavard = bavard;
    }

    public boolean isMen_only() {
        return men_only;
    }

    public void setMen_only(boolean men_only) {
        this.men_only = men_only;
    }

    public boolean isWomen_only() {
        return women_only;
    }

    public void setWomen_only(boolean women_only) {
        this.women_only = women_only;
    }

    public boolean isAnimaux() {
        return animaux;
    }

    public void setAnimaux(boolean animaux) {
        this.animaux = animaux;
    }

    public Date getDate_annonce() {
        return date_annonce;
    }

    public void setDate_annonce(Date date_annonce) {
        this.date_annonce = date_annonce;
    }

    public boolean isRegulier() {
        return regulier;
    }

    public void setRegulier(boolean regulier) {
        this.regulier = regulier;
    }

    public String getHoraire_depart() {
        return horaire_depart;
    }

    public void setHoraire_depart(String horaire_depart) {
        this.horaire_depart = horaire_depart;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Membre getM() {
        return m;
    }

    public void setM(Membre m) {
        this.m = m;
    }

    public Voiture getV() {
        return v;
    }

    public void setV(Voiture v) {
        this.v = v;
    }

    
    
    @Override
    public String toString() {
        return "Annonce{" + "id_annonce=" + id_annonce + ", depart=" + depart + ", destination=" + destination + ", fumeur=" + fumeur + ", bavard=" + bavard + ", men_only=" + men_only + ", women_only=" + women_only + ", animaux=" + animaux + ", date_annonce=" + date_annonce + ", regulier=" + regulier + ", horaire_depart=" + horaire_depart + ", places=" + places + ", tarif=" + tarif + ", description=" + description +"Membre=" + m + ", Voiture=" + v + '}';
    }
    
}

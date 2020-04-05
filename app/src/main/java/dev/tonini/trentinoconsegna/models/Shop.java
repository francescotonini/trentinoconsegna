package dev.tonini.trentinoconsegna.models;

import java.util.List;

public class Shop {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public List<String> getDeliveryCities() {
        return deliveryCities;
    }

    public void setDeliveryCities(List<String> deliveryCities) {
        this.deliveryCities = deliveryCities;
    }

    public List<String> getComunitaConsegna() {
        return comunitaConsegna;
    }

    public void setComunitaConsegna(List<String> comunitaConsegna) {
        this.comunitaConsegna = comunitaConsegna;
    }

    public boolean isDeliversEverywhere() {
        return deliversEverywhere;
    }

    public void setDeliversEverywhere(boolean deliversEverywhere) {
        this.deliversEverywhere = deliversEverywhere;
    }

    private String name;
    private String description;
    private double lat;
    private double lon;
    private String category;
    private String phoneNumber;
    private String email;
    private String whatsappNumber;
    private String facebookUrl;
    private List<String> deliveryCities;
    private List<String> comunitaConsegna;
    private boolean deliversEverywhere;
}

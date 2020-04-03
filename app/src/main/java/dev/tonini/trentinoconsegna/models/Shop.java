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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
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

    public List<String> getComuniConsegna() {
        return comuniConsegna;
    }

    public void setComuniConsegna(List<String> comuniConsegna) {
        this.comuniConsegna = comuniConsegna;
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
    private List<String> categories;
    private String phoneNumber;
    private String email;
    private String whatsappNumber;
    private String facebookUrl;
    private List<String> comuniConsegna;
    private List<String> comunitaConsegna;
    private boolean deliversEverywhere;
}

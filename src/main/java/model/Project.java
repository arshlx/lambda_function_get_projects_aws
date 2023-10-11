package model;

import com.google.gson.annotations.SerializedName;

   
public class Project {

   @SerializedName("id")
   int id;

   @SerializedName("organization")
   Organization organization;

   @SerializedName("active")
   boolean active;

   @SerializedName("title")
   String title;

   @SerializedName("summary")
   String summary;

   @SerializedName("themeName")
   String themeName;

   @SerializedName("country")
   String country;

   @SerializedName("iso3166CountryCode")
   String iso3166CountryCode;

   @SerializedName("status")
   String status;

   @SerializedName("imageLink")
   String imageLink;

   @SerializedName("donationOptions")
   DonationOptions donationOptions;

   @SerializedName("type")
   String type;

   @SerializedName("themes")
   Themes themes;

   @SerializedName("countries")
   Countries countries;


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
    public Organization getOrganization() {
        return organization;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean getActive() {
        return active;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getSummary() {
        return summary;
    }
    
    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
    public String getThemeName() {
        return themeName;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }
    
    public void setIso3166CountryCode(String iso3166CountryCode) {
        this.iso3166CountryCode = iso3166CountryCode;
    }
    public String getIso3166CountryCode() {
        return iso3166CountryCode;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
    public String getImageLink() {
        return imageLink;
    }
    
    public void setDonationOptions(DonationOptions donationOptions) {
        this.donationOptions = donationOptions;
    }
    public DonationOptions getDonationOptions() {
        return donationOptions;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    
    public void setThemes(Themes themes) {
        this.themes = themes;
    }
    public Themes getThemes() {
        return themes;
    }
    
    public void setCountries(Countries countries) {
        this.countries = countries;
    }
    public Countries getCountries() {
        return countries;
    }
    
}
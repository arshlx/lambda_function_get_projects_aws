package model;

import com.google.gson.annotations.SerializedName;

   
public class Organization {

   @SerializedName("id")
   int id;

   @SerializedName("name")
   String name;

   @SerializedName("ein")
   String ein;

   @SerializedName("addressLine1")
   String addressLine1;

   @SerializedName("addressLine2")
   String addressLine2;

   @SerializedName("city")
   String city;

   @SerializedName("state")
   String state;

   @SerializedName("postal")
   String postal;

   @SerializedName("iso3166CountryCode")
   String iso3166CountryCode;

   @SerializedName("url")
   String url;

   @SerializedName("logoUrl")
   String logoUrl;

   @SerializedName("mission")
   String mission;

   @SerializedName("totalProjects")
   int totalProjects;

   @SerializedName("activeProjects")
   int activeProjects;

   @SerializedName("themes")
   Themes themes;

   @SerializedName("countries")
   Countries countries;

   @SerializedName("country")
   String country;


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void setEin(String ein) {
        this.ein = ein;
    }
    public String getEin() {
        return ein;
    }
    
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    public String getAddressLine1() {
        return addressLine1;
    }
    
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    public String getAddressLine2() {
        return addressLine2;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }
    
    public void setPostal(String postal) {
        this.postal = postal;
    }
    public String getPostal() {
        return postal;
    }
    
    public void setIso3166CountryCode(String iso3166CountryCode) {
        this.iso3166CountryCode = iso3166CountryCode;
    }
    public String getIso3166CountryCode() {
        return iso3166CountryCode;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
    
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    public String getLogoUrl() {
        return logoUrl;
    }
    
    public void setMission(String mission) {
        this.mission = mission;
    }
    public String getMission() {
        return mission;
    }
    
    public void setTotalProjects(int totalProjects) {
        this.totalProjects = totalProjects;
    }
    public int getTotalProjects() {
        return totalProjects;
    }
    
    public void setActiveProjects(int activeProjects) {
        this.activeProjects = activeProjects;
    }
    public int getActiveProjects() {
        return activeProjects;
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
    
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }
    
}
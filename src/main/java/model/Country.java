package model;

import com.google.gson.annotations.SerializedName;

   
public class Country {

   @SerializedName("name")
   String name;

   @SerializedName("iso3166CountryCode")
   String iso3166CountryCode;


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void setIso3166CountryCode(String iso3166CountryCode) {
        this.iso3166CountryCode = iso3166CountryCode;
    }
    public String getIso3166CountryCode() {
        return iso3166CountryCode;
    }
    
}
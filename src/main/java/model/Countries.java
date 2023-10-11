package model;
import java.util.List;

import com.google.gson.annotations.SerializedName;

   
public class Countries {

   @SerializedName("country")
   List<Country> country;


    public void setCountry(List<Country> country) {
        this.country = country;
    }
    public List<Country> getCountry() {
        return country;
    }
    
}
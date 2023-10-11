package model;

import com.google.gson.annotations.SerializedName;

   
public class Filter {

   @SerializedName("name")
   String name;

   @SerializedName("value")
   String value;


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    
}
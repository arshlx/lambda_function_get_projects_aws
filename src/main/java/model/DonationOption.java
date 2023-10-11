package model;

import com.google.gson.annotations.SerializedName;

   
public class DonationOption {

   @SerializedName("amount")
   int amount;

   @SerializedName("description")
   String description;


    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    
}
package model;
import java.util.List;

import com.google.gson.annotations.SerializedName;

   
public class DonationOptions {

   @SerializedName("donationOption")
   List<DonationOption> donationOption;


    public void setDonationOption(List<DonationOption> donationOption) {
        this.donationOption = donationOption;
    }
    public List<DonationOption> getDonationOption() {
        return donationOption;
    }
    
}
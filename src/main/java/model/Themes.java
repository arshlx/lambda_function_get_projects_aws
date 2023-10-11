package model;
import java.util.List;

import com.google.gson.annotations.SerializedName;

   
public class Themes {

   @SerializedName("theme")
   List<Theme> theme;


    public void setTheme(List<Theme> theme) {
        this.theme = theme;
    }
    public List<Theme> getTheme() {
        return theme;
    }
    
}
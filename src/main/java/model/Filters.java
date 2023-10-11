package model;
import java.util.List;

import com.google.gson.annotations.SerializedName;

   
public class Filters {

   @SerializedName("filter")
   List<Filter> filter;


    public void setFilter(List<Filter> filter) {
        this.filter = filter;
    }
    public List<Filter> getFilter() {
        return filter;
    }
    
}
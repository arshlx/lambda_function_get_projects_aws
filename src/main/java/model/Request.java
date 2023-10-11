package model;

import com.google.gson.annotations.SerializedName;

   
public class Request {

   @SerializedName("q")
   String q;

   @SerializedName("start")
   int start;

   @SerializedName("filters")
   Filters filters;

   @SerializedName("summary")
   boolean summary;


    public void setQ(String q) {
        this.q = q;
    }
    public String getQ() {
        return q;
    }
    
    public void setStart(int start) {
        this.start = start;
    }
    public int getStart() {
        return start;
    }
    
    public void setFilters(Filters filters) {
        this.filters = filters;
    }
    public Filters getFilters() {
        return filters;
    }
    
    public void setSummary(boolean summary) {
        this.summary = summary;
    }
    public boolean getSummary() {
        return summary;
    }
    
}
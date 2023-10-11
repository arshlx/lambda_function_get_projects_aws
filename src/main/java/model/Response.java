package model;

import com.google.gson.annotations.SerializedName;

   
public class Response {

   @SerializedName("projects")
   Projects projects;

   @SerializedName("start")
   int start;

   @SerializedName("numberFound")
   int numberFound;


    public void setProjects(Projects projects) {
        this.projects = projects;
    }
    public Projects getProjects() {
        return projects;
    }
    
    public void setStart(int start) {
        this.start = start;
    }
    public int getStart() {
        return start;
    }
    
    public void setNumberFound(int numberFound) {
        this.numberFound = numberFound;
    }
    public int getNumberFound() {
        return numberFound;
    }
    
}
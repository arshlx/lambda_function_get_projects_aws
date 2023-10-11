package model;
import java.util.List;

import com.google.gson.annotations.SerializedName;

   
public class Projects {

   @SerializedName("project")
   List<Project> projectList;


    public void setProject(List<Project> project) {
        this.projectList = project;
    }
    public List<Project> getProject() {
        return projectList;
    }
    
}
package model;

import com.google.gson.annotations.SerializedName;

public class NameInfo {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("common")
    private String name;
}

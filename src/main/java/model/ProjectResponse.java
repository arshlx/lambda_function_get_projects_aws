package model;

import com.google.gson.annotations.SerializedName;

public class ProjectResponse {
    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }
    @SerializedName("search")
    Search search;
}

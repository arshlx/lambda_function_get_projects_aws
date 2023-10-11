package model;

import com.google.gson.annotations.SerializedName;

public class Search {
   @SerializedName("request")
   Request request;

   @SerializedName("response")
   Response response;


    public void setRequest(Request request) {
        this.request = request;
    }
    public Request getRequest() {
        return request;
    }
    
    public void setResponse(Response response) {
        this.response = response;
    }
    public Response getResponse() {
        return response;
    }
    
}
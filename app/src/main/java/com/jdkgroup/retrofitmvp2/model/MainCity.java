package com.jdkgroup.retrofitmvp2.model;

/**
 * Created by kamlesh on 6/27/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainCity {

    @SerializedName("RestResponse")
    @Expose
    private RestResponse restResponse;

    public RestResponse getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(RestResponse restResponse) {
        this.restResponse = restResponse;
    }

}
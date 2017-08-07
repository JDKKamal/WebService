package com.jdkgroup.retrofitmvp2.model;

/**
 * Created by kamlesh on 6/27/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alpha2_code")
    @Expose
    private String alpha2Code;
    @SerializedName("alpha3_code")
    @Expose
    private String alpha3Code;

    public String getName() {
        return name;
    }
}
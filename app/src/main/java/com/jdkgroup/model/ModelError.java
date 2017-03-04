package com.jdkgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelError {

    @SerializedName("message")
    @Expose
    private Integer message;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("currentdate")
    @Expose
    private String currentdate;

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

}
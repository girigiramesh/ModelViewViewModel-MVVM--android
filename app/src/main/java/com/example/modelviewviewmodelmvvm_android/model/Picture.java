package com.example.modelviewviewmodelmvvm_android.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ramesh on 2/1/17.
 */

public class Picture implements Serializable {

    @SerializedName("large")
    public String large;

    @SerializedName("medium")
    public String medium;

    @SerializedName("thumbnail")
    public String thumbnail;
}

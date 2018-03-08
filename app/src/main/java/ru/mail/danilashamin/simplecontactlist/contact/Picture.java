package ru.mail.danilashamin.simplecontactlist.contact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Picture implements Serializable {
    @Expose
    @SerializedName("large")
    private String largePictureUrl;

    public String getLargePictureUrl() {
        return largePictureUrl;
    }

}

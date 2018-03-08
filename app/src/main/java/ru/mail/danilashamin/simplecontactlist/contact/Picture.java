package ru.mail.danilashamin.simplecontactlist.contact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Picture {
    @Expose
    @SerializedName("large")
    private String largePictureUrl;

    public String getLargePictureUrl() {
        return largePictureUrl;
    }

}

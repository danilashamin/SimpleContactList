package ru.mail.danilashamin.simplecontactlist.http.contact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {
    @Expose
    @SerializedName("picture")
    private Picture picture;
    @Expose
    @SerializedName("name")
    private Name name;
    @Expose
    @SerializedName("gender")
    private String gender;

    public Picture getPicture() {
        return picture;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

}

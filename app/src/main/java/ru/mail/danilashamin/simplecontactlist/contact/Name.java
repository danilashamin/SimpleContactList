package ru.mail.danilashamin.simplecontactlist.contact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Name implements Serializable {
    @Expose
    @SerializedName("last")
    private String lastName;
    @Expose
    @SerializedName("first")
    private String firstName;
    @Expose
    @SerializedName("title")
    private String title;

    public String getLastName() {
        return wordWithFirstLetterUpperCase(lastName);
    }

    public String getFirstName() {
        return wordWithFirstLetterUpperCase(firstName);
    }

    public String getTitle() {
        return wordWithFirstLetterUpperCase(title);
    }

    private String wordWithFirstLetterUpperCase(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}

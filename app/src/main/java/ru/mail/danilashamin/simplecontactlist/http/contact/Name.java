package ru.mail.danilashamin.simplecontactlist.http.contact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {
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
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

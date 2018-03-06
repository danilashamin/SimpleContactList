package ru.mail.danilashamin.simplecontactlist.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.mail.danilashamin.simplecontactlist.http.contact.Contact;

/**
 * Created by Danil on 06.03.2018 on 14:57.
 */

public class Results {
    @Expose
    @SerializedName("results")
    private List<Contact> results;

    public List<Contact> getResults() {
        return results;
    }

    public void setResults(List<Contact> results) {
        this.results = results;
    }
}

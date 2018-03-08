package ru.mail.danilashamin.simplecontactlist.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import ru.mail.danilashamin.simplecontactlist.contact.Contact;

/**
 * Created by Danil on 06.03.2018 on 14:57.
 */

public class Results {
    @Expose
    @SerializedName("results")
    private ArrayList<Contact> results;

    public ArrayList<Contact> getResults() {

        return results;
    }

}

package ru.mail.danilashamin.simplecontactlist.http;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.mail.danilashamin.simplecontactlist.contact.Contact;

/**
 * Created by Danil on 06.03.2018 on 13:32.
 */

public interface RequestInterface {
    @GET("?results=40&inc=gender,name,picture&noinfo")
    Call<List<Contact>> contacts();
}

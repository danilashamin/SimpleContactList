package ru.mail.danilashamin.simplecontactlist.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Danil on 06.03.2018 on 13:32.
 */

public interface RequestInterface {
    @GET("api/")
    Call<Results> contacts(@Query("results") int countOfContacts, @Query("inc") String included, @Query("noinfo") String noInfo);
}

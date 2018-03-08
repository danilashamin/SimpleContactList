package ru.mail.danilashamin.simplecontactlist.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mail.danilashamin.simplecontactlist.R;
import ru.mail.danilashamin.simplecontactlist.adapters.ListViewAdapter;
import ru.mail.danilashamin.simplecontactlist.contact.Contact;
import ru.mail.danilashamin.simplecontactlist.http.RequestInterface;
import ru.mail.danilashamin.simplecontactlist.http.Results;

import static ru.mail.danilashamin.simplecontactlist.C.API_BASE_URL;
import static ru.mail.danilashamin.simplecontactlist.C.API_COUNT_OF_CONTACTS;
import static ru.mail.danilashamin.simplecontactlist.C.API_INCLUDED;
import static ru.mail.danilashamin.simplecontactlist.C.API_NO_INFO;

public class FirstImplementationActivity extends AppCompatActivity {
    private ProgressBar pbLoading;

    private ListView contactListView;
    private ListViewAdapter adapter;

    private RequestInterface requestInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_implementation);

        initView();
        initServerRequestAPI();
    }

    private void initView() {
        pbLoading = findViewById(R.id.pbLoading);
        contactListView = findViewById(R.id.contactListView);
        findViewById(R.id.loadContactsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload();
            }
        });

        adapter = new ListViewAdapter(this);
    }


    private void initServerRequestAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        requestInterface = retrofit.create(RequestInterface.class);

    }

    private void startDownload() {
        pbLoading.setVisibility(View.VISIBLE);
        Call<Results> contacts = requestInterface.contacts(API_COUNT_OF_CONTACTS, API_INCLUDED, API_NO_INFO);
        contacts.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(@NonNull Call<Results> call, @NonNull Response<Results> response) {
                onResponseSuccessful(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<Results> call, @NonNull Throwable t) {
                onResponseFailure(t);
            }
        });
    }

    private void onResponseSuccessful(List<Contact> contactList) {
        pbLoading.setVisibility(View.INVISIBLE);
        adapter.setContactsList(contactList);
        adapter.notifyDataSetChanged();
        contactListView.setAdapter(adapter);
    }

    private void onResponseFailure(Throwable t) {
        pbLoading.setVisibility(View.INVISIBLE);
        Toast.makeText(FirstImplementationActivity.this, getString(R.string.loading_error), Toast.LENGTH_SHORT).show();
        Log.d("failure", "Failed to load, error message: " + t.getMessage());
    }

}

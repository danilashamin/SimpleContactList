package ru.mail.danilashamin.simplecontactlist.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mail.danilashamin.simplecontactlist.R;
import ru.mail.danilashamin.simplecontactlist.adapters.RecyclerViewAdapter;
import ru.mail.danilashamin.simplecontactlist.contact.Contact;
import ru.mail.danilashamin.simplecontactlist.http.RequestInterface;
import ru.mail.danilashamin.simplecontactlist.http.Results;

import static ru.mail.danilashamin.simplecontactlist.C.API_BASE_URL;
import static ru.mail.danilashamin.simplecontactlist.C.API_COUNT_OF_CONTACTS;
import static ru.mail.danilashamin.simplecontactlist.C.API_INCLUDED;
import static ru.mail.danilashamin.simplecontactlist.C.API_NO_INFO;
import static ru.mail.danilashamin.simplecontactlist.C.SAVE_INSTANCE_STATE_CONTACT_LIST;

public class SecondImplementationActivity extends AppCompatActivity {

    @BindView(R.id.mainContactList)
    RecyclerView mainContactList;

    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    private RecyclerViewAdapter adapter;
    private RequestInterface requestInterface;
    private ArrayList<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_implementation);
        ButterKnife.bind(this);

        initView();
        initServerRequestAPI();
    }


    private void initView() {
        mainContactList.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(SecondImplementationActivity.this);
        mainContactList.setLayoutManager(mLayoutManager);
        adapter = new RecyclerViewAdapter();
    }

    @OnClick(R.id.loadContactsButton)
    public void onViewClicked() {
        loadContactsListFromServer();
    }

    private void initServerRequestAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        requestInterface = retrofit.create(RequestInterface.class);
    }

    private void loadContactsListFromServer() {
        pbLoading.setVisibility(View.VISIBLE);
        Call<Results> contacts = requestInterface.contacts(API_COUNT_OF_CONTACTS, API_INCLUDED, API_NO_INFO);
        contacts.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(@NonNull Call<Results> call, @NonNull Response<Results> response) {
                contactList = response.body().getResults();
                updateContactListView(contactList);
            }

            @Override
            public void onFailure(@NonNull Call<Results> call, @NonNull Throwable t) {
                onResponseFailure(t);
            }
        });
    }

    private void updateContactListView(List<Contact> contactList) {
        pbLoading.setVisibility(View.INVISIBLE);
        adapter.setContactsList(contactList);
        adapter.notifyDataSetChanged();
        mainContactList.setAdapter(adapter);
    }

    private void onResponseFailure(Throwable t) {
        pbLoading.setVisibility(View.INVISIBLE);
        Toast.makeText(SecondImplementationActivity.this, getString(R.string.loading_error), Toast.LENGTH_SHORT).show();
        Log.d("failure", "Failed to load, error message: " + t.getMessage());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (contactList != null) {
            outState.putParcelableArrayList(SAVE_INSTANCE_STATE_CONTACT_LIST, contactList);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            contactList = savedInstanceState.getParcelableArrayList(SAVE_INSTANCE_STATE_CONTACT_LIST);
            updateContactListView(contactList);
        }
    }

}

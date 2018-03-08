package ru.mail.danilashamin.simplecontactlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mail.danilashamin.simplecontactlist.adapters.RecyclerViewAdapter;
import ru.mail.danilashamin.simplecontactlist.http.RequestInterface;
import ru.mail.danilashamin.simplecontactlist.http.Results;

import static ru.mail.danilashamin.simplecontactlist.C.API_BASE_URL;
import static ru.mail.danilashamin.simplecontactlist.C.API_COUNT_OF_CONTACTS;
import static ru.mail.danilashamin.simplecontactlist.C.API_INCLUDED;
import static ru.mail.danilashamin.simplecontactlist.C.API_NO_INFO;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainContactList)
    RecyclerView mainContactList;

    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    private RequestInterface requestInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        requestInterface = retrofit.create(RequestInterface.class);
    }

    @OnClick(R.id.loadContactsButton)
    public void onViewClicked() {
        pbLoading.setVisibility(View.VISIBLE);
        Call<Results> contacts = requestInterface.contacts(API_COUNT_OF_CONTACTS, API_INCLUDED, API_NO_INFO);
        contacts.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(@NonNull Call<Results> call, @NonNull Response<Results> response) {
                pbLoading.setVisibility(View.INVISIBLE);
                mainContactList.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(MainActivity.this);
                mainContactList.setLayoutManager(mLayoutManager);

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(response.body().getResults());
                mainContactList.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<Results> call, @NonNull Throwable t) {
                pbLoading.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, getString(R.string.loading_error), Toast.LENGTH_SHORT).show();
                Log.d("failure", "Failed to load, error message: " + t.getMessage());
            }
        });
    }
}

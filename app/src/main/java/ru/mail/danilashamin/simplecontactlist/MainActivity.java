package ru.mail.danilashamin.simplecontactlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mail.danilashamin.simplecontactlist.adapters.RecyclerViewAdapter;
import ru.mail.danilashamin.simplecontactlist.contact.Contact;
import ru.mail.danilashamin.simplecontactlist.http.RequestInterface;

import static ru.mail.danilashamin.simplecontactlist.C.API_BASE_URL;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainContactList)
    RecyclerView mainContactList;

    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.loadContactsButton)
    Button loadContactsButton;

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
        Call<List<Contact>> contacts = requestInterface.contacts();
        contacts.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                mainContactList.setHasFixedSize(true);

                mLayoutManager = new LinearLayoutManager(MainActivity.this);
                mainContactList.setLayoutManager(mLayoutManager);

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(response.body());
                mainContactList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Log.d("failure", "failed to load");
            }
        });
    }
}

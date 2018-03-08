package ru.mail.danilashamin.simplecontactlist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.mail.danilashamin.simplecontactlist.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.buttonFirstImplementation, R.id.buttonSecondImplementation})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.buttonFirstImplementation:
                intent = new Intent(this, FirstImplementation.class);
                startActivity(intent);
                break;
            case R.id.buttonSecondImplementation:
                intent = new Intent(this, SecondImplementation.class);
                startActivity(intent);
                break;
        }
    }
}

package com.example.onaapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.onaapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.seeUsersButton) Button mUsersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mUsersButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view == mUsersButton){
            Intent intent = new Intent(MainActivity.this, UsersListActivity.class);
            Toast.makeText(MainActivity.this, "CLICKED ON THE START BUTTON", Toast.LENGTH_SHORT).show();
            Log.d("Clicked", "Clicked on the Button");
            startActivity(intent);
        }
    }
}

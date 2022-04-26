package com.example.ad340_wk2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        Bundle b = getIntent().getExtras();
        TextView textViewUsername = findViewById(R.id.textViewUsername);
        textViewUsername.setText(b.getString(Constants.USERNAME_KEY));
    }

    public void back(View view) {
        this.finish();
    }
}

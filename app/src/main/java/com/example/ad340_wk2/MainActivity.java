package com.example.ad340_wk2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;

public class MainActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText emailAddressField;
    private EditText usernameField;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.name);
        emailAddressField = findViewById(R.id.email);
        usernameField = findViewById(R.id.username);
        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK);
    }

    @Override
    public void onResume() {
        super.onResume();
        nameField = findViewById(R.id.name);
        nameField.setText("");
        emailAddressField = findViewById(R.id.email);
        emailAddressField.setText("");
        usernameField = findViewById(R.id.username);
        usernameField.setText("");
        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK);
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void onSubmit(View view) {
        String name = nameField.getText().toString();
        String emailAddress = emailAddressField.getText().toString();
        String username = usernameField.getText().toString();

        if (name.equals("") || emailAddress.equals("") || username.equals("")) {
            // empty strings are not valid form input show a Toast to the user
            Toast.makeText(getApplicationContext(), getString(R.string.forgot_data_error), Toast.LENGTH_LONG).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            Toast.makeText(getApplicationContext(), getString(R.string.email_address_error), Toast.LENGTH_LONG).show();
            return;
        }

        DatePicker dp = datePickerDialog.getDatePicker();
        if (Period.between(LocalDate.of(dp.getYear(), dp.getMonth(), dp.getDayOfMonth()), LocalDate.now()).getYears() < 18) {
            Toast.makeText(getApplicationContext(), getString(R.string.age_error), Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, WelcomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.USERNAME_KEY, username);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
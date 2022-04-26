package com.example.ad340_wk2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextUsername;
    private Button buttonBirthday;
    private DatePickerDialog datePickerDialog;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        buttonBirthday = findViewById(R.id.buttonBirthday);
        java.util.Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year1, int month1, int day1) {
                year = year1;
                month = month1 + 1;
                day = day1;
                String date = month + "/" + day + "/" + year;
                buttonBirthday.setText(date);
            }
        }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onRestart() {
        super.onRestart();
        editTextName.setText(R.string.empty);
        editTextEmail.setText(R.string.empty);
        editTextUsername.setText(R.string.empty);
        buttonBirthday.setText(R.string.buttonBirthday);
        java.util.Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year1, int month1, int day1) {
                year = year1;
                month = month1 + 1;
                day = day1;
                String date = month + "/" + day + "/" + year;
                buttonBirthday.setText(date);
            }
        }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void onSubmit(View view) {
        String name = editTextName.getText().toString();
        String emailAddress = editTextEmail.getText().toString();
        String username = editTextUsername.getText().toString();

        if (name.equals("") || emailAddress.equals("") || username.equals("")) {
            // empty strings are not valid form input show a Toast to the user
            Toast.makeText(getApplicationContext(), getString(R.string.errorEmptyEditText), Toast.LENGTH_LONG).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            Toast.makeText(getApplicationContext(), getString(R.string.errorWrongEmail), Toast.LENGTH_LONG).show();
            return;
        }

        if (Period.between(LocalDate.of(year, month, day), LocalDate.now()).getYears() < 18) {
            Toast.makeText(getApplicationContext(), getString(R.string.errorAge), Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, WelcomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.USERNAME_KEY, username);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
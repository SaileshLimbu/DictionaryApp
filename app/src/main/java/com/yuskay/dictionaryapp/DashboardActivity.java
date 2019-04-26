package com.yuskay.dictionaryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddCountry, btnDictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnAddCountry = findViewById(R.id.btnAddCountries);
        btnDictionary = findViewById(R.id.btnDictionary);

        btnDictionary.setOnClickListener(this);
        btnAddCountry.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnDictionary)
        {
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.btnAddCountries)
        {
            Intent intent = new Intent(DashboardActivity.this, AddWordActivity.class);
            startActivity(intent);
        }
    }
}

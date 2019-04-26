package com.yuskay.dictionaryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

//    private TextView tvCapital;
//
//    public static final String countries[] = {
//            "Nepal", "Kathmandu",
//            "India", "New Delhi",
//            "China", "Beijing",
//            "UK", "London",
//            "US", "Washington D.C.",
//            "Canada", "Ottawa"
//
//    };
    private ListView countrieslist;
    private Map<String, String> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countrieslist = findViewById(R.id.countrieslist);
//        tvCapital = findViewById(R.id.tvCapital);
//
//        dictionary = new HashMap<>();
//        for (int i = 0; i < countries.length; i += 2) {
//            dictionary.put(countries[i], countries[i + 1]);
//        }
        dictionary = new HashMap<>();

        readFromFile();

        ArrayAdapter adapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet())
        );

        countrieslist.setAdapter(adapter);

        countrieslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String country = parent.getItemAtPosition(position).toString();
                String capital = dictionary.get(country);

                //Toast.makeText(getApplicationContext(), capital.toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, CapitalActivity.class);
                intent.putExtra("capital", capital);
                startActivity(intent);
            }
        });
    }

    private void readFromFile()
    {
        try {
            FileInputStream fileInputStream = openFileInput("file.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line=bufferedReader.readLine()) != null)
            {
                String[] parts = line.split("->");
                dictionary.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

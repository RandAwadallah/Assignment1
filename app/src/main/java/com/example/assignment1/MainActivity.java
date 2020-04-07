package com.example.assignment1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sets navigation (Action bar) title
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Car Brands");

        final String[] carsArr = getResources().getStringArray(R.array.cars_array);

        intent = new Intent(MainActivity.this, CarDetails.class);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, carsArr) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                if (position % 2 == 1)
                    view.setBackgroundColor(Color.rgb(142, 171, 251));
                else
                    view.setBackgroundColor(Color.WHITE);
                return view;
            }
        };
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


        /*To listen to list view click events*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Pass the Position (index of the list view that has been clicked to the new Activity)
                intent.putExtra("POSITION", position);
                startActivity(intent);
            }
        });
    }
}

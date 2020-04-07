package com.example.assignment1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.StringTokenizer;


public class CarDetails extends AppCompatActivity {

      String[] carsDetailsArr;
    TextView nameTxt;
    TextView priceTxt;
    TextView millageTxt;
    TextView colorTxt;
    TextView yearTxt;
    ImageView imageView;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        //Sets navigation (Action bar) title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Car Details");

        //get car details array
        carsDetailsArr = getResources().getStringArray(R.array.cars_details_array);

        ImageButton btnBefore = (ImageButton) findViewById(R.id.before);
        ImageButton btnAfter = (ImageButton) findViewById(R.id.after);
        imageView = (ImageView) findViewById(R.id.ShowImageView);
        yearTxt = (TextView) findViewById(R.id.year_result);
        colorTxt = (TextView) findViewById(R.id.color_result);
        millageTxt = (TextView) findViewById(R.id.millage_result);
        priceTxt = (TextView) findViewById(R.id.price_result);
        nameTxt = (TextView) findViewById(R.id.name_result);


        Bundle extras = getIntent().getExtras();

        try {
            //get the passed position
            position = extras.getInt("POSITION");

            filterCarDetailsArrByIndex(position);

        } catch (Exception e) {
            Log.e("exception: ", String.valueOf(e));
        }


        btnBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 0)
                    refreshActivity(position - 1);
            }
        });

        btnAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < carsDetailsArr.length - 1)
                    refreshActivity(position + 1);
            }
        });
    }


    /*
     * This method is called when the user clicks on left / right button
     * It reloads the activity with the new position(index)
     */
    private void refreshActivity(int position) {
        Intent intent = getIntent();
        intent.putExtra("POSITION", position);
        finish();
        startActivity(intent);
    }

    /*
     * This method filters the carsDetailsArr using the position parameter
     */
    private void filterCarDetailsArrByIndex(int position) {

        try {
            String name = "", year = "", color = "", millage = "", price = "";
            JSONObject objName = null;
            StringTokenizer st = new StringTokenizer("");

            JSONObject json = (JSONObject) new JSONTokener(carsDetailsArr[position]).nextValue();
            name = (String) json.getString("name");
            year = (String) json.getString("year");
            color = (String) json.getString("color");
            millage = (String) json.getString("milage");
            price = (String) json.getString("price");

            fillCarDetailsData(name, year, color, millage, price, position);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*
     * This method updates the UI components with its data.
     */
    private void fillCarDetailsData(String name, String year, String color, String millage, String price, int position) {

        Drawable drawable = getResources().getDrawable(getResources()
                .getIdentifier("car" + (position + 1), "drawable", getPackageName()));

        imageView.setImageDrawable(drawable);
        nameTxt.setText(name);
        yearTxt.setText(year);
        colorTxt.setText(color);
        millageTxt.setText(millage);
        priceTxt.setText(price);

    }
}


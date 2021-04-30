package com.example.driverassistant.Home;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.driverassistant.Map.MapsActivity;
import com.example.driverassistant.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class History extends AppCompatActivity {
    private TextView loai_xe;
    private TextView ngay_theo_doi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_history);

        loai_xe = findViewById(R.id.tv_loai_xe);
        ngay_theo_doi = findViewById(R.id.tv_ngay_theo_doi);

        setBottomNavigation();

        loai_xe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaixe();
            }
        });
        ngay_theo_doi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate();
            }
        });
    }

    private void setBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.home_nav);

        bottomNavigationView.setSelectedItemId(R.id.home_bottom_report);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_bottom_report:
                    startActivity(new Intent(History.this,Home.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.home_bottom_history:

                    return true;

                case R.id.home_bottom_map:
                    startActivity(new Intent(History.this, MapsActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.home_bottom_more:
                    return true;
            }

            return false;
        });
    }

    private void loaixe() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // set title
        builder.setTitle("Chọn hãng xe bạn dùng: ");

        // generate an array of places
        final String[] places = new String[]{
                "Honda",
                "HuynDai",
                "Ferrari",
                "Lamboghini"
        };

        // set single choice
        builder.setSingleChoiceItems(places, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // get the selected item
                String selectedItem = Arrays.asList(places).get(i);

                // set selected item to edit text
                loai_xe.setText(selectedItem);
            }
        });

        // Create the alert dialog
        AlertDialog dialog = builder.create();

        // Finally, display the alert dialog
        dialog.show();
    }//loai_xe dialog

    private void pickDate() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                ngay_theo_doi.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

}

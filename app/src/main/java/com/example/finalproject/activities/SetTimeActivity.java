package com.example.finalproject.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.utils.SharedData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SetTimeActivity extends AppCompatActivity {

    private Button etFromTime, etToTime;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_set_time);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etFromTime = findViewById(R.id.etFromTime);
        etToTime = findViewById(R.id.etToTime);


        etFromTime.setOnClickListener(v -> {
            showDatePicker(SetTimeActivity.this, etFromTime, false);
        });

        etToTime.setOnClickListener(v -> {
            showDatePicker(SetTimeActivity.this, etToTime, true);
        });

        findViewById(R.id.ok_button).setOnClickListener( v -> {
            if (SharedData.from_date != null && SharedData.to_date != null) {
                startActivity(new Intent(SetTimeActivity.this, MapsActivity.class));
            } else {
                Toast.makeText(this, "Please select dates", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.cancel_button).setOnClickListener( v -> {
            Intent intent = new Intent(SetTimeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SharedData.from_date == null) {
            etFromTime.setText("");
        } else {
            etFromTime.setText(sdf.format(SharedData.from_date));
        }
        if (SharedData.to_date == null) {
            etToTime.setText("");
        } else {
            etToTime.setText(sdf.format(SharedData.to_date));
        }
    }

    private void showDatePicker(Context context, final Button editText, boolean isAfterDate) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                (view, year1, monthOfYear, dayOfMonth1) -> {
                    calendar.set(year1, monthOfYear, dayOfMonth1);
                    editText.setText(sdf.format(calendar.getTime()));

                    if (isAfterDate){
                        calendar.set(Calendar.HOUR_OF_DAY, 23);
                        calendar.set(Calendar.MINUTE, 59);
                        calendar.set(Calendar.SECOND, 59);
                        calendar.set(Calendar.MILLISECOND, 999);
                        SharedData.to_date = calendar.getTime();
                    } else {
                        calendar.set(Calendar.HOUR_OF_DAY, 0);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        SharedData.from_date = calendar.getTime();
                    }
                }, year, month, dayOfMonth);
        if (isAfterDate){
            if(SharedData.from_date == null) {
                Toast.makeText(this, "Please select from date first", Toast.LENGTH_SHORT).show();
                return;
            }
            datePickerDialog.getDatePicker().setMinDate(SharedData.from_date.getTime());
        } else {
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        }
        datePickerDialog.show();
    }
}
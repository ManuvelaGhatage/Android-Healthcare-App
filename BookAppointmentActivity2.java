package com.example.a1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class BookAppointmentActivity2 extends AppCompatActivity {

    EditText ed1, ed2,ed3, ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, book, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment2);

        tv = findViewById(R.id.textView3);
        ed1 = findViewById(R.id.editTextFullNameAppointment);
        ed2 = findViewById(R.id.editTextAddressApp);
        ed3 = findViewById(R.id.editTextContactApp);
        ed4 = findViewById(R.id.editTextConsultantFeesApp);
        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppDate2);
        book = findViewById(R.id.bookAppointment);
        back = findViewById(R.id.bookAppointmentBack);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullName = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullName);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText(fees);

        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity2.this, FindDoctorActivity2.class));
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext(),"healthcare1",null,1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","Manuvela").toString();

//                if(db.checkAppointmentExists(username,title+" => "+fullName,address,contact,dateButton.getText().toString(),timeButton.getText().toString()) == 1){
//                    Toast.makeText(getApplicationContext(),"Appointment already booked",Toast.LENGTH_SHORT).show();
//                }
//                else{
                    db.addOrder(username,title+" => "+fullName,address,contact,"",dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appointment");
                    Toast.makeText(getApplicationContext(),"Appointment done successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointmentActivity2.this, HomeActivity2.class));
                //}


            }
        });





    }

    public void initDatePicker(){
       DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker view, int i, int i1, int i2) {
               i1 = i1 + 1;
               dateButton.setText(i2 + "/" + i1 + "/" + i);
           }};

           Calendar cal = Calendar.getInstance();
           int year = cal.get(Calendar.YEAR);
           int month = cal.get(Calendar.MONTH);
           int day = cal.get(Calendar.DAY_OF_MONTH);

           int style = AlertDialog.THEME_HOLO_DARK;
           datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
           datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }





    public void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int i, int i1) {
               timeButton.setText(i + " " + i1);
            }
        };

        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,min,true);

    }


}
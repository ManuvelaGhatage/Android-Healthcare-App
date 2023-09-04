package com.example.a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {

    Button book;
    EditText name, address, contact, pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        name = findViewById(R.id.fullNameLTB);
        address = findViewById(R.id.addressLTD);
        contact = findViewById(R.id.ContactNoLTB);
        pincode = findViewById(R.id.pinCodeLTB);
        book = findViewById(R.id.bookLTB);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","Manuvela").toString();

              Database db1 = new Database(getApplicationContext(),"healthcare",null,1);
                db1.addOrder(username,name.getText().toString(),address.getText().toString(),contact.getText().toString(),pincode.getText().toString(),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"labTest");
                db1.removeCart(username,"labTest");
                Toast.makeText(getApplicationContext(), "Your booking is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBookActivity.this,HomeActivity2.class));
            }
        });

    }
}
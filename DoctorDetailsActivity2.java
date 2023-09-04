package com.example.a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity2 extends AppCompatActivity {

    private String[][] doctor_details1 = {
            {"Doctor Name : Shruti Bogar", "Hospital Address : Belgum,Karnataka", "Phone number : 1234567890","Experience : 2yrs", "400"},
            {"Doctor Name : Dharanija Nallamothu", "Hospital Address : Vinukonda,AP", "Phone number : 1112131415","Experience : 3yrs", "500"},
            {"Doctor Name : Manuvela Ghatage", "Hospital Address : Ichalkaranji", "Phone number : 1617181920", "Experience : 5yrs", "600"},
            {"Doctor Name : Sneha Patil", "Hospital Address : Mumbai", "Phone number : 2122232425","Experience : 8yrs", "800"},
            {"Doctor Name : Pragati Patil", "Hospital Address : Kolhapur", "Phone number : 2627282930","Experience : 1yrs", "250"}
    };
    private String[][] doctor_details2 = {
            {"Doctor Name : Shruti Bogar", "Hospital Address : Belgum,Karnataka", "Phone number : 1234567890","Experience : 2yrs", "400"},
            {"Doctor Name : Dharanija Nallamothu", "Hospital Address : Vinukonda,AP", "Phone number : 1112131415","Experience : 3yrs", "500"},
            {"Doctor Name : Manuvela Ghatage", "Hospital Address : Ichalkaranji", "Phone number : 1617181920", "Experience : 5yrs", "600"},
            {"Doctor Name : Sneha Patil", "Hospital Address : Mumbai", "Phone number : 2122232425","Experience : 8yrs", "800"},
            {"Doctor Name : Pragati Patil", "Hospital Address : Kolhapur", "Phone number : 2627282930","Experience : 1yrs", "250"}
    };
    private String[][] doctor_details3 = {
            {"Doctor Name : Shruti Bogar", "Hospital Address : Belgum,Karnataka", "Phone number : 1234567890","Experience : 2yrs", "400"},
            {"Doctor Name : Dharanija Nallamothu", "Hospital Address : Vinukonda,AP", "Phone number : 1112131415","Experience : 3yrs", "500"},
            {"Doctor Name : Manuvela Ghatage", "Hospital Address : Ichalkaranji", "Phone number : 1617181920", "Experience : 5yrs", "600"},
            {"Doctor Name : Sneha Patil", "Hospital Address : Mumbai", "Phone number : 2122232425","Experience : 8yrs", "800"},
            {"Doctor Name : Pragati Patil", "Hospital Address : Kolhapur", "Phone number : 2627282930","Experience : 1yrs", "250"}
    };
    private String[][] doctor_details4 = {
            {"Doctor Name : Shruti Bogar", "Hospital Address : Belgum,Karnataka", "Phone number : 1234567890","Experience : 2yrs", "400"},
            {"Doctor Name : Dharanija Nallamothu", "Hospital Address : Vinukonda,AP", "Phone number : 1112131415","Experience : 3yrs", "500"},
            {"Doctor Name : Manuvela Ghatage", "Hospital Address : Ichalkaranji", "Phone number : 1617181920", "Experience : 5yrs", "600"},
            {"Doctor Name : Sneha Patil", "Hospital Address : Mumbai", "Phone number : 2122232425","Experience : 8yrs", "800"},
            {"Doctor Name : Pragati Patil", "Hospital Address : Kolhapur", "Phone number : 2627282930","Experience : 1yrs", "250"}
    };
    private String[][] doctor_details5 = {
            {"Doctor Name : Shruti Bogar", "Hospital Address : Belgum,Karnataka", "Phone number : 1234567890","Experience : 2yrs", "400"},
            {"Doctor Name : Dharanija Nallamothu", "Hospital Address : Vinukonda,AP", "Phone number : 1112131415","Experience : 3yrs", "500"},
            {"Doctor Name : Manuvela Ghatage", "Hospital Address : Ichalkaranji", "Phone number : 1617181920", "Experience : 5yrs", "600"},
            {"Doctor Name : Sneha Patil", "Hospital Address : Mumbai", "Phone number : 2122232425","Experience : 8yrs", "800"},
            {"Doctor Name : Pragati Patil", "Hospital Address : Kolhapur", "Phone number : 2627282930","Experience : 1yrs", "250"}
    };
    TextView tv;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details2);

        tv = findViewById(R.id.DefaultTextDD);
        bt = findViewById(R.id.backDD);
        HashMap<String, String> item;
        String[][] doctor_details = {};

        ArrayList list;
        SimpleAdapter simpleAdapter;
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0)
            doctor_details = doctor_details1;
        else if(title.compareTo("Dietician")==0)
            doctor_details = doctor_details2;
        else if(title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else if(title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else if(title.compareTo("Cardiologists")==0)
            doctor_details = doctor_details5;

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity2.this, FindDoctorActivity2.class));
            }
        });

        list = new ArrayList<>();
        for (int i = 0; i < doctor_details.length; i++) {
            item = new HashMap<>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5",doctor_details[i][4] + "Rs");
            list.add(item);
        }

        simpleAdapter = new SimpleAdapter(this,list,
                R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
        new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        ListView listView = findViewById(R.id.ListViewDD);
        listView.setAdapter(simpleAdapter);

        String[][] finalDoctor_details = doctor_details;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it = new Intent(DoctorDetailsActivity2.this, BookAppointmentActivity2.class);
                it.putExtra("text1",title);
                it.putExtra("text2", finalDoctor_details[i][0]);
                it.putExtra("text3", finalDoctor_details[i][1]);
                it.putExtra("text4", finalDoctor_details[i][3]);
                it.putExtra("text5", finalDoctor_details[i][4]);
                startActivity(it);
            }
        });





    }
}
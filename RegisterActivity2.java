package com.example.a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity2 extends AppCompatActivity {
    EditText usernameReg, emailReg, passReg, confirmPassReg;
    Button signUp;
    TextView tx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        usernameReg = findViewById(R.id.editTextUsernameRegister);
        emailReg = findViewById(R.id.editTextEmailRegister);
        passReg = findViewById(R.id.editTextPasswordRegister);
        confirmPassReg = findViewById(R.id.editTextConfirmPasswordLogin);
        signUp = findViewById(R.id.signup);
        tx = findViewById(R.id.textViewAlreadyHaveAccount);

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity2.this,LoginActivity2.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameReg.getText().toString();
                String email = emailReg.getText().toString();
                String password = passReg.getText().toString();
                String confirmPassword = confirmPassReg.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null ,1);

                if(username.length() == 0 || email.length() == 0 || password.length() == 0 || confirmPassword.length() == 0){
                    Toast.makeText(getApplicationContext(),"Please fill all the details!",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.compareTo(confirmPassword)==0){
                        if(isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(),"You record has been created!!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity2.this,LoginActivity2.class));

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Password must contain at least 8 character, should contain letter digit and 1 special symbol!",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Password and confirm password didn't match!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String password){
        int f1 = 0, f2 = 0, f3 = 0;
        if(password.length() < 8){
            return false;
        }
        else{
            for (int i = 0; i < password.length(); i++) {
                if(Character.isLetter(password.charAt(i))){
                    f1 = 1;
                }
            }

            for (int i = 0; i < password.length(); i++) {
                if(Character.isDigit(password.charAt(i))){
                    f2 = 1;
                }
            }

            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if(c >= 33 && c <= 46 || c==64){
                    f3 = 1;
                }
            }

            if (f1 == 1 && f2 == 1 && f3 == 1){
                return true;
            }

            return false;
        }
    }
}
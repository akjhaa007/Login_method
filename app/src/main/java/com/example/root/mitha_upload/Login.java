package com.example.root.mitha_upload;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.PasswordAuthentication;

public class Login extends AppCompatActivity {

    EditText userid;
    private PasswordAuthentication passwordadmin;
    Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Mithila Upload");


        Login =(Button)findViewById(R.id.login_button);
        Login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         Intent i =new Intent(Login.this,MainActivity.class);
                                         startActivity(i);
                                         Toast.makeText(getApplicationContext(),"You clicked on Login button require",Toast.LENGTH_SHORT).show();
                                     }
                                 }
        );


    }
}

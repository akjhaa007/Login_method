package com.example.root.mitha_upload;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.PasswordAuthentication;

public class Login extends AppCompatActivity  {

    private EditText user_id,user_Password;
    private   Button bLogin;
    private  TextView tv;
    private  int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Mithila Upload");


        user_id=(EditText)findViewById(R.id.user_id);
        user_Password=(EditText)findViewById(R.id.password);

        bLogin=(Button)findViewById(R.id.login_button);

        tv=(TextView) findViewById(R.id.info_attempt);

        tv.setText("No of Attempt Remaining :5");


        bLogin.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {

                                validation(user_id.getText().toString(),user_Password.getText().toString());
                                     }
                                 }
        );


    }

    private  void validation(String Username,String UserPassword){

        if ((Username.equals("Admin"))&&(UserPassword.equals("nahipta"))){

            Intent intent =new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        }

        else {

            counter--;
            tv.setText("No of Login Attempt"+String.valueOf(counter));
        }

        if(counter ==0){
            bLogin.setEnabled(false);
        }
    }
}

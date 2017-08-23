package com.example.root.mitha_upload;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.URI;

import static android.R.attr.bitmap;
import static android.R.attr.path;
import static android.content.Intent.ACTION_PICK;
import static android.provider.MediaStore.Images.Thumbnails.INTERNAL_CONTENT_URI;

public class MainActivity extends AppCompatActivity {

    ImageView Image_v;
    Button btn;
    Uri imgUri;


    Button conf,btnn;
    EditText title, description, dimHeight, dimWidth,gprice;


    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //----------------Spinner Adapter activity-----------------
        final Spinner spinner =(Spinner)findViewById(R.id.getImageType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.image_type, android.R.layout.simple_spinner_item);
               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



               spinner.setAdapter(adapter);



        final Spinner spinner_ava = (Spinner)findViewById(R.id.getAvailability);
        ArrayAdapter<CharSequence> adapter_next = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.availability, android.R.layout.simple_spinner_item);
        adapter_next.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_ava.setAdapter(adapter_next);




/*
        Bitmap bitmap =BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Intent i =new Intent();
        i.setClass(MainActivity.this,Confirm.class);
        i.putExtra("Bitmap",bitmap);
        startActivity(i);
*/


        if (getActionBar() != null) {
            getActionBar().setHomeButtonEnabled(true);
            getActionBar().setDisplayHomeAsUpEnabled(true);

        }

        setTitle("Upload database");
        btn = (Button) findViewById(R.id.pick_image);
        Image_v = (ImageView) findViewById(R.id.image_file);

        conf = (Button) findViewById(R.id.submit_button);

        //-----Edit----------------------Text-------------------
        title = (EditText) findViewById(R.id.get_title);
        description = (EditText) findViewById(R.id.getDescription);
        dimHeight = (EditText) findViewById(R.id.getHeight);
        dimWidth = (EditText) findViewById(R.id.getWidth);
        gprice= (EditText) findViewById(R.id.get_price);
       // gprice.setTag(Integer.valueOf(12));



        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Confirm.class);
                intent.putExtra("title", title.getText().toString());
                intent.putExtra("description", description.getText().toString());
                intent.putExtra("height", dimHeight.getText().toString());
                intent.putExtra("width", dimWidth.getText().toString());


                intent.putExtra("USD", gprice.getText().toString());

                //--------------Spinner
                intent.putExtra("SpinnerValue",spinner.getSelectedItem().toString());
                intent.putExtra("Available",spinner_ava.getSelectedItem().toString());


                intent.setData(imgUri);
              //  intent.putExtra("imagePath",imgUri);

               // intent.setData(imgUri);
                //intent.putExtra("image",imgUri);
                //intent.putExtra("Bitmap",bitmap);
                startActivity(intent);

                //startActivity(intent);

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {

                                       openGallery();
                                   }
                               }
        );




    }



    public void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);

    }



   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){


        super.onActivityResult(resultCode,requestCode,data);
        if (resultCode==RESULT_OK && requestCode==PICK_IMAGE ){

            imgUri=data.getData();
            Image_v.setImageURI(imgUri);




        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

        case android.R.id.home:
            Intent i =new Intent(getApplicationContext(),Login.class);
            Toast.makeText(getApplicationContext(),"Welcome back to login activity",Toast.LENGTH_SHORT).show();
            startActivity(i);

        return true;

        }
        return super.onOptionsItemSelected(item);
    }
}

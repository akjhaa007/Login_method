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
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
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

public class MainActivity extends AppCompatActivity  implements TextWatcher{

    ImageView Image_v;
    Button btn;
    Uri imgUri;
    Boolean isHeight =false;
    Boolean isWidth =false;
    Boolean isAvaialable =false;

    private final String PREFIX="http://";
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

       // dimWidth.addTextChangedListener(this);
      //  dimHeight.addTextChangedListener(this);

        dimWidth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {






                if(!dimWidth.getText().toString().contains("cm") && !isWidth) {


                    dimWidth.setText(dimWidth.getText() + "cm");


                    isWidth = true;
                }
                else if(dimWidth.getText().length() == 0 && isWidth && !dimWidth.getText().toString().contains("cm"))
                {

                    dimWidth.setText(dimWidth.getText() + "cm");


                    isWidth = false;


                }




            }
        });

      //-----------------------------------------------------Height--------------------------------

        dimHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {




                if(!dimHeight.getText().toString().contains("cm") && !isHeight) {


                    dimHeight.setText(dimHeight.getText() + "cm");


                    isHeight = true;
                }
                else if(dimHeight.getText().length() == 0 && isAvaialable && !dimHeight.getText().toString().contains("cm"))
                {

                    dimHeight.setText(dimHeight.getText() + "cm");


                    isHeight = false;


                }



            }
        });





//--------------------------------------set USD-------------------------------------------------------------

        gprice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!gprice.getText().toString().contains("$") && !isAvaialable) {
                    gprice.setText(gprice.getText() + "$");
                    isAvaialable = true;
                }
                else if(gprice.getText().length() == 0 && isAvaialable && !gprice.getText().toString().contains("$")){
                    gprice.setText(gprice.getText() + "$");
                    isAvaialable = false;
                }









            }
        });












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


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {


    }
}

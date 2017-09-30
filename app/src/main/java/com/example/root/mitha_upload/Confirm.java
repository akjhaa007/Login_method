package com.example.root.mitha_upload;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.button;

public class Confirm extends AppCompatActivity {

    Button upload;

    TextView showtitle,showdescription,showHeight,showWidth,showPrice;

    ImageView imageview;

    Uri imgUri,setImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    // Bitmap bitmap =(Bitmap) this.getIntent().getParcelableExtra("Bitmap");

        setContentView(R.layout.activity_confirm);


        Bundle extra =getIntent().getExtras();
                String transportItemChose = extra.getString("SpinnerValue");
                TextView test =(TextView)findViewById(R.id.showimagetype);
                test.setText(transportItemChose);



        String transportItemChosen = extra.getString("Available");
        TextView test2 =(TextView)findViewById(R.id.show_available_status);
        test2.setText(transportItemChosen);

//---------------------home button-----------------

        if (getActionBar()!=null) {


            getActionBar().setHomeButtonEnabled(true);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

 //------------------------------------------------------------------------------------------------

         imageview=(ImageView)findViewById(R.id.imageconfa) ;
         //imageview.setImageURI(Uri.parse(imgUri));

        imageview.setImageURI(getIntent().getData());


      //  String imagePath = getIntent().getStringExtra("imagePath");
        //-----------------------------------------------------------------------------------------

        showtitle=(TextView)findViewById(R.id.titleView);
        showtitle.setText(getIntent().getExtras().getString("title"));

        showdescription=(TextView)findViewById(R.id.description);
        showdescription.setText(getIntent().getExtras().getString("description"));

        showHeight=(TextView)findViewById(R.id.dimheight);
        showHeight.setText(getIntent().getExtras().getString("height"));

        showWidth=(TextView)findViewById(R.id.dimwidth);
        showWidth.setText(getIntent().getExtras().getString("width"));
        showPrice=(TextView)findViewById(R.id.showprice);
        showPrice.setText(getIntent().getExtras().getString("USD"));


       // showPrice.setTag(Integer.valueOf(1));

        upload =(Button)findViewById(R.id.upload_confirm);





        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Uploading proceed to the data base",Toast.LENGTH_LONG).show();
            }
        });


    }












    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:

                Intent i =new Intent(getApplicationContext(),MainActivity.class);
                Toast.makeText(this, "Update Detail of you data base carefully", Toast.LENGTH_SHORT).show();
                startActivity(i);




                return true;

        }
        return super.onOptionsItemSelected(item);
    }



}

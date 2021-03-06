package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextEmailMessage;
    private Button buttonSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmailMessage = findViewById(R.id.editText_mail);
        buttonSend = findViewById(R.id.button_send);

        //initialise an empty intent
        final Intent sendItent = new Intent();
        final Intent pickIntent = new Intent();

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Chose action, in this case send something through an implicit intent
                sendItent.setAction(Intent.ACTION_SEND);

                //Chose action, in this case we want to pick something
                pickIntent.setAction(Intent.ACTION_PICK);

                pickIntent.addCategory(Intent.CATEGORY_APP_GALLERY);

                //Put the message, we used Intent.EXTRA_TEXT which is an in-build constant, but we can create our
                sendItent.putExtra(Intent.EXTRA_TEXT,editTextEmailMessage.getText().toString());

                //Tell intent which type of data you are going to send
               sendItent.setType("text/plain");

               //We choose image type
                pickIntent.setType("image/jpeg");


                //check if we have any apps that can handle our intent
         /*       if(sendItent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(Intent.createChooser(sendItent,"Send email"));
                }

         */
                  if(pickIntent.resolveActivity(getPackageManager()) != null)
                  {
                      startActivity(Intent.createChooser(pickIntent,"Choose photo.."));
                  }else {
                      Log.d("message","No apps to pick");
                  }
            }
        });


    }
}
package com.example.wydarzenia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PlaygroundActivity extends AppCompatActivity {
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);

        image = findViewById(R.id.image_test);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/eventapp-ad86e.appspot.com/o/eventid1.PNG?alt=media&token=61370b69-f4ea-4faa-b9cd-fc24af2c6747").into(image);


    }
}

package com.example.maratmamin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int id = getIntent().getIntExtra("id", -1);

        if (id >= 0) {
            String [] description = LocationSQLiteOpenHelper.getInstance(DetailActivity.this).getDescriptionById(id);
//            String locationName = description[0];

//            ImageView imageView = (ImageView) findViewById(R.id.image_view);
//            imageView.setImageResource(getDrawableValue(locationName));

            TextView textView = (TextView) findViewById(R.id.description);
            textView.setText(description[0]);

            TextView textView2 = (TextView) findViewById(R.id.price);
            textView2.setText(description[1]);

            TextView textView3 = (TextView) findViewById(R.id.type);
            textView3.setText(description[2]);

        }

    }

//    private int getDrawableValue(String icon){
//        switch(icon){
//            case "search":
//                return android.R.drawable.ic_menu_search;
//            case "add":
//                return android.R.drawable.ic_menu_add;
//            case "upload":
//                return android.R.drawable.ic_menu_upload;
//            case "play":
//                return android.R.drawable.ic_media_play;
//            default:
//                return 0;
//        }
//    }
}

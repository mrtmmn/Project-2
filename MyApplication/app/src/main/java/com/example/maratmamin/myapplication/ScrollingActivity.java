package com.example.maratmamin.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {
    private LocationSQLiteOpenHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mHelper = LocationSQLiteOpenHelper.getInstance(ScrollingActivity.this);

        final int id = getIntent().getIntExtra("id", -1);

        if (id >= 0) {
            String [] description = LocationSQLiteOpenHelper.getInstance(ScrollingActivity.this).getDescriptionById(id);
            //getFavoritedById
//            String locationName = description[0];

//            ImageView imageView = (ImageView) findViewById(R.id.image_view);
//            imageView.setImageResource(getDrawableValue(locationName));

//            final CheckBox favoriteButton = (CheckBox) findViewById(R.id.fav_button);
//            favoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (favoriteButton.isChecked()) {
//                       favoriteButton.setChecked(true);
//                        mHelper.updateFavorite(id, 1);
//                    }
//                }
//            });

            TextView textView = (TextView) findViewById(R.id.cool_textview);
            textView.setText(description[0]);
//
//            TextView textView2 = (TextView) findViewById(R.id.price);
//            textView2.setText(description[1]);
//
//            TextView textView3 = (TextView) findViewById(R.id.type);
//            textView3.setText(description[2]);

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


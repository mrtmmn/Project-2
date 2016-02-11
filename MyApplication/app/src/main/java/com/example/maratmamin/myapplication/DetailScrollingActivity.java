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

public class DetailScrollingActivity extends AppCompatActivity {
    private LocationSQLiteOpenHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mHelper = LocationSQLiteOpenHelper.getInstance(DetailScrollingActivity.this);

        final int id = getIntent().getIntExtra("id", -1);

        if (id >= 0) {
            String[] description = mHelper.getDescriptionById(id);
            int details = mHelper.checkFavoriteById(id);
            //getFavoritedById
//            String locationName = description[0];

//            ImageView imageView = (ImageView) findViewById(R.id.image_view);
//            imageView.setImageResource(getDrawableValue(locationName));

            final CheckBox favoriteButton = (CheckBox) findViewById(R.id.fav_button);

            if (details == 1) {

                favoriteButton.setChecked(true);
            }

            favoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (favoriteButton.isChecked()) {
                        favoriteButton.setChecked(true);
                        mHelper.updateAddFavorite(id, 1);
                    } else if (!favoriteButton.isChecked()) {
                        favoriteButton.setChecked(false);
                        mHelper.updateRemoveFavorite(id, 0);
                    }
                }
            });

            TextView textView = (TextView) findViewById(R.id.cool_textview);
            textView.setText(description[0]);

            TextView textView2 = (TextView) findViewById(R.id.nice_tv);
            textView2.setText(description[1]);

            TextView textView3 = (TextView) findViewById(R.id.good_tv);
            textView3.setText(description[2]);

        }

    }
}

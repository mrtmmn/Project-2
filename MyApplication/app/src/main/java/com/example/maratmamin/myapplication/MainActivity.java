package com.example.maratmamin.myapplication;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private ListView mSearchListView;
    private LocationSQLiteOpenHelper mHelper;
    private Cursor cursor;
    private CursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchListView = (ListView) findViewById(R.id.location_list_view);
        mHelper = LocationSQLiteOpenHelper.getInstance(this);


        cursor = mHelper.getLocationList();

        cursorAdapter = new CursorAdapter(MainActivity.this, cursor, 0) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.icon_list_item, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                ImageView imageView = (ImageView) view.findViewById(R.id.location_image_view);
                TextView textView = (TextView) view.findViewById(R.id.location_name_text_view);
                String locationName = cursor.getString(cursor.getColumnIndex("LOCATION_NAME"));

                imageView.setImageResource(getDrawableValue(locationName));
                textView.setText((locationName));
            }
        };

        mSearchListView.setAdapter(cursorAdapter);

      handleIntent(getIntent());

        mSearchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailScrollingActivity.class);
                cursorAdapter.getCursor().moveToPosition(position);
                //use cursorAdapter.getCursor() so that adapter always knows what its looking at
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndex(LocationSQLiteOpenHelper.COL_ID)));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        searchView.setSearchableInfo(searchableInfo);


        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor cursor = mHelper.searchLocationList(query);
            cursorAdapter.swapCursor(cursor);
            cursorAdapter.notifyDataSetChanged();
        }
    }

//      NOW JUST TO FIGURE OUT HOW TO STORE THE PICTURE

    private int getDrawableValue(String icon) {
        switch (icon) {
            case "Central Park":
                return R.drawable.central_park;
            case "Bronx Zoo":
                return R.drawable.bronx_zoo;
            case "Botanical Gardens":
                return R.drawable.botanical_gardens;
            case "Madison Square Garden":
                return R.drawable.madison_square_garden;
            case "Grand Central":
                return R.drawable.grand_central;
            default:
                return 0;
        }
    }
}


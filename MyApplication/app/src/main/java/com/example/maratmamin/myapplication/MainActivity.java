package com.example.maratmamin.myapplication;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView mSearchListView;
    private CursorAdapter mCursorAdapter;
    private LocationSQLiteOpenHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchListView = (ListView) findViewById(R.id.location_list_view);
        mHelper = LocationSQLiteOpenHelper.getInstance(MainActivity.this);

        final Cursor cursor = mHelper.getLocationList();

        mCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,
                new String[] {LocationSQLiteOpenHelper.COL_LOCATION_NAME}, new int []{android.R.id.text1}, 0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}

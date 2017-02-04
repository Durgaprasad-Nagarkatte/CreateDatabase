package com.dpcompany.historicalevents;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            SQLiteDatabase HistoricalEvents = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);
            HistoricalEvents.execSQL("CREATE TABLE IF NOT EXISTS events(name VARCHAR, year INT(4))");
            HistoricalEvents.execSQL("INSERT INTO events(name, year) VALUES('Quit India Movement', 1942)");
            HistoricalEvents.execSQL("INSERT INTO events(name, year) VALUES('Indian Independence', 1947)");
            HistoricalEvents.execSQL("INSERT INTO events(name, year) VALUES('Republic Day', 1950)");
            Cursor c = HistoricalEvents.rawQuery("SELECT * FROM events", null);
            int nameIndex = c.getColumnIndex("name");
            int yearIndex = c.getColumnIndex("year");
            c.moveToFirst();
            while(c != null){
                Log.i("name", c.getString(nameIndex));
                Log.i("year", Integer.toString(c.getInt(yearIndex)));
                c.moveToNext();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

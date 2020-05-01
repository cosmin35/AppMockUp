package uk.ac.wlv.nhs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class roombooking extends AppCompatActivity {

    SQLiteDatabase SQLDB;
    DatabaseHelper db;
    Cursor cursor;

    private static final String TAG = "MainActivity";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Bitmap> mImages = new ArrayList<android.graphics.Bitmap>();
    private ArrayList<Integer> mCap = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roombooking);
        Log.d(TAG, "onCreate: started");

        initImageBitmaps();

    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: Preparing Bitmaps");

        mImages.add(null);
        mNames.add("Room1");
        mCap.add(90);

        mImages.add(null);
        mNames.add("Room2");
        mCap.add(10);

        mImages.add(null);
        mNames.add("Room3");
        mCap.add(50);

        mImages.add(null);
        mNames.add("Room4");
        mCap.add(25);

        mImages.add(null);
        mNames.add("Room5");
        mCap.add(15);

        mImages.add(null);
        mNames.add("Room6");
        mCap.add(75);

        mImages.add(null);
        mNames.add("Room7");
        mCap.add(120);

        /*db = new DatabaseHelper(getApplicationContext());
        SQLDB = db.getReadableDatabase();
        cursor = db.getRoomInfo(SQLDB);
        if(cursor.moveToFirst())
        {
            do{
                mNames.add(cursor.getString(1));
                mCap.add(cursor.getInt(2));
                //byte[] imgByte = cursor.getBlob(3);
                //mImages.add(BitmapFactory.decodeByteArray(imgByte,0,imgByte.length));
            }
            while(cursor.moveToNext());
        }*/

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: Recycler Initiating");

        RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames,mImages,mCap,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

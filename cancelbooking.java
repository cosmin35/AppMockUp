package uk.ac.wlv.nhs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class cancelbooking extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();
    private ArrayList<Integer> mCap = new ArrayList<>();
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelbooking);
        textView = findViewById(R.id.txt_arrayListData);

        try {
            ArrayList<SavedRooms> savedRooms = (ArrayList<SavedRooms>) getIntent().getSerializableExtra("savedRoomArrayList");  //TRIED TO GET THE ARRAYLIST OF SAVEDROOMS FROM THE ROOMDISPLAY ACTIVITY
            String string = savedRooms.toString();

            textView.setText(string);
        } catch (Exception e) {

        }
    }

    private void InitiateDetails() {

        mImages.add(null);
        mNames.add("Booking 1");
        mCap.add(null);

        mImages.add(null);
        mNames.add("Booking 1");
        mCap.add(null);


        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: Recycler Initiating");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, mImages, mCap, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

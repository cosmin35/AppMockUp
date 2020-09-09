package uk.ac.wlv.nhs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RoomDisplay extends AppCompatActivity {

    private static final String TAG = "RoomDisplay";
    int[] roomImages = {R.drawable.imgroom1, R.drawable.imgroom2, R.drawable.imgroom3, R.drawable.imgroom4, R.drawable.imgroom5, R.drawable.imgroom6, R.drawable.imgroom7};

    private ArrayList<SavedRooms> savedRooms = new ArrayList<>(); //arraylist of SavedRooms object;i tried to send all the data from this arrayList to the CancelBooking activity

    TextView textTv;  //initialized text views and imageviews
    TextView textSeats;
    TextView textProj;
    TextView textTab;
    TextView textCoffee;
    ImageView roomImage;
    TextView parkingSpots;
    Button button;

//...


    SQLiteDatabase SQLDB;
    DatabaseHelper db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_display);
        Log.d(TAG, "onCreate: Room Display Started");


        textTv = findViewById(R.id.txtTvAnsw);  //initialized the views with the given id
        textSeats = findViewById(R.id.txtSeatsAnsw);
        textProj = findViewById(R.id.txtProjAnsw);
        textTab = findViewById(R.id.txtTabAnsw);
        textCoffee = findViewById(R.id.txtCoffeeAnsw);
        roomImage = findViewById(R.id.imgwtf);
        parkingSpots = findViewById(R.id.txtParkingSpacesAnsw);
        button = findViewById(R.id.btn_confirmButton);//...


        getIncomingIntent();
        setOnClickListener();
    }

    private void getIncomingIntent() {

        Log.d(TAG, "getIncomingIntent: checking for incoming intent");

        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")) {
            Log.d(TAG, "getIncomingIntent: found intets extra");
            Bundle extras = getIntent().getExtras();   //initilized a bundle to get the data from the activity
            final int position = extras.getInt("position");    //getting the position
            roomImage.setImageResource(roomImages[position]); //setting the image of the selected room
            textSeats.setText(getIntent().getStringExtra("text_seats")); //setting the answer for the seats textview
            textCoffee.setText(getIntent().getStringExtra("text_coffee")); //...
            textTab.setText(getIntent().getStringExtra("text_tab"));
            textProj.setText(getIntent().getStringExtra("text_proj"));
            textTv.setText(getIntent().getStringExtra("text_tv"));
            parkingSpots.setText(getIntent().getStringExtra("text_parking"));//...

            String imageUrl = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("image_name");

            setPage(imageUrl, imageName);
        }
    }

    private void setPage(String imageUrl, String imageName) {
        Log.d(TAG, "setImage: setting image and name");

        TextView name = findViewById(R.id.RoomName);
        name.setText(imageName);

       /* ImageView image = findViewById(R.id.imageRoomBook1);  //deleted this line of code because it was overlapping the imageview that i created and it was producing null poitner exception
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);*/
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void confirmBooking(View view) {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String today = dateFormat.format(date);

        TextView textView = findViewById(R.id.RoomName);
        String name = (String) textView.getText();

        db.addBooking(db.getRoomId(name), today);

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void setOnClickListener(){ //created new button in the xml file and then set a newClickListener on it to pass the data to another activity
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle extras = getIntent().getExtras();   //initilized a bundle to get the data
                final int position = extras.getInt("position");
                savedRooms.add(new SavedRooms(textTv.getText().toString()
                        , textSeats.getText().toString()
                        , textProj.getText().toString()
                        , textTab.getText().toString()
                        , textCoffee.getText().toString()
                        , roomImages[position]
                        , parkingSpots.getText().toString()));

                Intent intent=new Intent(RoomDisplay.this,cancelbooking.class);
                intent.putExtra("savedRoomArrayList",savedRooms);


                Toast.makeText(RoomDisplay.this, "Room details added.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

package uk.ac.wlv.nhs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class BookingDisplay extends AppCompatActivity {

    private static final String TAG = "BookingDisplay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_display);
        Log.d(TAG, "onCreate: Room Display Started");


        getIncomingIntent();

    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        if(getIntent().hasExtra("image_url")&&getIntent().hasExtra("image_name")){
            Log.d(TAG, "getIncomingIntent: found intets extra");

            String image = getIntent().getStringExtra("image");
            String Name = getIntent().getStringExtra("name");

            setPage(image,Name);


        }
    }

    private void setPage(String imageUrl, String imageName){
        Log.d(TAG, "setImage: setting image and name");

        TextView name = findViewById(R.id.RoomName);
        name.setText(imageName);

        ImageView image = findViewById(R.id.imageRoomBook1);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);

    }

    public void confirm(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}

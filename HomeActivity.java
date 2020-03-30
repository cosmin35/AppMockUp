package uk.ac.wlv.nhs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button bookingroom, cancelbooking ,Changebooking ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bookingroom = (Button) findViewById(R.id.bookingroom);
        cancelbooking = (Button) findViewById(R.id.cancelbooking);
        Changebooking = (Button)findViewById(R.id.changebooking);

        bookingroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(HomeActivity.this, roombooking.class);
                startActivity(registerIntent);
            }
        });
        cancelbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(HomeActivity.this, roombooking.class);
                startActivity(registerIntent);
            }
        });


        Changebooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(HomeActivity.this, roombooking.class);
                startActivity(registerIntent);
            }
        });
    }
}

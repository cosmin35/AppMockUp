package uk.ac.wlv.nhs;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>(); //modified the arraylist to be an array of integers
    private ArrayList<Integer> mCap = new ArrayList<>();
    private Context mContext;

    private int[] roomImages = {R.drawable.imgroom1, R.drawable.imgroom2, R.drawable.imgroom3, R.drawable.imgroom4, R.drawable.imgroom5, R.drawable.imgroom6, R.drawable.imgroom7}; //list with all the images

    private String[] tvScreen = {"Yes", "No", "No", "Yes", "No", "Yes", "No"}; //list with facilities answers
    private String[] seatsAvailable = {"8", "20", "11", "9", "10", "8", "16"}; //...
    private String[] projector = {"Yes", "Yes", "No", "No", "Yes", "No", "No"}; //...
    private String[] tablets = {"Yes - 8", "No", "No", "Yes - 9", "No", "No", "No"}; //...
    private String[] coffeeMachine = {"Yes", "No", "No", "Yes", "No", "No", "Yes"};
    private String[] parkingSpots = {"10", "21", "30", "18", "16", "28", "30"};//...

    public RecyclerViewAdapter(ArrayList<String> imageName, ArrayList<Integer> images, ArrayList<Integer> cap, Context context) {
        mImageNames = imageName;
        mImages = images;
        mContext = context;
        mCap = cap;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.roomlist, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.imageName.setText(mImageNames.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));

                Intent intent = new Intent(mContext, RoomDisplay.class);
                intent.putExtra("position", position);  //added the position of the selected room into a bundle
                intent.putExtra("room_img", roomImages[position]); //added the image of the selected room in bundle
                intent.putExtra("text_tv", tvScreen[position]); //added the String answer for the TV
                intent.putExtra("text_seats", seatsAvailable[position]); //...
                intent.putExtra("text_proj", projector[position]);//...
                intent.putExtra("text_tab", tablets[position]);//...
                intent.putExtra("text_coffee", coffeeMachine[position]);
                intent.putExtra("text_parking",parkingSpots[position]);//...
                intent.putExtra("image_url", mImages.get(position));
                intent.putExtra("image_name", mImageNames.get(position));

                intent.putExtra("Cap", mCap.get(position));

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        AppCompatImageView image;
        TextView imageName;
        RelativeLayout parentLayout;
        TextView bookText;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageRoomBook1);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            bookText = itemView.findViewById(R.id.book);
        }

    }


}

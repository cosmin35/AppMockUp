package uk.ac.wlv.nhs;

import java.io.Serializable;

public class SavedRooms implements Serializable {
    String textTV;
    String textSeats;
    String textProj;
    String textTab;
    String textCoffee;
    int imgRoomImage;
    String parkingSpots;

    //CONSTRUCTOR
    public SavedRooms(String textTV, String textSeats, String textProj, String textTab, String textCoffee, int imgRoomImage, String parkingSpots) {
        this.textTV = textTV;
        this.textSeats = textSeats;
        this.textProj = textProj;
        this.textTab = textTab;
        this.textCoffee = textCoffee;
        this.imgRoomImage = imgRoomImage;
        this.parkingSpots = parkingSpots;
    }

    public String getTextTV() {
        return textTV;
    }

    public String getTextSeats() {
        return textSeats;
    }

    public String getTextProj() {
        return textProj;
    }

    public String getTextTab() {
        return textTab;
    }

    public String getTextCoffee() {
        return textCoffee;
    }

    public int getImgRoomImage() {
        return imgRoomImage;
    }

    public String getParkingSpots() {
        return parkingSpots;
    }

}

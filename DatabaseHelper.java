package uk.ac.wlv.nhs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="register.db";
    public static final String TABLE_NAME ="registeruser";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";

    public static final String ROOM_TABLE_NAME = "registerroom";
    public static final String ROOM_COL_1 = "ID";
    public static final String ROOM_COL_2 = "name";
    public static final String ROOM_COL_3 = "capacity";
    public static final String ROOM_COL_4 = "image";

    public static final String BOOKING_TABLE_NAME = "bookings";
    public static final String BOOK_COL_1 = "ID";
    public static final String BOOK_COL_2 = "roomID";
    public static final String BOOK_COL_3 = "date";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE registerroom (ID INTEGER PRIMARY  KEY AUTOINCREMENT, name TEXT, capacity INTEGER)");// add image BITMAP
        sqLiteDatabase.execSQL("CREATE TABLE bookings (ID INTEGER PRIMARY KEY AUTOINCREMENT, roomID INTEGER, date INTEGER)");

        //addRoom("Room_1", 90);
        //addRoom("Room_2", 10);
        //addRoom("Room_3", 50);
        //addRoom("Room_4", 25);
        //addRoom("Room_5", 75);
        //addRoom("Room_6", 120);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + ROOM_TABLE_NAME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + BOOKING_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res = db.insert("registeruser",null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

    public long addRoom(String name, int capacity){
        SQLiteDatabase db = this.getWritableDatabase();
        //byte[] data = getBitmapAsByteArray(img);
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("capacity",capacity);
        //cv.put("image",data);
        long res = db.insert("registerroom",null,cv);
        db.close();
        return res;
    }

    public boolean checkRoom(String name, int capacity){
        String[] columns = {ROOM_COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = ROOM_COL_2+"=?"+" and "+ ROOM_COL_3+"=?";//add +" and "+ROOM_COL_4+"=?"
        String[] selectionArgs = {name, String.valueOf(capacity)};
        Cursor cursor = db.query(ROOM_TABLE_NAME, columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;
    }

    public Cursor getRoomInfo(SQLiteDatabase db){
        Cursor cursor;
        String[] projections = {ROOM_COL_1,ROOM_COL_2,ROOM_COL_3,ROOM_COL_4};

        cursor=db.query(ROOM_TABLE_NAME,projections,null,null,null,null,null);
        return(cursor);
    }

    public int getRoomId(String name){
        String[] columns = {ROOM_COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = ROOM_COL_2+"=?"+" and "+ ROOM_COL_3+"=?";//add +" and "+ROOM_COL_4+"=?"
        String[] selectionArgs = {name};
        Cursor cursor = db.query(ROOM_TABLE_NAME, columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();

        int RoomId = cursor.getColumnIndex(ROOM_COL_1);

        cursor.close();
        db.close();

        return(RoomId);
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public long addBooking(int roomID, String date){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("roomID",roomID);
        contentValues.put("date",date);
        long res = db.insert("bookings",null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkBooking(int roomID, String date){
        String[] columns = {BOOK_COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = BOOK_COL_2+"=?"+" and "+ BOOK_COL_3+"=?";//add +" and "+ROOM_COL_4+"=?"
        String[] selectionArgs = {String.valueOf(roomID), String.valueOf(date)};
        Cursor cursor = db.query(BOOKING_TABLE_NAME, columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;
    }

}
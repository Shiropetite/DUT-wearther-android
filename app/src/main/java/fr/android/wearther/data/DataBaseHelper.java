package fr.android.wearther.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.IDN;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String COLUMN_ID = "ID";

    private static final String USER_TABLE = "USER";
    private static final String COLUMN_USER_LOGIN = "LOGIN";
    private static final String COLUMN_USER_PASSWORD = "PASSWORD";

    private static final String CLOTH_TABLE = "CLOTH";
    private static final String COLUMN_CLOTH_NAME = "NAME";
    private static final String COLUMN_CLOTH_BODYPART = "BODYPART";
    private static final String COLUMN_CLOTH_MINTEMPERATURE = "MIN_TEMPERATURE";
    private static final String COLUMN_CLOTH_MAXTEMPERATURE = "MAX_TEMPERATURE";
    private static final String COLUMN_CLOTH_ID_USER = "ID_USER";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "wearther.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUser = "CREATE TABLE " + USER_TABLE
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_LOGIN + " TEXT, "
                + COLUMN_USER_PASSWORD + " TEXT"
                + ")";

        String createTableCloth = "CREATE TABLE " + CLOTH_TABLE
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CLOTH_NAME + " TEXT, "
                + COLUMN_CLOTH_BODYPART + " TEXT, "
                + COLUMN_CLOTH_MINTEMPERATURE + " DECIMAL, "
                + COLUMN_CLOTH_MAXTEMPERATURE + " DECIMAL, "
                + COLUMN_CLOTH_ID_USER + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_CLOTH_ID_USER + ") REFERENCES " + USER_TABLE + "(" + COLUMN_ID + ")"
                + ")";

        db.execSQL(createTableUser);
        db.execSQL(createTableCloth);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv_user = new ContentValues();

        cv_user.put(COLUMN_USER_LOGIN,user.getLogin());
        cv_user.put(COLUMN_USER_PASSWORD,user.getPassword());
        long insert = db.insert(USER_TABLE, null , cv_user);

        // On recupere l'id généré
        User user_db = getUser(user.getLogin(),user.getPassword());

        for(Cloth cloth : user.getClothes()) {
            ContentValues cv_cloth = new ContentValues();

            cv_cloth.put(COLUMN_CLOTH_NAME,cloth.getName());
            cv_cloth.put(COLUMN_CLOTH_BODYPART,cloth.getBodyPart().toString());
            cv_cloth.put(COLUMN_CLOTH_MINTEMPERATURE,cloth.getMinTemperature());
            cv_cloth.put(COLUMN_CLOTH_MAXTEMPERATURE,cloth.getMaxTemperature());
            cv_cloth.put(COLUMN_CLOTH_ID_USER,user_db.getId());

            if(insert != -1)
                insert = db.insert(CLOTH_TABLE, null , cv_cloth);
        }


        if(insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public User getUser(String login, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + USER_TABLE
                + " WHERE " + COLUMN_USER_LOGIN + " = '" + login
                + "' AND " + COLUMN_USER_PASSWORD + " = '" + password + "'";

        Cursor cursor = db.rawQuery(queryString,null);
        User user_db = null;

        if(cursor.moveToFirst()) {
            do {
                int userID = cursor.getInt(0);
                String userLogin = cursor.getString(1);
                String userPassword = cursor.getString(2);

                user_db = new User(userID,userLogin,userPassword);
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        return user_db;
    }
}

package com.example.tp4.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLHelper extends SQLiteOpenHelper {
    public static final String TABLE_USERS = "clients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOMCOMPLET = "nom_complet";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ADRESSE = "adresse";
    public static final String COLUMN_TELEPHONE = "telephone";
    private static final String DATABASE_NAME = "clients.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE = "create table "
            + TABLE_USERS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NOMCOMPLET + " text , "+ COLUMN_LOGIN + " text , "
            + COLUMN_EMAIL + " text, "+ COLUMN_ADRESSE + " text , " + COLUMN_TELEPHONE + " text , "
            + COLUMN_PASSWORD + " text);";

    public SQLHelper (Context context){
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLHelper.class.getName(),
                "Mise à niveau de la base de données à partir de la version  " + oldVersion + " à "
                        + newVersion + ", qui détruira toutes les anciennes données");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
}

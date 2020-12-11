package com.example.tp4.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserDataSource {

    private SQLiteDatabase database;
    private SQLHelper dbHelper;
    private String[] allColumns = { SQLHelper.COLUMN_ID,
            SQLHelper.COLUMN_NOMCOMPLET, SQLHelper.COLUMN_LOGIN, SQLHelper.COLUMN_EMAIL , SQLHelper.COLUMN_PASSWORD, SQLHelper.COLUMN_ADRESSE, SQLHelper.COLUMN_TELEPHONE };
    public UserDataSource(Context context){
        dbHelper = new SQLHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public User createUser(User user) {
        ContentValues values = new ContentValues();
        values.put(SQLHelper.COLUMN_NOMCOMPLET, user.getNomComplet());
        values.put(SQLHelper.COLUMN_LOGIN, user.getLogin());
        values.put(SQLHelper.COLUMN_EMAIL, user.getEmail());
        values.put(SQLHelper.COLUMN_PASSWORD, user.getPassword());
        values.put(SQLHelper.COLUMN_ADRESSE, user.getAdresse());
        values.put(SQLHelper.COLUMN_TELEPHONE, user.getTelephone());

        long insertId = database.insert(SQLHelper.TABLE_USERS, null,
                values);
        Cursor cursor = database.query(SQLHelper.TABLE_USERS,
                allColumns, SQLHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    public void deleteUser(User user) {
        long id = user.getId();
        System.out.println("utilisateur supprimé avec identifiant: " + id);
        database.delete(SQLHelper.TABLE_USERS, SQLHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(SQLHelper.TABLE_USERS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return users;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(0));
        user.setNomComplet(cursor.getString(1));
        user.setLogin(cursor.getString(2));
        user.setEmail(cursor.getString(3));
        user.setPassword(cursor.getString(4));
        user.setAdresse(cursor.getString(5));
        user.setTelephone(cursor.getString(6));

        return user;
    }



}


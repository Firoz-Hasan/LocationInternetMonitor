package com.example.monitorLocationInternet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "monitorManager";
    private static final String TABLE_MONITOR = "monitor";
    private static final String KEY_EMP_ID = "id";
    private static final String KEY_MONITOR_NAME = "monitorname";
    private static final String KEY_MONITOR_STATUS = "monitorstatus";
    private static final String KEY_MONITOR_DATE = "monitordate";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MONITOR_TABLE = "CREATE TABLE " + TABLE_MONITOR + "("
                + KEY_EMP_ID + " INTEGER PRIMARY KEY," + KEY_MONITOR_NAME + " TEXT,"
                + KEY_MONITOR_DATE + " TEXT,"
                +KEY_MONITOR_STATUS + " TEXT" + ")";
        db.execSQL(CREATE_MONITOR_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONITOR);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void addMonitor(MonitorGpsInternet contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MONITOR_NAME, contact.get_monitorName()); // Contact Name
        values.put(KEY_MONITOR_STATUS, contact.get_monitorStatus()); // Contact Phone
        values.put(KEY_MONITOR_DATE, contact.get_dateTime());


        // Inserting Row
        db.insert(TABLE_MONITOR, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    MonitorGpsInternet getMonitor(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MONITOR, new String[] { KEY_EMP_ID,
                        KEY_MONITOR_NAME, KEY_MONITOR_STATUS, KEY_MONITOR_DATE }, KEY_EMP_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        MonitorGpsInternet contact =
                new MonitorGpsInternet(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2)
                , cursor.getString(3));
        // return contact
        return contact;
    }

    // code to get all contacts in a list view
    public List<MonitorGpsInternet> getAllContacts() {
        List<MonitorGpsInternet> contactList = new ArrayList<MonitorGpsInternet>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MONITOR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MonitorGpsInternet contact = new MonitorGpsInternet();
                contact.set_empId(Integer.parseInt(cursor.getString(0)));
                contact.set_monitorName(cursor.getString(1));
                contact.set_monitorStatus(cursor.getString(2));
                contact.set_dateTime(cursor.getString(3));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

/*    // code to update the single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }*/

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MONITOR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}

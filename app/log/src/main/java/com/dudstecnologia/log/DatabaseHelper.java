package com.dudstecnologia.log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    private static final String DB_NAME = "dvclog";

    private static final String TB_LOGS = "tb_logs";

    private static final String COL_ID = "id";
    private static final String COL_EVENT = "event";
    private static final String COL_BATERY = "batery";
    private static final String COL_CHARGE = "charge";
    private static final String COL_NETWORK = "network";
    private static final String COL_ISWIFI = "isWIFI";
    private static final String COL_DATE = "date";

    private static final String SCRIPT_TB_LOGS = "CREATE TABLE " + TB_LOGS
            + " (" + COL_ID + " INTEGER PRIMARY KEY,"+ COL_EVENT + " TEXT, "
            + COL_BATERY + " TEXT, " + COL_CHARGE + " TEXT, "
            + COL_NETWORK + " TEXT, " + COL_ISWIFI + " TEXT, "
            + COL_DATE + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT_TB_LOGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_LOGS);

        onCreate(db);
    }

    public long saveLog(LogModel logModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_EVENT, logModel.getEvent());
        values.put(COL_BATERY, logModel.getBatery());
        values.put(COL_CHARGE, logModel.getCharge());
        values.put(COL_NETWORK, logModel.getNetwork());
        values.put(COL_ISWIFI, logModel.getIsWifi());
        values.put(COL_DATE, logModel.getDate());

        long insert = db.insert(TB_LOGS, null, values);
        closeDB();

        return insert;
    }

    public LogModel getLog(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TB_LOGS, new String[] { COL_ID, COL_EVENT, COL_BATERY, COL_CHARGE, COL_NETWORK, COL_ISWIFI, COL_DATE }, COL_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        LogModel logModel = new LogModel();

        if (cursor != null) {
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();

                logModel = new LogModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
            }
        }

        closeDB();

        return logModel;
    }

    public void deleteLog(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TB_LOGS, COL_ID + " = ?",
                new String[] { String.valueOf(id) });

        closeDB();
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}

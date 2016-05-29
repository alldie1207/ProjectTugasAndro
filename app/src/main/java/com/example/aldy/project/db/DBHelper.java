package com.example.aldy.project.db;

/**
 * Created by Aldy on 5/24/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

    public static final String TABLE_ANIME = "tbl_anime";
    public static final String COLUMN_ID_ANIME = "id_anime";
    public static final String COLUMN_ANIME = "nama_anime";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_SUMBER = "sumber";

    public static final String TABLE_GENRE = "tbl_genre";
    public static final String COLUMN_ID_GENRE = "id_genre";
    public static final String COLUMN_GENRE = "nama_genre";

    public static final String TABLE_LOGIN = "login";
    public static final String COLUMN_ID_USER = "id_user";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_ALAMAT = "alamat";
    public static final String COLUMN_EMAIL = "email";

    private static final String db_name ="mydb";
    private static final int db_version=1;

    private static final String sql_create_anime = "create table "
            + TABLE_ANIME + "("
            + COLUMN_ID_ANIME +" integer primary key autoincrement, "
            + COLUMN_ANIME+ " varchar(50) not null, "
            + COLUMN_GENRE+ " varchar(50) not null, "
            + COLUMN_SCORE+ " varchar(50) not null, "
            + COLUMN_SUMBER+ " varchar(50) not null);";

    private static final String sql_create_genre = "create table "
            + TABLE_GENRE + "("
            + COLUMN_ID_GENRE +" integer primary key autoincrement, "
            + COLUMN_GENRE+ " varchar(50) not null);";

    private static final String sql_create_login = "create table "
            + TABLE_LOGIN + "("
            + COLUMN_ID_USER +" integer primary key autoincrement, "
            + COLUMN_USERNAME+ " varchar(50) not null, "
            + COLUMN_PASSWORD+ " varchar(50) not null, "
            + COLUMN_NAMA+ " varchar(50) not null, "
            + COLUMN_ALAMAT+ " varchar(50) not null, "
            + COLUMN_EMAIL+ " varchar(50) not null);";

    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql_create_anime);
        db.execSQL(sql_create_genre);
        db.execSQL(sql_create_login);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GENRE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        onCreate(db);
    }

}

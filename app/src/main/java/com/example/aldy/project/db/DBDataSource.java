package com.example.aldy.project.db;

/**
 * Created by Aldy on 5/24/2016.
 */
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBDataSource {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    private String[] allColumnsAnime = { DBHelper.COLUMN_ID_ANIME,
            DBHelper.COLUMN_ANIME, DBHelper.COLUMN_GENRE,
            DBHelper.COLUMN_SCORE, DBHelper.COLUMN_SUMBER};

    private String[] allColumnsGenre = { DBHelper.COLUMN_ID_GENRE,
            DBHelper.COLUMN_GENRE};

    private String[] allColumnsLogin = { DBHelper.COLUMN_ID_USER,
            DBHelper.COLUMN_USERNAME, DBHelper.COLUMN_PASSWORD,
            DBHelper.COLUMN_NAMA, DBHelper.COLUMN_ALAMAT, DBHelper.COLUMN_EMAIL};

    public DBDataSource(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //START FUNCTION CLASS ANIME     //START FUNCTION CLASS ANIME
    //START FUNCTION CLASS ANIME     //START FUNCTION CLASS ANIME
    //START FUNCTION CLASS ANIME     //START FUNCTION CLASS ANIME

    public Anime createAnime(String nama_anime, String nama_genre, String score, String sumber) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ANIME, nama_anime);
        values.put(DBHelper.COLUMN_GENRE, nama_genre);
        values.put(DBHelper.COLUMN_SCORE, score);
        values.put(DBHelper.COLUMN_SUMBER, sumber);

        long insertId = database.insert(DBHelper.TABLE_ANIME, null,
                values);

        Cursor cursorA = database.query(DBHelper.TABLE_ANIME,
                allColumnsAnime, DBHelper.COLUMN_ID_ANIME + " = " + insertId, null,
                null, null, null);

        cursorA.moveToFirst();

        Anime newAnime = cursorToAnime(cursorA);

        cursorA.close();

        return newAnime;
    }

    private Anime cursorToAnime(Cursor cursorA)
    {
        Anime anime = new Anime();

        anime.setId_anime(cursorA.getLong(0));
        anime.setNama_anime(cursorA.getString(1));
        anime.setNama_genre(cursorA.getString(2));
        anime.setScore(cursorA.getString(3));
        anime.setSumber(cursorA.getString(4));

        return anime;
    }

    //mengambil semua data barang
    public ArrayList<Anime> getAllAnime() {
        ArrayList<Anime> daftarAnime = new ArrayList<Anime>();

        // select all SQL query
        Cursor cursor = database.query(DBHelper.TABLE_ANIME,
                allColumnsAnime, null, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data barang ke
        // daftar barang
        while (!cursor.isAfterLast()) {
            Anime anime = cursorToAnime(cursor);
            daftarAnime.add(anime);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarAnime;
    }

    public Anime getAnime(long id_anime)
    {
        Anime anime = new Anime();

        Cursor cursor = database.query(DBHelper.TABLE_ANIME, allColumnsAnime, "id_anime="+id_anime, null, null, null, null);
        cursor.moveToFirst();
        anime = cursorToAnime(cursor);
        cursor.close();
        return anime;
    }

    public void updateAnime(Anime b)
    {
        String strFilter = "id_anime=" + b.getId_anime();
        ContentValues args = new ContentValues();
        args.put(DBHelper.COLUMN_ANIME, b.getNama_anime());
        args.put(DBHelper.COLUMN_GENRE, b.getNama_genre());
        args.put(DBHelper.COLUMN_SCORE, b.getScore());
        args.put(DBHelper.COLUMN_SUMBER, b.getSumber());
        database.update(DBHelper.TABLE_ANIME, args, strFilter, null);
    }

    public void deleteAnime(long id_anime)
    {
        String strFilter = "id_anime=" + id_anime;
        database.delete(DBHelper.TABLE_ANIME, strFilter, null);
    }

    //END FUNCTION CLASS ANIME     //END FUNCTION CLASS ANIME
    //END FUNCTION CLASS ANIME     //END FUNCTION CLASS ANIME
    //END FUNCTION CLASS ANIME     //END FUNCTION CLASS ANIME

    //START FUNCTION CLASS GENRE     //START FUNCTION CLASS GENRE
    //START FUNCTION CLASS GENRE     //START FUNCTION CLASS GENRE
    //START FUNCTION CLASS GENRE     //START FUNCTION CLASS GENRE

    public Genre createGenre(String nama_genre) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_GENRE, nama_genre);

        long insertId = database.insert(DBHelper.TABLE_GENRE, null,
                values);

        Cursor cursorG = database.query(DBHelper.TABLE_GENRE,
                allColumnsGenre, DBHelper.COLUMN_ID_GENRE + " = " + insertId, null,
                null, null, null);

        cursorG.moveToFirst();

        Genre newGenre = cursorToGenre(cursorG);

        cursorG.close();

        return newGenre;
    }

    private Genre cursorToGenre(Cursor cursorG)
    {
        Genre genre = new Genre();

        genre.setId_genre(cursorG.getLong(0));
        genre.setNama_genre(cursorG.getString(1));

        return genre;
    }

    public ArrayList<Genre> getAllGenre() {
        ArrayList<Genre> daftarGenre = new ArrayList<Genre>();

        Cursor cursor = database.query(DBHelper.TABLE_GENRE,
                allColumnsGenre, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Genre genre = cursorToGenre(cursor);
            daftarGenre.add(genre);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarGenre;
    }

    public Genre getGenre(long id_genre)
    {
        Genre genre = new Genre();

        Cursor cursor = database.query(DBHelper.TABLE_GENRE, allColumnsGenre, "id_genre ="+id_genre, null, null, null, null);
        cursor.moveToFirst();
        genre = cursorToGenre(cursor);
        cursor.close();
        return genre;
    }

    public void updateGenre(Genre b)
    {
        String strFilter = "id=" + b.getId_genre();
        ContentValues args = new ContentValues();
        args.put(DBHelper.COLUMN_GENRE, b.getNama_genre());
        database.update(DBHelper.TABLE_ANIME, args, strFilter, null);
    }

    public void deleteGenre(long id_genre)
    {
        String strFilter = "id_genre=" + id_genre;
        database.delete(DBHelper.TABLE_GENRE, strFilter, null);
    }

    //END FUNCTION CLASS GENRE     //END FUNCTION CLASS GENRE
    //END FUNCTION CLASS GENRE     //END FUNCTION CLASS GENRE
    //END FUNCTION CLASS GENRE     //END FUNCTION CLASS GENRE

    //START FUNCTION CLASS LOGIN     //START FUNCTION CLASS LOGIN
    //START FUNCTION CLASS LOGIN     //START FUNCTION CLASS LOGIN
    //START FUNCTION CLASS LOGIN     //START FUNCTION CLASS LOGIN

    public User createUser( String username, String password, String nama, String alamat, String email) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_USERNAME, username);
        values.put(DBHelper.COLUMN_PASSWORD, password);
        values.put(DBHelper.COLUMN_NAMA, nama);
        values.put(DBHelper.COLUMN_ALAMAT, alamat);
        values.put(DBHelper.COLUMN_EMAIL, email);

        long insertId = database.insert(DBHelper.TABLE_LOGIN, null,
                values);

        Cursor cursorG = database.query(DBHelper.TABLE_LOGIN,
                allColumnsLogin, DBHelper.COLUMN_ID_USER + " = " + insertId, null,
                null, null, null);

        cursorG.moveToFirst();

        User newUser = cursorToUser(cursorG);

        cursorG.close();

        return newUser;
    }

    private User cursorToUser(Cursor cursorG)
    {
        User user = new User();

        user.setId_user(cursorG.getLong(0));
        user.setUsername(cursorG.getString(1));
        user.setPassword(cursorG.getString(2));
        user.setNama(cursorG.getString(3));
        user.setAlamat(cursorG.getString(4));
        user.setPassword(cursorG.getString(5));

        return user;
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> daftarUser = new ArrayList<User>();

        Cursor cursor = database.query(DBHelper.TABLE_LOGIN,
                allColumnsLogin, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            daftarUser.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarUser;
    }

    public User getUser(long id_User)
    {
        User user = new User();

        Cursor cursor = database.query(DBHelper.TABLE_LOGIN, allColumnsLogin, "id_user ="+id_User, null, null, null, null);
        cursor.moveToFirst();
        user = cursorToUser(cursor);
        cursor.close();
        return user;
    }

    public void updateUser(User b)
    {
        String strFilter = "id=" + b.getId_user();
        ContentValues args = new ContentValues();
        args.put(DBHelper.COLUMN_USERNAME, b.getUsername());
        args.put(DBHelper.COLUMN_PASSWORD, b.getPassword());
        database.update(DBHelper.TABLE_LOGIN, args, strFilter, null);
    }

    public void deleteUser(long id_user)
    {
        String strFilter = "id_user=" + id_user;
        database.delete(DBHelper.TABLE_LOGIN, strFilter, null);
    }

}

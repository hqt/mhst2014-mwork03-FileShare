package com.fpt.mobile.solutions.provider;

/**
 * Created by ThaoHQSE60963 on 5/30/14.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.fpt.mobile.solutions.utils.LogUtils;

/**
 * helper for managing {@link SQLiteDatabase} that stores data for
 */
public class SampleDatabase extends SQLiteAssetHelper {

    private static final String TAG = LogUtils.makeLogTag(SampleDatabase.class);

    private final Context mContext;

    public static final String DATABASE_NAME = "sample.db";
    public static final int DATABASE_VERSION = 1;

    // NOTE: carefully update onUpgrade() when bumping database versions
    // to make sure user data is saved

    public SampleDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // super(context, "hopamchuan", factory, version);
        mContext = context;
    }

    public SampleDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    /**
     * Called when the database connection is being configured
     * to enable features such as write-ahead logging or foreign key support.
     */
    @Override
    public void onConfigure(SQLiteDatabase db) {
    }

    /**
     * Called when the database has been opened. The implementation should check isReadOnly() before updating the database.
     This method is called after the database connection has been configured
     and after the database schema has been created, upgraded or downgraded as necessary.
     If the database connection must be configured in some way before the schema is created, upgraded, or downgraded
     do it in onConfigure(SQLiteDatabase) instead.
     * @param db
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

    }

    /**
     * Called when database is created for the first time
     * create all suitable tables here
     */
   /* @Override
    public synchronized void  onCreate(SQLiteDatabase db) {

        *//**
     * base table :
     * Artist Chords Songs
     *//*
        db.execSQL("CREATE TABLE " + Tables.ARTIST + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ArtistsColumns.ARTIST_ID + " INTEGER,"
                + ArtistsColumns.ARTIST_NAME + " TEXT NOT NULL,"
                + ArtistsColumns.ARTIST_ASCII + " TEXT NOT NULL,"
                + "UNIQUE (" + ArtistsColumns.ARTIST_ID + ") ON CONFLICT REPLACE)");

        db.execSQL("CREATE TABLE " + Tables.CHORD + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ChordsColumns.CHORD_ID + " INTEGER,"
                + ChordsColumns.CHORD_NAME + " TEXT NOT NULL,"
                + ChordsColumns.CHORD_RELATION + " TEXT,"
                + "UNIQUE (" + ChordsColumns.CHORD_ID + ") ON CONFLICT REPLACE)");

        db.execSQL("CREATE TABLE " + Tables.SONG + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SongsColumns.SONG_ID + " INTEGER,"
                + SongsColumns.SONG_TITLE + " TEXT NOT NULL,"
                + SongsColumns.SONG_LINK + " TEXT,"
                + SongsColumns.SONG_CONTENT + " TEXT,"
                + SongsColumns.SONG_FIRST_LYRIC + " TEXT,"
                + SongsColumns.SONG_DATE + " TEXT,"
                + SongsColumns.SONG_TITLE_ASCII + " TEXT,"
                + SongsColumns.SONG_LASTVIEW + " INTEGER,"
                + SongsColumns.SONG_ISFAVORITE + " INTEGER,"
                + SongsColumns.SONG_RHYTHM + " TEXT,"
                + "UNIQUE (" + SongsColumns.SONG_ID + ") ON CONFLICT REPLACE)");

        *//**
     * Derivative tables :
     * Songs - Authors   ||    Songs - Chords  ||   Songs - Singers
     *//*
        db.execSQL("CREATE TABLE " + Tables.SONG_AUTHOR + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SongsColumns.SONG_ID + " INTEGER " + References.SONG_ID + ","
                + ArtistsColumns.ARTIST_ID + " INTEGER " + References.ARTIST_ID + ","
                + "UNIQUE (" + SongsColumns.SONG_ID + ","
                        + ArtistsColumns.ARTIST_ID + ") ON CONFLICT REPLACE)");

        db.execSQL("CREATE TABLE " + Tables.SONG_SINGER + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SongsColumns.SONG_ID + " INTEGER " + References.SONG_ID + ","
                + ArtistsColumns.ARTIST_ID + " INTEGER " + References.ARTIST_ID + ","
                + "UNIQUE (" + SongsColumns.SONG_ID + ","
                        + ArtistsColumns.ARTIST_ID + ") ON CONFLICT REPLACE)");

        db.execSQL("CREATE TABLE " + Tables.SONG_CHORD + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SongsColumns.SONG_ID + " INTEGER " + References.SONG_ID + ","
                + ChordsColumns.CHORD_ID + " INTEGER " + References.CHORD_ID + ","
                + "UNIQUE (" + SongsColumns.SONG_ID + ","
                        + ChordsColumns.CHORD_ID + ") ON CONFLICT REPLACE)");

        *//**
     * Playlist table and Playlist-Songs table
     *//*
        db.execSQL("CREATE TABLE " + Tables.PLAYLIST + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + HopAmChuanDBContract.PlaylistColumns.PLAYLIST_ID + " INTEGER,"
                + HopAmChuanDBContract.PlaylistColumns.PLAYLIST_NAME + " TEXT,"
                + HopAmChuanDBContract.PlaylistColumns.PLAYLIST_DESCRIPTION + " TEXT,"
                + HopAmChuanDBContract.PlaylistColumns.PLAYLIST_DATE + " TEXT,"
                + HopAmChuanDBContract.PlaylistColumns.PLAYLIST_PUBLIC + " INTEGER,"
                + "UNIQUE (" + HopAmChuanDBContract.PlaylistColumns.PLAYLIST_ID + ") ON CONFLICT REPLACE)");

        db.execSQL("CREATE TABLE " + Tables.PLAYLIST_SONG + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + HopAmChuanDBContract.PlaylistColumns.PLAYLIST_ID + " INTEGER " + References.PLAYLIST_ID + ","
                + SongsColumns.SONG_ID + " INTEGER " + Query.References.SONG_ID + ","
                + "UNIQUE (" + HopAmChuanDBContract.PlaylistColumns.PLAYLIST_ID + ","
                + SongsColumns.SONG_ID + ") ON CONFLICT REPLACE)");


        // Full-text search index. Update using updateSessionSearchIndex method.
        // Use the porter tokenizer for simple stemming, so that "frustration" matches "frustrated."

    }*/

    /**
     * Work when upgrade database version (update database schema)
     * often : save old data
     * drop tables.
     * create tables
     * return old data has been saved
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtils.LOGD(TAG, "onUpgrade() from " + oldVersion + " to " + newVersion);

        // drop all tables

        // create new tables
    }

    public static void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}

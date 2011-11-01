package at.almeida.mypanini;

import java.util.Collection;
import java.util.Iterator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Simple class to access the database
 * @author miguel
 *
 */
public class StickerAlbumDbAdapter {

    public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "stickerAlbum";
	public static final String KEY_NAME = "title";
	public static final String KEY_ID = "_id";
	public static final String KEY_NUMBER = "number";
	public static final String KEY_TIMES = "times";
	public static final String FK_ALBUM = "album_id";
	public static final String TABLE_ALBUM = "album";
	public static final String TABLE_STICKERS = "sticker";
	private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
	private Context mCtx;
	
	private static final String TAG = "StickerAlbumDbAdapter";
    private static final String DATABASE_CREATE_ALBUM =
        "create table " + TABLE_ALBUM + " ("+KEY_ID+" integer primary key autoincrement, "
                + KEY_NAME+ " text not null);" ;
    private static final String DATABASE_CREATE_STICKERS =
        "create table " + TABLE_STICKERS + " ("+KEY_ID+" integer primary key autoincrement, "
                + KEY_NUMBER + " text not null, " 
                + FK_ALBUM +" integer not null, "
                + KEY_TIMES + " integer default 0"
                + ");";
    
    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context within which to work
     */
    public StickerAlbumDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

	private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE_ALBUM);
            db.execSQL(DATABASE_CREATE_STICKERS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALBUM);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_STICKERS);
            onCreate(db);
        }
		
	}
	
	public StickerAlbumDbAdapter open() {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public Cursor getAllAlbums() {
		return mDb.query(TABLE_ALBUM,  new String[] {KEY_ID, KEY_NAME}, null, null, null, null, null);
	}
	
    public long createAlbum(String albumName,int from, int to,Collection<String> extras){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, albumName);
        long albumId =mDb.insert(TABLE_ALBUM, null, initialValues);
        
        int i = from;
        while (i<=to) {
        	insertAlbumLine(String.valueOf(i),0,albumId);
        	i++;
		}
        if(extras != null){
	        for (Iterator<String> iterator = extras.iterator(); iterator.hasNext();) {
				String extra = iterator.next();
				insertAlbumLine(extra,0,albumId);			
			}
        }
        return albumId;
    }

	public Cursor getAlbumStickers(long albumID) {
		return mDb.query(TABLE_STICKERS,  new String[] {KEY_ID,KEY_NUMBER,KEY_TIMES,FK_ALBUM}, FK_ALBUM + "= "+ albumID, null, null, null, null);
	}

	public Cursor getAlbumMissingStickers(long albumId) {
		
		return mDb.query(TABLE_STICKERS,  new String[] {KEY_ID,KEY_NUMBER,KEY_TIMES,FK_ALBUM}, FK_ALBUM + "= "+ albumId + " AND " + KEY_TIMES + " = 0", null, null, null, null);
	}

	private Cursor generalStickerQuery(String where) {
		
		return mDb.query(TABLE_STICKERS,  new String[] {KEY_ID,KEY_NUMBER,KEY_TIMES,FK_ALBUM}, where, null, null, null, null);
	}
	private void insertAlbumLine(String stickerNumber,int times,long albumId){
    	ContentValues initialValues = new ContentValues();
    	initialValues.put(KEY_NUMBER, stickerNumber);
    	initialValues.put(KEY_TIMES, 0);
    	initialValues.put(FK_ALBUM, albumId);
    	mDb.insert(TABLE_STICKERS, null, initialValues);
	}

	public void deleteAlbum(long albumID) {
		mDb.beginTransaction();
		mDb.delete(TABLE_ALBUM, KEY_ID +"=?", new String []{String.valueOf(albumID)});
		mDb.delete(TABLE_STICKERS, FK_ALBUM +"=?", new String []{String.valueOf(albumID)});
		
		mDb.setTransactionSuccessful();
		mDb.endTransaction();
	}

	public void changeStickerCount(int stickerToChange, int newCount) {
		mDb.beginTransaction();
		ContentValues updateValues = new ContentValues();
		updateValues.put(KEY_NUMBER, stickerToChange);
		updateValues.put(KEY_TIMES, newCount);
		mDb.update(TABLE_STICKERS, updateValues, KEY_ID+"=?",  new String []{String.valueOf(stickerToChange)});
		
		mDb.setTransactionSuccessful();
		mDb.endTransaction();
		
	}


}


